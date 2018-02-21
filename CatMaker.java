package q2Monitors;

import java.util.Random;

public class CatMaker extends Thread {
	
	private Cat cat;
	private Random random;
	private long startTime;
	public long waitingTime = 0;

	public CatMaker() {
		random = new Random();
	}

	public void run() {
		while(Main.counter.getAndIncrement() < 250) {
			synchronized (Main.catBin) {	//Unnecessary since 1 thread, but to ensure scaleability
				cat = (Cat)Main.catBin.getCatPart();
			}
			getHead();
			getBody();
			synchronized (Main.completedCatBin) {	//also a bit unneccessary, but if needed if more cat maker robot/threads are needed
				Main.completedCatBin.addPart(cat);
			}
			try {
				Thread.sleep(random.nextInt(11) + 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Main.keepMaking.set(false);	//let all the robots know to stop
		synchronized (Main.foreLegBin) {
			Main.foreLegBin.notifyAll();	//if there was some waiting for a leg type to be made, free them 
		}
		synchronized (Main.hindLegBin) {
			Main.hindLegBin.notifyAll();
		}
		
		
	}

	private void getHead() {
		startTime = System.currentTimeMillis();
		synchronized (Main.completedHeadBin) {
			while(Main.completedHeadBin.catPartList.isEmpty()) {	//if there are no completed head, wait
				try {
					Main.completedHeadBin.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			waitingTime += System.currentTimeMillis()-startTime;
			cat.addHead((Head)Main.completedHeadBin.catPartList.get(Main.completedHeadBin.catPartList.size()-1));	//add a head to the cat 
			Main.completedHeadBin.catPartList.remove(Main.completedHeadBin.catPartList.size()-1);	//remove head from bin
		}
		
	}

	private void getBody() {
		startTime = System.currentTimeMillis();
		synchronized (Main.completedBodyBin) {
			while(Main.completedBodyBin.catPartList.isEmpty()) {	//if there are no completed bodies, wait
				try {
					Main.completedBodyBin.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			waitingTime += System.currentTimeMillis()-startTime;
			cat.addBody((Body)Main.completedBodyBin.catPartList.get(Main.completedBodyBin.catPartList.size()-1));	//adding a body to the cat
			Main.completedBodyBin.catPartList.remove(Main.completedBodyBin.catPartList.size()-1);
		}
		
	}

}
