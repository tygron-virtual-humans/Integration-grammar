/**
 * This class contains the methods to parse a text documents given it has the correct syntax, it returns a correct EmotionConfig object.
 */
package languageTools.parser.relationParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import languageTools.exceptions.relationParser.InvalidEmotionConfigFile;
import languageTools.exceptions.relationParser.InvalidGamSubGoalException;
import languageTools.exceptions.relationParser.InvalidGamSubGoalString;
import languageTools.exceptions.relationParser.InvalidGamGoalString;
import languageTools.exceptions.relationParser.InvalidGamRelationException;
import languageTools.exceptions.relationParser.InvalidGamRelationString;

/**
 * Parser for an emotionconfiguration
 *
 */
abstract public class RelationParser {
	
	/**
	 * Highest level parse method
	 * @param file - input file
	 * @return an EmtionConfig object
	 * @throws FileNotFoundException - occurs when the file is not found
	 * @throws InvalidEmotionConfigFile - occurs when the syntax is not right
	 */
	public static EmotionConfig parse(File file) throws FileNotFoundException, InvalidEmotionConfigFile{
	// creating scanner + lists	
	Scanner fileScanner = new Scanner(file);
	HashMap<String, ArrayList<GamSubGoal>> SubGoals = new HashMap<String,ArrayList<GamSubGoal>>();
	HashMap<String,HashMap<String,GamGoal>> goals = new HashMap<String,HashMap<String,GamGoal>>();
	ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
	
	//creating resulting configuration
	EmotionConfig configuration = new EmotionConfig(SubGoals,goals,relations);
	int lineNum = 1;
	while(fileScanner.hasNextLine()){
		String line = fileScanner.nextLine();
		String[] objects = line.split(","); // split lines by ','
		objects[0] = objects[0].toUpperCase();
		configuration = parseObjects(objects, configuration, lineNum);
		lineNum++;
		
	}
	fileScanner.close();
	
	return configuration;
	}
	
	/**
	 * Highest level parse method. It creates a file out of the filepath and then uses the other parse method the return the configuration object.
	 * @param filePath - input file path 
	 * @return - configuration object
	 * @throws FileNotFoundException - Occurs when file is not found
	 * @throws InvalidEmotionConfigFile - Occurs when the EmotionConfigFile is invalid
	 */
	public static EmotionConfig parse(String filePath) throws FileNotFoundException, InvalidEmotionConfigFile { 
		File file = new File(filePath);
		return parse(file);
	}
	
