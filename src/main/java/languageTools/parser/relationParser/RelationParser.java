package languageTools.parser.relationParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import languageTools.exceptions.relationParser.InvalidEmotionConfigFile;
import languageTools.exceptions.relationParser.InvalidGamBeliefException;
import languageTools.exceptions.relationParser.InvalidGamBeliefString;
import languageTools.exceptions.relationParser.InvalidGamGoalString;
import languageTools.exceptions.relationParser.InvalidGamRelationException;
import languageTools.exceptions.relationParser.InvalidGamRelationString;

public class RelationParser {
	
	
	public static EmotionConfig parse(File file) throws FileNotFoundException, InvalidEmotionConfigFile{
	Scanner fileScanner = new Scanner(file);
	ArrayList<GamBelief> beliefs = new ArrayList<GamBelief>();
	ArrayList<GamGoal> goals = new ArrayList<GamGoal>();
	ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
	EmotionConfig configuration = new EmotionConfig(beliefs,goals,relations);
	System.out.println("called 2");
	while(fileScanner.hasNextLine()){
		String line = fileScanner.nextLine();
		System.out.println(line);
		String[] objects = line.split(",");
		int lineNum = 0;
		configuration = parseObjects(objects, configuration, lineNum);
		System.out.println("lineNum: " + lineNum);
		System.out.println("obj: " + Arrays.toString(objects));
		System.out.println("confBel: " + configuration.beliefs.toString());
		System.out.println("confGoal: " + configuration.goals.toString());
		System.out.println("confRel: " + configuration.relations.toString());

		lineNum++;
		
	}
	
	return configuration;
	}
	
	public static EmotionConfig parse(String filePath) throws FileNotFoundException, InvalidEmotionConfigFile { 
		System.out.println("called");
		File file = new File(filePath);
		return parse(file);
	}
	
	public static EmotionConfig parseObjects(String[] objects, EmotionConfig config, int lineNum) throws InvalidEmotionConfigFile {
		EmotionConfig res = new EmotionConfig(config.beliefs, config.goals, config.relations);
		System.out.println("Obj[0]: " + objects[0]);
		if(objects[0] == "BEL"){
			GamBelief belief;
			try {
				belief = parseBelief(objects);
				System.out.println("PARSED BEL: " + belief);
				res.beliefs.add(belief);
			} catch (Exception e) {
				e.printStackTrace();
				throw new InvalidEmotionConfigFile("Belief on line: " + lineNum + " is invalid");
			}
		}
		if(objects[0] == "REL"){
			GamRelation relation;
			try {
				relation = parseRelation(objects);
				res.relations.add(relation);
			} catch (Exception e) {
				e.printStackTrace();
				throw new InvalidEmotionConfigFile("Relation on line: " + lineNum + " is invalid");
			}
		}	
		if(objects[0] == "GOAL"){
			GamGoal gamgoal;
			try {
				gamgoal = parseGoal(objects);
				res.goals.add(gamgoal);	
			} catch (InvalidGamGoalString e) {
				e.printStackTrace();
				throw new InvalidEmotionConfigFile("Relation on line: " + lineNum + "is invalid");
			}
		}
		System.out.println("resBel: " + res.beliefs.toString());
		System.out.println("resGoal: " + res.goals.toString());
		System.out.println("resRel: " +  res.relations.toString());
		return res;
	}
	
	public static GamBelief parseBelief(String[] objects) throws InvalidGamBeliefException, InvalidGamBeliefString{
		GamBelief belief = null;
		try{
		 double likelihood = Double.parseDouble(objects[1]);
		 String causal = objects[2];
		 String affected = objects[3];
		 double congruence = Double.parseDouble(objects[4]);
		 boolean isincremental = Boolean.parseBoolean(objects[4]);
		 belief = new GamBelief(likelihood,causal,affected,congruence,isincremental);
		} catch(Exception e) {
			e.printStackTrace();
			throw new InvalidGamBeliefString("Cannot parse the gam belief");
		}
		return belief;	
	}
	
	
	public static GamRelation parseRelation(String[] objects) throws InvalidGamRelationException, InvalidGamRelationString{
		GamRelation relation = null;
		try{ 
		 String agent1 = objects[1];
		 String agent2 = objects[2];
		 double value = Double.parseDouble(objects[3]);
		 relation = new GamRelation(agent1,agent2,value);
		} catch(Exception e) {
			throw new InvalidGamRelationString("Cannot parse the gam relation");
		}
		return relation;
	}
	
	
	public static GamGoal parseGoal(String[] objects) throws InvalidGamGoalString{
		GamGoal gamgoal = null;
		try{
		 String agent = objects[1];
		 String goal = objects[2];
		 double value = Double.parseDouble(objects[3]);
	     gamgoal = new GamGoal(agent,goal,value);
		} catch(Exception e) {
			e.printStackTrace();
			throw new InvalidGamGoalString("Cannot read the goal string: " + objects.toString());
		}
	
		return gamgoal;
	}

}
