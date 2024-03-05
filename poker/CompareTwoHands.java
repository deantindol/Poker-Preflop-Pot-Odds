package poker;

import java.util.*;
import poker.checkResult.classification;

public class CompareTwoHands extends HandEvaluation {
	
	static Vector<Vector<Integer>> ans = new Vector<Vector<Integer>>();
    static Vector<Integer> tmp = new Vector<Integer>();
    
    static String pushTest ="";
    
    static int p1Wins;
    static int p2Wins;
    static int pushes;
    static Card[] p1 ;
    static Card[] p2 ;
    
    //ArrayList of the indicies of the unknown cards from 1-52.
    //Does not contain the four cards held by players.
    static ArrayList<Integer> fe = new ArrayList<Integer>();
    
    
    static int rf1 = 0;
    static int sf1 = 0;
    static int q1 = 0;
    static int fh1 = 0;
    static int f1 = 0;
    static int s1 = 0;
    static int t1 = 0;
    static int tp1 = 0;
    static int pair1 = 0;
    static int hc1 =0;
    
    static int rf2 = 0;
    static int sf2 = 0;
    static int q2 = 0;
    static int fh2 = 0;
    static int f2 = 0;
    static int s2 = 0;
    static int t2 = 0;
    static int tp2 = 0;
    static int pair2 = 0;
    static int hc2 =0;
    
    
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
					return 81;
				} else if (fifth2 > fifth1) {
					System.out.println("Player 2 wins with quads kicker!");
					return 82;	
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
		//This doesn't get called. Just there to satisfy return requirements.
		throw new RuntimeException("whoWins: error.");
	} //whoWins
	
	static void makeCombiUtil(int n, int left, int k)
    {
		
        // Pushing this vector to a vector of vector
        if (k == 0) {
            ans.add(tmp);
            
            Card one = new Card(fe.get(tmp.get(0) - 1));
            Card two = new Card(fe.get(tmp.get(1) - 1));
            Card three = new Card(fe.get(tmp.get(2) - 1));
            Card four = new Card(fe.get(tmp.get(3) - 1));
            Card five = new Card(fe.get(tmp.get(4) -1 ));
            
            Card[] tab = {one, two, three, four, five};
            
            int res = whoWins(p1, p2, tab);
            if (res == 3 && pushes > 1100 && pushes <=1600) {
        		pushTest += (tab[0]+"\t"+tab[1]+"\t"+tab[2]+"\t"+tab[3]+"\t"+tab[4]+"\n");

            }
            if (res == 3) {
            	pushes++;
            } else if (res % 2 == 1) {
            	p1Wins++;
            	if (res/10 == 10) {
                	rf1++;
                } else if (res/10 == 9) {
                	sf1++;
                } else if (res/10 == 8) {
                	q1++;
                }  else if (res/10 == 7) {
                	fh1++;
                }  else if (res/10 == 6) {
                	f1++;
                }  else if (res/10 == 5) {
                	s1++;
                }  else if (res/10 == 4) {
                	t1++;
                }  else if (res/10 == 3) {
                	tp1++;
                }  else if (res/10 == 2) {
                	pair1++;
                }  else if (res/10 == 1) {
                	hc1++;
                } 
            } else if (res % 2 == 0) {
            	p2Wins++;
            	if (res/10 == 10) {
                	rf2++;
                } else if (res/10 == 9) {
                	sf2++;
                } else if (res/10 == 8) {
                	q2++;
                }  else if (res/10 == 7) {
                	fh2++;
                }  else if (res/10 == 6) {
                	f2++;
                }  else if (res/10 == 5) {
                	s2++;
                }  else if (res/10 == 4) {
                	t2++;
                }  else if (res/10 == 3) {
                	tp2++;
                }  else if (res/10 == 2) {
                	pair2++;
                }  else if (res/10 == 1) {
                	hc2++;
                } 
            }
            
            
            
            
            return;
        }
  
        // i iterates from left to n. First time
        // left will be 1
        for (int i = left; i <= n; ++i)
        {
            tmp.add(i);
            makeCombiUtil(n, i + 1, k - 1);
  
            // Popping out last inserted element
            // from the vector
            tmp.remove(tmp.size() - 1);
        }
    }
	
    // Prints all combinations of size k of numbers
    // from 1 to n.
    static Vector<Vector<Integer>> makeCombiN(int n, int k)
    {
        makeCombiUtil(n, 1, k);
        return ans;
    }
	
    public static void onevone (Card[] pl1, Card[] pl2) {
    	p1 = pl1;
    	p2 = pl2;
    	
    	for (int i = 1; i < 53; i++) {
    		fe.add(i);
    	}
    	fe.remove(fe.indexOf(pl1[0].getoft())+1);
    	fe.remove(fe.indexOf(pl1[1].getoft())+1);
    	fe.remove(fe.indexOf(pl2[0].getoft())+1);
    	fe.remove(fe.indexOf(pl2[1].getoft())+1);
    	
    	for(int i:fe) {
    		System.out.print(i+":\t");
    		Card a = new Card(i);
    		System.out.println(a);
    	}
    	
    	
    	makeCombiN(48,5);
    	
    	System.out.println("For "+ pl1[0] + " and " + pl1[1] +" vs "+ pl2[0] +" and " + pl2[1]);
    	
    	System.out.print("Player one wins: ");
    	System.out.printf("%.2f", ((double)p1Wins/1712304)*100);
    	System.out.println("%");
    	
    	System.out.print("Player two wins: ");
    	System.out.printf("%.2f", ((double)p2Wins/1712304)*100);
    	System.out.println("%");

    	
    	System.out.print("Pushes: ");
    	System.out.printf("%.2f", ((double)pushes/1712304)*100);
    	System.out.println("%");

    	

    	System.out.println("P1  :  P2");
    	System.out.println(rf1+" \tRF\t"+rf2);
    	System.out.println(sf1+" \tSF\t"+sf2);
    	System.out.println(q1+" \tQ \t"+q2);
    	System.out.println(fh1+" \tFH\t"+fh2);
    	System.out.println(f1+" \tF\t"+f2);
    	System.out.println(s1+" \tS\t"+s2);
    	System.out.println(t1+" \tT\t"+t2);
    	System.out.println(tp1+" \tTP\t"+tp2);
    	System.out.println(pair1+" \tPAIR\t"+pair2);
    	System.out.println(hc1+" \tHC\t"+hc2);
    }
	
	public static void main(String[] args) {
		Card a = new Card("King of spades");
		Card b = new Card("6 of spades");
		Card c = new Card("Ace of hearts");
		Card d = new Card("6 of clubs");
		Card[] pone = {a,b};
		Card[] ptwo = {c,d};
		onevone(pone,ptwo);
		
		//System.out.println(pushTest);
		
	}
	
	
	
} // CompareTwoHands
	
	

