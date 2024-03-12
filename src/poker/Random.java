package poker;


/**
 * Generates four random cards, two for each player.
 * Pre-flop odds are calculated and printed
 */
public class Random extends PokerDriver {
	
	private static long startTime = System.currentTimeMillis();
	
    public static void main(String[] args) {
    	Card[] tab = new Card[4];

        int r1 = (int)(Math.random() * 52) + 1;
        int r2 = (int)(Math.random() * 52) + 1;
        while (r1 == r2) {
            r2 = (int)(Math.random() * 52) + 1;
        }
        int r3 = (int)(Math.random() * 52) + 1;
        while (r1 == r3 || r2 == r3) {
            r3 = (int)(Math.random() * 52) + 1;
        }
        int r4 = (int)(Math.random() * 52) + 1;
        while (r4 == r3 || r4 == r2 || r4 == r1) {
            r4 = (int)(Math.random() * 52) + 1;
        }
        Card one = new Card(r1);
        Card two = new Card(r2);
        Card three = new Card(r3);
        Card four = new Card(r4);
        
        boolean longOutput = false;
        

        System.out.println(one + ", " + two + " VS. " + three + ", " + four);

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
        
        
        
        if (args.length > 0) {
        	for (int i = 0; i < args.length; i++) {
        		 if (args[i].equals("-l")) {
         			longOutput = true;
         		} else if (args[i].equals("-t") && args.length > i) {
        			if (args[i + 1].equals("3")) {
        				int r5 = (int)(Math.random() * 52) + 1;
        		        while (r5 == r4 || r5 == r3 || r5 == r2 || r5 == r1) {
        		            r5 = (int)(Math.random() * 52) + 1;
        		        }
        		        int r6 = (int)(Math.random() * 52) + 1;
        		        while (r6 == r5 ||r6 == r4 || r6 == r3 || r6 == r2 || r6 == r1) {
        		            r6 = (int)(Math.random() * 52) + 1;
        		        }
        		        int r7 = (int)(Math.random() * 52) + 1;
        		        while (r7 == r6 || r7 == r5 ||r7 == r4 || r7 == r3 || r7 == r2 || r7 == r1) {
        		            r7 = (int)(Math.random() * 52) + 1;
        		        }
        		        Card five = new Card(r5);
        		        Card six = new Card(r6);
        		        Card seven = new Card(r7);
        		        Card[] temp = new Card[3];
        		        temp[0] = five;
        		        temp[1] = six;
        		        temp[2] = seven;
        		        tab = temp;
        		        		
        		        
        		        System.out.print("Table : ");
        		        for (int j = 0; j < tab.length; j++) {
        		        	System.out.print(tab[j]);
        		        	if (j != tab.length - 1) {
        		        		System.out.print(", ");
        		        	}
        		        }
        		        System.out.println();
        		        

        		        
        			} else if (args[i + 1].equals("4")) {
        				int r5 = (int)(Math.random() * 52) + 1;
        		        while (r5 == r4 || r5 == r3 || r5 == r2 || r5 == r1) {
        		            r5 = (int)(Math.random() * 52) + 1;
        		        }
        		        int r6 = (int)(Math.random() * 52) + 1;
        		        while (r6 == r5 ||r6 == r4 || r6 == r3 || r6 == r2 || r6 == r1) {
        		            r6 = (int)(Math.random() * 52) + 1;
        		        }
        		        int r7 = (int)(Math.random() * 52) + 1;
        		        while (r7 == r6 || r7 == r5 ||r7 == r4 || r7 == r3 || r7 == r2 || r7 == r1) {
        		            r7 = (int)(Math.random() * 52) + 1;
        		        }
        		        int r8 = (int)(Math.random() * 52) + 1;
        		        while (r8 == r7 || r8 == r6 || r8 == r5 ||r8 == r4 || r8 == r3 || r8 == r2 || r8 == r1) {
        		            r8 = (int)(Math.random() * 52) + 1;
        		        }
        		        Card five = new Card(r5);
        		        Card six = new Card(r6);
        		        Card seven = new Card(r7);
        		        Card eight = new Card(r8);
        		        tab[0] = five;
        		        tab[1] = six;
        		        tab[2] = seven;
        		        tab[3] = eight;
        		        System.out.print("Table : ");
        		        for (int j = 0; j < tab.length; j++) {
        		        	System.out.print(tab[j]);
        		        	if (j != tab.length - 1) {
        		        		System.out.print(", ");
        		        	}
        		        }
        		      

        			}
        		}
        	} 
        }
       
        if (tab[0] == null) {
        	tab = new Card[0];
        }
        onevone(playerOneHand, playerTwoHand, longOutput, tab);
        System.out.println("Executed in: " + (System.currentTimeMillis() - startTime) + " ms");


    }
}
