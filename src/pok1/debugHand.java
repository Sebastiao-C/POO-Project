package pok1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class debugHand extends Hand{
	public debugHand (String cards[]) {
		super(cards);
	}
	
	public String readCard(File file, int index) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		String cardfile = scan.nextLine();
		String[] readCards = cardfile.split(" ");
		return readCards[index];
		
	}
	
	public void draw(int pos, File file) throws FileNotFoundException {
		for (int i = 0; i<5;i++) {
			cards[i]=readCard(file,pos+i);
		}
	}
	
	public void hold(int index[],int pos, File file) throws FileNotFoundException {
		boolean found;
		for (int i = 0; i < 5; i++) {
			found = false;
			for (int j = 0; j < index.length; j++ ){
				if (i+1==index[j]) {
					found = true;
				}
			}
			if (found==false) {
				cards[i]=readCard(file,pos);
				pos++;
			}
			
		}
		
	}
}
