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
        if (inputTwo.equalsIgnoreCase(inputOne)) {
            s.close();
            throw new IllegalArgumentException("Card cannot be the same as a previous card.");
        }
        Card two = new Card(inputTwo);

        System.out.print("Enter player 2 Card 1: ");
        String inputThree = s.nextLine();
        if (inputThree.equalsIgnoreCase(inputOne) ||
            inputThree.equalsIgnoreCase(inputTwo)) {
            s.close();
            throw new IllegalArgumentException("Card cannot be the same as a previous card.");
        }
        Card three = new Card(inputThree);

        System.out.print("Enter player 2 Card 2: ");
        String inputFour = s.nextLine();
        if (inputFour.equalsIgnoreCase(inputOne) ||
            inputFour.equalsIgnoreCase(inputTwo) ||
            inputFour.equals(inputThree)) {
            s.close();
            throw new IllegalArgumentException("Card cannot be the same as a previous card.");
        }
        Card four = new Card(inputFour);


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
        if (args.length > 0 && args[0].equalsIgnoreCase("-l")) {
            onevone(playerOneHand, playerTwoHand, true);
        } else {
            onevone(playerOneHand, playerTwoHand, false);
        }

        s.close();
    }
}
