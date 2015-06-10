package languageTools.parser.relationParser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import languageTools.exceptions.relationParser.InvalidEmotionConfigFile;
import languageTools.exceptions.relationParser.InvalidGamSubGoalException;
import languageTools.exceptions.relationParser.InvalidGamGoalString;
import languageTools.exceptions.relationParser.InvalidGamRelationException;

import org.junit.After;
import org.junit.Test;

public class RelationparserTest {
	
	@After
	public void tearDown(){
		EmotionConfig.reset();
	}

	
	@Test
	public void testValidFile() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException {
		//System.out.println("TESTVALIDFILE +++++++++++++++++++++++++++++++++++++++++++++++");
		EmotionConfig testConfig = EmotionConfig.getInstance();
		EmotionConfig.parse("src/test/languageTools/parser/relationParser/validFile");
		HashMap<String,ArrayList<GamSubGoal>> SubGoals = new HashMap<String,ArrayList<GamSubGoal>>();
		HashMap<String,HashMap<String,GamGoal>> goals = new HashMap<String,HashMap<String,GamGoal>>();
		HashMap<String, GamGoal> innerGoals1 = new HashMap<String, GamGoal>();
		HashMap<String, GamGoal> innerGoals2 = new HashMap<String, GamGoal>();

		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamSubGoal SubGoal = new GamSubGoal("subgoal1",0.2, "maingoal1", 0.1, false);
		ArrayList<GamSubGoal> toPut = new ArrayList<GamSubGoal>();
		toPut.add(SubGoal);
		SubGoals.put(SubGoal.getGoalName(), toPut);
		SubGoal = new GamSubGoal("subgoal2",0.3,  "maingoal2", 0.5, true);
		toPut = new ArrayList<GamSubGoal>();
		toPut.add(SubGoal);
		SubGoals.put(SubGoal.getGoalName(), toPut);
		GamGoal goal = new GamGoal("goal2", 0.8, false, "ANYAGENT");
		innerGoals2.put("ANYAGENT",goal);
		goal = new GamGoal("goal1", 3, true, "ANYAGENT");
		innerGoals1.put("ANYAGENT",goal);
		goals.put("goal1", innerGoals1);
		goals.put("goal2", innerGoals2);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		relation = new GamRelation("agent3", "agent4", 0.9);
	    relations.add(relation);
	    
		assertEquals(SubGoals,testConfig.getSubGoals());
		assertEquals(goals,testConfig.getGoals());
		assertEquals(relations,testConfig.getRelations());
		double util=1;
		assertEquals(util,testConfig.getDefaultUtility(),0.0);

	}
	
	@Test
	public void testValidFileAgentAndUtility() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException {
		//System.out.println("TESTVALIDFILE +++++++++++++++++++++++++++++++++++++++++++++++");
		EmotionConfig testConfig = EmotionConfig.getInstance();
		EmotionConfig.parse("src/test/languageTools/parser/relationParser/validFileAgentAndUtility");
		HashMap<String,ArrayList<GamSubGoal>> SubGoals = new HashMap<String,ArrayList<GamSubGoal>>();
		HashMap<String,HashMap<String,GamGoal>> goals = new HashMap<String,HashMap<String,GamGoal>>();
		HashMap<String, GamGoal> innerGoals1 = new HashMap<String, GamGoal>();
		HashMap<String, GamGoal> innerGoals2 = new HashMap<String, GamGoal>();

		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamSubGoal SubGoal = new GamSubGoal("subgoal1",0.2, "maingoal1", 0.1, false);
		ArrayList<GamSubGoal> toPut = new ArrayList<GamSubGoal>();
		toPut.add(SubGoal);
		SubGoals.put(SubGoal.getGoalName(), toPut);
		SubGoal = new GamSubGoal("subgoal2",0.3,  "maingoal2", 0.5, true);
		toPut = new ArrayList<GamSubGoal>();
		toPut.add(SubGoal);
		SubGoals.put(SubGoal.getGoalName(), toPut);
		GamGoal goal = new GamGoal("goal2", 0.8, false, "ANYAGENT");
		innerGoals2.put("ANYAGENT",goal);
		goal = new GamGoal("goal1", 3, true, "testAgent");
		innerGoals1.put("testAgent",goal);
		goals.put("goal1", innerGoals1);
		goals.put("goal2", innerGoals2);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		relation = new GamRelation("agent3", "agent4", 0.9);
	    relations.add(relation);
	    
		assertEquals(SubGoals,testConfig.getSubGoals());
		assertEquals(goals,testConfig.getGoals());
		assertEquals(relations,testConfig.getRelations());
		double util=1;
		assertEquals(util,testConfig.getDefaultUtility(),0.0);

	}
	
