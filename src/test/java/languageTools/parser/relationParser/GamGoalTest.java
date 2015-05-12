package languageTools.parser.relationParser;

import static org.junit.Assert.*;

import org.junit.Test;

public class GamGoalTest {

	@Test
	public void testConstructorEmpty() {
		GamGoal testGoal = new GamGoal("", "", 0.0);
		assertEquals("", testGoal.getAgent());
		assertEquals("", testGoal.getGoal());
		assertEquals(0.0, testGoal.getValue(), 0);
	}
	
	@Test
	public void testConstructor() {
		GamGoal testGoal = new GamGoal("Agent1", "Goal1", 9.2);
		assertEquals("Agent1", testGoal.getAgent());
		assertEquals("Goal1", testGoal.getGoal());
		assertEquals(9.2, testGoal.getValue(), 0);
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
	
	@Test
	public void testSetAgent() {
		String agentFirst = "agent1";
		String agentSecond = "test";
		GamGoal goal1 = new GamGoal(agentFirst, "goal1", 0.1);
		assertEquals(agentFirst, goal1.getAgent());
		goal1.setAgent(agentSecond);
		assertEquals(agentSecond, goal1.getAgent());
	}
	
	@Test
	public void testSetValue() {
		double valueFirst = 0.2;
		double valueSecond = -3;
		GamGoal goal1 = new GamGoal("agent1", "goal1", valueFirst);
		assertEquals(valueFirst, goal1.getValue(), 0.0);
		goal1.setValue(valueSecond);
		assertEquals(valueSecond, goal1.getValue(), 0.0);
	}
	
	@Test
	public void testSetGoal() {
		String goalFirst = "goal1";
		String goalSecond = "test";
		GamGoal goal1 = new GamGoal("agent1'", goalFirst, 0.1);
		assertEquals(goalFirst, goal1.getGoal());
		goal1.setGoal(goalSecond);
		assertEquals(goalSecond, goal1.getGoal());
	}
}
