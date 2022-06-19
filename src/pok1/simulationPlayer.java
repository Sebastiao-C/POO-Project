package pok1;

public class simulationPlayer extends Player {
	protected simulationHand simulationHand;
	public simulationPlayer(int credit, String[] cards) {
		super(credit,cards);
		this.simulationHand= new simulationHand(cards);
	}
}
