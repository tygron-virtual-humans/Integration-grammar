/**
 * A class that represents a configuration of emotions by its rules. You have goals for the agents, 
 * relations between them and beliefs about how bad or good some events are for the goals.
 */

package languageTools.parser.relationParser;

import java.util.ArrayList;
public class EmotionConfig {

  ArrayList<GamBelief> beliefs;
  ArrayList<GamGoal> goals;
  ArrayList<GamRelation> relations;

/** 
 * Constructor for the configuration of the emotions.
 * @param beliefs - the believes that are present about goals
 * @param goals - the specified goals for the agents
 * @param relations - the relations between the agents
 */
  public EmotionConfig(ArrayList<GamBelief> beliefs,ArrayList<GamGoal> goals,
      ArrayList<GamRelation> relations ) {
    this.beliefs = beliefs;
    this.goals = goals;
    this.relations = relations;
  }
  
  /**
   * Override equals method.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof EmotionConfig) {
      EmotionConfig other = (EmotionConfig) object;
      Boolean value = true;
      
      if (!this.beliefs.equals(other.beliefs)) {
        value = false;
      }
      if (!this.goals.equals(other.goals)) {
        value = false;
      }
      if (!this.relations.equals(other.relations)) {
        value = false;
      }
      return value;
    } else {
      return false;
    }
  }
  
  /**
   * toString method.
   */
  public String toString() {
	  String bel = beliefs.toString();
	  String goal = goals.toString();
	  String rel = relations.toString();
	  return "{Config: " + bel + ", " + goal + ", " + rel + "}";
  }
}
