/**
 * A class that represents a configuration of emotions by its rules. You have goals for the agents, 
 * relations between them and SubGoals about how bad or good some events are for the goals.
 */

package languageTools.parser.relationParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import languageTools.exceptions.relationParser.InvalidEmotionConfigFile;
import languageTools.exceptions.relationParser.InvalidGamSubGoalException;
public class EmotionConfig {

  private HashMap<String,ArrayList<GamSubGoal>> SubGoals;
  private HashMap<String, HashMap<String,GamGoal>> goals;
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
 * @param SubGoals - the believes that are present about goals
 * @param goals - the specified goals for the agents
 * @param relations - the relations between the agents
 */
  public EmotionConfig(HashMap<String, ArrayList<GamSubGoal>> SubGoals,HashMap<String,HashMap<String,GamGoal>> goals,
     ArrayList<GamRelation> relations) {
    this.setSubGoals(SubGoals);
    this.setGoals(goals);
    this.setRelations(relations);
    this.setDefaultUtility(1);
	this.setDefaultNegativeCongruence(-1);
	this.setDefaultPositiveCongruence(1);
	this.setDefaultBelLikelihood(1);
	this.setDefaultIsIncremental(false);
	this.setWhiteList(false);
  }
  
  /**
   * Singleton method to get the instance
   * @return
   */
  public synchronized static EmotionConfig getInstance(){
	  if(configuration == null){
		  configuration = new EmotionConfig(new HashMap<String, ArrayList<GamSubGoal>>(), new HashMap<String, HashMap<String,GamGoal>>(), new ArrayList<GamRelation>());
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
	  String bel = getSubGoals().toString();
	  String goal = getGoals().toString();
	  String rel = getRelations().toString();
	  return "{Config: " + bel + ", " + goal + ", " + rel + ", utility: " + defaultUtility + ", negativeCongruence: " + defaultNegativeCongruence + ", positiveCongruence: " + defaultPositiveCongruence + ", SubGoallikelihood: " + defaultBelLikelihood + ", isincremental: " + defaultIsIncremental + "}";
  }

/**
 * @return the SubGoals
 */
public HashMap<String,ArrayList<GamSubGoal>> getSubGoals() {
	return SubGoals;
}

/**
 * @param SubGoals the SubGoals to set
 */
public void setSubGoals(HashMap<String, ArrayList<GamSubGoal>> SubGoals) {
	this.SubGoals = SubGoals;
}

/**
 * @return the goals
 */
public HashMap<String,HashMap<String,GamGoal>> getGoals() {
	return goals;
}

/**
 * @param goals the goals to set
 */
public void setGoals(HashMap<String, HashMap<String,GamGoal>> goals) {
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
 * getter for the default SubGoal likelihood
 * @return
 */
public double getDefaultBelLikelihood() {
	return defaultBelLikelihood;
}

/**
 * setter for the default SubGoal likelihood
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
	if(goals.containsKey(goal.getGoal())) {
		goals.get(goal.getGoal()).put(goal.getAgent(), goal);
	} else {
		HashMap<String, GamGoal> toPut = new HashMap<String, GamGoal>();
		toPut.put(goal.getAgent(), goal);
		goals.put(goal.getGoal(), toPut);
		
	}
}

/**
 * add the beleif to the emotionconfig
 * @param SubGoal SubGoal to be aded
 */
public void addSubGoal(GamSubGoal SubGoal) {
	if(this.getSubGoals().containsKey(SubGoal.getGoalName())) {
	 this.getSubGoals().get(SubGoal.getGoalName()).add(SubGoal);
	 ArrayList<GamSubGoal> SubGoals = this.getSubGoals().get(SubGoal.getGoalName());
	 for(int i = SubGoals.size()-1; i>=0; i--) {
		 if(SubGoals.get(i).getAffectedGoalName().equals(SubGoal.getAffectedGoalName())) {
			 SubGoals.remove(i); //Otherwise we would count these as a "double" subgoal, only keep the latest info added
		 }
	 }
	 SubGoals.add(SubGoal);
	 this.getSubGoals().put(SubGoal.getGoalName(), SubGoals);
	} else {
		ArrayList<GamSubGoal> SubGoals = new ArrayList<GamSubGoal>();
		SubGoals.add(SubGoal);
		this.getSubGoals().put(SubGoal.getGoalName(), SubGoals);
	}
}

/**
 * get goal configuration by the name goalName, returns a default configuration if it is not specified.
 * @param goalName goal name for which to retrieve the configuration
 * @return
 */
public GamGoal getGoal(String goalName, String agentName) {
	if(this.getGoals().containsKey(goalName) && this.getGoals().get(goalName).containsKey(agentName)){
		return this.getGoals().get(goalName).get(agentName);
	} else if(this.getGoals().containsKey(goalName) && this.getGoals().get(goalName).containsKey("ANYAGENT")){ //The string ANYAGENT is used to signify these goalparamters hold for any agent that does not have them set individually
		return this.getGoals().get(goalName).get("ANYAGENT");
	} else {
		if(this.hasWhiteList()) {
			return new GamGoal(goalName, 0, false, "ANYAGENT");
		} else {
			return new GamGoal(goalName, this.getDefaultUtility(),false, "ANYAGENT");
		}
	}
}

/**
 * get SubGoal configuration by the SubGoalName, returns as default configuration if it is not specified.
 * @param SubGoalName
 * @return
 * @throws InvalidGamSubGoalException 
 */
public ArrayList<GamSubGoal> getSubGoal(String SubGoalName) throws InvalidGamSubGoalException {
	if(this.getSubGoals().containsKey(SubGoalName)) {
		return this.getSubGoals().get(SubGoalName);
	} else {
		return new ArrayList<GamSubGoal>();
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