	/**
	 * Method that parses a given line.
	 * @param objects - objects that are found by splitting the line on ','
	 * @param config - the current configuration that will be extended with the new line.
	 * @param lineNum - linenumber of the current line (debug purposes)
	 * @return - new config file with line extended to it.
	 * @throws InvalidEmotionConfigFile
	 */
	public static EmotionConfig parseObjects(String[] objects, EmotionConfig config, int lineNum) throws InvalidEmotionConfigFile {
		EmotionConfig res = EmotionConfig.getInstance();
		objects = removeWhiteSpaces(objects);
		//BELIEVES
		if(objects[0].equals("SUB")){
			GamSubGoal SubGoal;
			try {
				SubGoal = parseSubGoal(objects);
				res.addSubGoal(SubGoal);
			} catch (Throwable e) {
				throw new InvalidEmotionConfigFile("Subgoal on line: " + lineNum + " is invalid");
			}
		
		}
		
		//whitelist
		else if(objects[0].equals("WHITELIST")){
			try {
				if(objects[1].toUpperCase().equals("ON")) {
					res.setWhiteList(true);
				}
			} catch (Throwable e) {
				throw new InvalidEmotionConfigFile("Cannot read whitelist parameter on: " + lineNum);
			}
				
		}
		//RELATIONS
		else if(objects[0].equals("REL")){
			GamRelation relation;
			try {
				relation = parseRelation(objects);
				res.getRelations().add(relation);
			} catch (Throwable e) {
				throw new InvalidEmotionConfigFile("Relation on line: " + lineNum + " is invalid");
			}
			
		}
		
		// DEFAULT GOAL UTILITY
		else if(objects[0].equals("DEFAULT GOAL UTILITY")){
			
			double utility = parseUtility(objects);
			res.setDefaultUtility(utility);		
		}
		
		//DEFAULT POSITIVE CONGRUENCE
		else if(objects[0].equals("DEFAULT ACHIEVED CONGRUENCE")) {
			double congruence = parseCongruence(objects);
			res.setDefaultPositiveCongruence(congruence);
		}
		
		//DEFAULT NEGEATIVE CONGRUENCE
		else if(objects[0].equals("DEFAULT DROPPED CONGRUENCE")) {
			double congruence = parseCongruence(objects);
			res.setDefaultNegativeCongruence(congruence);
		}
		
		// DEFAULT SubGoal LIKELIHOOD
		else if(objects[0].equals("DEFAULT BELIEF LIKELIHOOD")) {
			double likelihood = parseLikelihood(objects);
			res.setDefaultBelLikelihood(likelihood);
		}
		
		//DEFAULT IS INCREMENTAL
		else if(objects[0].equals("DEFAULT IS INCREMENTAL")) {
			boolean isIncremental = parseIsIncremental(objects);
			res.setDefaultIsIncremental(isIncremental);
		}

		//GOALS
		else if(objects[0].equals("IGOAL") || objects[0].equals("CGOAL")){
			GamGoal gamgoal;
			try {
				gamgoal = parseGoal(objects);
				res.addGoal(gamgoal);	
			} catch (Throwable e) {
				throw new InvalidEmotionConfigFile("Relation on line: " + lineNum + " is invalid");
			}
		}
		
		//DECAY
		else if (objects[0].equalsIgnoreCase("decay")) {
			
			parseDecay(objects,res);
		}
		
		
		else {
			throw new InvalidEmotionConfigFile("Identifying tag on line: " + lineNum + " is invalid");
		}
		return res;
	}
	
	
	/**
	 * Parses the decay set in the emotionconfig
	 * @param objects
	 * @param res
	 * @throws InvalidEmotionConfigFile
	 */
	public static void parseDecay(String[] objects, EmotionConfig res) throws InvalidEmotionConfigFile{
		
		if (objects.length != 3) { //DECAY, TYPE, VALUE
			throw new InvalidEmotionConfigFile("Invalid Decay configuration, use: \"decay, type, value\"");
		}
		
		if (objects[1].equalsIgnoreCase("exp") || objects[1].equalsIgnoreCase("exponential")) {
			res.setDecayExponential(true);
		} else if (objects[1].equalsIgnoreCase("lin") || objects[1].equalsIgnoreCase("linear")) {
			res.setDecayExponential(false);
		} else {
			throw new InvalidEmotionConfigFile("Invalid Decay type, use: \"exponential\" or \"linear\"");
		}
		
		try {
			res.setDecay(Double.parseDouble(objects[2]));
		} catch(Throwable e) {
			throw new InvalidEmotionConfigFile("Invalid Decay value");
		}	
	}


	/**
	 * Specific SubGoal parser
	 * @param objects - parameters 
	 * @return GamSubGoal object
	 * @throws InvalidGamSubGoalException
	 * @throws InvalidGamSubGoalString
	 */
	public static GamSubGoal parseSubGoal(String[] objects) throws InvalidGamSubGoalException, InvalidGamSubGoalString{
		GamSubGoal SubGoal = null;
		try{
		 String goalName = objects[1];
		 double likelihood = Double.parseDouble(objects[2]);
		 String affectedGoalName = objects[3];
		 double congruence = Double.parseDouble(objects[4]);
		 boolean isincremental = parseBoolean(objects[5]);
		 SubGoal = new GamSubGoal(goalName, likelihood, affectedGoalName, congruence, isincremental);
		} catch(Throwable e) {
			try{
			 String goalName = objects[1];
			 String affectedGoalName = objects[2];
			 double likelihood = EmotionConfig.getInstance().getDefaultBelLikelihood();
			 double congruence = Double.parseDouble(objects[3]);
			 boolean isincremental = parseBoolean(objects[4]);
			 SubGoal = new GamSubGoal(goalName, likelihood, affectedGoalName, congruence, isincremental);
			} catch(Throwable t) {
				throw new InvalidGamSubGoalString("Cannot parse the gam SubGoal");
			}
		}
		return SubGoal;	
	}
	
