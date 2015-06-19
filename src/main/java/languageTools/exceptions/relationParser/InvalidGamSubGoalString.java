/**
 * This exceptions is thrown when the EmotionConfig text file contains a wrong line that is caused
 * by a wrong goal setting syntax.
 * @author bkreynen
 *
 */
package languageTools.exceptions.relationParser;

public class InvalidGamSubGoalString extends Exception{
	public InvalidGamSubGoalString(String message) {
        super(message);
    }	
}