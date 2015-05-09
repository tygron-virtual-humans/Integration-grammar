package languageTools.parser.relationParser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import languageTools.exceptions.relationParser.InvalidEmotionConfigFile;
import languageTools.exceptions.relationParser.InvalidGamBeliefException;
import languageTools.exceptions.relationParser.InvalidGamRelationException;

import org.junit.Test;

public class RelationparserTest {

//	BEL, 0.2, agent1, agent2, 0.1, false
//	REL, agent1, agent2, -3
//	GOAL, agent1, goal1, 3
	
	@Test
	public void testValidFile() throws FileNotFoundException, InvalidEmotionConfigFile, InvalidGamBeliefException, InvalidGamRelationException {
		EmotionConfig testConfig = RelationParser.parse("src/test/languageTools/parser/relationParser/validFile");
		ArrayList<GamBelief> beliefs = new ArrayList<GamBelief>();
		ArrayList<GamGoal> goals = new ArrayList<GamGoal>();
		ArrayList<GamRelation> relations = new ArrayList<GamRelation>();
		
		GamBelief belief = new GamBelief(0.2, "agent1", "agent2", 0.1, false);
		beliefs.add(belief);
		GamGoal goal = new GamGoal("agent1", "goal1", 3);
		goals.add(goal);
		GamRelation relation = new GamRelation("agent1", "agent2", -1);
		relations.add(relation);
		EmotionConfig validConfig = new EmotionConfig(beliefs, goals, relations);
		
		System.out.println(validConfig.beliefs.toString());
		System.out.println(testConfig.beliefs.toString());

		System.out.println(validConfig.goals.toString());
		System.out.println(testConfig.goals.toString());

		System.out.println(validConfig.relations.toString());
		System.out.println(testConfig.relations.toString());

		assertEquals(validConfig, testConfig);
		
		
	}

}
