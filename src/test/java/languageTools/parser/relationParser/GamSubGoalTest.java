package languageTools.parser.relationParser;

import static org.junit.Assert.*;
import languageTools.exceptions.relationParser.InvalidGamSubGoalException;

import org.junit.Test;

public class GamSubGoalTest {

	@Test
	public void testConstructorIncrementalFalse() throws InvalidGamSubGoalException {
	 GamSubGoal testBel = new GamSubGoal("bel1",0, "test2", 0.2, false);
	 assertEquals(0, testBel.likelihood, 0);
	 assertEquals(0.2, testBel.congruence, 0);
	 assertEquals("test2", testBel.getAffectedGoalName());
	 assertEquals(false, testBel.isIncremental);
	 assertEquals("bel1", testBel.getGoalName());
	}
	
	@Test
	public void testConstructorIncrementalTrue() throws InvalidGamSubGoalException {
	 GamSubGoal testBel = new GamSubGoal("bel1", 0,"test2", 0.2, true);
	 assertEquals(true, testBel.isIncremental);
	}
	
	@Test(expected = InvalidGamSubGoalException.class)  
	public void testTooHighLikelihood() throws InvalidGamSubGoalException {
		 GamSubGoal testBel = new GamSubGoal("bel2", 1.1, "test2", 0.2, false);
	}
	
	
	@Test(expected = InvalidGamSubGoalException.class)  
	public void testTooLowLikelihood() throws InvalidGamSubGoalException {
		 GamSubGoal testBel = new GamSubGoal("bel", -1.1,"test2", 0.2, false);
	}
	
	@Test
	public void testLikelihood1() throws InvalidGamSubGoalException {
	 GamSubGoal testBel = new GamSubGoal("bel",1,"test1", 0.2, false);
	 assertEquals(1, testBel.likelihood, 0);
	}
	
	@Test
	public void testLikelihoodMin1() throws InvalidGamSubGoalException {
	 GamSubGoal testBel = new GamSubGoal("bel",-1, "test2", 0.2, false);
	 assertEquals(-1, testBel.likelihood, 0);
	}
	
	@Test(expected = InvalidGamSubGoalException.class)  
	public void testTooHighCongruence() throws InvalidGamSubGoalException {
		 GamSubGoal testBel = new GamSubGoal("bel",0, "test2", 1.1, false);
	}
	
	@Test(expected = InvalidGamSubGoalException.class)  
	public void testTooLowCongruence() throws InvalidGamSubGoalException {
		 GamSubGoal testBel = new GamSubGoal("bel",0,"test2", -1.1, false);
	}

	@Test
	public void testCongruence1() throws InvalidGamSubGoalException {
	 GamSubGoal testBel = new GamSubGoal("bel",0, "test2", 1, false);
	 assertEquals(1, testBel.congruence, 0);
	}
	
	@Test
	public void testCongruenceMin1() throws InvalidGamSubGoalException {
	 GamSubGoal testBel = new GamSubGoal("bel",0, "test2", -1, false);
	 assertEquals(-1, testBel.congruence, 0);
	}
	
	@Test
	public void testEqualsTrue() throws InvalidGamSubGoalException {
		 GamSubGoal testBel1 = new GamSubGoal("bel", 0, "test2", -1, false);
		 GamSubGoal testBel2 = new GamSubGoal("bel", 0, "test2", -1, false);
		 assertTrue(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherLikelihood() throws InvalidGamSubGoalException {
		 GamSubGoal testBel1 = new GamSubGoal("bel", 0, "test2", -1, false);
		 GamSubGoal testBel2 = new GamSubGoal("bel", 0.2, "test2", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherAgent() throws InvalidGamSubGoalException {
		 GamSubGoal testBel1 = new GamSubGoal("bel", 0, "test2", -1, false);
		 GamSubGoal testBel2 = new GamSubGoal("bel", 0, "test1", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	
	@Test
	public void testEqualsOtherGoal() throws InvalidGamSubGoalException {
		 GamSubGoal testBel1 = new GamSubGoal("bel", 0, "test2", -1, false);
		 GamSubGoal testBel2 = new GamSubGoal("bel", 0, "test3", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherCongruence() throws InvalidGamSubGoalException {
		 GamSubGoal testBel1 = new GamSubGoal("bel", 0, "test2", -1, false);
		 GamSubGoal testBel2 = new GamSubGoal("bel", 0, "test3", 0.4, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherIsIncremental() throws InvalidGamSubGoalException {
		 GamSubGoal testBel1 = new GamSubGoal("bel", 0, "test2", -1, false);
		 GamSubGoal testBel2 = new GamSubGoal("bel", 0, "test3", -1, true);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherObject() throws InvalidGamSubGoalException {
		 GamSubGoal testBel1 = new GamSubGoal("bel", 0, "test2", -1, false);
		 String c = "c";
		 assertFalse(testBel1.equals(c));
	}
	
	@Test
	public void testToString() throws InvalidGamSubGoalException {
		 GamSubGoal testBel = new GamSubGoal("bel", 0, "test2", -1, false);
		 String correct = "{SUB: bel, 0.0, test2, -1.0, false}";
		 assertEquals(correct, testBel.toString());
	}
	
	@Test
	public void testEqualsOtherbelName() throws InvalidGamSubGoalException {
		 GamSubGoal testBel1 = new GamSubGoal("bel1", 0.2, "test2", -1, false);
		 GamSubGoal testBel2 = new GamSubGoal("bel", 0.2, "test2", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void setNameString() throws InvalidGamSubGoalException {
		 GamSubGoal testBel1 = new GamSubGoal("bel1", 0.2, "test2", -1, false);
		 testBel1.setGoalName("bel2");
		 assertEquals(testBel1.getGoalName(),"bel2");
	}
	

}
