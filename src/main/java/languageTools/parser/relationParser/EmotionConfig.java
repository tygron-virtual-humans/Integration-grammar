package languageTools.parser.relationParser;

import java.util.ArrayList;

public class EmotionConfig {
	
	ArrayList<GamBelief> believes;
	ArrayList<GamGoal> goals;
	ArrayList<GamRelation> relations;
	
	
	public EmotionConfig(ArrayList<GamBelief> believes,ArrayList<GamGoal> goals,ArrayList<GamRelation> relations ){
		this.believes = believes;
		this.goals = goals;
		this.relations = relations;
	}

}
