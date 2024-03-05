package pokerpotodds;

import java.util.*;

/**
 * Class for determining the preflop win percantange for 2 players.
 * N choose K combo algo is from https://www.geeksforgeeks.org/make-combinations-size-k/
 * Modified it to conform to needs for this project.
 */
public class preflopOddsCalc extends PokahDriver{
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
    
	
	static void makeCombiUtil(int n, int left, int k)
    {
       
        // Pushing this vector to a vector of vector
        if (k == 0) {
            ans.add(tmp);
            
            Card one = new Card(fe.get(tmp.get(0) - 1));
            Card two = new Card(fe.get(tmp.get(1) -1));
            Card three = new Card(fe.get(tmp.get(2)-1));
            Card four = new Card(fe.get(tmp.get(3)-1));
            Card five = new Card(fe.get(tmp.get(4)-1));
            
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
    	
    	System.out.print("Player one wins: ");
    	System.out.printf("%.4f", ((double)p1Wins/1712304)*100);
    	System.out.println("%");
    	
    	System.out.print("Player two wins: ");
    	System.out.printf("%.4f", ((double)p2Wins/1712304)*100);
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
		Card a = new Card("10 of spades");
		Card b = new Card("5 of spades");
		Card c = new Card("9 of spades");
		Card d = new Card("6 of clubs");
		Card[] pone = {a,b};
		Card[] ptwo = {c,d};
		onevone(pone,ptwo);
		
		
	}
}