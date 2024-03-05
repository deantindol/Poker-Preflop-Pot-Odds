package pokerpotodds;

import java.util.ArrayList;
public class checkResult{
	private ArrayList<Card> bestFive;
	private classification handClassification;
		
	public checkResult(ArrayList<Card> five, classification c) {
		bestFive = five;
		handClassification = c;
	}
	public ArrayList<Card> getBestFive() {
		return bestFive;
	}
	public classification getHandClassification() {
		return handClassification;
	}
	public String toString() {
		String build = handClassification+":\n";
		for (Card x : bestFive) {
			build += x.toString()+"\n";
		}
		return build;
	}
	public checkResult() {
		bestFive = null;
		handClassification = null;
		
	}
    enum classification{
    	HIGHCARD,
    	PAIR,
    	TWOPAIR,
    	TRIPS,
    	STRAIGHT,
    	FLUSH,
    	FULLHOUSE,
    	QUADS,
    	STRAIGHTFLUSH,
    	ROYALFLUSH;
    }
    	
    
}
