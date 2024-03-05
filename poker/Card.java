package poker;

/**
 * Represents a standard playing card. Holds information about the card's suit and rank.
 */
public class Card {
	private int indexNumber;
	private String suit;
	//Index from one to 52. Created in order to be able to track known cards for deriving odds.
	private int oft;
	
	public Card(int x) {
		if (x < 1 || x > 52) {
			throw new IllegalArgumentException("Constructor Card(int x): Enter a valid integer 1 <= x <= 52.");
		} else {
			oft = x;
			indexNumber = (x - 1) % 13;
			int suitRange = (x - 1) / 13;
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
	public Card(String s) {
		String x = s.substring(0,s.indexOf(" "));
		String r = s.substring(s.indexOf("of ") + 3);
		if (x.equalsIgnoreCase("Ace")) {
			indexNumber = 12;
		} else if (x.equalsIgnoreCase("King")) {
			indexNumber = 11;
		} else if (x.equalsIgnoreCase("Queen")) {
			indexNumber = 10;
		} else if (x.equalsIgnoreCase("Jack")) {
			indexNumber = 9;
		}
		else {
			try {
				int shownCardNumber = Integer.parseInt(x);
				if (shownCardNumber < 2 || shownCardNumber > 10) {
					throw new IllegalArgumentException("Enter valid card in this format: 'Ace of Spades' or '10 of Clubs'.");
				}
				indexNumber = shownCardNumber - 2;
			} catch(NumberFormatException ex) {
				System.err.print("Enter valid card in this format: 'Ace of Spades' or '10 of Clubs'.");
			}
			
		}
		if (r.equalsIgnoreCase("Clubs")) {
			suit = "Clubs";
			oft = indexNumber;
		} else if (r.equalsIgnoreCase("Diamonds")) {
			suit = "Diamonds";
			oft = 13 + indexNumber;
		} else if (r.equalsIgnoreCase("Hearts")) {
			suit = "Hearts";
			oft = 26 + indexNumber;
		} else if (r.equalsIgnoreCase("Spades")) {
			suit = "Spades";
			oft = 39 + indexNumber;
		} else {
			throw new IllegalArgumentException("Enter valid card in this format: 'Ace of Spades' or '10 of Clubs'.");
		}
		
	}
	public Card() {
		indexNumber = -1;
		suit = null;
	}
	public String getSuit() {
		return suit;
	}
	public int getIndexNumber() {
		return indexNumber;
	}
	public String getShownNumber() {
		return indexToShown(indexNumber);
	}
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
	public String toString() {
		String shownCardNumber = indexToShown(indexNumber);
		return shownCardNumber + " of " + suit;
	}
	public int getoft() {
		return oft;
	}
	public static void main(String[] args) {
		Card a = new Card("Ace of spades");
		System.out.println(a.getIndexNumber());
	}
}
