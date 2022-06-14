package pok1;
import java.io.*;
import java.util.Scanner;

public class Poker {
	


	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Deck deck;
		Player player;
		
		/*
		File cmd = new File (System.getProperty("user.dir") + "\\src\\pok1\\" + "cmd-file.txt");
		Scanner scan = new Scanner(cmd);
		String cmds=scan.nextLine();
				
		for (int i = 0; i<cmds.length(); i++) {
			char c = cmds.charAt(i);
			switch(c) {
				case 'b':
					System.out.println(" b");
					break;
				case '$':
					System.out.println("$");
					break;
				case 'd':
					System.out.println(" d");
					break;
				case 'h':
					System.out.println(" h");
					break;
				case 'a':
					System.out.println(" a");
					break;
				case 's':
					System.out.println(" s");
					break;
				 default:
					 break;
			}
		}
		*/
		
		deck = new Deck();
		player = new Player(1000,deck.deal(5));
		player.bet(10);
		player.showCredit();
		player.hand.printCards();
	}
}
