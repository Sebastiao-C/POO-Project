package pok1;

public class Poker {
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck deck = new Deck();
		deck.printDeck();
		System.out.println();
		//Random rdm = new Random();
		//for(int i = 0; i < 100; i++)
		//System.out.print(rdm.nextInt(32) + " ");
		//int numDealt = 23;
		Hand myHand = new Hand(deck.deal(7));
		
		//String dealt[] = deck.deal(numDealt);

		
		deck.printDeck();
		System.out.println();
		/*
		for(int i = 0; i < myHand.getNumCards(); i++) {
			System.out.println(myHand.getCard(i));
		}
		*/
		
		myHand.printCards();
		System.out.println();
		myHand.draw(1, deck);
		myHand.printCards();
	}
	
	

}
