package poker;

/**
 * Represents a standard playing card. Holds information about the card's suit and rank.
 */
public class Card {
	//Number that represents a Card's rank. Index 0 is a 2 and index 12 is an ace.
	private int indexNumber;
	private String suit;
	//Index from one to 52. Used to be able to track known cards for deriving odds.
	private int oft;
	
	/**
	 * Constructor for a {@code Card} object. Accepts integers from 1 to 52.
	 * @param index the index of the card to be created from 1 to 52. The index goes in the order of Clubs, Diamonds, Hearts, and Spades, from 2 to Ace.
	 */
	public Card(int index) {
		if (index < 1 || index > 52) {
			throw new IllegalArgumentException("Constructor Card(int x): Enter a valid integer 1 <= x <= 52.");
		} else {
			oft = index;
			indexNumber = (index - 1) % 13;
			int suitRange = (index - 1) / 13;
			switch (suitRange) {
			case 0 : 
				suit = "Clubs";
				break;	
			case 1 : 
				suit = "Diamonds";
				break;
			case 2 : 
				suit = "Hearts";
				break;
			case 3 : 
				suit = "Spades";
				break;
			
			}
		}
	}
	/**
	 * Constructor for a {@code Card} object given the name of the {@code Card} as a string.
	 * @param s the name of the {@code Card} object to be created in the format 'Ace of spades' or '10 of clubs' with no whitespace following the suit.
	 */
	public Card(String s) {
		String rankInput = s.substring(0,s.indexOf(" "));
		String suitInput = s.substring(s.indexOf("of ") + 3);
		if (rankInput.equalsIgnoreCase("Ace")) {
			indexNumber = 12;
		} else if (rankInput.equalsIgnoreCase("King")) {
			indexNumber = 11;
		} else if (rankInput.equalsIgnoreCase("Queen")) {
			indexNumber = 10;
		} else if (rankInput.equalsIgnoreCase("Jack")) {
			indexNumber = 9;
		}
		else {
			try {
				int shownCardNumber = Integer.parseInt(rankInput);
				if (shownCardNumber < 2 || shownCardNumber > 10) {
					throw new IllegalArgumentException("Enter valid card in this format: 'Ace of Spades' or '10 of Clubs'.");
				}
				indexNumber = shownCardNumber - 2;
			} catch (NumberFormatException ex) {
				System.err.print("Enter valid card in this format: 'Ace of Spades' or '10 of Clubs'.");
			}
			
		}
		if (suitInput.equalsIgnoreCase("Clubs")) {
			suit = "Clubs";
			oft = indexNumber;
		} else if (suitInput.equalsIgnoreCase("Diamonds")) {
			suit = "Diamonds";
			oft = 13 + indexNumber;
		} else if (suitInput.equalsIgnoreCase("Hearts")) {
			suit = "Hearts";
			oft = 26 + indexNumber;
		} else if (suitInput.equalsIgnoreCase("Spades")) {
			suit = "Spades";
			oft = 39 + indexNumber;
		} else {
			throw new IllegalArgumentException("Enter valid card in this format: 'Ace of Spades' or '10 of Clubs' with no white space following the suit.");
		}
		
	}
	
	/**
	 * Default constructor for a {@code Card} object.
	 */
	public Card() {
		indexNumber = -1;
		suit = null;
	}
	
	/**
	 * Returns the suit of the Card.
	 * @return the suit of the Card.
	 */
	public String getSuit() {
		return suit;
	}
	
	/**
	 * Returns the index number of the Card where index 0 is a 2 and index 12 is an Ace.
	 * @return the index number of the Card where index 0 is a 2 and index 12 is an Ace.
	 */
	public int getIndexNumber() {
		return indexNumber;
	}
	
	/**
	 * Returns the Card's rank as a String.
	 * @return the Card's rank as a String.
	 */
	public String getShownNumber() {
		return indexToShown(indexNumber);
	}
	
	/**
	 * Converts an index number to a String of a Card's rank.
	 * @param x the index number to be converted to a String of the equivalent rank.
	 * @return the String of the Card's rank.
	 */
	public String indexToShown(int x) {
		if (indexNumber < 9) {
			return Integer.toString(indexNumber + 2);
		} else {
			switch (indexNumber) {
			case 9:
				return "Jack";
			case 10:
				return "Queen";
			case 11:
				return "King";
			case 12:
				return "Ace";
			}
		}
		throw new IllegalArgumentException("indexToShown: error");
	}
	
	/**
	 * Returns a String representation of the Card object in the format 'Ace of Spades' and '7 of Diamonds'.
	 * @return a String representation of the Card object.
	 */
	@Override
	public String toString() {
		String shownCardNumber = indexToShown(indexNumber);
		return shownCardNumber + " of " + suit;
	}
	/**
	 * Returns the index of the Card from 1 to 52 similar to the index used in {@code Card(int x)}.
	 * @return the index of the Card from 1 to 52.
	 */
	public int getoft() {
		return oft;
	}
}
