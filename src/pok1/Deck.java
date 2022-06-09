package pok1;

import java.util.Random;
import java.util.random.*;

public class Deck {
	private final char suits[] = {'H', 'D', 'C', 'S'};
	private final char values[] = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
	private String cards[];
	private int numCards;
	
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

	
	public void printDeck() {
		//if(cards.length == 52){
		int divis = 1;
		if (cards.length % 4 == 0)
			divis = 0;
		for(int j = 0; j < cards.length; j++) {
			if (cards[j] != null) {
				if (j%(cards.length/4 + divis) == (cards.length/4 - 1 + divis)) 
					System.out.println(cards[j]);
				
				else
					System.out.print(cards[j] + " ");
			}
		}
		//}
	}
	
	public String[] deal(int numCards) {
		this.numCards -= numCards;
		Random rng = new Random();
		
	}
}
