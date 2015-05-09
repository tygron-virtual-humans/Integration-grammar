package languageTools.parser.relationParser;

public class GamGoal {
	
	double value;
	String goal;
	String agent;
	
	
	public GamGoal(String agent, String goal, double value){
		this.agent = agent;
		this.value = value;
		this.goal = goal;
	}
	
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
	
	public String toString() {
		 return "{GOAL: " + value + ", " + goal + ", " + agent + "}";
	}
}
