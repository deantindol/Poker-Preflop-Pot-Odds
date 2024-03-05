package pokerpotodds;

import java.util.*;
import pokerpotodds.checkResult.classification;

public class PokahDriver extends Game {
	public static int whoWins(Card[] player1, Card[] player2, Card[] table) {
		if (player1.length != 2 || player2.length != 2 || table.length != 5) {
			throw new IllegalArgumentException("Enter valid Card[] lengths: 2 for each player, 5 for table cards");
		}
		Card[] player1Full = new Card[7];
		Card[] player2Full = new Card[7];
		player1Full[0] = player1[0];
		player1Full[1] = player1[1];
		player2Full[0] = player2[0];
		player2Full[1] = player2[1];
		for (int i = 2; i < 7; i ++) {
			player1Full[i] = table[i - 2];
			player2Full[i] = table[i - 2];
		}
		
		int player1HandRank = -1;
		int player2HandRank = -1;
		ArrayList<Card> player1BestFive = new ArrayList<Card>();
		ArrayList<Card> player2BestFive = new ArrayList<Card>();
		
		
		

		if (royalFlush(player1Full).getHandClassification()==classification.ROYALFLUSH) {
			player1HandRank = 10;
			player1BestFive = royalFlush(player1Full).getBestFive();
		} else if (straightFlush(player1Full).getHandClassification()==classification.STRAIGHTFLUSH) {
			player1HandRank = 9;
			player1BestFive = straightFlush(player1Full).getBestFive();
		} else if (quads(player1Full).getHandClassification()==classification.QUADS) {
			player1HandRank = 8;
			player1BestFive = quads(player1Full).getBestFive();
		} else if (fullHouse(player1Full).getHandClassification()==classification.FULLHOUSE) {
			player1HandRank = 7;
			player1BestFive = fullHouse(player1Full).getBestFive();
		} else if (flush(player1Full).getHandClassification()==classification.FLUSH) {
			player1HandRank = 6;
			player1BestFive = flush(player1Full).getBestFive();
		} else if (straight(player1Full).getHandClassification()==classification.STRAIGHT) {
			player1HandRank = 5;
			player1BestFive = straight(player1Full).getBestFive();
		} else if (trips(player1Full).getHandClassification()==classification.TRIPS) {
			player1HandRank = 4;
			player1BestFive = trips(player1Full).getBestFive();
		} else if (twoPair(player1Full).getHandClassification()==classification.TWOPAIR) {
			player1HandRank = 3;
			player1BestFive = twoPair(player1Full).getBestFive();
		} else if (pair(player1Full).getHandClassification()==classification.PAIR) {
			player1HandRank = 2;
			player1BestFive = pair(player1Full).getBestFive();
		} else if (highCard(player1Full).getHandClassification()==classification.HIGHCARD) {
			player1HandRank = 1;
			player1BestFive = highCard(player1Full).getBestFive();
		}
		if (royalFlush(player2Full).getHandClassification()==classification.ROYALFLUSH) {
			player2HandRank = 10;
			player2BestFive = royalFlush(player2Full).getBestFive();
		} else if (straightFlush(player2Full).getHandClassification()==classification.STRAIGHTFLUSH) {
			player2HandRank = 9;
			player2BestFive = straightFlush(player2Full).getBestFive();
		} else if (quads(player2Full).getHandClassification()==classification.QUADS) {
			player2HandRank = 8;
			player2BestFive = quads(player2Full).getBestFive();
		} else if (fullHouse(player2Full).getHandClassification()==classification.FULLHOUSE) {
			player2HandRank = 7;
			player2BestFive = fullHouse(player2Full).getBestFive();
		} else if (flush(player2Full).getHandClassification()==classification.FLUSH) {
			player2HandRank = 6;
			player2BestFive = flush(player2Full).getBestFive();
		} else if (straight(player2Full).getHandClassification()==classification.STRAIGHT) {
			player2HandRank = 5;
			player2BestFive = straight(player2Full).getBestFive();
		} else if (trips(player2Full).getHandClassification()==classification.TRIPS) {
			player2HandRank = 4;
			player2BestFive = trips(player2Full).getBestFive();
		} else if (twoPair(player2Full).getHandClassification()==classification.TWOPAIR) {
			player2HandRank = 3;
			player2BestFive = twoPair(player2Full).getBestFive();
		} else if (pair(player2Full).getHandClassification()==classification.PAIR) {
			player2HandRank = 2;
			player2BestFive = pair(player2Full).getBestFive();
		} else if (highCard(player2Full).getHandClassification()==classification.HIGHCARD) {
			player2HandRank = 1;
			player2BestFive = highCard(player2Full).getBestFive();
		}
		
		/** REATTACH TO ELSE SWITCH ONCE DEBUGGING IS DONE
		if (player1HandRank > player2HandRank) {
			System.out.println("Player 1 wins!");
			return 1;
		} else if (player2HandRank > player1HandRank) {
			System.out.println("Player 2 wins");
			return 2;
		}
		*/
		if (player1HandRank > player2HandRank) {
			switch(player1HandRank) {
				case 10:
					return 101;
				case 9:
					return 91;
				case 8:
					return 81;
				case 7: 
					return 71;
				case 6: 
					return 61;
				case 5:
					return 51;
				case 4:
					return 41;
				case 3: 
					return 31;
				case 2:
					return 21;
				case 1: 
					return 11;
				
			}
				
		} else if (player2HandRank>player1HandRank) {
			switch(player2HandRank) {
			case 10:
				return 102;
			case 9:
				return 92;
			case 8:
				return 82;
			case 7: 
				return 72;
			case 6: 
				return 62;
			case 5:
				return 52;
			case 4:
				return 42;
			case 3: 
				return 32;
			case 2:
				return 22;
			case 1: 
				return 12;
			}
		}
		
		if (false) {
			System.out.println();
		}
		else {
			switch(player1HandRank) {
				case 9: 
					int p1 = player1BestFive.get(4).getIndexNumber();
					int p2 = player2BestFive.get(4).getIndexNumber();
					if (p1 > p2) {
						System.out.println("Player 1 wins with a higher straight flush!");
						return 91;
					} else if (p2 > p1) {
						System.out.println("Player 2 wins with a higher straight flush!");
						return 92;
					} else if (p1 == p2){
						System.out.println("Push: Tied for same straight flush");
						return 3;
					}
				case 10:
					System.out.println("Push: Royal Flush on the table");
					return 3;
				case 8:
					int quadsp1 = player1BestFive.get(0).getIndexNumber();
					int quadsp2 = player2BestFive.get(0).getIndexNumber();
					if (quadsp1 > quadsp2) {
						return 81;
					} else if (quadsp2 > quadsp1) {
						return 82;
					}
					
					int fifth1 = player1BestFive.get(4).getIndexNumber();
					int fifth2 = player2BestFive.get(4).getIndexNumber();
					if (fifth1 > fifth2) {
						System.out.println("Player 1 wins with quads kicker!");
						return 1;
					} else if (fifth2 > fifth1) {
						System.out.println("Player 2 wins with quads kicker!");
						return 2;	
					} else if (fifth1 == fifth2){
						System.out.println("PUSH: Tied for same quads + kicker");
						return 3;
					}

				case 7:
					int p13 = player1BestFive.get(0).getIndexNumber();
					int p12 = player1BestFive.get(3).getIndexNumber();
					int p23 = player2BestFive.get(0).getIndexNumber();
					int p22 = player2BestFive.get(3).getIndexNumber();
					if (p13 > p23) {
						System.out.println("Player 1 wins with better full house");
						return 71;
					} else if (p23 > p13) {
						System.out.println("Player 2 wins with better full house");
						return 72;
					} else if (p12 > p22) {
						System.out.println("Player 1 wins with better full house");
						return 71;
					} else if (p22 > p12) {
						System.out.println("Player 2 wins with better full house");
						return 72;
					} else if (p13 == p23 && p12 == p22){
						System.out.println("Push: Tied for same full house.");
						return 3;
					}
				case 6:
					if (player1BestFive.get(0).getIndexNumber() > player2BestFive.get(0).getIndexNumber()) {
						System.out.println("Player 1 wins with a better flush");
						return 61;
					} else if (player2BestFive.get(0).getIndexNumber() > player1BestFive.get(0).getIndexNumber()) {
						System.out.println("Player 2 wins with a better flush");
						return 62;
					} else if (player1BestFive.get(1).getIndexNumber() > player2BestFive.get(1).getIndexNumber()) {
						System.out.println("Player 1 wins with a better flush");
						return 61;
					} else if (player2BestFive.get(1).getIndexNumber() > player1BestFive.get(1).getIndexNumber()) {
						System.out.println("Player 2 wins with a better flush");
						return 62;
					} else if (player1BestFive.get(2).getIndexNumber() > player2BestFive.get(2).getIndexNumber()) {
						System.out.println("Player 1 wins with a better flush");
						return 61;
					} else if (player2BestFive.get(2).getIndexNumber() > player1BestFive.get(2).getIndexNumber()) {
						System.out.println("Player 2 wins with a better flush");
						return 62;
					} else if (player1BestFive.get(3).getIndexNumber() > player2BestFive.get(3).getIndexNumber()) {
						System.out.println("Player 1 wins with a better flush");
						return 61;
					} else if (player2BestFive.get(3).getIndexNumber() > player1BestFive.get(3).getIndexNumber()) {
						System.out.println("Player 2 wins with a better flush");
						return 62;
					} else if (player1BestFive.get(4).getIndexNumber() > player2BestFive.get(4).getIndexNumber()) {
						System.out.println("Player 1 wins with a better flush");
						return 61;
					} else if (player2BestFive.get(4).getIndexNumber() > player1BestFive.get(4).getIndexNumber()) {
						System.out.println("Player 2 wins with a better flush");
						return 62;
					} else {
						System.out.println("PUSH: tied for the same flush");
						return 3;
					}

				case 5: 
					if (player1BestFive.get(0).getIndexNumber() > player2BestFive.get(0).getIndexNumber()) {
						System.out.println("Player 1 wins with better straight");
						return 51;
					} else if (player2BestFive.get(0).getIndexNumber() > player1BestFive.get(0).getIndexNumber()) {
						System.out.println("Player 2 wins with better straight");
						return 52;
					} else {
						System.out.println("PUSH: tied for same straight");
						return 3;
					}
				case 4:
					if (player1BestFive.get(0).getIndexNumber() > player2BestFive.get(0).getIndexNumber()) {
						System.out.println("Player 1 wins with better trips");
						return 41;
					} else if (player1BestFive.get(0).getIndexNumber() < player2BestFive.get(0).getIndexNumber()) {
						System.out.println("Player 2 wins with better trips");
						return 42;
					} else if (player1BestFive.get(3).getIndexNumber() > player2BestFive.get(3).getIndexNumber()) {
						System.out.println("Player 1 wins with better trips kicker");
						return 41;
					} else if (player1BestFive.get(3).getIndexNumber() < player2BestFive.get(3).getIndexNumber()) {
						System.out.println("Player 2 wins with better trips kicker");
						return 42;
					} else if (player1BestFive.get(4).getIndexNumber() > player2BestFive.get(4).getIndexNumber()) {
						System.out.println("Player 1 wins with better trips kicker");
						return 41;
					} else if (player1BestFive.get(4).getIndexNumber() < player2BestFive.get(4).getIndexNumber()) {
						System.out.println("Player 2 wins with better trips kicker");
						return 42;
					} else {
						System.out.println("PUSH: tied for same trips and kicker");
						return 3;
					}
				case 3:
					if (player1BestFive.get(0).getIndexNumber() > player2BestFive.get(0).getIndexNumber()) {
						System.out.println("Player 1 wins with better two pair");
						return 31;
					} else if (player1BestFive.get(0).getIndexNumber() < player2BestFive.get(0).getIndexNumber()) {
						System.out.println("Player 2 wins with better two pair");
						return 32;
					} else if (player1BestFive.get(2).getIndexNumber() > player2BestFive.get(2).getIndexNumber()) {
						System.out.println("Player 1 wins with better two pair");
						return 31;
					} else if (player1BestFive.get(2).getIndexNumber() < player2BestFive.get(2).getIndexNumber()) {
						System.out.println("Player 2 wins with better two pair");
						return 32;
					} else if (player1BestFive.get(4).getIndexNumber() > player2BestFive.get(4).getIndexNumber()) {
						System.out.println("Player 1 wins with better two pair kicker");
						return 31;
					} else if (player1BestFive.get(4).getIndexNumber() < player2BestFive.get(4).getIndexNumber()) {
						System.out.println("Player 2 wins with better two pair kicker");
						return 32;
					} else {
						System.out.println("PUSH: tied for same trips and kicker");
						return 3;
					}
				case 2:
					for (int i = 0; i < 5 ; i++) {
						if (player1BestFive.get(i).getIndexNumber() > player2BestFive.get(i).getIndexNumber()) {
							if (i == 0) {
								System.out.println("Player 1 wins with better pair");

							} else {
								System.out.println("Player 1 wins with better pair kicker");
							}
							return 21;
						} else if (player1BestFive.get(i).getIndexNumber() < player2BestFive.get(i).getIndexNumber()) {
							if (i == 0) {
								System.out.println("Player 2 wins with better pair");

							} else {
								System.out.println("Player 2 wins with better pair kicker");
							}
							return 22;
						}
					}
					System.out.println("PUSH: tied for same pair + kickers");
					return 3;
				case 1:
					for (int i = 0; i < 5; i++) {
						if (player1BestFive.get(i).getIndexNumber() > player2BestFive.get(i).getIndexNumber()) {
							System.out.println("Player 1 wins with high card");
							return 11;
						} else if (player1BestFive.get(i).getIndexNumber() < player2BestFive.get(i).getIndexNumber()) {
							System.out.println("Player 2 wins with high card");
							return 12;
						}
					}
					System.out.println("PUSH: tied for same high card + kickers");
					return 3;
			}
		}
		System.out.println(player1BestFive);
		System.out.println(player2BestFive);
		throw new IllegalArgumentException();
	}
	public static void main(String[] args) {
		Card a = new Card("10 of clubs");
		Card b = new Card("Jack of clubs");
		Card c = new Card("Queen of clubs");
		Card d = new Card("King of clubs");
		Card e = new Card("Ace of clubs");
		
		Card f = new Card("Jack of clubs");
		Card g = new Card("10 of clubs");
		
		Card h = new Card("7 of diamonds");
		Card i = new Card("7 of clubs");
		
		Card[] p1 = {f, g};
		Card[] p2 = {h, i};
		Card[] t = {a, b, c, d, e};
		Card[] p17 = {f, g, a, b, c, d, e};
		Card[] p27 = {h,i,a,b,c,d,e};
		whoWins(p1, p2, t);
		//System.out.println(royalFlush(p17).getHandClassification());
		System.out.println(royalFlush(p17).getBestFive());
		
	}
}
	
	

