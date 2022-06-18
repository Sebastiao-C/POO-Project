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
		for(i = 0; i < 100000; i++) {
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
			if(value == 7) {
				counter++;
				myHand.printCards();
			}
		}
		System.out.println("Number of " + 5 + "'s: " + counter);
		
		int a = 1;
		int b = 2;
		int c = 3;
		int myarray[] = {a,b,c};
		System.out.println(myarray[0]);
		addOne(myarray);
		System.out.println(myarray[0]);
		
		deck = new Deck();
		//deck.printDeck();
		//System.out.println();
		//Random rdm = new Random();
		//for(int i = 0; i < 100; i++)
		//System.out.print(rdm.nextInt(32) + " ");
		//int numDealt = 23;
		myHand = new Hand(deck.deal(5));
		myHand.printCards();
		reorder(myHand.cards);
		myHand.printCards();
		
		String myCards[] = {"TC", "TS", "9S", "7C", "6S"}; 
		int myArray[] = Combos.checkFor4toS(myCards);
		for(int var = 0; var < myArray.length; var++) {
			System.out.print(myArray[var] + ", ");
		}
		System.out.println();
		for(int var = 0; var < myCards.length; var++) {
			System.out.print(myCards[var] + ", ");
		}
		

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
	
	public static void addOne(int a[]) {
		int b[] = {100,95};
		a = b;
	}
	public static void reorder(String cards[]) {
		String tempCard = cards[0];
		cards[0] = cards[1];
		cards[1] = cards[2];
		cards[2] = cards[3];
		cards[3] = cards[4];
		cards[4] = tempCard;
	}
	

}
