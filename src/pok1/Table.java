package pok1;

public final class Table {
	public static int[][] Table;
	private static final String[] handNames = {"ROYAL FLUSH","STRAIGHT FLUSH","FOUR ACES","FOUR 2-4","FOUR 5-K",
	"FULL HOUSE","FLUSH","STRAIGHT","THREE OF A KIND","TWO PAIR","JACKS OR BETTER","OTHER"};
	public Table() {
		Table = new int[12][6];
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
}
