package poker;

import java.util.*;
import java.text.NumberFormat;

public class HandOperations {
    static Vector<Vector<Integer>> ans = new Vector<Vector<Integer>>();
    static Vector<Integer> tmp = new Vector<Integer>();

    static int p1Wins;
    static int p2Wins;
    static int pushes;
    static Card[] p1 ;
    static Card[] p2 ;

    //ArrayList of the indicies of the unknown cards from 1-52.
    //Does not contain the four cards held by players.
    static ArrayList<Integer> fe = new ArrayList<Integer>();
    static ArrayList<Card> possibleTableCards = new ArrayList<Card>();


    static int rf1 = 0;
    static int sf1 = 0;
    static int q1 = 0;
    static int fh1 = 0;
    static int f1 = 0;
    static int s1 = 0;
    static int t1 = 0;
    static int tp1 = 0;
    static int pair1 = 0;
    static int hc1 = 0;

    static int rf2 = 0;
    static int sf2 = 0;
    static int q2 = 0;
    static int fh2 = 0;
    static int f2 = 0;
    static int s2 = 0;
    static int t2 = 0;
    static int tp2 = 0;
    static int pair2 = 0;
    static int hc2 = 0;

    static Card one;
    static Card two;
    static Card three;
    static Card four;
    static Card five;



