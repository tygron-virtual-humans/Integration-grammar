package languageTools.parser.relationParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RelationParser {
	
	
	public static EmotionConfig parse(File file) throws FileNotFoundException{
	Scanner fileScanner = new Scanner(file);
	ArrayList<GamBelief> believes = new ArrayList<GamBelief>();
	ArrayList<GamGoal> goals = new ArrayList<GamGoal>();
	ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
	
	
	while(fileScanner.hasNextLine()){
		String line = fileScanner.nextLine();
		String[] objects = line.split(",");
		
		if(objects[0] == "BEL"){
			
			GamBelief belief = parseBelief(objects);
			believes.add(belief);
		}
		
		if(objects[0] == "REL"){
			
			GamRelation relation = parseRelation(objects);
			relations.add(relation);
		}	
		if(objects[0] == "GOAL"){
			
			GamGoal gamgoal = parseGoal(objects);
			goals.add(gamgoal);	
		}
	
	}
	
	
	EmotionConfig configuration = new EmotionConfig(believes,goals,relations);
	return configuration;
	}
	
	
	
	public static GamBelief parseBelief(String[] objects){
		
		double likelihood = Double.parseDouble(objects[1]);
		String causal = objects[2];
		String affected = objects[3];
		double congruence = Double.parseDouble(objects[4]);
		String isincremental = null;
		
		//check if it is specifed
		if(!objects[5].equals(null)){
		isincremental = objects[5];
		}
		
		GamBelief belief = new GamBelief(likelihood,causal,affected,congruence,isincremental);
		return belief;	
	}
	
	
	public static GamRelation parseRelation(String[] objects){
		String agent1 = objects[1];
		String agent2 = objects[2];
		double value = Double.parseDouble(objects[3]);
		GamRelation relation = new GamRelation(agent1,agent2,value);
		return relation;
	}
	
	
	public static GamGoal parseGoal(String[] objects){
		String agent = objects[1];
		String goal = objects[2];
		double value = Double.parseDouble(objects[3]);
		
		GamGoal gamgoal = new GamGoal(agent,goal,value);
		return gamgoal;
	}

}
