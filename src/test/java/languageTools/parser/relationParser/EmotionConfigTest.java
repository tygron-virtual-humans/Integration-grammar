package languageTools.parser.relationParser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import languageTools.exceptions.relationParser.InvalidGamSubGoalException;
import languageTools.exceptions.relationParser.InvalidGamRelationException;

import org.junit.After;
import org.junit.Test;

public class EmotionConfigTest {

	@After
	public void tearDown() {
		EmotionConfig.getInstance().reset();
	}
	
	@Test
	public void testConstructorEmpty() {
		EmotionConfig testEmotion = new EmotionConfig(new HashMap<String,ArrayList<GamSubGoal>>(), new HashMap<String,HashMap<String,GamGoal>>(), new ArrayList<GamRelation>());
		assertEquals(new HashMap<String,ArrayList<GamSubGoal>>(), testEmotion.getSubGoals());
		assertEquals(new HashMap<String,GamGoal>(), testEmotion.getGoals());
		assertEquals(new ArrayList<GamRelation>(), testEmotion.getRelations());
	}

	@Test
	public void testTostring() throws InvalidGamSubGoalException, InvalidGamRelationException {
	 HashMap<String, ArrayList<GamSubGoal>> SubGoals = new HashMap<String, ArrayList<GamSubGoal>>();
	 HashMap<String, HashMap<String, GamGoal>> goals = new HashMap<String, HashMap<String,GamGoal>>();
	 HashMap<String, GamGoal> innerGoals = new HashMap<String, GamGoal>();
	 ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
	

	ArrayList<GamSubGoal> SubGoalsList = new ArrayList<GamSubGoal>();
	 GamSubGoal  SubGoal = new GamSubGoal("subgoal2",0.3,  "maingoal2", 0.5, true);
	 SubGoalsList.add(SubGoal);
	 SubGoals.put(SubGoal.getGoalName(), SubGoalsList);
	 GamGoal goal = new GamGoal("goal2", 0.8, false, "ANYAGENT");
	 innerGoals.put(goal.getAgent(),goal);
	 goals.put(goal.getGoal(), innerGoals);
	 GamRelation relation = new GamRelation("agent1", "agent2", -1);
	 relations.add(relation);
	 relation = new GamRelation("agent3", "agent4", 0.9);
	 relations.add(relation);
	
	 EmotionConfig thisConfig = EmotionConfig.getInstance();
	 thisConfig.setSubGoals(SubGoals);
	 thisConfig.setGoals(goals);
	 thisConfig.setRelations(relations);
	 //System.out.println(thisConfig.toString());

	 String correct = "{Config: {subgoal2=[{SUB: subgoal2, 0.3, maingoal2, 0.5, true}]}, {goal2={ANYAGENT={CGOAL: goal2, 0.8, ANYAGENT}}}, [{REL: agent1, agent2, -1.0}, {REL: agent3, agent4, 0.9}], utility: 1.0, negativeCongruence: -1.0, positiveCongruence: 1.0, SubGoallikelihood: 1.0, isincremental: false}";
	 assertEquals(correct, thisConfig.toString());
	}
	
	
	@Test
	public void testGetters() {
		EmotionConfig conf = EmotionConfig.getInstance();
		 double DefaultUtility = 1;
		 double DefaultNegativeCongruence = -1;
		 double DefaultPositiveCongruence = 1;
		 double DefaultBelLikelihood = 1;
		 boolean DefaultIsIncremental = false;
		 
		 assertEquals(DefaultUtility,conf.getDefaultUtility(),0.0);
		 assertEquals(DefaultNegativeCongruence,conf.getDefaultNegativeCongruence(),0.0);
		 assertEquals(DefaultPositiveCongruence,conf.getDefaultPositiveCongruence(),0.0);
		 assertEquals(DefaultBelLikelihood,conf.getDefaultBelLikelihood(),0.0);
		 assertEquals(DefaultIsIncremental,conf.isDefaultIsIncremental());
	}
	
