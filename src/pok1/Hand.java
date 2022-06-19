package pok1;

abstract class Hand extends SetOfCards{
	private static final String[] handNames = {"ROYAL FLUSH","STRAIGHT FLUSH","FOUR ACES","FOUR 2-4","FOUR 5-K",
	"FULL HOUSE","FLUSH","STRAIGHT","THREE OF A KIND","TWO PAIR","JACKS OR BETTER","OTHER"};

	public Hand(String cards[]) {
		super(cards);
	}
	
	public void draw(int numDrawn, Deck deck) {
		
		System.out.println("In draw()");
		int prevNumCards = numCards;
	
		String drawn[] = new String[numDrawn];
		drawn = deck.deal(numDrawn);
			
		// "Deals" cards from the hand and does nothing with them. Basically removing with the tidyness 
		// of "deal"
		deal(numDrawn);
			
		//System.out.println("NumCards is " + numCards);
			
		// Fills emptied spots with cards drawn from the deck
		for(int i = numDrawn - 1; i >= 0; i--) {
			cards[prevNumCards - i - 1] = drawn[i];
			numCards++;
		}
	}
	
	public String getHandName (int index) {
		return handNames[index];
	}
}
