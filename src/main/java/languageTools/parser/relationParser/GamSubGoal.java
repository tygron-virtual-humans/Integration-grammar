/**
 * Class that represents a Gamygdala SubGoal rule and its values.
 */
package languageTools.parser.relationParser;

import languageTools.exceptions.relationParser.InvalidGamSubGoalException;

/**
 * Represents the configuration of a subgoal for gamygdala
 *
 */
public class GamSubGoal {
	String goalName;
	double likelihood;
	String affectedGoalName;
	double congruence;
	boolean isIncremental;
	
	/**
	 * Constructor of a GAM SubGoal
	 * @param likelihood - likelihood that this SubGoal is true.
	 * @param causal - agent who caused this event
	 * @param affected - goals that are affected by the agent
	 * @param congruence - how important
	 * @param isIncremental - optional
	 * @throws InvalidGamSubGoalException
	 */

	public GamSubGoal(String goalName, double likelihood, String affectedGoalName, double congruence, Boolean isIncremental) throws InvalidGamSubGoalException{
		
		//check that it is between given boundaries -1 and 1
		if(likelihood < -1 || likelihood > 1) {
			throw new InvalidGamSubGoalException("Likelihood of SubGoal is not in the [-1, 1] range");
		}
		
		//same check
		if(congruence < -1 || congruence > 1) {
			throw new InvalidGamSubGoalException("Congruence of SubGoal is not in the [-1, 1] range");
		}
		this.goalName = goalName;
		this.likelihood = likelihood;
		this.affectedGoalName = affectedGoalName;
		this.congruence = congruence;
		this.isIncremental = isIncremental;
	}
	
	
	/**
	 * Equals method
	 */
	public boolean equals(Object object) {
		  if(object instanceof GamSubGoal) {
			  GamSubGoal other = (GamSubGoal) object;
			  Boolean value = true;
			  value = value && this.goalName.equals(other.goalName);
			  value = value && this.likelihood == other.likelihood;
			  value = value && this.congruence == other.congruence;
			  value = value && this.isIncremental == other.isIncremental;
			  value = value && this.affectedGoalName.equals(other.affectedGoalName);
			  return value;
		  } else {
			  return false;
		  }
	  }
	
	/**
	 * toString method
	 */
	public String toString() {
		return "{SUB: " + goalName + ", " + likelihood + ", " + affectedGoalName + ", "  + congruence +   ", " + isIncremental +"}";
	}
	
	/**
	 * returns the name of the SubGoal
	 * @return name of SubGoal
	 */
	public String getGoalName() {
		return goalName;
	}

	/**
	 * sets the SubGoal name
	 * @param SubGoalName new SubGoal name
	 */
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	
	/**
	 * gets the affected goal of the subgoal
	 * @return
	 */
	public String getAffectedGoalName() {
		return affectedGoalName;
	}

	/**
	 * sets the affected goal of the subgoal
	 * @param affectedGoalName
	 */
	public void setAffectedGoalName(String affectedGoalName) {
		this.affectedGoalName = affectedGoalName;
	}
	
	/**
	 * returns the likelihood of this subgoal
	 */
	public double getLikelihood() {
		return likelihood;
	}

	/**
	 * Sets the likelihood of this subgoal
	 */
	public void setLikelihood(double likelihood) {
		this.likelihood = likelihood;
	}
	
	/**
	 * Get the congurence that this subgoal should be appraised with
	 */
	public double getCongruence() {
		return congruence;
	}

	/**
	 * Set the congruence
	 */
	public void setCongruence(double congruence) {
		this.congruence = congruence;
	}

	/**
 	* returns true if the subgoal should be appraised as an incremental goal
 	* @return
 	*/
	public boolean isIncremental() {
		return isIncremental;
	}

	/**
	 * Set whether the goal is incremental or not
	 * @param isIncremental
	 */
	public void setIncremental(boolean isIncremental) {
		this.isIncremental = isIncremental;
	}


}
