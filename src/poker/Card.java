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
     * @param index the index of the card to be created from 1 to 52
     *. The index goes in the order of Clubs, Diamonds, Hearts, and Spades, from 2 to Ace.
     */
    public Card(int index) {
        if (index < 1 || index > 52) {
            throw new IllegalArgumentException("Enter a valid integer 1 <= x <= 52.");
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
     * @param s the name of the {@code Card} object to be created in the format
     * 'Ace of spades' or '10 of clubs'.
     */
    public Card(String s) {
        String throwMessage = "Enter valid card in this format:";
        throwMessage += " 'Ace of Spades', 'Ten of Diamonds', or 'Two of Clubs'";
        if (s.length() == 2) {
            String rankInputShort = s.substring(0,1);
            String suitInputShort = s.substring(1);
            if (rankInputShort.equalsIgnoreCase("A")) {
                indexNumber = 12;
            } else if (rankInputShort.equalsIgnoreCase("K")) {
                indexNumber = 11;
            } else if (rankInputShort.equalsIgnoreCase("Q")) {
                indexNumber = 10;
            } else if (rankInputShort.equalsIgnoreCase("J")) {
                indexNumber = 9;
            } else if (rankInputShort.equalsIgnoreCase("T")) {
                indexNumber = 8;
            } else {
                try {
                    int shownCardNumber = Integer.parseInt(rankInputShort);
                    if (shownCardNumber < 2 || shownCardNumber > 9) {
                        throw new IllegalArgumentException("Valid short card format: Tc, 9h, Ad");
                    }
                    indexNumber = shownCardNumber - 2;
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException(throwMessage);
                }

            }
            if (suitInputShort.equalsIgnoreCase("C")) {
                suit = "Clubs";
                oft = indexNumber + 1;
            } else if (suitInputShort.equalsIgnoreCase("D")) {
                suit = "Diamonds";
                oft = indexNumber + 14;
            } else if (suitInputShort.equalsIgnoreCase("H")) {
                suit = "Hearts";
                oft = indexNumber + 27;
            } else if (suitInputShort.equalsIgnoreCase("S")) {
                suit = "Spades";
                oft = indexNumber + 40;
            } else {
                throw new IllegalArgumentException("Valid short card format: Tc, 9h, Ad");
            }



        } else {
            try {
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
                } else if (rankInput.equalsIgnoreCase("Ten")) {
                    indexNumber = 8;
                } else if (rankInput.equalsIgnoreCase("nine")) {
                    indexNumber = 7;
                } else if (rankInput.equalsIgnoreCase("eight")) {
                    indexNumber = 6;
                } else if (rankInput.equalsIgnoreCase("seven")) {
                    indexNumber = 5;
                } else if (rankInput.equalsIgnoreCase("six")) {
                    indexNumber = 4;
                } else if (rankInput.equalsIgnoreCase("five")) {
                    indexNumber = 3;
                } else if (rankInput.equalsIgnoreCase("four")) {
                    indexNumber = 2;
                } else if (rankInput.equalsIgnoreCase("three")) {
                    indexNumber = 1;
                } else if (rankInput.equalsIgnoreCase("two")) {
                    indexNumber = 0;
                } else {
                    int shownCardNumber = Integer.parseInt(rankInput);
                    if (shownCardNumber < 2 || shownCardNumber > 10) {
                        throw new IllegalArgumentException(throwMessage);
                    }
                    indexNumber = shownCardNumber - 2;
                }
                if (suitInput.equalsIgnoreCase("Clubs")) {
                    suit = "Clubs";
                    oft = indexNumber + 1;
                } else if (suitInput.equalsIgnoreCase("Diamonds")) {
                    suit = "Diamonds";
                    oft = 14 + indexNumber;
                } else if (suitInput.equalsIgnoreCase("Hearts")) {
                    suit = "Hearts";
                    oft = 27 + indexNumber;
                } else if (suitInput.equalsIgnoreCase("Spades")) {
                    suit = "Spades";
                    oft = 40 + indexNumber;
                } else {
                    throw new IllegalArgumentException(throwMessage);
                }
            } catch (StringIndexOutOfBoundsException ex) {
                throw new IllegalArgumentException(throwMessage);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException(throwMessage);
            }
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
     * Returns a String in the format "rank of suit".
     * @return a String representation of the Card object.
     */
    @Override
    public String toString() {
        String shownCardNumber = indexToShown(indexNumber);
        return shownCardNumber + " of " + suit;
    } //toString

    /**
     * Returns the index of the Card from 1 to 52 similar to the index used in {@code Card(int x)}.
     * @return the index of the Card from 1 to 52.
     */
    public int getoft() {
        return oft;
    } //getoft
} //Card
