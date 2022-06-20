package pok1;

abstract class Hand extends SetOfCards{
	private static final String[] handNames = {"ROYAL FLUSH","STRAIGHT FLUSH","FOUR ACES","FOUR 2-4","FOUR 5-K",
	"FULL HOUSE","FLUSH","STRAIGHT","THREE OF A KIND","TWO PAIR","JACKS OR BETTER","OTHER"};

	public Hand(String cards[]) {
		super(cards);
	}
	
	public String getHandName (int index) {
		return handNames[index];
	}
}
