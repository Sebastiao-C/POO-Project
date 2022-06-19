package pok1;

public class Deck extends SetOfCards{
	public static final char suits[] = {'H', 'D', 'C', 'S'};
	public static final char values[] = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

	/* Constructor that doesn't have the "cards" argument. Doesn't really make sense to create a deck
	 * with cards other than the 52 standard ones. 
	 */
	public Deck() {
		cards = new String[52];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				char card[] = {values[j], suits[i]};
				cards[i*13 + j] = new String(card);
			}
		}
		numCards = 52;
	}

	/* Self-explanatory. Right now adjusts format depending on the number of cards 
	 * (formatting way to complicated to be removed, I think)
	 */
	public void printDeck() {
		//if(cards.length == 52){
		int divis = 1;
		int count = 0;
		if (numCards % 4 == 0)
			divis = 0;
		for(int j = 0; j < 52; j++) {
			if (cards[j] != null) {
				if (count%(numCards/4 + divis) == (numCards/4 - 1 + divis)) 
					System.out.println(cards[j]);
				
				else
					System.out.print(cards[j] + " ");
				count++;
			}

		}
		//}
	}
	

	

}
