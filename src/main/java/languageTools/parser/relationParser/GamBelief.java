package languageTools.parser.relationParser;

import languageTools.exceptions.relationParser.InvalidGamBeliefException;

public class GamBelief {
	
	double likelihood;
	String causal;
	String affected;
	double congruence;
	boolean isIncremental;
	
	
	public GamBelief(double likelihood,String causal, String affected, double congruence, Boolean isIncremental) throws InvalidGamBeliefException{
		if(likelihood < -1 || likelihood > 1) {
			throw new InvalidGamBeliefException("Likelihood of belief is not in the [-1, 1] range");
		}
		if(congruence < -1 || congruence > 1) {
			throw new InvalidGamBeliefException("Congruence of belief is not in the [-1, 1] range");
		}
		this.likelihood = likelihood;
		this.causal = causal;
		this.affected = affected;
		this.congruence = congruence;
		this.isIncremental = isIncremental;
	}
	
	public boolean equals(Object object) {
		  if(object instanceof GamBelief) {
			  GamBelief other = (GamBelief) object;
			  Boolean value = true;
			  value = value && this.likelihood == other.likelihood;
			  value = value && this.congruence == other.congruence;
			  value = value && this.isIncremental == other.isIncremental;
			  value = value && this.affected.equals(other.affected);
			  value = value && this.causal.equals(other.causal);
			  return value;
		  } else {
			  return false;
		  }
	  }
}
