package poker;

import java.util.ArrayList;

/**
 * Represents the result of checking a 7 {@code Card} hand for a specific hand rank
 * i.e. flush, pair. If the 7 card hand can be classified as the specific hand rank
 * checked for, it will be stored with the classification enumeration. If this is the
 * case, the best five cards are stored in an ArrayList for the purpose of evaluating
 * which hand of equal ranks is better. If the hand cannot be classified
 * as the specific hand checked, both fields will be set to null.
 */
public class checkResult {
    private ArrayList<Card> bestFive;
    protected boolean isHand;

    /**
     * Constructor for a {@code checkResult} object.
     * @param five the ArrayList<{@code Card}> containing the best five cards.
     * @param c the classification enumeration.
     */
    public checkResult(ArrayList<Card> five, boolean b) {
        bestFive = five;
        isHand = b;
    }

    /**
     * Returns the best five cards in an ArrayList of cards.
     * @return the best five cards in an ArrayList of cards.
     */
    public ArrayList<Card> getBestFive() {
        return bestFive;
    }

    /**
     * Returns the classification of the hand rank check.
     * @return the classification of the hand rank check.
     */
    public boolean getHandClassification() {
        return isHand;
    }

    /**
     * Returns the String representation of the hand rank check.
     * Includes the hand rank and best five cards.
     * @return the String representation of the hand rank check.
     */
    public String toString() {
        String build = isHand + ":\n";
        for (Card x : bestFive) {
            build += x.toString() + "\n";
        }
        return build;
    }

    /**
     * Default constructor for a {@code checkResult} object.
     */
    public checkResult() {
        bestFive = null;
        isHand = false;
    }

    
}
