package languageTools.parser.relationParser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import languageTools.exceptions.relationParser.InvalidGamBeliefException;
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
		EmotionConfig testEmotion = new EmotionConfig(new HashMap<String,GamBelief>(), new HashMap<String,GamGoal>(), new ArrayList<GamRelation>());
		assertEquals(new HashMap<String,GamBelief>(), testEmotion.getBeliefs());
		assertEquals(new HashMap<String,GamGoal>(), testEmotion.getGoals());
		assertEquals(new ArrayList<GamRelation>(), testEmotion.getRelations());
	}

	@Test
	public void testTostring() throws InvalidGamBeliefException, InvalidGamRelationException {
	 HashMap<String, GamBelief> beliefs = new HashMap<String, GamBelief>();
	 HashMap<String, GamGoal> goals = new HashMap<String, GamGoal>();
	 ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
	
	 GamBelief belief = new GamBelief("subgoal1",0.2, "maingoal1", 0.1, false);
	 beliefs.put(belief.getBeliefName(),belief);
	 belief = new GamBelief("subgoal2",0.3,  "maingoal2", 0.5, true);
	 beliefs.put(belief.getBeliefName(),belief);
	 GamGoal goal = new GamGoal("goal2", 0.8, false);
	 goals.put(goal.getGoal(),goal);
	 goal = new GamGoal("goal1", 3, true);
	 goals.put(goal.getGoal(),goal);
	 GamRelation relation = new GamRelation("agent1", "agent2", -1);
	 relations.add(relation);
	 relation = new GamRelation("agent3", "agent4", 0.9);
	 relations.add(relation);
	
	 EmotionConfig thisConfig = EmotionConfig.getInstance();
	 thisConfig.setBeliefs(beliefs);
	 thisConfig.setGoals(goals);
	 thisConfig.setRelations(relations);
	 //System.out.println(thisConfig.toString());
	 String correct = "{Config: {subgoal2={SUB: subgoal2, 0.3, maingoal2, 0.5, true}, subgoal1={SUB: subgoal1, 0.2, maingoal1, 0.1, false}}, {goal2={CGOAL: goal2, 0.8}, goal1={IGOAL: goal1, 3.0}}, [{REL: agent1, agent2, -1.0}, {REL: agent3, agent4, 0.9}], utility: 1.0, negativeCongruence: -1.0, positiveCongruence: 1.0, belieflikelihood: 1.0, isincremental: false}";
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
	public void testGetDefaultBelief() throws InvalidGamBeliefException {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamBelief bel = conf.getBelief("test"); //get a belief by a name that has not been added.
		assertEquals("test", bel.getBeliefName());
		assertEquals("NONE", bel.affected);
		assertEquals(conf.getDefaultBelLikelihood(), bel.likelihood, 0.0);
		assertEquals(conf.getDefaultIsIncremental(), bel.isIncremental);
		assertEquals(conf.getDefaultPositiveCongruence(), bel.congruence, 0.0);
	}
	
	@Test
	public void testGetInsertedBelief() throws InvalidGamBeliefException {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamBelief bel = new GamBelief("test", 0.2, "test3", 0.1, false); //get a belief by a name that has not been added.
		conf.addBelief(bel);
		GamBelief testBel = conf.getBelief("test");
		assertEquals(bel.getBeliefName(), testBel.getBeliefName());
		assertEquals(bel.affected, testBel.affected);
		assertEquals(bel.likelihood, testBel.likelihood,0.0);
		assertEquals(bel.isIncremental, testBel.isIncremental);
		assertEquals(bel.congruence, testBel.congruence,0.0);
	}
	
	@Test
	public void testGetDefaultGoalNoWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamGoal goal = conf.getGoal("test");
		assertEquals("test", goal.getGoal());
		assertEquals(conf.getDefaultUtility(), goal.getValue(), 0.0);
	}	
	
	@Test
	public void testGetDefaultGoalWithWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		conf.setWhiteList(true);
		GamGoal goal = conf.getGoal("test");
		assertEquals("test", goal.getGoal());
		assertEquals(0.0, goal.getValue(), 0.0);
	}
	
	@Test
	public void testGetInsertedGoalNoWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamGoal goal = new GamGoal("testG", 0.9, false);
		conf.addGoal(goal);
		GamGoal testGoal = conf.getGoal("testG");
		assertEquals(false, testGoal.isIndividualGoal());
		assertEquals("testG", testGoal.getGoal());
		assertEquals(0.9, testGoal.getValue(), 0.0);
	}
	
	@Test
	public void testGetInsertedGoalWithWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		conf.setWhiteList(true);
		GamGoal goal = new GamGoal("testG", 0.9, true);
		conf.addGoal(goal);
		GamGoal testGoal = conf.getGoal("testG");
		assertEquals(true, testGoal.isIndividualGoal());
		assertEquals("testG", testGoal.getGoal());
		assertEquals(0.9, testGoal.getValue(), 0.0);
	}
	
}
