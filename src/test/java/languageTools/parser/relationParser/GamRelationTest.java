package languageTools.parser.relationParser;

import static org.junit.Assert.*;
import languageTools.exceptions.relationParser.InvalidGamBeliefException;
import languageTools.exceptions.relationParser.InvalidGamRelationException;

import org.junit.Test;

public class GamRelationTest {

	@Test
	public void testConstructor() throws InvalidGamRelationException {
		GamRelation testRelation = new GamRelation("agent1", "agent2", 0.2);
		assertEquals("agent1", testRelation.agent1);
		assertEquals("agent2", testRelation.agent2);
		assertEquals(0.2, testRelation.value, 0);
	}
	
	@Test
	public void testValue1() throws InvalidGamBeliefException, InvalidGamRelationException {
	 GamRelation testRelation = new GamRelation("agent1", "agent2", 1);
	 assertEquals(1, testRelation.value, 0);
	}
	
	@Test
	public void testValueMin1() throws InvalidGamBeliefException, InvalidGamRelationException {
	 GamRelation testRelation = new GamRelation("agent1", "agent2", -1);
	 assertEquals(-1, testRelation.value, 0);
	}
	
	@Test(expected = InvalidGamRelationException.class)  
	public void testTooHighValue() throws InvalidGamRelationException {
		 GamRelation testRelation = new GamRelation("agent1", "agent2", -1.1);
	}
	
	
	@Test(expected = InvalidGamRelationException.class)  
	public void testTooLowValue() throws InvalidGamRelationException {
		 GamRelation testRelation = new GamRelation("agent1", "agent2", 1.1);
	}
	
	@Test
	public void testEqualstrue() throws InvalidGamRelationException {
		GamRelation relation1 = new GamRelation("agent1","agent2", 1);
		GamRelation relation2 = new GamRelation("agent1","agent2", 1);
		
		assertTrue(relation1.equals(relation2));
	}
	
	@Test
	public void testEqualsOtherAgent1() throws InvalidGamRelationException {
		GamRelation relation1 = new GamRelation("agent1","agent2", 1);
		GamRelation relation2 = new GamRelation("agent3","agent2", 1);
		
		assertFalse(relation1.equals(relation2));
	}
	
	@Test
	public void testEqualsOtherAgent2() throws InvalidGamRelationException {
		GamRelation relation1 = new GamRelation("agent1","agent2", 1);
		GamRelation relation2 = new GamRelation("agent1","agent4", 1);
		
		assertFalse(relation1.equals(relation2));
	}
	
	@Test
	public void testEqualsOtherValue() throws InvalidGamRelationException {
		GamRelation relation1 = new GamRelation("agent1","agent2", 1);
		GamRelation relation2 = new GamRelation("agent1","agent2", 0.9);
		
		assertFalse(relation1.equals(relation2));
	}

}
