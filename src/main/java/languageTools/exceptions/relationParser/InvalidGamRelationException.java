/**
 * This exceptions is thrown when the EmotionConfig text file contains a wrong line that is caused
 * by a wrong relation setting.
 * @author bkreynen
 *
 */
package languageTools.exceptions.relationParser;

public class InvalidGamRelationException extends Exception{
	public InvalidGamRelationException(String message) {
        super(message);
    }	
}