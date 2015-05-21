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
	 String correct = "{Config: [{BEL: 0.2, agent1, agent2, 0.1, false}], [{GOAL: agent1, goal1, 3.0}], [{REL: agent1, agent2, -1.0}], 1.0}";
	 assertEquals(correct, thisConfig.toString());
	}
	
	
}
