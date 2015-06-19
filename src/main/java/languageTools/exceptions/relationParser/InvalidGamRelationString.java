/**
 * This exceptions is thrown when the EmotionConfig text file contains a wrong line that is caused
 * by a wrong relation setting syntax.
 * @author bkreynen
 *
 */
package languageTools.exceptions.relationParser;

public class InvalidGamRelationString extends Exception{
	public InvalidGamRelationString(String message) {
        super(message);
    }	
}