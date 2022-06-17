package pok1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class debugHand extends Hand{
	
	File file;
	String[] readCards;
	int numCardsFile;

	public debugHand (File file, String cards[]) throws FileNotFoundException {
		super(cards);
		this.file=file;
		Scanner scan = new Scanner(file);
		String cardfile = scan.nextLine();
		this.readCards = cardfile.split(" ");
		this.numCardsFile=readCards.length;
	}
	
	public void draw(int pos, File file) throws FileNotFoundException {
		for (int i = 0; i<5;i++) {
			cards[i]=readCards[pos+i];
		}
	}
	
	public void hold(int index[],int pos) {
		boolean found;
		for (int i = 0; i < 5; i++) {
			found = false;
			for (int j = 0; j < index.length; j++ ){
				if (i+1==index[j]) {
					found = true;
				}
			}
			if (found==false) {
				cards[i]=readCards[pos];
				pos++;
			}
		}
	}
}
