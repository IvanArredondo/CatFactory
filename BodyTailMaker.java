package q2Monitors;

import java.util.Random;

public class BodyTailMaker extends Thread {
	
	Body body;
	Random random;
	private long startTime;
	public long waitingTime = 0;

	public BodyTailMaker() {
		random = new Random();
	}

	public void run() {
		while(Main.keepMaking.get()) {
			startTime = System.currentTimeMillis();
			synchronized (Main.bodyLegBin) {	//get acess to bin with bodies with legs
				waitingTime += System.currentTimeMillis()-startTime;
				if(!Main.bodyLegBin.catPartList.isEmpty()) {	//check to see if theres any availble
					body = (Body)Main.bodyLegBin.catPartList.get(Main.bodyLegBin.catPartList.size()-1);	//get it and remove it from the bin
					Main.bodyLegBin.catPartList.remove(Main.bodyLegBin.catPartList.size()-1);
				}
			}
			if(body == null) {	//if there wasnt any available
				startTime = System.currentTimeMillis();
				synchronized (Main.bodyBin) {
					waitingTime += System.currentTimeMillis()-startTime;	//time spent idle getting the lock
					body = (Body)Main.bodyBin.getCatPart();	//grab a no legs body from the infinite bin
				}
				getTail();
				startTime = System.currentTimeMillis();
				synchronized (Main.bodyTailBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					Main.bodyTailBin.addPart(body);	//add it to the bin with bodies that have tails
				}
			}else {	//if a body with legs was acquired
				getTail();
				startTime = System.currentTimeMillis();
				synchronized (Main.completedBodyBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					Main.completedBodyBin.addPart(body);	//add it to the bin of completed bodies
					Main.completedBodyBin.notify();	//let the cat maker know that theres a completed body
				}
			}
			
			try {
				Thread.sleep(random.nextInt(11) + 10);	//random time sleep according to instructions
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getTail() {
		startTime = System.currentTimeMillis();
		synchronized (Main.tailBin) {	//access the tail bin
			waitingTime += System.currentTimeMillis()-startTime;
			body.addTail((Tail)Main.tailBin.getCatPart());	//add the tail to the body object
		}
	}

}
