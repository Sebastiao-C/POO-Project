package pok1;

public class Hand {
	private char cards[][];
	

	public char[][] getCards() {
		return cards;
	}
	
	public char[] getCard(int index) {
		return cards[index];
	}

	public void setCards(char cards[][]) {
		this.cards = cards;
	}
	
	public void setCard(int index, char[] card) {
		cards[index] = card;
	}
	
	

}
