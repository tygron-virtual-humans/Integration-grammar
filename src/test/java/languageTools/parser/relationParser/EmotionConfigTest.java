package languageTools.parser.relationParser;

import static org.junit.Assert.*;

import java.util.ArrayList;

import languageTools.exceptions.relationParser.InvalidGamBeliefException;
import languageTools.exceptions.relationParser.InvalidGamRelationException;

import org.junit.Test;

public class EmotionConfigTest {

	@Test
	public void testConstructorEmpty() {
		EmotionConfig testEmotion = new EmotionConfig(new ArrayList<GamBelief>(), new ArrayList<GamGoal>(), new ArrayList<GamRelation>());
		assertEquals(new ArrayList<GamBelief>(), testEmotion.getBeliefs());
		assertEquals(new ArrayList<GamGoal>(), testEmotion.getGoals());
		assertEquals(new ArrayList<GamBelief>(), testEmotion.getRelations());
	}

	@Test
	public void testTostring() throws InvalidGamBeliefException, InvalidGamRelationException {
	 ArrayList<GamBelief> beliefs = new ArrayList<GamBelief>();
	 ArrayList<GamGoal> goals = new ArrayList<GamGoal>();
	 ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
	
	 GamBelief belief = new GamBelief(0.2, "agent1", "agent2", 0.1, false);
	 beliefs.add(belief);
	 GamGoal goal = new GamGoal("agent1", "goal1", 3);
	 goals.add(goal);
	 GamRelation relation = new GamRelation("agent1", "agent2", -1);
	 relations.add(relation);
	
	 EmotionConfig thisConfig = EmotionConfig.getInstance();
	 thisConfig.setBeliefs(beliefs);
	 thisConfig.setGoals(goals);
	 thisConfig.setRelations(relations);
	 //System.out.println(thisConfig.toString());
	 String correct = "{Config: [{BEL: 0.2, agent1, agent2, 0.1, false}], [{GOAL: agent1, goal1, 3.0}], [{REL: agent1, agent2, -1.0}], utility: 1.0, negativeCongruence: -0.1, positiveCongruence: 0.5, belieflikelihood: 1.0, isincremental: false}";
	 assertEquals(correct, thisConfig.toString());
	}
	
	
	@Test
	public void testGetters() {
		EmotionConfig conf = EmotionConfig.getInstance();
		 double DefaultUtility = 1;
		 double DefaultNegativeCongruence = -0.1;
		 double DefaultPositiveCongruence = 0.5;
		 double DefaultBelLikelihood = 1;
		 boolean DefaultIsIncremental = false;
		 
		 assertEquals(DefaultUtility,conf.getDefaultUtility(),0.0);
		 assertEquals(DefaultNegativeCongruence,conf.getDefaultNegativeCongruence(),0.0);
		 assertEquals(DefaultPositiveCongruence,conf.getDefaultPositiveCongruence(),0.0);
		 assertEquals(DefaultBelLikelihood,conf.getDefaultBelLikelihood(),0.0);
		 assertEquals(DefaultIsIncremental,conf.isDefaultIsIncremental());
	}
	
}
