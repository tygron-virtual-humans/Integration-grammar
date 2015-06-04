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
	
	@Test
	public void testEqualsOtherObject() throws InvalidGamRelationException {
		GamRelation relation1 = new GamRelation("agent1","agent2", 1);
		String c = "dsad";
		
		assertFalse(relation1.equals(c));
	}
	
	@Test
	public void testToString() throws InvalidGamRelationException {
		GamRelation relation1 = new GamRelation("agent1","agent2", 1);
		String correct = "{REL: agent1, agent2, 1.0}";
		assertEquals(correct, relation1.toString());
	}
	
	@Test
	public void settersTest() throws InvalidGamRelationException{
		GamRelation relation1 = new GamRelation("agent1","agent2", 1);
		relation1.setAgent1("agent2");
		relation1.setAgent2("agent3");
		relation1.setValue(0.5);
		
		GamRelation relation2 = new GamRelation("agent2","agent3", 0.5);
		
		assertEquals(relation2,relation1);
		
	}
	
	@Test
	public void getterTest() throws InvalidGamRelationException{
		GamRelation relation = new GamRelation("agent1","agent2", 0.87);
		String agent1 = relation.getAgent1();
		String agent2 = relation.getAgent2();
		double value = relation.getValue();
		
		//GamRelation relation2 = new GamRelation("agent2","agent3", 1);
		
		assertEquals(agent1,"agent1");
		assertEquals(agent2,"agent2");
		assertEquals(value,0.87,0.0);
		
	}

}
