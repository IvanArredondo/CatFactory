package q2Monitors;

import java.util.Random;

public class BodyLegMaker extends Thread {

	Body body;
	Random random;
	private long startTime;
	public long waitingTime =0;

	public BodyLegMaker() {
		random = new Random();
	}

	public void run() {
		while(Main.keepMaking.get()) {
			startTime = System.currentTimeMillis();	//starts timer
			synchronized (Main.bodyTailBin) {
				waitingTime += System.currentTimeMillis()-startTime;	//after acquiring the lock, not idle anymore
				if(!Main.bodyTailBin.catPartList.isEmpty()) {	//checks if theres any free bodies with tails
					body = (Body) Main.bodyTailBin.catPartList.get(Main.bodyTailBin.catPartList.size() -1 );	//gets the last element added to the body and tail cat part array
					Main.bodyTailBin.catPartList.remove(Main.bodyTailBin.catPartList.size() -1);	//removes the element that was retrieved, if I had time i would have changed all of these to remove() which also returns the object
				}
			}
			if(body == null) {	//if nothing was retrieved, ie. no bodies with tails ready
				startTime = System.currentTimeMillis();
				synchronized (Main.bodyBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					body = (Body)Main.bodyBin.getCatPart();	//get a new body from the infinity bin
				}
				getForeLeg();	//doing one at a time as per instructions
				getHindLeg();	//iterating between hind and fore leg for hopefully a small speedup
				getForeLeg();
				getHindLeg();
				startTime = System.currentTimeMillis();
				synchronized (Main.bodyLegBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					Main.bodyLegBin.addPart(body);	//adding to the bin of just bodies and legs (no tail)
				}
			}else {	//this is if a body with a tail was retrieved
				getForeLeg();	//adding legs
				getHindLeg();
				getForeLeg();
				getHindLeg();
				startTime = System.currentTimeMillis();
				synchronized (Main.completedBodyBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					Main.completedBodyBin.addPart(body);		//adding to the completed body array in the bin
					Main.completedBodyBin.notify();	//letting know the catmaker that a completed body is available
				}
			}

			try {
				Thread.sleep(random.nextInt(31) + 20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void getForeLeg() {
		startTime = System.currentTimeMillis();
		synchronized (Main.foreLegBin) {	//only one thread can get from any bin at a time
			while(Main.foreLegBin.catPartList.isEmpty() && Main.keepMaking.get()) {	//if theres non available and still cats to be made, wait
				try {
					Main.foreLegBin.wait();	//if theres non available, wait
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!Main.keepMaking.get()) {
					break;
				}
			}
			waitingTime += System.currentTimeMillis()-startTime;	//record the time spent waiting
			if(Main.keepMaking.get()) {	//if theres cats left to be made
				body.foreLegs.add((Leg)Main.foreLegBin.catPartList.get(Main.foreLegBin.catPartList.size()-1));	//get them from the bin and delete it
				Main.foreLegBin.catPartList.remove(Main.foreLegBin.catPartList.size()-1);
			}
		}
	}
	public void getHindLeg() {
		startTime = System.currentTimeMillis();
		synchronized (Main.hindLegBin) {
			while(Main.hindLegBin.catPartList.isEmpty()  && Main.keepMaking.get()) { //if theres non available and still cats to be made, wait
				try {
					Main.hindLegBin.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!Main.keepMaking.get()) {
					break;
				}
			}
			waitingTime += System.currentTimeMillis()-startTime;	//record the time spent waiting
			if(Main.keepMaking.get()) {
				body.hindLegs.add((Leg) Main.hindLegBin.catPartList.get(Main.hindLegBin.catPartList.size()-1));
				Main.hindLegBin.catPartList.remove(Main.hindLegBin.catPartList.size()-1);
			}
		}
	}
}
