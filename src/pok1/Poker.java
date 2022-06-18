package pok1;
import java.io.*;
import java.util.Scanner;

public abstract class Poker {
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Deck deck;
		Player player;
		Table table;
		
		if (args[0].equals("-d")){
			String cards[] = new String[5];
			deck = new Deck();
			table = new Table();
			File cmds = new File (System.getProperty("user.dir")+ "\\" + args[2]+".txt");
			File filecards = new File (System.getProperty("user.dir") + "\\" + args[3]+".txt");
			player = new Player(Integer.parseInt(args[1]),cards,filecards);
			Scanner scan = new Scanner(cmds);
			String cmd=scan.nextLine();
			scan.close();
			String[] readcmd = cmd.split(" ");
			int pos = 0; //Posicao atual da leitura no ficheiro//
			int bet = 5; //Bet default//
			boolean canBet = true; // Valores logicos para a possibilidade de execucao de comandos //
			boolean canDeal = false;
			boolean canHold = false;
			int gain = 0;
					
			for (int i = 0; i < readcmd.length; i++) {
				String c = readcmd[i];
				System.out.println(c);
				switch(c) {
					case "b":
						if (canBet) {
							String toBet = readcmd[i+1];
							if (!(isNumeric(toBet))) {
								player.bet(bet);
								System.out.println("-cmd b");
								System.out.println("player is betting " + bet);
								canBet = false;
								canDeal = true;
							}
							else {
								System.out.println("-cmd b " + toBet);
								if ((Integer.valueOf(toBet) < 1) || (Integer.valueOf(toBet) > 5)){
									System.out.println("b: illegal amount");
								}
								else {
									bet=Integer.valueOf(toBet);
									player.bet(bet);
									System.out.println("player is betting " + bet);
									canBet = false;
									canDeal = true;
								}
								i++;
								c = readcmd[i];
							}
						}
						else {
							System.out.println("b: illegal command");
						}
						System.out.println();
						break;
					case "$":
						System.out.println("-cmd $");
						System.out.print("player's credit is ");
						player.showCredit();
						System.out.println();
						break;
					case "d":
						if (canDeal && pos+5 <= player.debugHand.numCardsFile) {
							player.debugHand.draw(pos);
							pos+=5;
							System.out.println("-cmd d");
							System.out.print("player's hand ");
							player.hand.printCards();
							canDeal = false;
							canHold = true;
						}
						else {
							System.out.println("d: illegal command");
						}
						System.out.println();
						break;
					case "h":
						if (canHold) {
							int indexes[] = new int[5];
							int j=0;
							int add=5;
							i++;
							c = readcmd[i];
							while (isNumeric(c)) {
								indexes[j]=Integer.valueOf(c);
								j++;
								add--;
								if (add!=0) {
									i++;
									c = readcmd[i];
								}
								
								else {
									break;
								}
							}
							if (pos+add<=player.debugHand.numCardsFile) {
								player.debugHand.hold(indexes, pos);
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
								player.hand.setCards(Combos.sortCards(player.hand.getCards()));
								int handIndex = Combos.getTableIndex(player.hand.getCards());
								if (handIndex == 11) {
									System.out.print("player loses and his credit is ");
									player.showCredit();
								}
								else {
									gain=table.checkTableBet(handIndex, bet);
									player.win(gain);
									System.out.print("player wins with a " + table.checkTableName(handIndex) + " and his credit is ");
									player.showCredit();
								}
								player.addFreq(handIndex,bet,gain);
								canHold = false;
								canBet = true;
							}
							else {
								System.out.println("h: illegal command");
							}
						}
						else {
							System.out.println("h: illegal command");
						}
						System.out.println();
						break;
					case "a":
						break;
					case "s":
						System.out.println();
						player.getStatistics();
						break;
					 default:
						System.out.println("Command with no effect");
						System.out.println();
						break;
				}
			}
		}
		
		else {
			return;
		}
	}
}
