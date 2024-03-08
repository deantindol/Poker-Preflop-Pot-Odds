package poker;

public class Random extends PokerDriver{

	public static void main(String[] args) {
		int r1 = (int)(Math.random()*51) + 1;
		System.out.println(r1);
		int r2 = (int)(Math.random()*51) + 1;
		while (r1 == r2) {
			r2 = (int)(Math.random()*51) + 1;
		}
		System.out.println(r2);
		int r3 = (int)(Math.random()*51) + 1;
		while (r1 == r3 || r2 == r3) {
			r3 = (int)(Math.random()*51) + 1;
		}
		System.out.println(r3);
		int r4 = (int)(Math.random()*51) + 1;
		while (r4 == r3 || r4 == r2 || r4 == r1) {
			r4 = (int)(Math.random()*51) + 1;
		}
		System.out.println(r4);
		Card one = new Card(r1);
		Card two = new Card(r2);
		Card three = new Card(r3);
		Card four = new Card(r4);
		
		System.out.println(one+", "+two+" VS. "+three +", "+four);
		
		Card[] a = {one, two};
		Card[] b = {three, four};

		onevone(a,b);
		
	}
}