	@Test
	public void testValidFileNoSubLikeli() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException {
		//System.out.println("TESTVALIDFILE +++++++++++++++++++++++++++++++++++++++++++++++");
		EmotionConfig testConfig = EmotionConfig.getInstance();
		EmotionConfig.parse("src/test/languageTools/parser/relationParser/validFileNoSubLikeli");
		HashMap<String,ArrayList<GamSubGoal>> SubGoals = new HashMap<String,ArrayList<GamSubGoal>>();
		HashMap<String,HashMap<String,GamGoal>> goals = new HashMap<String,HashMap<String,GamGoal>>();
		HashMap<String, GamGoal> innerGoals1 = new HashMap<String, GamGoal>();
		HashMap<String, GamGoal> innerGoals2 = new HashMap<String, GamGoal>();

		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamSubGoal SubGoal = new GamSubGoal("subgoal1",EmotionConfig.getInstance().getDefaultBelLikelihood(), "maingoal1", 0.1, false);
		ArrayList<GamSubGoal> toPut = new ArrayList<GamSubGoal>();
		toPut.add(SubGoal);
		SubGoals.put(SubGoal.getGoalName(), toPut);
		SubGoal = new GamSubGoal("subgoal2",EmotionConfig.getInstance().getDefaultBelLikelihood(),  "maingoal2", 0.5, true);
		toPut = new ArrayList<GamSubGoal>();
		toPut.add(SubGoal);
		SubGoals.put(SubGoal.getGoalName(), toPut);
		GamGoal goal = new GamGoal("goal2", 0.8, false, "ANYAGENT");
		innerGoals2.put("ANYAGENT",goal);
		goal = new GamGoal("goal1", 3, true, "ANYAGENT");
		innerGoals1.put("ANYAGENT",goal);
		goals.put("goal1", innerGoals1);
		goals.put("goal2", innerGoals2);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		relation = new GamRelation("agent3", "agent4", 0.9);
	    relations.add(relation);
	    
		assertEquals(SubGoals,testConfig.getSubGoals());
		assertEquals(goals,testConfig.getGoals());
		assertEquals(relations,testConfig.getRelations());
		double util=1;
		assertEquals(util,testConfig.getDefaultUtility(),0.0);

	}
	
