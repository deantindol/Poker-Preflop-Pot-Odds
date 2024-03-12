package poker;

import java.util.Scanner;

/**
 * Accepts user input for 4 cards, two for each player.
 * Resulting pre-flop odds are computed and printed.
 */
public class PokerDriver extends HandOperations {

    public static void main(String[] args) {
        boolean longOutput = false;
        Card[] tab = new Card[4];
        Scanner s = new Scanner(System.in);
        System.out.println("Use the format '10 of Clubs' or 'Ace of Spades'");


        System.out.print("Enter player 1 Card 1: ");
        String inputOne = s.nextLine();
        Card one = new Card(inputOne);

        System.out.print("Enter player 1 Card 2: ");
        String inputTwo = s.nextLine();
        Card two = new Card(inputTwo);

        System.out.print("Enter player 2 Card 1: ");
        String inputThree = s.nextLine();
        Card three = new Card(inputThree);

        System.out.print("Enter player 2 Card 2: ");
        String inputFour = s.nextLine();
        Card four = new Card(inputFour);
        
        
        
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-l")) {
                    longOutput = true;
                } else if (args[i].equals("-t") && args.length > i + 1){
                    if (args[i + 1].equals("3")) {
                        System.out.print("Enter flop card 1: ");
                        String inputFive = s.nextLine();
                        Card five = new Card(inputFive);
                        
                        System.out.print("Enter flop card 2: ");
                        String inputSix = s.nextLine();
                        Card six = new Card(inputSix);
                        
                        System.out.print("Enter flop card 3: ");
                        String inputSeven = s.nextLine();
                        Card seven = new Card(inputSeven);
                        
                        Card[] build = {five, six, seven};
                        tab = build;
                        
                        String[] cardNames = new String[7];
                        cardNames[0] = one.toString();
                        cardNames[1] = two.toString();
                        cardNames[2] = three.toString();
                        cardNames[3] = four.toString();
                        cardNames[4] = five.toString();
                        cardNames[5] = six.toString();
                        cardNames[6] = seven.toString();
                        
                        for (int j = 0; j < 7; j++) {
                            for (int k = j + 1; k <7; k++) {
                                if (cardNames[j].equals(cardNames[k])) {
                                    System.err.print("Cannot enter same card more than once.");
                                    System.exit(0);
                                }
                            }
                            
                        }
                       
                        System.out.print("Table : ");
                        for (int j = 0; j < tab.length; j++) {
                            System.out.print(tab[j]);
                            if (j != tab.length - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println();
                    } else if (args[i + 1].equals("4")) {
                        System.out.print("Enter flop card 1: ");
                        String inputFive = s.nextLine();
                        Card five = new Card(inputFive);
                    
                        System.out.print("Enter flop card 2: ");
                        String inputSix = s.nextLine();
                        Card six = new Card(inputSix);
                
                        System.out.print("Enter flop card 3: ");
                        String inputSeven = s.nextLine();
                        Card seven = new Card(inputSeven);
                        
                        System.out.print("Enter turn card: ");
                        String inputEight = s.nextLine();
                        Card eight = new Card(inputEight);
                        
                        tab[0] = five;
                        tab[1] = six;
                        tab[2] = seven;
                        tab[3] = eight;
                        
                        String[] cardNames = new String[8];
                        cardNames[0] = one.toString();
                        cardNames[1] = two.toString();
                        cardNames[2] = three.toString();
                        cardNames[3] = four.toString();
                        cardNames[4] = five.toString();
                        cardNames[5] = six.toString();
                        cardNames[6] = seven.toString();
                        cardNames[7] = eight.toString();
                        
                        for (int j = 0; j < 8; j++) {
                            for (int k = j + 1; k < 8; k++) {
                                if (cardNames[j].equals(cardNames[k])) {
                                    System.err.print("Cannot enter same card more than once.");
                                    System.exit(0);
                                }
                            }
                            
                        }
                        
                        System.out.print("Table : ");
                        for (int j = 0; j < tab.length; j++) {
                            System.out.print(tab[j]);
                            if (j != tab.length - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println();
                    }
                }
            }
            
        }

        
        
        final long startTime = System.currentTimeMillis();


        Card[] playerOneHand = {one, two};
        if (one.getIndexNumber() < two.getIndexNumber()) {
            playerOneHand[0] = two;
            playerOneHand[1] = one;
        }
        Card[] playerTwoHand = {three, four};
        if (three.getIndexNumber() < four.getIndexNumber()) {
            playerTwoHand[0] = four;
            playerTwoHand[1] = three;
        }
        
        if (tab[0] == null) {
            tab = new Card[0];
        }
        onevone(playerOneHand, playerTwoHand, longOutput, tab);

        s.close();
        System.out.println("Executed in: " + (System.currentTimeMillis() - startTime) + " ms");
        }
    
}
