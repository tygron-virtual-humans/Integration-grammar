package languageTools.parser.relationParser;

public class GamBelief {
	
	double likelihood;
	String causal;
	String affected;
	double congruence;
	boolean isIncremental;
	
	
	public GamBelief(double likelihood,String causal, String affected, double congruence, String isIncremental){
		this.likelihood = likelihood;
		this.causal = causal;
		this.affected = affected;
		this.congruence = congruence;
		if(isIncremental.equals(null)){
		this.isIncremental = false;
		}
		else{
		this.isIncremental = true;
		}
	}

}