	/**
	 * Specific Relation parser
	 * @param objects - parameters
	 * @return - GamRelation object
	 * @throws InvalidGamRelationException
	 * @throws InvalidGamRelationString
	 */
	public static GamRelation parseRelation(String[] objects) throws InvalidGamRelationException, InvalidGamRelationString{
		GamRelation relation = null;
		try{ 
		 String agent1 = objects[1];
		 String agent2 = objects[2];
		 double value = Double.parseDouble(objects[3]);
		 relation = new GamRelation(agent1,agent2,value);
		} catch(Throwable e) {
			throw new InvalidGamRelationString("Cannot parse the gam relation");
		}
		return relation;
	}
	
	/**
	 * Specific Goal parser
	 * @param objects - parameters
	 * @return GamGoal object
	 * @throws InvalidGamGoalString
	 */
	public static GamGoal parseGoal(String[] objects) throws InvalidGamGoalString{
		GamGoal gamgoal = null;
		//try{
			Boolean isIndividual = false;
			if(objects[0].equals("IGOAL")) {
				isIndividual = true;
			}
			String goal = objects[1];
			double value = 0.0;
			String agent;
			try{
				value = Double.parseDouble(objects[2]);
				if(objects.length > 3) {
					agent = objects[3];
				} else {
					agent = "ANYAGENT";
				}
			} catch(Throwable t) {
				value = EmotionConfig.getInstance().getDefaultUtility();
				try {
					agent = objects[2];
				} catch(Throwable t2) {
					agent = "ANYAGENT";
				}
			}
		gamgoal = new GamGoal(goal,value, isIndividual, agent);	
		return gamgoal;
	}
	
	
	/**
	 * Sub method to parse a default utility setting
	 * @param objects - line split by ","
	 * @return
	 * @throws InvalidEmotionConfigFile
	 */
	public static double parseUtility(String[] objects) throws InvalidEmotionConfigFile{
		double utility;
		try{
		 utility = Double.parseDouble(objects[1]);
		 
		} catch(Throwable e) {
			throw new InvalidEmotionConfigFile("Cannot read the default goal utlity: " + objects.toString());
		}
	
		return utility;
	}
	
	/**
	 * Sub method to parse a congruence default setting
	 * @param objects - line split by "," 
	 * @return congruence value
	 * @throws InvalidEmotionConfigFile
	 */
	public static double parseCongruence(String[] objects) throws InvalidEmotionConfigFile{
		double congruence;
		try{
		 congruence = Double.parseDouble(objects[1]);
		 
		} catch(Throwable e) {
			throw new InvalidEmotionConfigFile("Cannot read the congruence: " + objects.toString());
		}
	
		return congruence;
	}
	
	/**
	 * Sub method to parse likelihood
	 * @param objects - line split by ","
	 * @return likelihood value
	 * @throws InvalidEmotionConfigFile
	 */
	public static double parseLikelihood(String[] objects) throws InvalidEmotionConfigFile{
		double likelihood;
		try{
		 likelihood = Double.parseDouble(objects[1]);
		 
		} catch(Throwable e) {
			throw new InvalidEmotionConfigFile("Cannot read the likelihood: " + objects.toString());
		}
	
		return likelihood;
	}
	
	/**
	 * Sub method for parsing if the default IsIncremental is true or false.
	 * @param objects
	 * @return
	 * @throws InvalidEmotionConfigFile
	 */
	public static boolean parseIsIncremental(String[] objects) throws InvalidEmotionConfigFile{
		boolean isIncremental;
		try{
		 isIncremental = parseBoolean(objects[1]);
		 
		} catch(Throwable e) {
			throw new InvalidEmotionConfigFile("Cannot read the is incremental setting: " + objects.toString());
		}
	
		return isIncremental;
	}
	
	
	/**
	 * Overwrite of the Double.parseBoolean that throws errors if not true/false instead of returning false when something if not well-written.
	 * @param string
	 * @return
	 * @throws InvalidEmotionConfigFile
	 */
	public static boolean parseBoolean(String string) throws InvalidEmotionConfigFile{
		string = string.toUpperCase();
		if(string.equals("TRUE")){
			return true;
		}
		else if(string.equals("FALSE")){
			return false;
			
		}
		else{
			throw new InvalidEmotionConfigFile("The boolean is not written correctly and can not be parsed");
		}
	}
	
	
	/**
	 * Method to remove whitespacing in front and at the end of the splitted objects
	 * @param objects
	 * @return trimmed version of the String array
	 */
	public static String[] removeWhiteSpaces(String[] objects) {
		String[] res = new String[objects.length];
		for(int i = 0; i<objects.length; i++) {
			res[i] = objects[i].trim();
		}
		
		return res;
	}
}
