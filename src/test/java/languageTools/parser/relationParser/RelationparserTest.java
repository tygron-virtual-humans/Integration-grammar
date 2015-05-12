package languageTools.parser.relationParser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import languageTools.exceptions.relationParser.InvalidEmotionConfigFile;
import languageTools.exceptions.relationParser.InvalidGamBeliefException;
import languageTools.exceptions.relationParser.InvalidGamRelationException;

import org.junit.Test;

public class RelationparserTest {

	
	@Test
	public void testValidFile() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamBeliefException, InvalidGamRelationException {
		System.out.println("TESTVALIDFILE +++++++++++++++++++++++++++++++++++++++++++++++");
		EmotionConfig testConfig = RelationParser.parse("src/test/languageTools/parser/relationParser/validFile");
		ArrayList<GamBelief> beliefs = new ArrayList<GamBelief>();
		ArrayList<GamGoal> goals = new ArrayList<GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamBelief belief = new GamBelief(0.2, "agent1", "goal1", 0.1, false);
		beliefs.add(belief);
		belief = new GamBelief(0.3, "agent2", "goal2", 0.5, true);
		beliefs.add(belief);
		GamGoal goal = new GamGoal("agent2", "goal2", 0.8);
		goals.add(goal);
		goal = new GamGoal("agent1", "goal1", 3);
		goals.add(goal);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		relation = new GamRelation("agent3", "agent4", 0.9);
	    relations.add(relation);
		EmotionConfig validConfig = new EmotionConfig(beliefs, goals, relations);
		System.out.println(testConfig.getBeliefs());
		System.out.println(validConfig.getBeliefs());
		System.out.println(testConfig.getGoals());
		System.out.println(validConfig.getGoals());
		System.out.println(testConfig.getRelations());
		System.out.println(validConfig.getRelations());
		
		System.out.println("TESTVALIDFILE +++++++++++++++++++++++++++++++++++++++++++++++");

		assertEquals(validConfig, testConfig);
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testWrongBelief() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongBEL");
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testWrongRelation() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongREL");
		System.out.println(testConfig.getBeliefs());
		System.out.println(testConfig.getGoals());
		System.out.println(testConfig.getRelations());
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testWrongGoal() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongGOAL");
	}
	
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testWrongTag() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = RelationParser.parse("src/test/languageTools/parser/relationParser/wrongTAG");
	}
	
}
