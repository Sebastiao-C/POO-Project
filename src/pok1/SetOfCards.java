package pok1;

import java.util.Random;

public class SetOfCards {
	protected String cards[];
	protected int numCards;
	
	
	/* Necessary constructor due to Deck() not having the "cards" argument (See why in Deck.java)
	 */
	public SetOfCards() {
	}
	
	public SetOfCards(String cards[]) {
		// TODO Auto-generated constructor stub
		this.cards = cards;
		numCards = cards.length;
	}
	

	public String[] getCards() {
		return cards;
	}
	
	public String getCard(int index) {
		return cards[index];
	}

	public void setCards(String cards[]) {
		this.cards = cards;
	}
	
	public void setCard(int index, String card) {
		cards[index] = card;
	}
	
	public int getNumCards() {
		return numCards;
	}
	
	/* Protected because it isn't used outside the class
	 * Removes card by index, then "fills" the hole with the last card and sets the last card to null.
	 * Space in memory persists (the initial number of cards), but removing a card fills space with "null"
	 */
	protected void remove(int index) {
		
		numCards -= 1;
		cards[index] = cards[numCards];
		cards[numCards] = null;

	}
	
	public String[] deal(int numCards) {
		
		//System.out.println("In deal()");
		String dealt[] = new String[numCards];
		Random rng = new Random();
		for(int i = 0; i < numCards; i++) {
			int a = rng.nextInt(this.numCards);
			dealt[i] = cards[a];
			remove(a);
		}
		
		return dealt;
	}
	
	public void printCards() {
		
		System.out.println("In printCards()");
		for(int i = 0; i < numCards; i++) {
			System.out.print(cards[i] + " ");
		}
		System.out.println();
	}
}
