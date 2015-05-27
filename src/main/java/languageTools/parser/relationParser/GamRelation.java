/**
 * Class the represents a relation in Gamygdala involving its agents and value.
 */
package languageTools.parser.relationParser;

import languageTools.exceptions.relationParser.InvalidGamRelationException;

public class GamRelation {
	
	String agent1;

	String agent2;
	double value;

	/**
	 * Constructor
	 * @param agent1 - the first agent in the relation
	 * @param agent2 - the second agent in the relation
	 * @param value - value representing how good or bad their relation is. (-1 is bad, 1 is good)
	 * @throws InvalidGamRelationException
	 */
	public GamRelation(String agent1, String agent2, double value) throws InvalidGamRelationException{
		//check that value is between its boundaries -1 and 1.
		if(value > 1 || value < -1) {
			 throw new InvalidGamRelationException("Value of the relation is not in the [-1, 1] range");
			}
		this.agent1=agent1;
		this.agent2=agent2;
		this.value = value;
	}
	
	/**
	 * Equals method
	 */
	public boolean equals(Object object) {
		  if(object instanceof GamRelation) {
			  GamRelation other = (GamRelation) object;
			  Boolean res = true;
			  res = res && this.value == other.value;
			  res = res && this.agent1.equals(other.agent1);
			  res = res && this.agent2.equals(other.agent2);
			  return res;
		  } else {
			  return false;
		  }
	  }
	
	/**
	 * toString method
	 */
	public String toString() {
		return "{REL: " + agent1 + ", " + agent2 + ", " + value + "}"; 
	}
	
	public String getAgent1() {
		return agent1;
	}

	public void setAgent1(String agent1) {
		this.agent1 = agent1;
	}

	public String getAgent2() {
		return agent2;
	}

	public void setAgent2(String agent2) {
		this.agent2 = agent2;
	}
	
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}
