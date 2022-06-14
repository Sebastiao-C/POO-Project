package pok1;
import java.io.*;
import java.util.Scanner;

public class Poker {
	


	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Deck deck;
		Player player;
		
		if (args[0].equals("-d")){
			String cards[] = new String[5];
			deck = new Deck();
			player = new Player(Integer.parseInt(args[1]),cards);
			File cmds = new File (System.getProperty("user.dir")+ "\\" + args[2]+".txt");
			File filecards = new File (System.getProperty("user.dir") + "\\" + args[3]+".txt");
			Scanner scan = new Scanner(cmds);
			String cmd=scan.nextLine();
			int pos=0;
					
			for (int i = 0; i<cmds.length(); i++) {
				char c = cmd.charAt(i);
				switch(c) {
					case 'b':
						break;
					case '$':
						break;
					case 'd':
						player.hand.dDraw(pos, filecards);
						player.hand.printCards();
						pos+=5;
						break;
					case 'h':
						pos+=3;
						break;
					case 'a':
						break;
					case 's':
						break;
					 default:
						 break;
				}
			}
		}
		
		else {
			return;
		}
		
		/*
		deck = new Deck();
		player = new Player(1000,deck.deal(5));
		player.bet(10);
		player.showCredit();
		player.hand.printCards();
		*/
	}
}
