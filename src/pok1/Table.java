package pok1;

public final class Table {
	public static final int[][] Table = new int[11][5];
	public static final String[] handNames = {"ROYAL FLUSH","STRAIGHT FLUSH","FOUR ACES","FOUR 2-4","FOUR 5-K",
	"FULL HOUSE","FLUSH","STRAIGHT","THREE OF A KIND","TWO PAIR","JACKS OR BETTER"};
	public Table() {
		int[] Multipliyer = {250,50,160,80,50,10,7,5,3,1,1};
		for(int i = 0; i < 11; i++) {
			for (int j= 1; j < 5; j++) {
				Table[i][j]=(j+1)*Multipliyer[i];
			}
		}
		Table[0][4]=4000;
	}
	
	public int checkTableBet(int i, int bet) {
		return Table[i][bet-1];
	}
	
	public String checkTableName(int i) {
		return handNames[i];
	}
}
