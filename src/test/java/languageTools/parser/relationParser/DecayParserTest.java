package languageTools.parser.relationParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import languageTools.exceptions.relationParser.InvalidEmotionConfigFile;

import org.junit.After;
import org.junit.Test;

public class DecayParserTest {
	@After
	public void tearDown(){
		EmotionConfig.reset();
	}

	@Test
	public void testValidDecayFile() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = EmotionConfig.getInstance();
		EmotionConfig.parse("src/test/languageTools/parser/relationParser/validDecayFile");
		HashMap<String,GamBelief> beliefs = new HashMap<String,GamBelief>();
		HashMap<String, GamGoal> goals = new HashMap<String,GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamGoal goal = new GamGoal("agent1", "goal1", 1);
		goals.put(goal.getGoal(),goal);
		
		assertEquals(beliefs,  testConfig.getBeliefs());
		assertEquals(goals,    testConfig.getGoals());
		assertEquals(relations,testConfig.getRelations());
		
		assertEquals(0.25, testConfig.getDecay(), 0.0001);
		assertTrue(testConfig.isDecayExponential());
	}

	@Test
	public void testValidLineairDecayFile() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = EmotionConfig.getInstance();
		EmotionConfig.parse("src/test/languageTools/parser/relationParser/validLineairDecayFile");
		HashMap<String,GamBelief> beliefs = new HashMap<String,GamBelief>();
		HashMap<String, GamGoal> goals = new HashMap<String,GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamGoal goal = new GamGoal("agent1", "goal1", 1);
		goals.put(goal.getGoal(),goal);
		
		assertEquals(beliefs,  testConfig.getBeliefs());
		assertEquals(goals,    testConfig.getGoals());
		assertEquals(relations,testConfig.getRelations());
		
		assertEquals(0.375, testConfig.getDecay(), 0.0001);
		assertFalse(testConfig.isDecayExponential());
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testInvalidDecayFile() throws FileNotFoundException, InvalidEmotionConfigFile {
		RelationParser.parse("src/test/languageTools/parser/relationParser/invalidDecayFile");
	}
}
