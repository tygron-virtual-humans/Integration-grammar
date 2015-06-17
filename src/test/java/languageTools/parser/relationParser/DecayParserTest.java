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
	
		assertEquals(0.25, testConfig.getDecay(), 0.0001);
		assertTrue(testConfig.isDecayExponential());
	}

	@Test
	public void testValidLineairDecayFile() throws FileNotFoundException, InvalidEmotionConfigFile {
		EmotionConfig testConfig = EmotionConfig.getInstance();
		EmotionConfig.parse("src/test/languageTools/parser/relationParser/validLineairDecayFile");
		
		assertEquals(0.375, testConfig.getDecay(), 0.0001);
		assertFalse(testConfig.isDecayExponential());
	}
	
	@Test(expected = InvalidEmotionConfigFile.class)
	public void testInvalidDecayFile() throws FileNotFoundException, InvalidEmotionConfigFile {
		RelationParser.parse("src/test/languageTools/parser/relationParser/invalidDecayFile");
	}
}

