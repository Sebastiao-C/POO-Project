package pok1;

public class Poker {
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int value = 0;
		int i = 0;
		Deck deck;
		Hand myHand;
		/*
		do {
		i++;
		deck = new Deck();
		//deck.printDeck();
		//System.out.println();
		//Random rdm = new Random();
		//for(int i = 0; i < 100; i++)
		//System.out.print(rdm.nextInt(32) + " ");
		//int numDealt = 23;
		myHand = new Hand(deck.deal(5));
		myHand.setCards(Combos.sortCards(myHand.getCards()));
		value = Combos.getHandValue(myHand.cards);
		//myHand.printCards();
		//System.out.println("This is the hand's value " + value);
		}while(value != 7);
		System.out.println("i is " + i);
		myHand.printCards();
		System.out.println("This is the hand's value " + value);	
		*/
		int counter = 0;
		for(i = 0; i < 10000; i++) {
			deck = new Deck();
			//deck.printDeck();
			//System.out.println();
			//Random rdm = new Random();
			//for(int i = 0; i < 100; i++)
			//System.out.print(rdm.nextInt(32) + " ");
			//int numDealt = 23;
			myHand = new Hand(deck.deal(5));
			myHand.setCards(Combos.sortCards(myHand.getCards()));
			value = Combos.getHandValue(myHand.cards);
			if(value == 1) {
				counter++;
				myHand.printCards();
			}
		}
		System.out.println("Number of " + 10 + "'s: " + counter);
		
		//String dealt[] = deck.deal(numDealt);

		/*
		deck.printDeck();
		System.out.println();
		
		for(int i = 0; i < myHand.getNumCards(); i++) {
			System.out.println(myHand.getCard(i));
		}
		*/
		


		/*
		System.out.println();
		myHand.draw(1, deck);
		myHand.printCards();
		*/

	}
	
	

}
