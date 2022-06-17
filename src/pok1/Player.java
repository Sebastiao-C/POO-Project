package pok1;

import java.io.File;
import java.io.FileNotFoundException;

public class Player {
	/*TODO look into statistics*/
	public int credit;
	public Hand hand;
	public debugHand debugHand;
	
	public Player(int credit,String cards[],File file) throws FileNotFoundException {
		this.credit=credit;
		this.hand= new Hand(cards);
		this.debugHand= new debugHand(file,cards);
		
	}
	
	public int bet(int bet) {
		credit-=bet;
		return credit;
	}
	
	public void showCredit() {
		System.out.println(credit);
	}
	
}
