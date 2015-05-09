package languageTools.parser.relationParser;

import static org.junit.Assert.*;
import languageTools.exceptions.relationParser.InvalidGamBeliefException;

import org.junit.Test;

public class GamBeliefTest {

	@Test
	public void testConstructorIncrementalFalse() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief(0,"test1", "test2", 0.2, false);
	 assertEquals(0, testBel.likelihood, 0);
	 assertEquals(0.2, testBel.congruence, 0);
	 assertEquals("test1", testBel.causal);
	 assertEquals("test2", testBel.affected);
	 assertEquals(false, testBel.isIncremental);
	}
	
	@Test
	public void testConstructorIncrementalTrue() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief(0,"test1", "test2", 0.2, true);
	 assertEquals(true, testBel.isIncremental);
	}
	
	@Test(expected = InvalidGamBeliefException.class)  
	public void testTooHighLikelihood() throws InvalidGamBeliefException {
		 GamBelief testBel = new GamBelief(1.1,"test1", "test2", 0.2, false);
	}
	
	
	@Test(expected = InvalidGamBeliefException.class)  
	public void testTooLowLikelihood() throws InvalidGamBeliefException {
		 GamBelief testBel = new GamBelief(-1.1,"test1", "test2", 0.2, false);
	}
	
	@Test
	public void testLikelihood1() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief(1,"test1", "test2", 0.2, false);
	 assertEquals(1, testBel.likelihood, 0);
	}
	
	@Test
	public void testLikelihoodMin1() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief(-1,"test1", "test2", 0.2, false);
	 assertEquals(-1, testBel.likelihood, 0);
	}
	
	@Test(expected = InvalidGamBeliefException.class)  
	public void testTooHighCongruence() throws InvalidGamBeliefException {
		 GamBelief testBel = new GamBelief(0,"test1", "test2", 1.1, false);
	}
	
	@Test(expected = InvalidGamBeliefException.class)  
	public void testTooLowCongruence() throws InvalidGamBeliefException {
		 GamBelief testBel = new GamBelief(0,"test1", "test2", -1.1, false);
	}

	@Test
	public void testCongruence1() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief(0,"test1", "test2", 1, false);
	 assertEquals(1, testBel.congruence, 0);
	}
	
	@Test
	public void testCongruenceMin1() throws InvalidGamBeliefException {
	 GamBelief testBel = new GamBelief(0,"test1", "test2", -1, false);
	 assertEquals(-1, testBel.congruence, 0);
	}
	
	@Test
	public void testEqualsTrue() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief(0,"test1", "test2", -1, false);
		 GamBelief testBel2 = new GamBelief(0,"test1", "test2", -1, false);
		 assertTrue(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherLikelihood() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief(0,"test1", "test2", -1, false);
		 GamBelief testBel2 = new GamBelief(0.2,"test1", "test2", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherAgent() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief(0,"test1", "test2", -1, false);
		 GamBelief testBel2 = new GamBelief(0,"test3", "test2", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	
	@Test
	public void testEqualsOtherGoal() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief(0,"test1", "test2", -1, false);
		 GamBelief testBel2 = new GamBelief(0,"test1", "test3", -1, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherCongruence() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief(0,"test1", "test2", -1, false);
		 GamBelief testBel2 = new GamBelief(0,"test1", "test3", 0.4, false);
		 assertFalse(testBel1.equals(testBel2));
	}
	
	@Test
	public void testEqualsOtherIsIncremental() throws InvalidGamBeliefException {
		 GamBelief testBel1 = new GamBelief(0,"test1", "test2", -1, false);
		 GamBelief testBel2 = new GamBelief(0,"test1", "test3", -1, true);
		 assertFalse(testBel1.equals(testBel2));
	}
	

}
