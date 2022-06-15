package pok1;

public class Player {
	/*TODO look into statistics*/
	public int credit;
	public Hand hand;
	public debugHand debugHand;
	
	public Player(int credit,String cards[]) {
		this.credit=credit;
		this.hand= new Hand(cards);
		this.debugHand= new debugHand(cards);
		
	}
	
	public int bet(int bet) {
		credit-=bet;
		return credit;
	}
	
	public void showCredit() {
		System.out.println(credit);
	}
	
}
