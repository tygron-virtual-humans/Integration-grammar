/**
 * Class that represents a Gamygdala belief rule and its values.
 */
package languageTools.parser.relationParser;

import languageTools.exceptions.relationParser.InvalidGamBeliefException;

public class GamBelief {
	String beliefName;

	double likelihood;
	
	String affected;
	double congruence;
	boolean isIncremental;
	
	/**
	 * Constructor of a GAM belief
	 * @param likelihood - likelihood that this belief is true.
	 * @param causal - agent who caused this event
	 * @param affected - goals that are affected by the agent
	 * @param congruence - how important
	 * @param isIncremental - optional
	 * @throws InvalidGamBeliefException
	 */
	public GamBelief(String beliefName, double likelihood, String affected, double congruence, Boolean isIncremental) throws InvalidGamBeliefException{
		
		//check that it is between given boundaries -1 and 1
		if(likelihood < -1 || likelihood > 1) {
			throw new InvalidGamBeliefException("Likelihood of belief is not in the [-1, 1] range");
		}
		
		//same check
		if(congruence < -1 || congruence > 1) {
			throw new InvalidGamBeliefException("Congruence of belief is not in the [-1, 1] range");
		}
		this.beliefName = beliefName;
		this.likelihood = likelihood;
		this.affected = affected;
		this.congruence = congruence;
		this.isIncremental = isIncremental;
	}
	
	
	/**
	 * Equals method
	 */
	public boolean equals(Object object) {
		  if(object instanceof GamBelief) {
			  GamBelief other = (GamBelief) object;
			  Boolean value = true;
			  value = value && this.beliefName.equals(other.beliefName);
			  value = value && this.likelihood == other.likelihood;
			  value = value && this.congruence == other.congruence;
			  value = value && this.isIncremental == other.isIncremental;
			  value = value && this.affected.equals(other.affected);
			  return value;
		  } else {
			  return false;
		  }
	  }
	
	/**
	 * toString method
	 */
	public String toString() {
		return "{SUB: " + beliefName + ", " + likelihood + ", " + affected + ", "  + congruence +   ", " + isIncremental +"}";
	}
	
	/**
	 * returns the name of the belief
	 * @return name of belief
	 */
	public String getBeliefName() {
		return beliefName;
	}

	/**
	 * sets the belief name
	 * @param beliefName new belief name
	 */
	public void setBeliefName(String beliefName) {
		this.beliefName = beliefName;
	}
	
	public double getLikelihood() {
		return likelihood;
	}


	public void setLikelihood(double likelihood) {
		this.likelihood = likelihood;
	}


	public String getAffected() {
		return affected;
	}


	public void setAffected(String affected) {
		this.affected = affected;
	}


	public double getCongruence() {
		return congruence;
	}


	public void setCongruence(double congruence) {
		this.congruence = congruence;
	}


	public boolean isIncremental() {
		return isIncremental;
	}


	public void setIncremental(boolean isIncremental) {
		this.isIncremental = isIncremental;
	}


}
