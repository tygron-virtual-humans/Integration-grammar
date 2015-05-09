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
	int lineNum = 1;
	while(fileScanner.hasNextLine()){
		String line = fileScanner.nextLine();
		String[] objects = line.split(",");
		configuration = parseObjects(objects, configuration, lineNum);
		lineNum++;
		
	}
	fileScanner.close();
	
	return configuration;
	}
	
	public static EmotionConfig parse(String filePath) throws FileNotFoundException, InvalidEmotionConfigFile { 
		File file = new File(filePath);
		return parse(file);
	}
	
	public static EmotionConfig parseObjects(String[] objects, EmotionConfig config, int lineNum) throws InvalidEmotionConfigFile {
		EmotionConfig res = new EmotionConfig(config.beliefs, config.goals, config.relations);
		if(objects[0].equals("BEL")){
			GamBelief belief;
			try {
				belief = parseBelief(objects);
				res.beliefs.add(belief);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new InvalidEmotionConfigFile("Belief on line: " + lineNum + " is invalid");
			}
		} else if(objects[0].equals("REL")){
			GamRelation relation;
			try {
				relation = parseRelation(objects);
				res.relations.add(relation);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new InvalidEmotionConfigFile("Relation on line: " + lineNum + " is invalid");
			}
		}	else if(objects[0].equals("GOAL")){
			GamGoal gamgoal;
			try {
				gamgoal = parseGoal(objects);
				res.goals.add(gamgoal);	
			} catch (Throwable e) {
				e.printStackTrace();
				throw new InvalidEmotionConfigFile("Relation on line: " + lineNum + "is invalid");
			}
		} else {
			throw new InvalidEmotionConfigFile("Identifying tag on: " + lineNum + "is invalid");
		}
		return res;
	}
	
	public static GamBelief parseBelief(String[] objects) throws InvalidGamBeliefException, InvalidGamBeliefString{
		GamBelief belief = null;
		try{
			System.out.println(Arrays.toString(objects));
		 double likelihood = Double.parseDouble(objects[1]);
		 String causal = objects[2];
		 String affected = objects[3];
		 double congruence = Double.parseDouble(objects[4]);
		 System.out.println("Objects[5]: " + objects[5]);
		 boolean isincremental = Boolean.parseBoolean(objects[5]);
		 System.out.println(isincremental);
		 belief = new GamBelief(likelihood,causal,affected,congruence,isincremental);
		} catch(Throwable e) {
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
		} catch(Throwable e) {
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
		} catch(Throwable e) {
			e.printStackTrace();
			throw new InvalidGamGoalString("Cannot read the goal string: " + objects.toString());
		}
	
		return gamgoal;
	}

}
