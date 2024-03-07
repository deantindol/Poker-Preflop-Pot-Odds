package poker;

import java.util.*;
import poker.checkResult.classification;

public class HandOperations {
	
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
    
    static Card one;
    static Card two;
    static Card three;
    static Card four;
    static Card five;

    
    
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
					return 91;
				} else if (p2 > p1) {
					return 92;
				} else if (p1 == p2){
					return 3;
				}
			case 10:
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
					return 81;
				} else if (fifth2 > fifth1) {
					return 82;	
				} else if (fifth1 == fifth2){
					return 3;
				}
			case 7:
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
				} else if (p13 == p23 && p12 == p22){
					return 3;
				}
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
				if (player1BestFive.get(0).getIndexNumber() > player2BestFive.get(0).getIndexNumber()) {
					return 51;
				} else if (player2BestFive.get(0).getIndexNumber() > player1BestFive.get(0).getIndexNumber()) {
					return 52;
				} else {
					return 3;
				}
			case 4:
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
				for (int i = 0; i < 5 ; i++) {
					if (player1BestFive.get(i).getIndexNumber() > player2BestFive.get(i).getIndexNumber()) {
						if (i == 0) {
						} else {
						}
						return 21;
					} else if (player1BestFive.get(i).getIndexNumber() < player2BestFive.get(i).getIndexNumber()) {
						if (i == 0) {
						} else {
						}
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
	
	static void makeCombiUtil(int n, int left, int k)
    {
		
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
                	System.out.println(tab[0]);
                	System.out.println(tab[1]);
                	System.out.println(tab[2]);
                	System.out.println(tab[3]);
                	System.out.println(tab[4]);
                	System.out.println();
                	
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
    	
    	for (int x : fe) {
    		possibleTableCards.add(new Card(x));
    	}
    	
    	for (Card x: possibleTableCards) {
    		System.out.println(x);
    	}
    	
    	makeCombiN(48,5);
    	
    	//System.out.println("For "+ pl1[0] + " and " + pl1[1] +" vs "+ pl2[0] +" and " + pl2[1]);
    	
    	System.out.print("Player one wins: ");
    	System.out.printf("%.2f", ((double)p1Wins/1712304)*100);
    	System.out.println("%");
    	
    	System.out.print("Player two wins: ");
    	System.out.printf("%.2f", ((double)p2Wins/1712304)*100);
    	System.out.println("%");

    	
    	System.out.print("Pushes: ");
    	System.out.printf("%.2f", ((double)pushes/1712304)*100);
    	System.out.println("%");

    	

    	System.out.println("P1\t:\tP2");
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
	
	public static checkResult royalFlush(Card[] seven) {
    	if (straightFlush(seven).getHandClassification() == classification.STRAIGHTFLUSH) {
    		if (straightFlush(seven).getBestFive().get(4).getIndexNumber() == 12) {
    			return new checkResult(straightFlush(seven).getBestFive(), classification.ROYALFLUSH);
    		}
    	}
    	return new checkResult(null, null);
    }
    
    /**
     * Checks to see if the Card[] contains a flush.
     * @param seven the Card[] that is checked to be a flush. 
     * @return checkResult the checkResult object with best five card hand and hand classification. classification is null if not a flush
     * @throws IllegalArgumentException if the Card[] is not length seven.
     */
    public static checkResult flush(Card[] seven) {
    	if (seven.length != 7) {
    		throw new IllegalArgumentException("The Card[] passed to this method must be length 7");
    	}
    	int spade = 0; 
    	int heart = 0; 
    	int diamond = 0;
    	int club = 0;
    	ArrayList<Card> b5 = new ArrayList<Card>();
    	ArrayList<Card> suited = new ArrayList<Card>();
    	for(int i = 0 ; i < 7 ; i++) {
    		if (seven[i].getSuit().equals("Spades")){
    			spade++;
    		} else if (seven[i].getSuit().equals("Diamonds")) {
    			diamond++;
    		} else if (seven[i].getSuit().equals("Clubs")) {
    			club ++;
    		} else if (seven[i].getSuit().equals("Hearts")) {
    			heart++;
    		}
    	}
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
    	if (suited.size() > 4) {
    		int highest = -1;
    		int index = 0;
    		for (int i = 0 ; i < 5 ; i++) {
    			for (int j = 0 ; j < suited.size() ; j++) {
    				if (suited.get(j).getIndexNumber() > highest) {
    					highest = suited.get(j).getIndexNumber();
    					index = j;
    				}
    			}
    			b5.add(suited.get(index));
    			suited.remove(index);
    			highest = -1;
    		}
    	}
    	if (b5.size() > 0) {
    		classification c = classification.FLUSH;
    		checkResult build = new checkResult(b5, c);
    		return build;
    	} 
    	checkResult build = new checkResult();
    	return build;
    }
    
    public static checkResult straight (Card[] seven) {
    	ArrayList<Card> copy = new ArrayList<Card>();
    	for (Card x : seven) {
    		copy.add(x);
    	}
    	ArrayList<Integer> indicies = new ArrayList<Integer>();
    	for (Card x : copy) {
    		indicies.add(x.getIndexNumber());
    	}
    	ArrayList<Card> build = new ArrayList<Card>();
		Card one = new Card(); Card two = new Card(); Card three = new Card(); Card four = new Card(); Card five = new Card();

    	for (int i = 0 ; i < seven.length; i++) {
    		if (true) {
    			int f = indicies.get(i);
    			if (f==0 && indicies.contains(12) && indicies.contains(1) && indicies.contains(2) && indicies.contains(3)) {
    				for (int k = 0 ; k < copy.size(); k++) {
    					if (copy.get(k).getIndexNumber() == 12) {
    						one = copy.get(k);
    					}
    					else if (copy.get(k).getIndexNumber()== f) {
    						two = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f + 1) {
    						three = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f + 2) {
    						four = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f + 3) {
    						five = copy.get(k);
    					}
    					
    				}
    				if (build.size() > 0 && build.get(4).getIndexNumber() < five.getIndexNumber()) {
    					build.clear();
    					build.add(one); build.add(two); build.add(three); build.add(four); build.add(five);
    				} else if( build.size() == 0) {
    					build.add(one); build.add(two); build.add(three); build.add(four); build.add(five);

    				}
    				
    			}
    			if (indicies.contains(f + 1) && indicies.contains(f + 2) && indicies.contains(f + 3) && indicies.contains(f + 4)) {
    				for (int k = 0 ; k < copy.size(); k++) {
    					if (copy.get(k).getIndexNumber() == f) {
    						one = copy.get(k);
    					}
    					else if (copy.get(k).getIndexNumber()== f + 1) {
    						two = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f + 2) {
    						three = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f + 3) {
    						four = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f + 4) {
    						five = copy.get(k);
    					}
    					
    				}
    				if (build.size() > 0 && build.get(4).getIndexNumber() < five.getIndexNumber()) {
    					build.clear();
    					build.add(one); build.add(two); build.add(three); build.add(four); build.add(five);
    				} else if( build.size() == 0) {
    					build.add(one); build.add(two); build.add(three); build.add(four); build.add(five);

    				}
    			}
    		}
    	}
    	if(build.size() > 0) {
    		checkResult s = new checkResult(build, classification.STRAIGHT);
    		return s;
    	}
    	return new checkResult(build, null);
    }
    public static checkResult highCard(Card[] seven) {
    	ArrayList<Card> copy = new ArrayList<Card>();
    	for (Card x : seven) {
    		copy.add(x);
    	}
    	copy = order(copy);
    	ArrayList<Card> build = new ArrayList<Card>();
    	build.add(copy.get(6));
    	build.add(copy.get(5));
    	build.add(copy.get(4));
    	build.add(copy.get(3));
    	build.add(copy.get(2));
    	return new checkResult(build, classification.HIGHCARD);
    }
    
    public static checkResult fullHouse(Card[] seven) {
    	int index1 = -1;
    	int index2 = -1;
    	int index3 = -1;
    	int index4 = -1;
    	int index5 = -1;
    	ArrayList<Card> copy = new ArrayList<Card>();
    	ArrayList<Card> build = new ArrayList<Card>();

    	for (Card x : seven) {
    		copy.add(x);
    	}
    	copy = order(copy);

    	for (int i = 0; i < 5 ; i++) {
    		int i1 = copy.get(i).getIndexNumber();
    		int i2 = copy.get(i + 1).getIndexNumber();
    		int i3 = copy.get(i + 2).getIndexNumber();
   			if (i1 == i2 && i2 == i3) {
   				index1 = i;
    			index2 = i + 1;
    			index3 = i + 2;
    			}

    		
    	}
    	if (index1 != -1) {
    		build.add(copy.get(index1));
    		build.add(copy.get(index2));
    		build.add(copy.get(index3));
    		copy.remove(index3);
    		copy.remove(index2);
    		copy.remove(index1);

    	} else {
    		return new checkResult(null, null);
    	}
    	for (int i = 0; i < 3 ; i++) {
    		if(copy.get(i).getIndexNumber() == copy.get(i + 1).getIndexNumber()) {
    			index4 = i;
    			index5 = i + 1;
    			
   			}
    		
    	}
    	if (index4 != -1) {
    		build.add(copy.get(index4));
    		build.add(copy.get(index5));

    	} else {
    		return new checkResult(build, null);
    	}
    	
    	return new checkResult(build, classification.FULLHOUSE);
    	
    }
    
    public static checkResult twoPair(Card[] seven) {
    	ArrayList<Card> copy = new ArrayList<Card>();
    	for (Card x : seven) {
    		copy.add(x);
    	}
    	ArrayList<Card> build = new ArrayList<Card>();
    	int index1 = -1;
    	int index2 = -1;
    	int index3 = -1;
    	int index4 = -1;
    	int index5 = -1;
    	int index6 = -1;
    	copy = order(copy);
    	for ( int i = 0 ; i < 6 ; i++) {
    		if (copy.get(i).getIndexNumber() == copy.get(i + 1).getIndexNumber()) {
    			if (index1 == -1) {
    				index1 = i;
        			index2 = i + 1;
        			i++;
    			} else if(index3 == -1) {
    				index3 = i;
    				index4 = i + 1;
    				i++;
    			} else {
    				index5 = i;
    				index6 = i + 1;
    				i++;
    			}
    		}
    	}
    	if (index5 != -1) {
    		build.add(copy.get(index6));
    		build.add(copy.get(index5));
    		build.add(copy.get(index4));
    		build.add(copy.get(index3));
    		copy.remove(index6);
    		copy.remove(index5);
    		copy.remove(index4);
    		copy.remove(index3);

    	} else if(index3 != -1) {
    		build.add(copy.get(index4));
    		build.add(copy.get(index3));
    		build.add(copy.get(index2));
    		build.add(copy.get(index1));
    		copy.remove(index4);
    		copy.remove(index3);
    		copy.remove(index2);
    		copy.remove(index1);
    	} else {
    		return new checkResult(build, null);
    	}
    	build.add(copy.get(copy.size() - 1));
    	return new checkResult(build, classification.TWOPAIR);

    }
    public static checkResult quads(Card[] seven) {
    	ArrayList<Card> copy = new ArrayList<Card>();
    	for (Card x : seven) {
    		copy.add(x);
    	}
    	ArrayList<Card> build = new ArrayList<Card>();
    	int index1 = -1;
    	int index2 = -1;
    	int index3 = -1;
    	int index4 = -1;
    	copy = order(copy);
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
    	if (index1 != -1) {
    		build.add(copy.get(index1));
    		build.add(copy.get(index2));
    		build.add(copy.get(index3));
    		build.add(copy.get(index4));
    		copy.remove(index1);
    		copy.remove(index1);
    		copy.remove(index1);
    		copy.remove(index1);
    		build.add(copy.get(2));
    	}
    	if (build.size() > 0) {
    		checkResult s = new checkResult(build, classification.QUADS);
    		return s;
    	}
    	return new checkResult();
    	
    }
    
    
    public static checkResult trips(Card[] seven) {
    	ArrayList<Card> copy = new ArrayList<Card>();
    	for (Card x : seven) {
    		copy.add(x);
    	}
    	ArrayList<Card> build = new ArrayList<Card>();
    	int index1 = -1;
    	int index2 = -1;
    	int index3 = -1;
    	copy = order(copy);
    	for ( int i = 0 ; i < 5 ; i++) {
    		if (copy.get(i).getIndexNumber() == copy.get(i + 1).getIndexNumber() && copy.get(i + 1).getIndexNumber() == copy.get(i + 2).getIndexNumber()) {
    			index1 = i;
    			index2 = i + 1;
    			index3 = i + 2;
    		}
    	}
    	if (index1 != -1) {
    		build.add(copy.get(index1));
    		build.add(copy.get(index2));
    		build.add(copy.get(index3));
    		copy.remove(index1);
    		copy.remove(index1);
    		copy.remove(index1);
    		build.add(copy.get(3));
    		build.add(copy.get(2));
    	}
    	if (build.size() > 0) {
    		checkResult s = new checkResult(build, classification.TRIPS);
    		return s;
    	}
    	return new checkResult(build, null);
    }
    
    public static checkResult straightFlush(Card[] seven) {
    	ArrayList<Card> copy = new ArrayList<Card>();
    	for (Card x : seven) {
    		copy.add(x);
    	}
    	copy = order(copy);
    	int d = getNumOfSuit("Diamonds", seven);
    	int s = getNumOfSuit("Spades", seven);
    	int c = getNumOfSuit("Clubs", seven);
    	int h = getNumOfSuit("Hearts", seven);
    	if (d > 4) {
    		Card[] build = new Card[d];
    		int index = 0;
    		for (Card x : seven) {
    			if (x.getSuit().equals("Diamonds")) {
    				build[index] = x;
    				index++;
    			}
    		}
    		if(straight(build).getHandClassification()==classification.STRAIGHT) {
    			return new checkResult(straight(build).getBestFive(), classification.STRAIGHTFLUSH);
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
    		if(straight(build).getHandClassification()==classification.STRAIGHT) {
    			return new checkResult(straight(build).getBestFive(), classification.STRAIGHTFLUSH);
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
    		if(straight(build).getHandClassification()==classification.STRAIGHT) {
    			return new checkResult(straight(build).getBestFive(), classification.STRAIGHTFLUSH);
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
    		if(straight(build).getHandClassification()==classification.STRAIGHT) {
    			return new checkResult(straight(build).getBestFive(), classification.STRAIGHTFLUSH);
    		}
    	}
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
    	for ( int i = 0 ; i < 6 ; i++) {
    		if (copy.get(i).getIndexNumber() == copy.get(i + 1).getIndexNumber()) {
    			index1 = i;
    			index2 = i + 1;
    		}
    	}
    	if ( index1 != -1) {
    		build.add(copy.get(index1));
    		build.add(copy.get(index2));
    		copy.remove(index1);
    		copy.remove(index1);
    		build.add(copy.get(4));
    		build.add(copy.get(3));
    		build.add(copy.get(2));
    	}
    	if (build.size() > 0) {
    		checkResult s = new checkResult(build, classification.PAIR);
    		return s;
    	}
    	return new checkResult(build, null);

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
    
    public static void main(String[] args) {
    	Card a = new Card("Ace of spades");
    	Card b = new Card("Ace of diamonds");
    	Card c = new Card("10 of clubs");
    	Card d = new Card("Jack of clubs");
    	
    	
    }
	
	
	
} // HandOperations
	
	

