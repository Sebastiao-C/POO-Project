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
			String[] readcmd = cmd.split(" ");
			int pos=0;
					
			for (int i = 0; i < readcmd.length; i++) {
				String c = readcmd[i];
				switch(c) {
					case "b":
						String bet = readcmd[i+1];
						if (bet.equals("b") || bet.equals("$") || bet.equals("d") || bet.equals("h") || bet.equals("a") || bet.equals("s")) {
							bet="5";
							player.bet(5);
							System.out.println("-cmd b");
						}
						else {
							player.bet(Integer.valueOf(bet));
							System.out.println("-cmd b " + bet);
							if ((Integer.valueOf(bet) < 1) || (Integer.valueOf(bet) > 5)){
								System.out.println("b: illegal amount");
								break;
							}
						}
						System.out.println("player is betting " + bet);
						System.out.println();
						break;
					case "$":
						System.out.println("-cmd $");
						System.out.print("player's credit is ");
						player.showCredit();
						System.out.println();
						break;
					case "d":
						player.debugHand.draw(pos, filecards);
						pos+=5;
						System.out.println("-cmd d");
						System.out.print("player's hand ");
						player.hand.printCards();
						System.out.println();
						break;
					case "h":
						int indexes[] = new int[5];
						int j=0;
						int add=5;
						i++;
						c = readcmd[i];
						while (!(c.equals("b") || c.equals("$") || c.equals("d") || c.equals("h") || c.equals("a") || c.equals("s")) ) {
							indexes[j]=Integer.valueOf(c);
							j++;
							add--;
							if (i+1 < readcmd.length) {
								i++;
								c = readcmd[i];
							}
							else {
								break;
							}
						}
						i--;
						player.debugHand.hold(indexes, pos, filecards);
						pos+=add;
						System.out.print("-cmd h ");
						for (int k = 0; k < indexes.length; k++) {
							if (indexes[k]!=0) {
								System.out.print(indexes[k] + " ");
							}
						}
						System.out.println();
						System.out.print("player's hand ");
						player.hand.printCards();
						System.out.println();
						break;
					case "a":
						break;
					case "s":
						break;
					 default:
						 break;
				}
			}
		}
		
		else {
			return;
		}
	}
}