    public static int whoWins(Card[] player1, Card[] player2, Card[] table) {
        if (player1.length != 2 || player2.length != 2 || table.length != 5) {
            throw new IllegalArgumentException("Enter 3 Card arrays with lenghts 2, 2, and 5");
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

        
        checkResult p1Flush = flush(player1Full);
        checkResult p1Straight = straight(player1Full);
        
        if (p1Flush.getHandClassification()) {
        	if (p1Straight.getHandClassification()) {
        		checkResult p1StraightFlush = straightFlush(player1Full);
        		if (p1StraightFlush.getHandClassification()) {
        			if (p1StraightFlush.getBestFive().get(4).getIndexNumber() == 12) {
        				player1HandRank = 10;
        				player1BestFive = p1StraightFlush.getBestFive();
        			} else {
        				player1HandRank = 9;
        				player1BestFive = p1StraightFlush.getBestFive();
        			}
        		}
        	}
        }
        checkResult p1Pair = pair(player1Full);
        if (p1Pair.getHandClassification() && player1HandRank != 10 && player1HandRank != 9) {
        	checkResult p1Trips = trips(player1Full);
        	if (p1Trips.getHandClassification()) {
        		checkResult p1Quads = quads(player1Full);
        		if (p1Quads.getHandClassification()) {
        			player1HandRank = 8;
        			player1BestFive = quads(player1Full).getBestFive();
        		} else {
        			checkResult p1FullHouse = fullHouse(player1Full);
        			if (p1FullHouse.getHandClassification()) {
        				player1HandRank = 7;
        				player1BestFive = p1FullHouse.getBestFive();	
        			} else if (player1HandRank < 4){
        				player1HandRank = 4;
        				player1BestFive = p1Trips.getBestFive();
        			}
        		}
        		
        	} else {
        		checkResult p1TwoPair = twoPair(player1Full);
        		if (p1TwoPair.getHandClassification() && player1HandRank < 3) {
        			player1HandRank = 3;
        			player1BestFive = p1TwoPair.getBestFive();
        		} else if (player1HandRank < 2) {
        			player1HandRank = 2;
        			player1BestFive = p1Pair.getBestFive();
        		}
        	}
        }
        if (p1Flush.getHandClassification() && player1HandRank < 6) {
        	player1HandRank = 6;
        	player1BestFive = p1Flush.getBestFive();
        } else if (p1Straight.getHandClassification() && player1HandRank < 5) {
        	player1HandRank = 5;
        	player1BestFive = p1Straight.getBestFive();
        } else if (player1HandRank == -1) {
        	player1HandRank = 1;
        	player1BestFive = highCard(player1Full).getBestFive();
        }
        
        checkResult p2Flush = flush(player2Full);
        checkResult p2Straight = straight(player2Full);
        if (p2Flush.getHandClassification()) {
        	if (p2Straight.getHandClassification()) {
        		checkResult p2StraightFlush = straightFlush(player2Full);
        		if (p2StraightFlush.getHandClassification()) {
        			if (p2StraightFlush.getBestFive().get(4).getIndexNumber() == 12) {
        				player2HandRank = 10;
        				player2BestFive = p2StraightFlush.getBestFive();
        			} else {
        				player2HandRank = 9;
        				player2BestFive = p2StraightFlush.getBestFive();
        			}
        		}
        	}
        }
        checkResult p2Pair = pair(player2Full);
        if (p2Pair.getHandClassification() && player2HandRank != 10 && player2HandRank != 9) {
        	checkResult p2Trips = trips(player2Full);
        	if (p2Trips.getHandClassification()) {
        		checkResult p2Quads = quads(player2Full);
        		if (p2Quads.getHandClassification()) {
        			player2HandRank = 8;
        			player2BestFive = quads(player2Full).getBestFive();
        		} else {
        			checkResult p2FullHouse = fullHouse(player2Full);
        			if (p2FullHouse.getHandClassification()) {
        				player2HandRank = 7;
        				player2BestFive = p2FullHouse.getBestFive();	
        			} else if (player2HandRank < 4){
        				player2HandRank = 4;
        				player2BestFive = p2Trips.getBestFive();
        			}
        		}
        		
        	} else {
        		checkResult p2TwoPair = twoPair(player2Full);
        		if (p2TwoPair.getHandClassification() && player2HandRank < 3) {
        			player2HandRank = 3;
        			player2BestFive = p2TwoPair.getBestFive();
        		} else if (player2HandRank < 2) {
        			player2HandRank = 2;
        			player2BestFive = p2Pair.getBestFive();
        		}
        	}
        }
        if (p2Flush.getHandClassification() && player2HandRank < 6) {
        	player2HandRank = 6;
        	player2BestFive = p2Flush.getBestFive();
        } else if (p2Straight.getHandClassification() && player2HandRank < 5) {
        	player2HandRank = 5;
        	player2BestFive = p2Straight.getBestFive();
        } else if (player2HandRank == -1) {
        	player2HandRank = 1;
        	player2BestFive = highCard(player2Full).getBestFive();
        }
        
        if (player1HandRank > player2HandRank) {
            switch (player1HandRank) {
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
        } else if (player2HandRank > player1HandRank) {
            switch (player2HandRank) {
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
        //switch for same hand rank
        switch (player1HandRank) {
            //case straight flush
        case 9:
            int p1 = player1BestFive.get(4).getIndexNumber();
            int p2 = player2BestFive.get(4).getIndexNumber();
            if (p1 > p2) {
                return 91;
            } else if (p2 > p1) {
                return 92;
            } else if (p1 == p2) {
                return 3;
            }
        case 10:
            //case royal flush
            return 3;
        case 8:
            //case quads
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
                return 81;
            } else if (fifth2 > fifth1) {
                return 82;
            } else if (fifth1 == fifth2) {
                return 3;
            }
        case 7:
            //case full house
            int p13 = player1BestFive.get(0).getIndexNumber();
            int p12 = player1BestFive.get(3).getIndexNumber();
            int p23 = player2BestFive.get(0).getIndexNumber();
            int p22 = player2BestFive.get(3).getIndexNumber();
            if (p13 > p23) {
                return 71;
            } else if (p23 > p13) {
                return 72;
            } else if (p12 > p22) {
                return 71;
            } else if (p22 > p12) {
                return 72;
            } else if (p13 == p23 && p12 == p22) {
                return 3;
            }
            //case flush
        case 6:
            if (player1BestFive.get(0).getIndexNumber() > player2BestFive.get(0).getIndexNumber()) {
                return 61;
            } else if (player2BestFive.get(0).getIndexNumber() > player1BestFive.get(0).getIndexNumber()) {
                return 62;
            } else if (player1BestFive.get(1).getIndexNumber() > player2BestFive.get(1).getIndexNumber()) {
                return 61;
            } else if (player2BestFive.get(1).getIndexNumber() > player1BestFive.get(1).getIndexNumber()) {
                return 62;
            } else if (player1BestFive.get(2).getIndexNumber() > player2BestFive.get(2).getIndexNumber()) {
                return 61;
            } else if (player2BestFive.get(2).getIndexNumber() > player1BestFive.get(2).getIndexNumber()) {
                return 62;
            } else if (player1BestFive.get(3).getIndexNumber() > player2BestFive.get(3).getIndexNumber()) {
                return 61;
            } else if (player2BestFive.get(3).getIndexNumber() > player1BestFive.get(3).getIndexNumber()) {
                return 62;
            } else if (player1BestFive.get(4).getIndexNumber() > player2BestFive.get(4).getIndexNumber()) {
                return 61;
            } else if (player2BestFive.get(4).getIndexNumber() > player1BestFive.get(4).getIndexNumber()) {
                return 62;
            } else {
                return 3;
            }
        case 5:
            //case straight
            if (player1BestFive.get(4).getIndexNumber() > player2BestFive.get(4).getIndexNumber()) {
                return 51;
            } else if (player2BestFive.get(4).getIndexNumber() > player1BestFive.get(4).getIndexNumber()) {
                return 52;
            } else {
                return 3;
            }
        case 4:
            //case trips
            if (player1BestFive.get(0).getIndexNumber() > player2BestFive.get(0).getIndexNumber()) {
                return 41;
            } else if (player1BestFive.get(0).getIndexNumber() < player2BestFive.get(0).getIndexNumber()) {
                return 42;
            } else if (player1BestFive.get(3).getIndexNumber() > player2BestFive.get(3).getIndexNumber()) {
                return 41;
            } else if (player1BestFive.get(3).getIndexNumber() < player2BestFive.get(3).getIndexNumber()) {
                return 42;
            } else if (player1BestFive.get(4).getIndexNumber() > player2BestFive.get(4).getIndexNumber()) {
                return 41;
            } else if (player1BestFive.get(4).getIndexNumber() < player2BestFive.get(4).getIndexNumber()) {
                return 42;
            } else {
                return 3;
            }
        case 3:
            // case two pair
            if (player1BestFive.get(0).getIndexNumber() > player2BestFive.get(0).getIndexNumber()) {
                return 31;
            } else if (player1BestFive.get(0).getIndexNumber() < player2BestFive.get(0).getIndexNumber()) {
                return 32;
            } else if (player1BestFive.get(2).getIndexNumber() > player2BestFive.get(2).getIndexNumber()) {
                return 31;
            } else if (player1BestFive.get(2).getIndexNumber() < player2BestFive.get(2).getIndexNumber()) {
                return 32;
            } else if (player1BestFive.get(4).getIndexNumber() > player2BestFive.get(4).getIndexNumber()) {
                return 31;
            } else if (player1BestFive.get(4).getIndexNumber() < player2BestFive.get(4).getIndexNumber()) {
                return 32;
            } else {
                return 3;
            }
        case 2:
            // case pair
            for (int i = 0; i < 5 ; i++) {
                if (player1BestFive.get(i).getIndexNumber() > player2BestFive.get(i).getIndexNumber()) {
                    return 21;
                } else if (player1BestFive.get(i).getIndexNumber() < player2BestFive.get(i).getIndexNumber()) {
                    return 22;
                }
            }
            return 3;
        case 1:
            for (int i = 0; i < 5; i++) {
                if (player1BestFive.get(i).getIndexNumber() > player2BestFive.get(i).getIndexNumber()) {
                    return 11;
                } else if (player1BestFive.get(i).getIndexNumber() < player2BestFive.get(i).getIndexNumber()) {
                    return 12;
                }
            }
            return 3;
        }
        //This doesn't get called. Just there to satisfy return requirements.
        throw new RuntimeException("whoWins: error.");
    } //whoWins


   /**
    * N choose  K algo.
    * modified version of this algo that prints every n choose k combo
    * from https://www.geeksforgeeks.org/make-combinations-size-k/
    */
    static void makeCombiUtil(int n, int left, int k) {
        // Pushing this vector to a vector of vector
        if (k == 0) {
            ans.add(tmp);

            Card one = possibleTableCards.get(tmp.get(0) - 1);
            Card two = possibleTableCards.get(tmp.get(1) - 1);
            Card three = possibleTableCards.get(tmp.get(2) - 1);
            Card four = possibleTableCards.get(tmp.get(3) - 1);
            Card five = possibleTableCards.get(tmp.get(4) - 1);
            Card[] tab = {one, two, three, four, five};
            int res = whoWins(p1, p2, tab);
            if (res == 3) {
                pushes++;
            } else if (res % 2 == 1) {
                p1Wins++;
                if (res / 10 == 10) {
                    rf1++;
                } else if (res / 10 == 9) {
                    sf1++;
                } else if (res / 10 == 8) {
                    q1++;
                }  else if (res / 10 == 7) {
                    fh1++;
                }  else if (res / 10 == 6) {
                    f1++;
                }  else if (res / 10 == 5) {
                    s1++;
                }  else if (res / 10 == 4) {
                    t1++;
                }  else if (res / 10 == 3) {
                    tp1++;
                }  else if (res / 10 == 2) {
                    pair1++;
                }  else if (res / 10 == 1) {
                    hc1++;
                }
            } else if (res % 2 == 0) {
                p2Wins++;
                if (res / 10 == 10) {
                    rf2++;
                } else if (res / 10 == 9) {
                    sf2++;
                } else if (res / 10 == 8) {
                    q2++;
                }  else if (res / 10 == 7) {
                    fh2++;
                }  else if (res / 10 == 6) {
                    f2++;
                }  else if (res / 10 == 5) {
                    s2++;
                }  else if (res / 10 == 4) {
                    t2++;
                }  else if (res / 10 == 3) {
                    tp2++;
                }  else if (res / 10 == 2) {
                    pair2++;
                }  else if (res / 10 == 1) {
                    hc2++;
                }
            }
            return;
        }

        // i iterates from left to n. First time
        // left will be 1
        for (int i = left; i <= n; ++i) {
            tmp.add(i);
            makeCombiUtil(n, i + 1, k - 1);
            // Popping out last inserted element
            // from the vector
            tmp.remove(tmp.size() - 1);
        }
    }

    static Vector<Vector<Integer>> makeCombiN(int n, int k) {
        makeCombiUtil(n, 1, k);
        return ans;
    }

    public static void onevone (Card[] pl1, Card[] pl2, boolean longOutput) {
        p1 = pl1;
        p2 = pl2;
        for (int i = 1; i < 53; i++) {
            fe.add(i);
        }
        fe.remove(fe.indexOf(pl1[0].getoft()) );
        fe.remove(fe.indexOf(pl1[1].getoft()) );
        fe.remove(fe.indexOf(pl2[0].getoft()) );
        fe.remove(fe.indexOf(pl2[1].getoft()) );
        for (int x : fe) {
            possibleTableCards.add(new Card(x));
        }
        makeCombiN(48,5);
        System.out.print("Player one wins: ");
        System.out.printf("%.2f", ((double)p1Wins / 1712304) * 100);
        System.out.println("%");
        System.out.print("Player two wins: ");
        System.out.printf("%.2f", ((double)p2Wins / 1712304) * 100);
        System.out.println("%");
        System.out.print("Pushes: ");
        System.out.printf("%.2f", ((double)pushes / 1712304) * 100);
        System.out.println("%");

        if (longOutput) {
        	System.out.println("Player 1 winning hand breakdown: ");
            System.out.println("Royal Flush:\t"+ NumberFormat.getNumberInstance(Locale.US).format(rf1));
            System.out.println("Straight Flush:\t"+ NumberFormat.getNumberInstance(Locale.US).format(sf1));       
            System.out.println("Quads:\t\t"+ NumberFormat.getNumberInstance(Locale.US).format(q1));       
            System.out.println("Full House:\t"+ NumberFormat.getNumberInstance(Locale.US).format(fh1));       
            System.out.println("Flush:\t\t"+ NumberFormat.getNumberInstance(Locale.US).format(f1));       
            System.out.println("Straight:\t"+ NumberFormat.getNumberInstance(Locale.US).format(s1));       
            System.out.println("Trips:\t\t"+ NumberFormat.getNumberInstance(Locale.US).format(t1));   
            System.out.println("Two Pair:\t"+ NumberFormat.getNumberInstance(Locale.US).format(tp1));
            System.out.println("Pair:\t\t"+ NumberFormat.getNumberInstance(Locale.US).format(pair1)); 
            System.out.println("High Card:\t"+ NumberFormat.getNumberInstance(Locale.US).format(hc1));
            System.out.println();
            System.out.println("Player 2 winning hand breakdown: ");
            System.out.println("Royal Flush:\t"+ NumberFormat.getNumberInstance(Locale.US).format(rf2));
            System.out.println("Straight Flush:\t"+ NumberFormat.getNumberInstance(Locale.US).format(sf2));       
            System.out.println("Quads:\t\t"+ NumberFormat.getNumberInstance(Locale.US).format(q2));       
            System.out.println("Full House:\t"+ NumberFormat.getNumberInstance(Locale.US).format(fh2));       
            System.out.println("Flush:\t\t"+ NumberFormat.getNumberInstance(Locale.US).format(f2));       
            System.out.println("Straight:\t"+ NumberFormat.getNumberInstance(Locale.US).format(s2));       
            System.out.println("Trips:\t\t"+ NumberFormat.getNumberInstance(Locale.US).format(t2));   
            System.out.println("Two Pair:\t"+ NumberFormat.getNumberInstance(Locale.US).format(tp2));
            System.out.println("Pair:\t\t"+ NumberFormat.getNumberInstance(Locale.US).format(pair2)); 
            System.out.println("High Card:\t"+ NumberFormat.getNumberInstance(Locale.US).format(hc2));
            System.out.println();
            





            	
            
            

        }
        
    }


    /**
     *
     * @param seven the seven card array to be checked for a royal flush
     * @return checkResult the checkResult object containing the best five cards
     * and the classification of Royal Flush if there is a royal flush
     */
    public static checkResult royalFlush(Card[] seven) {
        //If the seven card hard is a straight flush
        if (straightFlush(seven).getHandClassification()) {
            // and if it is straight flush Ace high
            if (straightFlush(seven).getBestFive().get(4).getIndexNumber() == 12) {
                return new checkResult(straightFlush(seven).getBestFive(), true);
            }
        }
        return new checkResult();
    }

    /**
     * Checks to see if the Card[] contains a flush.
     * @param seven the Card[] that is checked to be a flush.
     * @return checkResult the checkResult object with best five card hand and hand classification.
     * classification is null if not a flush
     * @throws IllegalArgumentException if the Card[] is not length seven.
     */
    public static checkResult flush(Card[] seven) {
        if (seven.length != 7) {
            throw new IllegalArgumentException("The Card[] passed to this method must be length 7");
        }
        //counts of each suit in the seven card hand
        int spade = 0;
        int heart = 0;
        int diamond = 0;
        int club = 0;
        ArrayList<Card> b5 = new ArrayList<Card>();
        ArrayList<Card> suited = new ArrayList<Card>();
        for (int i = 0 ; i < 7 ; i++) {
            //tallying  the counts
            if (seven[i].getSuit().equals("Spades")) {
                spade++;
            } else if (seven[i].getSuit().equals("Diamonds")) {
                diamond++;
            } else if (seven[i].getSuit().equals("Clubs")) {
                club ++;
            } else if (seven[i].getSuit().equals("Hearts")) {
                heart++;
            }
        }
        // adding the suited cards to the list of suited cards
        if (spade > 4) {
            for (Card x : seven) {
                if (x.getSuit().equals("Spades")) {
                    suited.add(x);
                }
            }
        } else if (diamond > 4) {
            for (Card x : seven) {
                if (x.getSuit().equals("Diamonds")) {
                    suited.add(x);
                }
            }
        } else if (club > 4) {
            for (Card x : seven) {
                if (x.getSuit().equals("Clubs")) {
                    suited.add(x);
                }
            }
        } else if (heart > 4) {
            for (Card x : seven) {
                if (x.getSuit().equals("Hearts")) {
                    suited.add(x);
                }
            }
        }
        // if there are 5 suited cards
        if (suited.size() > 4) {
            int highest = -1;
            int index = 0;
            // finding the 5 highest suited cards for cases where there is a flush with 6 or 7 suited cards
            //outer loop loops five times to get five highest cards
            for (int i = 0 ; i < 5 ; i++) {
                //inner loop finds highest card not yet added to the list of the best 5
                for (int j = 0 ; j < suited.size() ; j++) {
                    if (suited.get(j).getIndexNumber() > highest) {
                        highest = suited.get(j).getIndexNumber();
                        index = j;
                    }
                }
                //adding the highest card to the list of the best 5
                b5.add(suited.get(index));
                // removing the highest card from the suited list so it doen't
                // go into the list of the highest 5 again.
                suited.remove(index);
                highest = -1;
            }
            // returns flush
            return new checkResult(b5, true);
        }
        // if there is no flush it returns null
        return new checkResult();
    }

    /**
     *
     * @param seven the seven card array to be checked for a straight
     * @return a checkResult object with the classification of straight and the best
     * five cards if there is a straight
     */
    public static checkResult straight(Card[] seven) {
        //copies the seven card array into an array list
        ArrayList<Card> copy = new ArrayList<Card>();
        for (Card x : seven) {
            copy.add(x);
        }
        //list of the card indicies 0-12 where 0 is a 2 and 12 is an ace
        ArrayList<Integer> indicies = new ArrayList<Integer>();
        for (Card x : copy) {
            indicies.add(x.getIndexNumber());
        }
        //declaring variables that might be needed later
        ArrayList<Card> build = new ArrayList<Card>();
        Card one = new Card(); Card two = new Card(); Card three = new Card(); Card four = new Card(); Card five = new Card();
        //loops through all cards
        for (int i = 0 ; i < seven.length; i++) {
            //i had another block here earlier and didnt want to unindenent it all :l
            if (true) {
                //index of the card currently being pointed to in the loop
                int f = indicies.get(i);
                // corner case for A-5 flush
                // if the list contains A-5
                if (f==0 && indicies.contains(12) && indicies.contains(1) && indicies.contains(2) && indicies.contains(3)) {
                    for (int k = 0 ; k < copy.size(); k++) {
                        // finds the A-5 and assigns them to Card one - five.
                        if (copy.get(k).getIndexNumber() == 12) {
                            one = copy.get(k);
                        } else if (copy.get(k).getIndexNumber() == 0) {
                            two = copy.get(k);
                        } else if (copy.get(k).getIndexNumber() == 1) {
                            three = copy.get(k);
                        } else if (copy.get(k).getIndexNumber() == 2) {
                            four = copy.get(k);
                        } else if (copy.get(k).getIndexNumber() == 3) {
                            five = copy.get(k);
                        }
                    }
                    //adding the A-5 to the array returned as the best five card hand
                    build.add(one); build.add(two); build.add(three); build.add(four); build.add(five);
                }
                //looking for all other straight combos.
                // if there is  a card one higher than the current card in loop
                // all the way through one four higher.
                if (indicies.contains(f + 1) && indicies.contains(f + 2) && indicies.contains(f + 3) && indicies.contains(f + 4)) {
                    //looping through to find these cards that make the straight
                    // and assigning them to Card one-five
                    for (int k = 0 ; k < copy.size(); k++) {
                        if (copy.get(k).getIndexNumber() == f) {
                            one = copy.get(k);
                        } else if (copy.get(k).getIndexNumber() == f + 1) {
                            two = copy.get(k);
                        } else if (copy.get(k).getIndexNumber() == f + 2) {
                            three = copy.get(k);
                        } else if (copy.get(k).getIndexNumber() == f + 3) {
                            four = copy.get(k);
                        } else if (copy.get(k).getIndexNumber() == f + 4) {
                            five = copy.get(k);
                        }
                    }
                    // if there is another straight and this one just found is better
                    if (build.size() > 0 && build.get(4).getIndexNumber() < five.getIndexNumber()) {
                        build.clear();
                        build.add(one); build.add(two); build.add(three); build.add(four); build.add(five);
                        // if there is not another straight already found
                    } else if (build.size() == 0) {
                        build.add(one); build.add(two); build.add(three); build.add(four); build.add(five);
                    }
                }
            }
        }
        // if there was a straight found
        if (build.size() > 0) {
            return new checkResult(build, true);
        }
        // else it returns null
        return new checkResult();
    }
    public static checkResult highCard(Card[] seven) {
        //copies seven card array to an array list
        ArrayList<Card> copy = new ArrayList<Card>();
        for (Card x : seven) {
            copy.add(x);
        }
        // orders the list from low to high based on rank
        copy = order(copy);
        ArrayList<Card> build = new ArrayList<Card>();

        //adds the five highest cards to a list
        build.add(copy.get(6));
        build.add(copy.get(5));
        build.add(copy.get(4));
        build.add(copy.get(3));
        build.add(copy.get(2));
        //returns highcard with the best 5 cards
        return new checkResult(build, true);
    }

    public static checkResult fullHouse(Card[] seven) {
        //variables to be set to the full house cards if they are found
        int index1 = -1;
        int index2 = -1;
        int index3 = -1;
        int index4 = -1;
        int index5 = -1;
        //new list for cards to be made into array list and list of best five cards
        ArrayList<Card> copy = new ArrayList<Card>();
        ArrayList<Card> build = new ArrayList<Card>();

        for (Card x : seven) {
            copy.add(x);
        }
        //orders list based on rank
        copy = order(copy);

        //looking to see if three cards in a row have the same rank
        for (int i = 0; i < 5 ; i++) {
            int i1 = copy.get(i).getIndexNumber();
            int i2 = copy.get(i + 1).getIndexNumber();
            int i3 = copy.get(i + 2).getIndexNumber();
            // if they do save these indicies.
            if (i1 == i2 && i2 == i3) {
                index1 = i;
                index2 = i + 1;
                index3 = i + 2;
            }
        }
        // if there are 3 cards with same rank add them to the build list to be returned later
        if (index1 != -1) {
            build.add(copy.get(index1));
            build.add(copy.get(index2));
            build.add(copy.get(index3));
            //removes the three so they can't also be counted as the two
            copy.remove(index3);
            copy.remove(index2);
            copy.remove(index1);
            // if there aren't three it returns null
        } else {
            return new checkResult();
        }
        //looking for two cards in same manor as the three
        for (int i = 0; i < 3 ; i++) {
            if (copy.get(i).getIndexNumber() == copy.get(i + 1).getIndexNumber()) {
                index4 = i;
                index5 = i + 1;
            }
        }
        //if two cards of same rank are found it adds them to the list of best five card hand
        if (index4 != -1) {
            build.add(copy.get(index4));
            build.add(copy.get(index5));
            //if there aren't it returns null
        } else {
            return new checkResult();
        }
        //returns fullhouse and the list of the cards in the fullhouse
        return new checkResult(build, true);
    }

    public static checkResult twoPair(Card[] seven) {
        //copies array into an array list
        ArrayList<Card> copy = new ArrayList<Card>();
        for (Card x : seven) {
            copy.add(x);
        }
        //list for best five card hand
        ArrayList<Card> build = new ArrayList<Card>();
        //varibles to store indicies of pairs in.
        int index1 = -1;
        int index2 = -1;
        int index3 = -1;
        int index4 = -1;
        int index5 = -1;
        int index6 = -1;
        //orders list
        copy = order(copy);
        //loops through the first 6 cards and checks if the one after it is of same rank
        for ( int i = 0 ; i < 6 ; i++) {
            //if card referenced by loop index has same rank as card after it
            if (copy.get(i).getIndexNumber() == copy.get(i + 1).getIndexNumber()) {
                // if this is the first pair found
                if (index1 == -1) {
                    //saves indicies of pair
                    index1 = i;
                    index2 = i + 1;
                    //increments loop index by one so it doesn't check the
                    //second card in the pair to save time
                    i++;
                    // if this is the second pair found
                } else if (index3 == -1) {
                    index3 = i;
                    index4 = i + 1;
                    i++;
                    // if this is the third
                } else {
                    index5 = i;
                    index6 = i + 1;
                    i++;
                }
            }
        }
        //if there are three pairs found adds the best two pairs to the return
        // list and removes them so they aren't considered for fifth card kicker
        if (index5 != -1) {
            build.add(copy.get(index6));
            build.add(copy.get(index5));
            build.add(copy.get(index4));
            build.add(copy.get(index3));
            copy.remove(index6);
            copy.remove(index5);
            copy.remove(index4);
            copy.remove(index3);

            //if only two pairs are found adds those and removes them
        } else if (index3 != -1) {
            build.add(copy.get(index4));
            build.add(copy.get(index3));
            build.add(copy.get(index2));
            build.add(copy.get(index1));
            copy.remove(index4);
            copy.remove(index3);
            copy.remove(index2);
            copy.remove(index1);
            //if one or no pair is found null is returned.
        } else {
            return new checkResult();
        }
        //adds highest remaining card
        build.add(copy.get(2));
        return new checkResult(build, true);
    }


    public static checkResult quads(Card[] seven) {
        //copies list into an array list
        ArrayList<Card> copy = new ArrayList<Card>();
        for (Card x : seven) {
            copy.add(x);
        }
        ArrayList<Card> build = new ArrayList<Card>();
        //variables to store the quads if they are foudn
        int index1 = -1;
        int index2 = -1;
        int index3 = -1;
        int index4 = -1;
        //orders list
        copy = order(copy);
        //loops through first 4 cards and sees if the three after them are of the same rank.
        for (int i = 0 ; i < 4 ; i++) {
            int i1 = copy.get(i).getIndexNumber();
            int i2 = copy.get(i + 1).getIndexNumber();
            int i3 = copy.get(i + 2).getIndexNumber();
            int i4 = copy.get(i + 3).getIndexNumber();
            if (i1 == i2 && i2 == i3 && i3 == i4) {
                index1 = i;
                index2 = i + 1;
                index3 = i + 2;
                index4 = i + 3;
            }
        }
        //if there are quads add them to list of best five and remove them from the rest
        // so they aren't considered for fifth card kicker
        if (index1 != -1) {
            build.add(copy.get(index1));
            build.add(copy.get(index2));
            build.add(copy.get(index3));
            build.add(copy.get(index4));

            copy.remove(index1);
            copy.remove(index1);
            copy.remove(index1);
            copy.remove(index1);
            //adds highest remaining card
            build.add(copy.get(2));
            return new checkResult(build, true);
        }
        // else returns null
        return new checkResult();
    }


    public static checkResult trips(Card[] seven) {
        // adds cards in array to an array list
        ArrayList<Card> copy = new ArrayList<Card>();
        for (Card x : seven) {
            copy.add(x);
        }
        ArrayList<Card> build = new ArrayList<Card>();
        //stores indicies of trips if found
        int index1 = -1;
        int index2 = -1;
        int index3 = -1;
        //orders list
        copy = order(copy);
        //loops through first five cards and checks if next two are of same rank
        for (int i = 0 ; i < 5 ; i++) {
            //if card at index has same rank as next two
            if (copy.get(i).getIndexNumber() == copy.get(i + 1).getIndexNumber() && copy.get(i + 1).getIndexNumber() == copy.get(i + 2).getIndexNumber()) {
                index1 = i;
                index2 = i + 1;
                index3 = i + 2;
            }
        }
        //if trips are found add the trips to the best five list and remove them from contention for kickership
        if (index1 != -1) {
            build.add(copy.get(index1));
            build.add(copy.get(index2));
            build.add(copy.get(index3));
            copy.remove(index3);
            copy.remove(index2);
            copy.remove(index1);
            //adds highest 2 cards remaining to best five card list
            build.add(copy.get(3));
            build.add(copy.get(2));
            return new checkResult(build, true);
        }
        // else returns null
        return new checkResult();
    }

    public static checkResult straightFlush(Card[] seven) {
        //copies array into an arrayList
        ArrayList<Card> copy = new ArrayList<Card>();
        for (Card x : seven) {
            copy.add(x);
        }
        //orders list
        copy = order(copy);
        //counts of each suit
        int d = getNumOfSuit("Diamonds", seven);
        int s = getNumOfSuit("Spades", seven);
        int c = getNumOfSuit("Clubs", seven);
        int h = getNumOfSuit("Hearts", seven);
        //for each suit if there are 5 or more, they are passed into straight method to find if there is a straight
        if (d > 4) {
            Card[] build = new Card[d];
            int index = 0;
            for (Card x : seven) {
                if (x.getSuit().equals("Diamonds")) {
                    build[index] = x;
                    index++;
                }
            }
            //if there is a straight with the suited cards, the cards that compose the straight are returned as best five with
            // the classification of straight flush
            if (straight(build).getHandClassification()) {
                return new checkResult(straight(build).getBestFive(), true);
            }
        }
        if (s > 4) {
            Card[] build = new Card[s];
            int index = 0;
            for (Card x : seven) {
                if (x.getSuit().equals("Spades")) {
                    build[index] = x;
                    index++;
                }
            }
            if (straight(build).getHandClassification()) {
                return new checkResult(straight(build).getBestFive(), true);
            }
        }
        if (c > 4) {
            Card[] build = new Card[c];
            int index = 0;
            for (Card x : seven) {
                if (x.getSuit().equals("Clubs")) {
                    build[index] = x;
                    index++;
                }
            }
            if (straight(build).getHandClassification()) {
                return new checkResult(straight(build).getBestFive(), true);
            }
        }
        if (h > 4) {
            Card[] build = new Card[h];
            int index = 0;
            for (Card x : seven) {
                if (x.getSuit().equals("Hearts")) {
                    build[index] = x;
                    index++;
                }
            }
            if (straight(build).getHandClassification()) {
                return new checkResult(straight(build).getBestFive(), true);
            }
        }
        //returns null if no straight flush is found
        return new checkResult();


    }




    public static checkResult pair(Card[] seven) {
        ArrayList<Card> copy = new ArrayList<Card>();
        for (Card x : seven) {
            copy.add(x);
        }
        ArrayList<Card> build = new ArrayList<Card>();
        int index1 = -1;
        int index2 = -1;
        copy = order(copy);
        for (int i = 0 ; i < 6 ; i++) {
            if (copy.get(i).getIndexNumber() == copy.get(i + 1).getIndexNumber()) {
                index1 = i;
                index2 = i + 1;
            }
        }
        if (index1 != -1) {
            build.add(copy.get(index1));
            build.add(copy.get(index2));
            copy.remove(index1);
            copy.remove(index1);
            build.add(copy.get(4));
            build.add(copy.get(3));
            build.add(copy.get(2));
            return new checkResult(build, true);
        }
        return new checkResult();
    }

    public static ArrayList<Card> order(ArrayList<Card> r) {
        ArrayList<Card> copy = r;
        int n = r.size();
        for (int i = 0 ; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (copy.get(j).getIndexNumber() < copy.get(min).getIndexNumber()) {
                    min = j;
                }
            }
            Card temp = copy.get(min);
            copy.set(min, copy.get(i));
            copy.set(i, temp);
        }
        return copy;

    }


    public static int getNumOfSuit(String suit, Card[] cards) {
        int count = 0;
        for (Card x : cards) {
            if (x.getSuit().equals(suit)) {
                count++;
            }
        }
        return count;
    }
} // HandOperations
