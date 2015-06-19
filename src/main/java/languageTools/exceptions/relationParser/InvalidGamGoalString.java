/**
 * This exceptions is thrown when the EmotionConfig text file contains a wrong line that is caused
 * by a wrong goal setting syntax.
 * @author bkreynen
 *
 */
package languageTools.exceptions.relationParser;

public class InvalidGamGoalString extends Exception{
	public InvalidGamGoalString(String message) {
        super(message);
    }	
}