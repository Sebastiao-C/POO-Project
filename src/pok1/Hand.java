package pok1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hand extends SetOfCards{

	public Hand(String cards[]) {
		super(cards);
	}
	
	public String readCard(File file, int index) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		String cardfile = scan.nextLine();
		String[] readCards = cardfile.split(" ");
		return readCards[index];
		
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
	
	public void dDraw(int pos, File file) throws FileNotFoundException {
		for (int i = 0; i<5;i++) {
			cards[i]=readCard(file,pos+i);
		}
	}
}
