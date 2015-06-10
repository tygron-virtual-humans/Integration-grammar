package languageTools.parser.relationParser;

import static org.junit.Assert.*;

import org.junit.Test;

public class GamGoalTest {

	@Test
	public void testConstructorEmpty() {
		GamGoal testGoal = new GamGoal("", 0.0, false, "ANYAGENT");
		assertEquals(false, testGoal.isIndividualGoal());
		assertEquals("", testGoal.getGoal());
		assertEquals(0.0, testGoal.getValue(), 0);
		assertEquals("ANYAGENT", testGoal.getAgent());
	}
	
	@Test
	public void testConstructor() {
		GamGoal testGoal = new GamGoal("Goal1", 9.2, true, "test");
		assertEquals(true, testGoal.isIndividualGoal());
		assertEquals("Goal1", testGoal.getGoal());
		assertEquals(9.2, testGoal.getValue(), 0);
		assertEquals("test", testGoal.getAgent());
	}
	
	@Test
	public void testEqualsTrue() {
		GamGoal goal1 = new GamGoal("goal1", 0.1, true, "test");
		GamGoal goal2 = new GamGoal("goal1", 0.1, true, "test");
		assertTrue(goal1.equals(goal2));
	}
	
	@Test
	public void testEqualsOtherIsIndividual() {
		GamGoal goal1 = new GamGoal("goal1", 0.1, true, "test");
		GamGoal goal2 = new GamGoal("goal1", 0.1, false, "test");
		assertFalse(goal1.equals(goal2));
	}
	
	@Test
	public void testEqualsOtherGoal() {
		GamGoal goal1 = new GamGoal("goal1", 0.1, true, "test");
		GamGoal goal2 = new GamGoal("goal2", 0.1, true, "test");
		assertFalse(goal1.equals(goal2));
	}
	
	@Test
	public void testEqualsOtherValue() {
		GamGoal goal1 = new GamGoal("goal1", 0.1, false, "test");
		GamGoal goal2 = new GamGoal("goal1", 0.2, false, "test");
		assertFalse(goal1.equals(goal2));
	}
	
	@Test
	public void testEqualsOtherAgent() {
		GamGoal goal1 = new GamGoal("goal1", 0.1, false, "test2");
		GamGoal goal2 = new GamGoal("goal1", 0.1, false, "test");
		assertFalse(goal1.equals(goal2));
	}
	
	@Test
	public void testEqualsOtherObject() {
		GamGoal goal1 = new GamGoal("goal1", 0.1, true, "test");
		String c = "sda";
		assertFalse(goal1.equals(c));
	}
	
	@Test
	public void testToStringI() {
		GamGoal goal1 = new GamGoal("goal1", 0.1, true, "test");
		String correct = "{IGOAL: goal1, 0.1, test}";
		assertEquals(correct, goal1.toString());
	}
	
	@Test
	public void testToStringC() {
		GamGoal goal1 = new GamGoal("goal1", 0.1, false, "ANYAGENT");
		String correct = "{CGOAL: goal1, 0.1, ANYAGENT}";
		assertEquals(correct, goal1.toString());
	}
	
	@Test
	public void testSetIsIndividual() {
		GamGoal goal1 = new GamGoal("goal1", 0.1, true, "test");
		assertEquals(true, goal1.isIndividualGoal());
		goal1.setIndividualGoal(false);
		assertEquals(false, goal1.isIndividualGoal());
	}
	
	@Test
	public void testSetValue() {
		double valueFirst = 0.2;
		double valueSecond = -3;
		GamGoal goal1 = new GamGoal("goal1", valueFirst, true, "test");
		assertEquals(valueFirst, goal1.getValue(), 0.0);
		goal1.setValue(valueSecond);
		assertEquals(valueSecond, goal1.getValue(), 0.0);
	}
	
	@Test
	public void testSetGoal() {
		String goalFirst = "goal1";
		String goalSecond = "test";
		GamGoal goal1 = new GamGoal(goalFirst, 0.1, true, "test");
		assertEquals(goalFirst, goal1.getGoal());
		goal1.setGoal(goalSecond);
		assertEquals(goalSecond, goal1.getGoal());
	}
}
