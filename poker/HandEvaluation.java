package poker;

import java.util.*;
import poker.checkResult.classification;

/**
 * Holds methods that check a hand for a specific hand rank. These methods return a {@code checkResult} object.
 */
public class HandEvaluation {
    
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
    	for ( int i = 0 ; i < 4 ; i++) {
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
    	return new checkResult(build, null);
    	
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
	
}

