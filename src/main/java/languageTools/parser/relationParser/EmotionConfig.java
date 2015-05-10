package languageTools.parser.relationParser;

import java.util.ArrayList;

public class EmotionConfig {
	
	ArrayList<GamBelief> beliefs;
	ArrayList<GamGoal> goals;
	ArrayList<GamRelation> relations;
	
	/**
	 * 
	 * @param believes
	 * @param goals
	 * @param relations
	 */
  public EmotionConfig(ArrayList<GamBelief> beliefs,ArrayList<GamGoal> goals,ArrayList<GamRelation> relations ) {
		this.beliefs = beliefs;
		this.goals = goals;
		this.relations = relations;
	}
  
  public boolean equals(Object object) {
	  if(object instanceof EmotionConfig) {
		  EmotionConfig other = (EmotionConfig) object;
		  Boolean value = true;
		  if(!this.beliefs.equals(other.beliefs)) {
			  value = false;
		  }
		  if(!this.goals.equals(other.goals)) {
			  value = false;
		  }
		  if(!this.relations.equals(other.relations)) {
			  value = false;
		  }
		  return value;
	  } else {
		  return false;
	  }
  }
  
  public String toString() {
	  String bel = beliefs.toString();
	  String goal = goals.toString();
	  String rel = relations.toString();
	  return "{Config: " + bel + ", " + goal + ", " + rel + "}";
  }
}
