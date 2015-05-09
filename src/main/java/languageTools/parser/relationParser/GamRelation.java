package languageTools.parser.relationParser;

import languageTools.exceptions.relationParser.InvalidGamRelationException;

public class GamRelation {
	
	String agent1;
	String agent2;
	double value;
	
	
	
	public GamRelation(String agent1, String agent2, double value) throws InvalidGamRelationException{
		if(value > 1 || value < -1) {
			 throw new InvalidGamRelationException("Value of the relation is not in the [-1, 1] range");
			}
		this.agent1=agent1;
		this.agent2=agent2;
		this.value = value;
	}
	
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

}
