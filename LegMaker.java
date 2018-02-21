package q2Monitors;

import java.util.Random;

public class LegMaker extends Thread {

	private Leg leg;
	Random random;
	private long startTime;
	public long waitingTime =0;

	public LegMaker() {
		random = new Random();
	}

	public void run() {
		while(Main.keepMaking.get()) {
			startTime = System.currentTimeMillis();
			synchronized (Main.legBin) {
				waitingTime += System.currentTimeMillis()-startTime;
				leg = (Leg) Main.legBin.getCatPart();	//get a leg from the infinite leg bin
			}
			if(random.nextBoolean()) {	//randomly decide whether it will be a hindleg or a foreleg
				getToe();	//doing one at a time for fairness a
				getToe();
				getToe();
				getToe();
				leg.fourToes = true;
				startTime = System.currentTimeMillis();
				synchronized (Main.hindLegBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					Main.hindLegBin.addPart(leg);	//add a hindleg to the hindleg bin
					Main.hindLegBin.notify();
				}
			}else {//foreleg
				getToe();
				getToe();
				getToe();
				getToe();
				getToe();
				leg.fourToes = false;
				startTime = System.currentTimeMillis();
				synchronized (Main.foreLegBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					Main.foreLegBin.addPart(leg);
					Main.foreLegBin.notify();
				}
			}

			try {
				Thread.sleep(random.nextInt(11) + 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	
	public void getToe() {
		startTime = System.currentTimeMillis();
		synchronized (Main.toeBin) {
			waitingTime += System.currentTimeMillis()-startTime;
			leg.addToe((Toe)Main.toeBin.getCatPart());	//add a toe to the leg from the infinite toe bin
		}
	}
}
