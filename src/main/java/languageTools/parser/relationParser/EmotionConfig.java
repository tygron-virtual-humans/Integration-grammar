/**
 * A class that represents a configuration of emotions by its rules. You have goals for the agents, 
 * relations between them and beliefs about how bad or good some events are for the goals.
 */

package languageTools.parser.relationParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import languageTools.exceptions.relationParser.InvalidEmotionConfigFile;
import languageTools.exceptions.relationParser.InvalidGamBeliefException;
public class EmotionConfig {

  private HashMap<String,ArrayList<GamBelief>> beliefs;
  private HashMap<String, GamGoal> goals;
  private ArrayList<GamRelation> relations;
  private static EmotionConfig configuration;
  private double defaultUtility;
  private double defaultPositiveCongruence;
  private double defaultNegativeCongruence;
  private double defaultBelLikelihood;
  private boolean defaultIsIncremental;
  private boolean whiteList;



/** 
 * Constructor for the configuration of the emotions.
 * @param beliefs - the believes that are present about goals
 * @param goals - the specified goals for the agents
 * @param relations - the relations between the agents
 */
  public EmotionConfig(HashMap<String, ArrayList<GamBelief>> beliefs,HashMap<String,GamGoal> goals,
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
		  configuration = new EmotionConfig(new HashMap<String, ArrayList<GamBelief>>(), new HashMap<String, GamGoal>(), new ArrayList<GamRelation>());
		  configuration.setDefaultUtility(1);
		  configuration.setDefaultNegativeCongruence(-1);
		  configuration.setDefaultPositiveCongruence(1);
		  configuration.setDefaultBelLikelihood(1);
		  configuration.setDefaultIsIncremental(false);
		  configuration.setWhiteList(false);
	  }
	  return configuration;
	  
	  
  }
  /**
   * Method that resets the configuration instance to null
   */
  public static void reset(){
	  configuration = null;
  }
  
  /**
   * Method that parses the file that is given as a classpath as String.
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
public HashMap<String,ArrayList<GamBelief>> getBeliefs() {
	return beliefs;
}

/**
 * @param beliefs the beliefs to set
 */
public void setBeliefs(HashMap<String, ArrayList<GamBelief>> beliefs) {
	this.beliefs = beliefs;
}

/**
 * @return the goals
 */
public HashMap<String,GamGoal> getGoals() {
	return goals;
}

/**
 * @param goals the goals to set
 */
public void setGoals(HashMap<String, GamGoal> goals) {
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

/**
 * Adds the goal to the emotionconfig.
 * @param goal goal to be added
 */
public void addGoal(GamGoal goal) {
	this.getGoals().put(goal.getGoal(), goal);
}

/**
 * add the beleif to the emotionconfig
 * @param belief belief to be aded
 */
public void addBelief(GamBelief belief) {
	if(this.getBeliefs().containsKey(belief.getGoalName())) {
	 this.getBeliefs().get(belief.getGoalName()).add(belief);
	 ArrayList<GamBelief> beliefs = this.getBeliefs().get(belief.getGoalName());
	 for(int i = beliefs.size()-1; i>=0; i--) {
		 if(beliefs.get(i).getAffectedGoalName().equals(belief.getAffectedGoalName())) {
			 beliefs.remove(i); //Otherwise we would count these as a "double" subgoal, only keep the latest info added
		 }
	 }
	 beliefs.add(belief);
	 this.getBeliefs().put(belief.getGoalName(), beliefs);
	} else {
		ArrayList<GamBelief> beliefs = new ArrayList<GamBelief>();
		beliefs.add(belief);
		this.getBeliefs().put(belief.getGoalName(), beliefs);
	}
}

/**
 * get goal configuration by the name goalName, returns a default configuration if it is not specified.
 * @param goalName goal name for which to retrieve the configuration
 * @return
 */
public GamGoal getGoal(String goalName) {
	if(this.getGoals().containsKey(goalName)) {
		return this.getGoals().get(goalName);
	} else {
		if(this.hasWhiteList()) {
			return new GamGoal(goalName, 0, false);
		} else {
			return new GamGoal(goalName, this.getDefaultUtility(),false);

		}
	}
}

/**
 * get belief configuration by the beliefName, returns as default configuration if it is not specified.
 * @param beliefName
 * @return
 * @throws InvalidGamBeliefException 
 */

public ArrayList<GamBelief> getBelief(String beliefName) throws InvalidGamBeliefException {
	if(this.getBeliefs().containsKey(beliefName)) {
		return this.getBeliefs().get(beliefName);
	} else {
		return new ArrayList<GamBelief>();
	}
}

/**
 * returns default is incremental
 * @return
 */
public boolean getDefaultIsIncremental() {
	return this.defaultIsIncremental;
}

/**
 * returns if a whitelist is set or not
 * @return
 */
public boolean hasWhiteList() {
	return whiteList;
}

/**
 * Sets the whitelist to true or false
 * @param whiteList
 */
public void setWhiteList(boolean whiteList) {
	this.whiteList = whiteList;
}

}
