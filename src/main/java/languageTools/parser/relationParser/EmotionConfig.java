/**
 * A class that represents a configuration of emotions by its rules. You have goals for the agents, 
 * relations between them and beliefs about how bad or good some events are for the goals.
 */

package languageTools.parser.relationParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import languageTools.exceptions.relationParser.InvalidEmotionConfigFile;
public class EmotionConfig {

  private ArrayList<GamBelief> beliefs;
  private ArrayList<GamGoal> goals;
  private ArrayList<GamRelation> relations;
  private static EmotionConfig configuration;
  private double defaultUtility;
  private double defaultPositiveCongruence;
  private double defaultNegativeCongruence;
  private double defaultBelLikelihood;
  private boolean defaultIsIncremental;





/** 
 * Constructor for the configuration of the emotions.
 * @param beliefs - the believes that are present about goals
 * @param goals - the specified goals for the agents
 * @param relations - the relations between the agents
 */
  public EmotionConfig(ArrayList<GamBelief> beliefs,ArrayList<GamGoal> goals,
     ArrayList<GamRelation> relations) {
    this.setBeliefs(beliefs);
    this.setGoals(goals);
    this.setRelations(relations);
  }
  
  /**
   * Singleton method to get the instance
   * @return
   */
  public synchronized static EmotionConfig getInstance(){
	  if(configuration == null){
		  configuration = new EmotionConfig(new ArrayList<GamBelief>(), new ArrayList<GamGoal>(), new ArrayList<GamRelation>());
		  configuration.setDefaultUtility(1);
		  configuration.setDefaultNegativeCongruence(-0.1);
		  configuration.setDefaultPositiveCongruence(0.5);
		  configuration.setDefaultBelLikelihood(1);
		  configuration.setDefaultIsIncremental(false);
	  }
	  return configuration;
	  
	  
  }
  
  public static void reset(){
	  configuration = null;
  }
  
  /**
   * 
   * @param filepath
   * @throws FileNotFoundException
   * @throws InvalidEmotionConfigFile
   */
  public static void parse(String filepath) throws FileNotFoundException, InvalidEmotionConfigFile{
	  File file = new File(filepath);
	  configuration = RelationParser.parse(file);
	  
  }
  
  /**
   * toString method.
   */
  public String toString() {
	  String bel = getBeliefs().toString();
	  String goal = getGoals().toString();
	  String rel = getRelations().toString();
	  return "{Config: " + bel + ", " + goal + ", " + rel + ", utility: " + defaultUtility + ", negativeCongruence: " + defaultNegativeCongruence + ", positiveCongruence: " + defaultPositiveCongruence + ", belieflikelihood: " + defaultBelLikelihood + ", isincremental: " + defaultIsIncremental + "}";
  }

/**
 * @return the beliefs
 */
public ArrayList<GamBelief> getBeliefs() {
	return beliefs;
}

/**
 * @param beliefs the beliefs to set
 */
public void setBeliefs(ArrayList<GamBelief> beliefs) {
	this.beliefs = beliefs;
}

/**
 * @return the goals
 */
public ArrayList<GamGoal> getGoals() {
	return goals;
}

/**
 * @param goals the goals to set
 */
public void setGoals(ArrayList<GamGoal> goals) {
	this.goals = goals;
}

/**
 * @return the relations
 */
public ArrayList<GamRelation> getRelations() {
	return relations;
}

/**
 * @param relations the relations to set
 */
public void setRelations(ArrayList<GamRelation> relations) {
	this.relations = relations;
}

/**
 * getter for he default utility
 * @return
 */
public double getDefaultUtility() {
	return this.defaultUtility;
}

/**
 * Setter for the default utility
 * @param defaultUtility
 */
public void setDefaultUtility(double defaultUtility) {
	this.defaultUtility = defaultUtility;
}

/**
 * getter for the default positive congruence
 * @return
 */
public double getDefaultPositiveCongruence() {
	return defaultPositiveCongruence;
}

/**
 * setter for the default positive congruence
 * @param defaultPositiveCongruence
 */
public void setDefaultPositiveCongruence(double defaultPositiveCongruence) {
	this.defaultPositiveCongruence = defaultPositiveCongruence;
}

/**
 * getter for the default negative congruence 
 * @return
 */
public double getDefaultNegativeCongruence() {
	return defaultNegativeCongruence;
}


/**
 * setter for the default negative congruence
 * @param defaultNegativeCongruence
 */
public void setDefaultNegativeCongruence(double defaultNegativeCongruence) {
	this.defaultNegativeCongruence = defaultNegativeCongruence;
}

/**
 * getter for the default belief likelihood
 * @return
 */
public double getDefaultBelLikelihood() {
	return defaultBelLikelihood;
}

/**
 * setter for the default belief likelihood
 * @param defaultBelLikelihood
 */
public void setDefaultBelLikelihood(double defaultBelLikelihood) {
	this.defaultBelLikelihood = defaultBelLikelihood;
}

/**
 * getter for the setting of the IsIncremental. See documentation.
 * @return
 */
public boolean isDefaultIsIncremental() {
	return defaultIsIncremental;
}

/**
 * setter for the setting of the IsIncremental.
 * @param defaultIsIncremental
 */
public void setDefaultIsIncremental(boolean defaultIsIncremental) {
	this.defaultIsIncremental = defaultIsIncremental;
}

}
