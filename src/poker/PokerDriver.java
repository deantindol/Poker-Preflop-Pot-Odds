package poker;

import java.util.Scanner;

/**
 * Accepts user input for 4 cards, two for each player.
 * Resulting pre-flop odds are compyted and printed.
 */
public class PokerDriver extends HandOperations {

    public static void main(String[] args) {
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

        String oneFullName = one.toString();
        String twoFullName = two.toString();
        String threeFullName = three.toString();
        String fourFullName = four.toString();

        if (oneFullName.equals(twoFullName) || threeFullName.equals(oneFullName) ||
            threeFullName.equals(twoFullName) || fourFullName.equals(oneFullName) ||
            fourFullName.equals(threeFullName) || fourFullName.equals(twoFullName)) {
        	s.close();
            throw new IllegalArgumentException("No card can be the same as another");
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
        Card[] tab = new Card[0];
        if (args.length > 0 && args[0].equalsIgnoreCase("-l")) {
            onevone(playerOneHand, playerTwoHand, true, tab);
        } else {
            onevone(playerOneHand, playerTwoHand, false, tab);
        }

        s.close();
        System.out.println("Executed in: " + (System.currentTimeMillis() - startTime) + " ms");

    }
}
