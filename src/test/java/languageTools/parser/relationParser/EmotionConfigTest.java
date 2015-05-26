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
	
	 GamBelief belief = new GamBelief("bel1", 0.2, "agent1", "agent2", 0.1, false);
	 beliefs.put(belief.getBeliefName(), belief);
	 GamGoal goal = new GamGoal("agent1", "goal1", 3);
	 goals.put(goal.getGoal(),goal);
	 GamRelation relation = new GamRelation("agent1", "agent2", -1);
	 relations.add(relation);
	
	 EmotionConfig thisConfig = EmotionConfig.getInstance();
	 thisConfig.setBeliefs(beliefs);
	 thisConfig.setGoals(goals);
	 thisConfig.setRelations(relations);
	 //System.out.println(thisConfig.toString());
	 String correct = "{Config: {bel1={BEL: bel1, 0.2, agent1, agent2, 0.1, false}}, {goal1={GOAL: agent1, goal1, 3.0}}, [{REL: agent1, agent2, -1.0}], utility: 1.0, negativeCongruence: -1.0, positiveCongruence: 1.0, belieflikelihood: 1.0, isincremental: false}";
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
		assertEquals("ANY", bel.causal);
		assertEquals("NONE", bel.affected);
		assertEquals(conf.getDefaultBelLikelihood(), bel.likelihood, 0.0);
		assertEquals(conf.getDefaultIsIncremental(), bel.isIncremental);
		assertEquals(conf.getDefaultPositiveCongruence(), bel.congruence, 0.0);
	}
	
	@Test
	public void testGetInsertedBelief() throws InvalidGamBeliefException {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamBelief bel = new GamBelief("test", 0.2,"test2", "test3", 0.1, false); //get a belief by a name that has not been added.
		conf.addBelief(bel);
		GamBelief testBel = conf.getBelief("test");
		assertEquals(bel.getBeliefName(), testBel.getBeliefName());
		assertEquals(bel.causal, testBel.causal);
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
		assertEquals("ANY", goal.getAgent());
		assertEquals(conf.getDefaultUtility(), goal.getValue(), 0.0);
	}	
	
	@Test
	public void testGetDefaultGoalWithWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		conf.setWhiteList(true);
		GamGoal goal = conf.getGoal("test");
		assertEquals("test", goal.getGoal());
		assertEquals("ANY", goal.getAgent());
		assertEquals(0.0, goal.getValue(), 0.0);
	}
	
	@Test
	public void testGetInsertedGoalNoWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		GamGoal goal = new GamGoal("testA", "testG", 0.9);
		conf.addGoal(goal);
		GamGoal testGoal = conf.getGoal("testG");
		assertEquals("testA", testGoal.getAgent());
		assertEquals("testG", testGoal.getGoal());
		assertEquals(0.9, testGoal.getValue(), 0.0);
	}
	
	@Test
	public void testGetInsertedGoalWithWhitelist() {
		EmotionConfig conf = EmotionConfig.getInstance();
		conf.setWhiteList(true);
		GamGoal goal = new GamGoal("testA", "testG", 0.9);
		conf.addGoal(goal);
		GamGoal testGoal = conf.getGoal("testG");
		assertEquals("testA", testGoal.getAgent());
		assertEquals("testG", testGoal.getGoal());
		assertEquals(0.9, testGoal.getValue(), 0.0);
	}
	
}
