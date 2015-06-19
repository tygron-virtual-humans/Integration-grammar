/**
 * This exceptions is thrown when the EmotionConfig text file contains a wrong line
 * @author bkreynen
 *
 */
package languageTools.exceptions.relationParser;

public class InvalidEmotionConfigFile extends Exception{
	public InvalidEmotionConfigFile(String message) {
        super(message);
    }	
}