	@Test
	public void testGetDefaultSubGoal() throws InvalidGamSubGoalException {
		EmotionConfig conf = EmotionConfig.getInstance();
		ArrayList<GamSubGoal> bel = conf.getSubGoal("test"); //get a SubGoal by a name that has not been added.
		assertEquals(new ArrayList<GamSubGoal>(), bel);
	}
	
	@Test
	public void testGetInsertedSubGoal() throws InvalidGamSubGoalException {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamSubGoal bel = new GamSubGoal("test", 0.2, "test3", 0.1, false); //get a SubGoal by a name that has not been added.
		conf.addSubGoal(bel);
		ArrayList<GamSubGoal> testBel = conf.getSubGoal("test");
		ArrayList<GamSubGoal> correctBel = new ArrayList<GamSubGoal>();
		correctBel.add(bel);
		assertEquals(correctBel, testBel);
	}
	
	@Test
	public void testGetInsertedSubGoalMutipleDistinctAffected() throws InvalidGamSubGoalException {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamSubGoal bel = new GamSubGoal("test", 0.2, "test3", 0.1, false); //get a SubGoal by a name that has not been added.
		GamSubGoal  SubGoal = new GamSubGoal("test",0.3,  "maingoal2", 0.5, true);
		conf.addSubGoal(bel);
		conf.addSubGoal(SubGoal);
		ArrayList<GamSubGoal> testBel = conf.getSubGoal("test");
		ArrayList<GamSubGoal> correctBel = new ArrayList<GamSubGoal>();
		correctBel.add(bel);
		correctBel.add(SubGoal);
		assertEquals(correctBel, testBel);
	}
	
	@Test
	public void testGetInsertedSubGoalMutipleSameAffected() throws InvalidGamSubGoalException {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamSubGoal bel = new GamSubGoal("test", 0.2, "test3", 0.1, false); //get a SubGoal by a name that has not been added.
		GamSubGoal  SubGoal = new GamSubGoal("test",0.3,  "test3", 0.5, true);
		conf.addSubGoal(bel);
		conf.addSubGoal(SubGoal);
		ArrayList<GamSubGoal> testBel = conf.getSubGoal("test");
		ArrayList<GamSubGoal> correctBel = new ArrayList<GamSubGoal>();
		correctBel.add(SubGoal);
		assertEquals(correctBel, testBel);
	}
	
	@Test
	public void testGetDefaultGoalNoWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamGoal goal = conf.getGoal("test", "testAgent1");
		assertEquals("test", goal.getGoal());
		assertEquals(conf.getDefaultUtility(), goal.getValue(), 0.0);
		assertEquals("ANYAGENT", goal.getAgent());
	}	
	
	@Test
	public void testGetDefaultGoalWithWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		conf.setWhiteList(true);
		GamGoal goal = conf.getGoal("test", "testAgent2");
		assertEquals("test", goal.getGoal());
		assertEquals(0.0, goal.getValue(), 0.0);
		assertEquals("ANYAGENT", goal.getAgent());
	}
	
	@Test
	public void testGetInsertedGoalNoWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamGoal goal = new GamGoal("testG", 0.9, false, "ANYAGENT");
		conf.addGoal(goal);
		GamGoal testGoal = conf.getGoal("testG", "test");
		assertEquals(false, testGoal.isIndividualGoal());
		assertEquals("testG", testGoal.getGoal());
		assertEquals(0.9, testGoal.getValue(), 0.0);
		assertEquals("ANYAGENT", testGoal.getAgent());
	}
	
	@Test
	public void testGetInsertedGoalWithWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		conf.setWhiteList(true);
		GamGoal goal = new GamGoal("testG", 0.9, true, "agent");
		conf.addGoal(goal);
		GamGoal testGoal = conf.getGoal("testG", "agent");
		assertEquals(true, testGoal.isIndividualGoal());
		assertEquals("testG", testGoal.getGoal());
		assertEquals(0.9, testGoal.getValue(), 0.0);
		assertEquals("agent", testGoal.getAgent());
	}
	

}
