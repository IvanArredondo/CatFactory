package q2;

import java.util.Random;

public class LegMaker extends Thread {
	
	private Leg leg;
	private Random random;

	public LegMaker() {
		random = new Random();
	}
	
	public void run() {
		while(Main.keepMaking.get()) {
			synchronized (Main.legBin) {
				leg = (Leg) Main.legBin.getCatPart();
			}
			if(random.nextBoolean()) {
				leg.addToe(new Toe());
				leg.addToe(new Toe());
				leg.addToe(new Toe());
				leg.addToe(new Toe());
				leg.fourToes = true;
				synchronized (Main.hindLegBin) {
					Main.hindLegBin.addPart(leg);;
					Main.hindLegBin.notify();
				}
			}else {
				leg.addToe(new Toe());
				leg.addToe(new Toe());
				leg.addToe(new Toe());
				leg.addToe(new Toe());
				leg.addToe(new Toe());
				leg.fourToes = false;
				synchronized (Main.foreLegBin) {
					Main.foreLegBin.addPart(leg);
					Main.foreLegBin.notify();
				}
			}
			
			
			
		}
	}
}
