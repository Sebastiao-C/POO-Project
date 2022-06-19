package pok1;


abstract class Player {
	protected int credit;
	protected int numRounds = 0;
	protected int sumBets = 0;
	protected int sumGain = 0;
	protected static final int[] Freqs = new int[12];
	
	public Player(int credit,String cards[]) {
		this.credit=credit;
		for (int i = 0; i < 12; i++) {
			Freqs[i]=0;
		}
		
	}
	
	public void bet(int amount) {
		credit-=amount;
	}
	
	public void win(int amount) {
		credit+=amount;
	}
	
	public void showCredit() {
		System.out.println(credit);
	}
	
	public void addFreq(int index, int bet, int gain) {
		if (index==2 || index==3 || index==4) {
			Freqs[2]++;
		}
		else {
		Freqs[index]++;
		}
		numRounds++;
		sumBets+=bet;
		sumGain+=gain;
	}
	
	public long getRatio() {
		long ratio;
		ratio=Math.round((double)sumGain/(double)sumBets*100);
		return ratio;
	}

}
