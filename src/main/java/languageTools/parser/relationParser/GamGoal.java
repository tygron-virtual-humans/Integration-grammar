/**
 * Class that represents a goal in Gamygdala including its values.
 */

package languageTools.parser.relationParser;

public class GamGoal {
	
	private double value;
	private String goal;
	private String agent;
	
	/**
	 * Constructor
	 * @param agent - the agent that has this goal
	 * @param goal - the name of the goal
	 * @param value - value that represent how badly the agents want this. 1 is high, -1 is low.
	 */
	public GamGoal(String agent, String goal, double value){
		this.agent = agent;
		this.value = value;
		this.goal = goal;
	}
	
	
	/**
	 * Equals method
	 */
	public boolean equals(Object object) {
		  if(object instanceof GamGoal) {
			  GamGoal other = (GamGoal) object;
			  Boolean res = true;
			  res = res && this.value == other.value;
			  res = res && this.goal.equals(other.goal);
			  res = res && this.agent.equals(other.agent);
			  return res;
		  } else {
			  return false;
		  }
	  }
	
	/**
	 * toString method
	 */
	public String toString() {
		 return "{GOAL: " + agent +  ", " + goal + ", " + value +"}";
	}


	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}


	/**
	 * @return the goal
	 */
	public String getGoal() {
		return goal;
	}


	/**
	 * @param goal the goal to set
	 */
	public void setGoal(String goal) {
		this.goal = goal;
	}


	/**
	 * @return the agent
	 */
	public String getAgent() {
		return agent;
	}


	/**
	 * @param agent the agent to set
	 */
	public void setAgent(String agent) {
		this.agent = agent;
	}
}