	@Test
	public void testValidFileSpecificAgent() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException {
		//System.out.println("TESTVALIDFILE +++++++++++++++++++++++++++++++++++++++++++++++");
		EmotionConfig testConfig = EmotionConfig.getInstance();
		EmotionConfig.parse("src/test/languageTools/parser/relationParser/validFileSpecificAgent");
		HashMap<String,ArrayList<GamSubGoal>> SubGoals = new HashMap<String,ArrayList<GamSubGoal>>();
		HashMap<String,HashMap<String,GamGoal>> goals = new HashMap<String,HashMap<String,GamGoal>>();
		HashMap<String, GamGoal> innerGoals1 = new HashMap<String, GamGoal>();
		HashMap<String, GamGoal> innerGoals2 = new HashMap<String, GamGoal>();

		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamSubGoal SubGoal = new GamSubGoal("subgoal1",0.2, "maingoal1", 0.1, false);
		ArrayList<GamSubGoal> toPut = new ArrayList<GamSubGoal>();
		toPut.add(SubGoal);
		SubGoals.put(SubGoal.getGoalName(), toPut);
		SubGoal = new GamSubGoal("subgoal2",0.3,  "maingoal2", 0.5, true);
		toPut = new ArrayList<GamSubGoal>();
		toPut.add(SubGoal);
		SubGoals.put(SubGoal.getGoalName(), toPut);
		GamGoal goal = new GamGoal("goal2", 0.8, false, "ANYAGENT");
		innerGoals2.put("ANYAGENT",goal);
		goal = new GamGoal("goal1", EmotionConfig.getInstance().getDefaultUtility(), true, "testAgent");
		innerGoals1.put("testAgent",goal);
		goals.put("goal1", innerGoals1);
		goals.put("goal2", innerGoals2);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		relation = new GamRelation("agent3", "agent4", 0.9);
	    relations.add(relation);
	    
		assertEquals(SubGoals,testConfig.getSubGoals());
		assertEquals(goals,testConfig.getGoals());
		assertEquals(relations,testConfig.getRelations());
		double util=1;
		assertEquals(util,testConfig.getDefaultUtility(),0.0);

	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testWrongSubGoal() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongBEL");
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testWrongRelation() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongREL");
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testWrongGoal() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongGOAL");
	}
	
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testWrongTag() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongTAG");
	}
	
	@Test
	public void testValidFileWithDefaults() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException {
		//System.out.println("TESTVALIDFILE +++++++++++++++++++++++++++++++++++++++++++++++");
		EmotionConfig testConfig = EmotionConfig.getInstance();
		EmotionConfig.parse("src/test/languageTools/parser/relationParser/validUtility");
		HashMap<String,ArrayList<GamSubGoal>> SubGoals = new HashMap<String,ArrayList<GamSubGoal>>();
		HashMap<String,HashMap<String,GamGoal>> goals = new HashMap<String,HashMap<String,GamGoal>>();
		HashMap<String, GamGoal> innerGoals1 = new HashMap<String, GamGoal>();
		HashMap<String, GamGoal> innerGoals2 = new HashMap<String, GamGoal>();

		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();

		GamSubGoal SubGoal = new GamSubGoal("subgoal1",0.2, "maingoal1", 0.1, false);
		ArrayList<GamSubGoal> toPut = new ArrayList<GamSubGoal>();
		toPut.add(SubGoal);
		SubGoals.put(SubGoal.getGoalName(), toPut);
		SubGoal = new GamSubGoal("subgoal2",0.3,  "maingoal2", 0.5, true);
		toPut = new ArrayList<GamSubGoal>();
		toPut.add(SubGoal);
		SubGoals.put(SubGoal.getGoalName(), toPut);
		GamGoal goal = new GamGoal("goal2", 0.8, false, "ANYAGENT");
		innerGoals2.put("ANYAGENT",goal);
		goal = new GamGoal("goal1", 3, true, "ANYAGENT");
		innerGoals1.put("ANYAGENT",goal);
		goals.put("goal1", innerGoals1);
		goals.put("goal2", innerGoals2);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		relation = new GamRelation("agent3", "agent4", 0.9);
	    relations.add(relation);
	
		assertEquals(SubGoals,testConfig.getSubGoals());
		assertEquals(goals,testConfig.getGoals());
		assertEquals(relations,testConfig.getRelations());
		double util=0.56;
		assertEquals(util,testConfig.getDefaultUtility(),0.0);
		assertEquals(0.7,testConfig.getDefaultBelLikelihood(),0.0);
		assertEquals(0.6,testConfig.getDefaultNegativeCongruence(),0.0);
		assertEquals(0.5,testConfig.getDefaultPositiveCongruence(),0.0);
		assertEquals(false,testConfig.isDefaultIsIncremental());
		
		
		
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testValidFileWithWrongUtility() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException {
		EmotionConfig testConfig = EmotionConfig.getInstance();
		EmotionConfig.parse("src/test/languageTools/parser/relationParser/invalidUtility");
		HashMap<String,GamSubGoal> SubGoals = new HashMap<String,GamSubGoal>();
		HashMap<String,HashMap<String,GamGoal>> goals = new HashMap<String,HashMap<String,GamGoal>>();
		HashMap<String, GamGoal> innerGoals1 = new HashMap<String, GamGoal>();
		HashMap<String, GamGoal> innerGoals2 = new HashMap<String, GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		

		GamSubGoal SubGoal = new GamSubGoal("subgoal1",0.2, "maingoal1", 0.1, false);
		SubGoals.put(SubGoal.getGoalName(),SubGoal);
		SubGoal = new GamSubGoal("subgoal2",0.3,  "maingoal2", 0.5, true);
		SubGoals.put(SubGoal.getGoalName(),SubGoal);
		GamGoal goal = new GamGoal("goal2", 0.8, false, "ANYAGENT");

		innerGoals2.put("ANYAGENT",goal);
		goal = new GamGoal("goal1", 3, true, "ANYAGENT");
		innerGoals1.put("ANYAGENT",goal);
		goals.put("goal1", innerGoals1);
		goals.put("goal2", innerGoals2);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		relation = new GamRelation("agent3", "agent4", 0.9);
	    relations.add(relation);
	    
		assertEquals(SubGoals,testConfig.getSubGoals());
		assertEquals(goals,testConfig.getGoals());
		assertEquals(relations,testConfig.getRelations());
		double util=0.56;
		assertEquals(util,testConfig.getDefaultUtility(),0.0);
		
		
	}
	
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testValidFileWithWrongUtility2() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException {
		EmotionConfig testConfig = EmotionConfig.getInstance();
		EmotionConfig.parse("src/test/languageTools/parser/relationParser/wrongUtility");
		HashMap<String,GamSubGoal> SubGoals = new HashMap<String,GamSubGoal>();
		HashMap<String,HashMap<String,GamGoal>> goals = new HashMap<String,HashMap<String,GamGoal>>();
		HashMap<String, GamGoal> innerGoals1 = new HashMap<String, GamGoal>();
		HashMap<String, GamGoal> innerGoals2 = new HashMap<String, GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamSubGoal SubGoal = new GamSubGoal("subgoal1",0.2, "maingoal1", 0.1, false);
		SubGoals.put(SubGoal.getGoalName(),SubGoal);
		SubGoal = new GamSubGoal("subgoal2",0.3,  "maingoal2", 0.5, true);
		SubGoals.put(SubGoal.getGoalName(),SubGoal);
		GamGoal goal = new GamGoal("goal2", 0.8, false, "ANYAGENT");

		innerGoals2.put("ANYAGENT",goal);
		goal = new GamGoal("goal1", 3, true, "ANYAGENT");
		innerGoals1.put("ANYAGENT",goal);
		goals.put("goal1", innerGoals1);
		goals.put("goal2", innerGoals2);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		relation = new GamRelation("agent3", "agent4", 0.9);
	    relations.add(relation);
	
		assertEquals(SubGoals,testConfig.getSubGoals());
		assertEquals(goals,testConfig.getGoals());
		assertEquals(relations,testConfig.getRelations());
		double util=0.56;
		assertEquals(util,testConfig.getDefaultUtility(),0.0);
		
		
	}
	
	@Test
	public void parseTest() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException {
		EmotionConfig conf = RelationParser.parse("src/test/languageTools/parser/relationParser/validFile");
		assertFalse(conf == null);
		assertEquals(conf.getSubGoals(),EmotionConfig.getInstance().getSubGoals());
		assertEquals(conf.getGoals(),EmotionConfig.getInstance().getGoals());
		assertEquals(conf.getRelations(),EmotionConfig.getInstance().getRelations());
		
		
	}
	
	@Test
	public void abstractTest(){
		RelationParser test = new RelationParser(){};
	}
	
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void parseCongruenceTest() throws InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException, FileNotFoundException{
		EmotionConfig conf = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongposcongruence");
		assertFalse(conf == null);
		
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void parseLikelihoodTest() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException{
		EmotionConfig conf = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongLikelihood");
		assertFalse(conf == null);
		
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void parseIsIncremental() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException{
		EmotionConfig conf = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongIsIncremental");
		assertFalse(conf == null);
		
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void parseIsIncremental2() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamSubGoalException, InvalidGamRelationException{
		EmotionConfig conf = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongIsIncremental2");
		assertFalse(conf == null);
		
	}
	
	
}
