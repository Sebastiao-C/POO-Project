package pok1;

public final class Table {
	public int numRounds = 0;
	public int sumBets = 0;
	public int sumGain = 0;
	public static final int[][] Table = new int[12][6];
	public static final String[] handNames = {"ROYAL FLUSH","STRAIGHT FLUSH","FOUR ACES","FOUR 2-4","FOUR 5-K",
	"FULL HOUSE","FLUSH","STRAIGHT","THREE OF A KIND","TWO PAIR","JACKS OR BETTER","OTHER"};
	public Table() {
		int[] Multipliyer = {250,50,160,80,50,10,7,5,3,1,1,0};
		for(int i = 0; i < 12; i++) {
			Table[i][0]=0;
			for (int j = 1; j < 6; j++) {
				Table[i][j]=(j)*Multipliyer[i];
			}
		}
		Table[0][5]=4000;
	}
	
	public int checkTableBet(int i, int bet) {
		return Table[i][bet];
	}
	
	public String checkTableName(int i) {
		return handNames[i];
	}
	
	public void addFreq(int i, int bet, int gain) {
		if (i==2 || i==3 || i==4) {
			Table[2][0]++;
		}
		else {
		Table[i][0]++;
		}
		numRounds++;
		sumBets+=bet;
		sumGain+=gain;
	}
	
	public void getStatistics() {
		System.out.println(String.format("%-15s %4s","HANDS","Nd"));
		System.out.println("----------------------");
		for (int i = 10; i > 4; i--) {
			System.out.println(String.format("%-15s %4d",handNames[i],Table[i][0]));
		}
		System.out.println(String.format("%-15s %4d","FOUR OF A KIND",Table[2][0]));
		for (int i = 1; i >= 0; i--) {
			System.out.println(String.format("%-15s %4d",handNames[i],Table[i][0]));
		}
		System.out.println(String.format("%-15s %4d",handNames[11],Table[11][0]));
		System.out.println("----------------------");
		System.out.println(String.format("%-15s %4d","TOTAL",numRounds));
		System.out.println("----------------------");
	}
}
