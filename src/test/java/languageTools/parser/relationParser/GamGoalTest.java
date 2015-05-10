package languageTools.parser.relationParser;

import static org.junit.Assert.*;

import org.junit.Test;

public class GamGoalTest {

	@Test
	public void testConstructorEmpty() {
		GamGoal testGoal = new GamGoal("", "", 0.0);
		assertEquals("", testGoal.agent);
		assertEquals("", testGoal.goal);
		assertEquals(0.0, testGoal.value, 0);
	}
	
	@Test
	public void testConstructor() {
		GamGoal testGoal = new GamGoal("Agent1", "Goal1", 9.2);
		assertEquals("Agent1", testGoal.agent);
		assertEquals("Goal1", testGoal.goal);
		assertEquals(9.2, testGoal.value, 0);
	}
	
	@Test
	public void testEqualsTrue() {
		GamGoal goal1 = new GamGoal("agent1", "goal1", 0.1);
		GamGoal goal2 = new GamGoal("agent1", "goal1", 0.1);
		assertTrue(goal1.equals(goal2));
	}
	
	@Test
	public void testEqualsOtherAgent() {
		GamGoal goal1 = new GamGoal("agent1", "goal1", 0.1);
		GamGoal goal2 = new GamGoal("agent2", "goal1", 0.1);
		assertFalse(goal1.equals(goal2));
	}
	
	@Test
	public void testEqualsOtherGoal() {
		GamGoal goal1 = new GamGoal("agent1", "goal1", 0.1);
		GamGoal goal2 = new GamGoal("agent1", "goal2", 0.1);
		assertFalse(goal1.equals(goal2));
	}
	
	@Test
	public void testEqualsOtherValue() {
		GamGoal goal1 = new GamGoal("agent1", "goal1", 0.1);
		GamGoal goal2 = new GamGoal("agent1", "goal1", 0.2);
		assertFalse(goal1.equals(goal2));
	}
	
	@Test
	public void testEqualsOtherObject() {
		GamGoal goal1 = new GamGoal("agent1", "goal1", 0.1);
		String c = "sda";
		assertFalse(goal1.equals(c));
	}
	
	@Test
	public void testToString() {
		GamGoal goal1 = new GamGoal("agent1", "goal1", 0.1);
		String correct = "{GOAL: agent1, goal1, 0.1}";
		assertEquals(correct, goal1.toString());
	}

}
