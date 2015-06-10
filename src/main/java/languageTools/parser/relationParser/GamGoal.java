/**
 * Class that represents a goal in Gamygdala including its values.
 */

package languageTools.parser.relationParser;

public class GamGoal {
	
	private double value;
	private String goal;
	private boolean individualGoal;
	private String agent; //Holds which agent this goal definition belongs to (ANYAGENT means that these values hold for any agent that does not have them set specifically)
	
	public String getAgent() {
		return agent;
	}


	public void setAgent(String agent) {
		this.agent = agent;
	}


	public boolean isIndividualGoal() {
		return individualGoal;
	}


	public void setIndividualGoal(boolean individualGoal) {
		this.individualGoal = individualGoal;
	}


	/**
	 * Constructor
	 * @param agent - the agent that has this goal
	 * @param goal - the name of the goal
	 * @param value - value that represent how badly the agents want this. 1 is high, -1 is low.
	 */
	public GamGoal(String goal, double value, boolean individualGoal, String agent){
		this.value = value;
		this.goal = goal;
		this.individualGoal = individualGoal;
		this.agent = agent;
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
			  res = res && this.individualGoal == other.individualGoal;
			  res = res && this.getAgent().equals(other.getAgent());
			  return res;
		  } else {
			  return false;
		  }
	  }
	
	/**
	 * toString method
	 */
	public String toString() {
		if(individualGoal) {
		 return "{IGOAL: " + goal + ", " + value + ", " + agent + "}";
		} else {
		 return "{CGOAL: " + goal + ", " + value + ", " + agent + "}";	
		}
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
}
