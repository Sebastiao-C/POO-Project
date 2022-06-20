package pok1;

import java.io.File;
import java.io.FileNotFoundException;

public class debugPlayer extends Player {
	public debugHand debugHand;

	public debugPlayer(int credit, String[] cards, File file) throws FileNotFoundException {
		super(credit, cards);
		this.debugHand= new debugHand(file,cards);
	}
	
	public void getStatistics() {
		System.out.println(String.format("%-15s %4s","HANDS","Nd"));
		System.out.println("----------------------");
		for (int i = 10; i > 4; i--) {
			System.out.println(String.format("%-15s %4d",debugHand.getHandName(i),Freqs[i]));
		}
		System.out.println(String.format("%-15s %4d","FOUR OF A KIND",Freqs[2]));
		for (int i = 1; i >= 0; i--) {
			System.out.println(String.format("%-15s %4d",debugHand.getHandName(i),Freqs[i]));
		}
		System.out.println(String.format("%-15s %4d",debugHand.getHandName(11),Freqs[11]));
		System.out.println("----------------------");
		System.out.println(String.format("%-15s %4d","TOTAL",numRounds));
		System.out.println("----------------------");
		System.out.println(String.format("%-10s %2d %s %2d %s %s","CREDIT",credit,"(",getRatio(),"%",")"));
	}
}
