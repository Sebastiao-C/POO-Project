package pok1;

import java.io.File;
import java.io.FileNotFoundException;

public class Player {
	/*TODO look into statistics*/
	protected int credit;
	protected int numRounds = 0;
	protected int sumBets = 0;
	protected int sumGain = 0;
	private static final String[] handNames = {"ROYAL FLUSH","STRAIGHT FLUSH","FOUR ACES","FOUR 2-4","FOUR 5-K",
	"FULL HOUSE","FLUSH","STRAIGHT","THREE OF A KIND","TWO PAIR","JACKS OR BETTER","OTHER"};
	public static final int[] Freqs = new int[12];
	protected Hand hand;
	protected debugHand debugHand;
	
	public Player(int credit,String cards[],File file) throws FileNotFoundException {
		this.credit=credit;
		this.hand= new Hand(cards);
		this.debugHand= new debugHand(file,cards);
		for (int i = 0; i < 12; i++) {
			Freqs[i]=0;
		}
		
	}
	
	public void bet(int amount) {
		credit-=amount;
	}
	
	public void win(int amount) {
		credit+=amount;
	}
	
	public void showCredit() {
		System.out.println(credit);
	}
	
	public void addFreq(int index, int bet, int gain) {
		if (index==2 || index==3 || index==4) {
			Freqs[2]++;
		}
		else {
		Freqs[index]++;
		}
		numRounds++;
		sumBets+=bet;
		sumGain+=gain;
	}
	
	public long getRatio() {
		long ratio;
		ratio=Math.round((double)sumGain/(double)sumBets*100);
		return ratio;
	}
	
	public void getStatistics() {
		System.out.println(String.format("%-15s %4s","HANDS","Nd"));
		System.out.println("----------------------");
		for (int i = 10; i > 4; i--) {
			System.out.println(String.format("%-15s %4d",handNames[i],Freqs[i]));
		}
		System.out.println(String.format("%-15s %4d","FOUR OF A KIND",Freqs[2]));
		for (int i = 1; i >= 0; i--) {
			System.out.println(String.format("%-15s %4d",handNames[i],Freqs[i]));
		}
		System.out.println(String.format("%-15s %4d",handNames[11],Freqs[11]));
		System.out.println("----------------------");
		System.out.println(String.format("%-15s %4d","TOTAL",numRounds));
		System.out.println("----------------------");
		System.out.println(String.format("%-10s %2d %s %2d %s %s","CREDIT",credit,"(",getRatio(),"%",")"));
	}

}
