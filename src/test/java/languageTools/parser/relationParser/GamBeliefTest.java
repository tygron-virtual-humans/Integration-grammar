package languageTools.parser.relationParser;

import static org.junit.Assert.*;
import languageTools.exceptions.relationParser.InvalidGamBeliefException;

import org.junit.Test;

public class GamBeliefTest {

	@Test
	public void testConstructorIncrementalFalse() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief("bel1",0, "test2", 0.2, false);
	 assertEquals(0, testBel.likelihood, 0);
	 assertEquals(0.2, testBel.congruence, 0);
	 assertEquals("test2", testBel.getAffectedGoalName());
	 assertEquals(false, testBel.isIncremental);
	 assertEquals("bel1", testBel.getGoalName());
	}
	
	@Test
	public void testConstructorIncrementalTrue() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief("bel1", 0,"test2", 0.2, true);
	 assertEquals(true, testBel.isIncremental);
	}
	
	@Test(expected = InvalidGamBeliefException.class)  
	public void testTooHighLikelihood() throws InvalidGamBeliefException {
		 GamBelief testBel = new GamBelief("bel2", 1.1, "test2", 0.2, false);
	}
	
	
	@Test(expected = InvalidGamBeliefException.class)  
	public void testTooLowLikelihood() throws InvalidGamBeliefException {
		 GamBelief testBel = new GamBelief("bel", -1.1,"test2", 0.2, false);
	}
	
	@Test
	public void testLikelihood1() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief("bel",1,"test1", 0.2, false);
	 assertEquals(1, testBel.likelihood, 0);
	}
	
	@Test
	public void testLikelihoodMin1() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief("bel",-1, "test2", 0.2, false);
	 assertEquals(-1, testBel.likelihood, 0);
	}
	
	@Test(expected = InvalidGamBeliefException.class)  
	public void testTooHighCongruence() throws InvalidGamBeliefException {
		 GamBelief testBel = new GamBelief("bel",0, "test2", 1.1, false);
	}
	
	@Test(expected = InvalidGamBeliefException.class)  
	public void testTooLowCongruence() throws InvalidGamBeliefException {
		 GamBelief testBel = new GamBelief("bel",0,"test2", -1.1, false);
	}

	@Test
	public void testCongruence1() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief("bel",0, "test2", 1, false);
	 assertEquals(1, testBel.congruence, 0);
	}
	
	@Test
	public void testCongruenceMin1() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief("bel",0, "test2", -1, false);
	 assertEquals(-1, testBel.congruence, 0);
	}
	
	@Test
	public void testEqualsTrue() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief("bel", 0, "test2", -1, false);
		 GamBelief testBel2 = new GamBelief("bel", 0, "test2", -1, false);
		 assertTrue(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherLikelihood() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief("bel", 0, "test2", -1, false);
		 GamBelief testBel2 = new GamBelief("bel", 0.2, "test2", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherAgent() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief("bel", 0, "test2", -1, false);
		 GamBelief testBel2 = new GamBelief("bel", 0, "test1", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	
	@Test
	public void testEqualsOtherGoal() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief("bel", 0, "test2", -1, false);
		 GamBelief testBel2 = new GamBelief("bel", 0, "test3", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherCongruence() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief("bel", 0, "test2", -1, false);
		 GamBelief testBel2 = new GamBelief("bel", 0, "test3", 0.4, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherIsIncremental() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief("bel", 0, "test2", -1, false);
		 GamBelief testBel2 = new GamBelief("bel", 0, "test3", -1, true);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherObject() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief("bel", 0, "test2", -1, false);
		 String c = "c";
		 assertFalse(testBel1.equals(c));
	}
	
	@Test
	public void testToString() throws InvalidGamBeliefException {
		 GamBelief testBel = new GamBelief("bel", 0, "test2", -1, false);
		 String correct = "{SUB: bel, 0.0, test2, -1.0, false}";
		 assertEquals(correct, testBel.toString());
	}
	
	@Test
	public void testEqualsOtherbelName() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief("bel1", 0.2, "test2", -1, false);
		 GamBelief testBel2 = new GamBelief("bel", 0.2, "test2", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	

}
