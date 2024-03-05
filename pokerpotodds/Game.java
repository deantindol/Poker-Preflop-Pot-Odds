package pokerpotodds;

import java.util.*;
import pokerpotodds.checkResult.classification;

public class Game {
	static Vector<Vector<Integer>> ans = new Vector<Vector<Integer>>();
    static Vector<Integer> tmp = new Vector<Integer>();
	static void makeCombiUtil(int n, int left, int k)
    {
       
        // Pushing this vector to a vector of vector
        if (k == 0) {
            ans.add(tmp);
            for(int i = 0; i < tmp.size(); i++)
            {
                System.out.print(tmp.get(i) + " ");
            }
            System.out.println();
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
    static Vector<Vector<Integer>> makeCombi(int n, int k)
    {
        makeCombiUtil(n, 1, k);
        return ans;
    }
    
    public static checkResult royalFlush(Card[] seven) {
    	if (straightFlush(seven).getHandClassification() == classification.STRAIGHTFLUSH) {
    		if (straightFlush(seven).getBestFive().get(4).getIndexNumber()==12) {
    			return new checkResult(straightFlush(seven).getBestFive(), classification.ROYALFLUSH);
    		}
    	}
    	return new checkResult(null, null);
    }
    
    /**
     * Checks to see if the Card[] contains a flush.
     * @param seven the Card[] that is checked to be a flush
     * @return checkResult the checkResult object with best five card hand and hand classification. classification is null if not a flush
     */
    public static checkResult flush(Card[] seven) {
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
    	if (suited.size()>4) {
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
    public static boolean isFlush(Card[] seven) {
    	checkResult x;
    	x = flush(seven);
    	if (x.getHandClassification().equals(classification.FLUSH)) {
    		return true;
    	}
    	return false;
    }
    public static boolean isPair(Card[] seven) {
    	checkResult x;
    	x=pair(seven);
    		if (x.getHandClassification().equals(classification.PAIR)) {
    			return true;
    	}
    		return false;
    }
    public static boolean isStraight(Card[] seven) {
    	checkResult x;
    	x = straight(seven);
    	if (x.getHandClassification().equals(classification.STRAIGHT)) {
    		return true;
    	}
    	return false;
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
    	copy = order(copy);
		Card one = new Card(); Card two = new Card(); Card three = new Card(); Card four = new Card(); Card five = new Card();

    	for (int i = 0 ; i < seven.length - 4 ; i++) {
    		for (int j = i ; j < i + 5; j++) {
    			int f = indicies.get(j);
    			if (f==0 && indicies.contains(12) && indicies.contains(1) && indicies.contains(2) && indicies.contains(3)) {
    				for (int k = 0 ; k < copy.size(); k++) {
    					if (copy.get(k).getIndexNumber() == 12) {
    						one = copy.get(k);
    					}
    					else if (copy.get(k).getIndexNumber()== f) {
    						two = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f+1) {
    						three = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f + 2) {
    						four = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f + 3) {
    						five = copy.get(k);
    					}
    					
    				}
    				build.clear();
    				build.add(one); build.add(two); build.add(three); build.add(four); build.add(five);
    				
    			}
    			if (indicies.contains(f+1) && indicies.contains(f+2) && indicies.contains(f+3) && indicies.contains(f+4)) {
    				for (int k = 0 ; k < copy.size(); k++) {
    					if (copy.get(k).getIndexNumber() == f) {
    						one = copy.get(k);
    					}
    					else if (copy.get(k).getIndexNumber()== f + 1) {
    						two = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f+2) {
    						three = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f+3) {
    						four = copy.get(k);
    					} else if (copy.get(k).getIndexNumber()==f+4) {
    						five = copy.get(k);
    					}
    					
    				}
    				build.clear();
    				build.add(one); build.add(two); build.add(three); build.add(four); build.add(five);
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
    	ArrayList<Card> diamonds = new ArrayList<Card>();
    	ArrayList<Card> clubs = new ArrayList<Card>();
    	ArrayList<Card> spades = new ArrayList<Card>();
    	ArrayList<Card> hearts = new ArrayList<Card>();
    	for (Card x : seven) {
    		if (x.getSuit() == "Spades") {
    			spades.add(x);
    		} else if (x.getSuit() == "Hearts") {
    			hearts.add(x);
    		} else if (x.getSuit() == "Clubs") {
    			clubs.add(x);
    		} else if (x.getSuit() == "Diamonds") {
    			diamonds.add(x);
    		}
    	}
    	if (diamonds.size() > 4) {
    		Card[] d = new Card[diamonds.size()];
    		int index = 0;
    		diamonds = order(diamonds);
    		for (Card x : diamonds) {
    			d[index] = x;
    			index++;
    		}
    		checkResult x = straight(d);
    		if (x.getHandClassification()==classification.STRAIGHT) {
    			return new checkResult(x.getBestFive(), classification.STRAIGHTFLUSH);
    		}
    		else return new checkResult(null, null);
    		
    	} else if (clubs.size() > 4) {
    		Card[] d = new Card[clubs.size()];
    		int index = 0;
    		clubs = order(clubs);
    		for (Card x : clubs) {
    			d[index] = x;
    			index++;
    		}
    		checkResult x = straight(d);
    		if (x.getHandClassification()==classification.STRAIGHT) {
    			return new checkResult(x.getBestFive(), classification.STRAIGHTFLUSH);
    		}
    		else return new checkResult(null, null);
    	} else if (spades.size() > 4) {
    		Card[] d = new Card[spades.size()];
    		int index = 0;
    		for (Card x : spades) {
    			d[index] = x;
    			index++;
    		}
    		checkResult x = straight(d);
    		if (x.getHandClassification()==classification.STRAIGHT) {
    			return new checkResult(x.getBestFive(), classification.STRAIGHTFLUSH);
    		}
    		else return new checkResult(null, null);
    		
    	} else if (hearts.size() > 4) {
    		Card[] d = new Card[hearts.size()];
    		int index = 0;
    		for (Card x : hearts) {
    			d[index] = x;
    			index++;
    		}
    		checkResult x = straight(d);
    		if (x.getHandClassification()==classification.STRAIGHT) {
    			return new checkResult(x.getBestFive(), classification.STRAIGHTFLUSH);
    		}
    		else return new checkResult(null, null);
    	}
    	return new checkResult(null, null);

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
	public static void main(String[] args) {
		makeCombi(48,5);
	}
	
	
	}

