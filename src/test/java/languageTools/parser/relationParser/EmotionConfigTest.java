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
	public void testEqualsTrue() throws InvalidGamRelationException, InvalidGamBeliefException {
		ArrayList<GamBelief> beliefs = new ArrayList<GamBelief>();
		ArrayList<GamGoal> goals = new ArrayList<GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamBelief belief = new GamBelief(0.2, "agent1", "agent2", 0.1, false);
		beliefs.add(belief);
		GamGoal goal = new GamGoal("agent1", "goal1", 3);
		goals.add(goal);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		
		EmotionConfig thisConfig = new EmotionConfig(beliefs, goals, relations);
		EmotionConfig otherConfig = new EmotionConfig(beliefs, goals, relations);
		
		assertTrue(thisConfig.equals(otherConfig));
	}

	@Test
	public void testEqualsOtherBeliefs() throws InvalidGamRelationException, InvalidGamBeliefException {
		ArrayList<GamBelief> beliefs = new ArrayList<GamBelief>();
		ArrayList<GamGoal> goals = new ArrayList<GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamBelief belief = new GamBelief(0.2, "agent1", "agent2", 0.1, false);
		beliefs.add(belief);
		GamGoal goal = new GamGoal("agent1", "goal1", 3);
		goals.add(goal);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		
		ArrayList<GamBelief> beliefs2 = new ArrayList<GamBelief>();
		belief = new GamBelief(0.3, "agent1", "agent2", 0.1,true);
		beliefs2.add(belief);
		
		EmotionConfig thisConfig = new EmotionConfig(beliefs, goals, relations);
		EmotionConfig otherConfig = new EmotionConfig(beliefs2, goals, relations);
		
		assertFalse(thisConfig.equals(otherConfig));
	}
	
	@Test
	public void testEqualsOtherRelations() throws InvalidGamRelationException, InvalidGamBeliefException {
		ArrayList<GamBelief> beliefs = new ArrayList<GamBelief>();
		ArrayList<GamGoal> goals = new ArrayList<GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamBelief belief = new GamBelief(0.2, "agent1", "agent2", 0.1, false);
		beliefs.add(belief);
		GamGoal goal = new GamGoal("agent1", "goal1", 3);
		goals.add(goal);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		
		ArrayList<GamRelation> relations2 = new ArrayList<GamRelation>();
		relation = new GamRelation("agent3", "agent2", 1);
		relations.add(relation);
		
		EmotionConfig thisConfig = new EmotionConfig(beliefs, goals, relations);
		EmotionConfig otherConfig = new EmotionConfig(beliefs, goals, relations2);
		
		assertFalse(thisConfig.equals(otherConfig));
	}
	
	@Test
	public void testEqualsOtherGoals() throws InvalidGamRelationException, InvalidGamBeliefException {
		ArrayList<GamBelief> beliefs = new ArrayList<GamBelief>();
		ArrayList<GamGoal> goals = new ArrayList<GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamBelief belief = new GamBelief(0.2, "agent1", "agent2", 0.1, false);
		beliefs.add(belief);
		GamGoal goal = new GamGoal("agent1", "goal1", 3);
		goals.add(goal);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		
		ArrayList<GamGoal> goals2 = new ArrayList<GamGoal>();
		goal = new GamGoal("agent1", "goal2", 3);
		goals.add(goal);
		
		EmotionConfig thisConfig = new EmotionConfig(beliefs, goals, relations);
		EmotionConfig otherConfig = new EmotionConfig(beliefs, goals2, relations);
		
		assertFalse(thisConfig.equals(otherConfig));
	}

	@Test
	public void testEqualsOtherObject() throws InvalidGamBeliefException, InvalidGamRelationException {
		ArrayList<GamBelief> beliefs = new ArrayList<GamBelief>();
		ArrayList<GamGoal> goals = new ArrayList<GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamBelief belief = new GamBelief(0.2, "agent1", "agent2", 0.1, false);
		beliefs.add(belief);
		GamGoal goal = new GamGoal("agent1", "goal1", 3);
		goals.add(goal);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		
		EmotionConfig thisConfig = new EmotionConfig(beliefs, goals, relations);
		
		int num = 0;
		
		assertFalse(thisConfig.equals(num));
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
	 String correct = "{Config: [{BEL: 0.2, agent1, agent2, 0.1, false}], [{GOAL: agent1, goal1, 3.0}], [{REL: agent1, agent2, -1.0}], 1.0}";
	 assertEquals(correct, thisConfig.toString());
	}
}
