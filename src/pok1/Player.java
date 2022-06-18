package pok1;

import java.io.File;
import java.io.FileNotFoundException;

public class Player {
	/*TODO look into statistics*/
	protected int credit; // Porque deve ser visível para outros métodos
	protected Hand hand;
	protected debugHand debugHand;
	
	public Player(int credit,String cards[],File file) throws FileNotFoundException {
		this.credit=credit;
		this.hand= new Hand(cards);
		this.debugHand= new debugHand(file,cards);
		
	}
	
	public void bet(int bet) {
		credit-=bet;
	}
	
	public void win(int amount) {
		credit+=amount;
	}
	
	public void showCredit() {
		System.out.println(credit);
	}
	
}
