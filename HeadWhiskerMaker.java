package q2Monitors;

import java.util.Random;

public class HeadWhiskerMaker extends Thread {

	Head head;
	Random random;
	private long startTime;
	public long waitingTime = 0;

	public HeadWhiskerMaker() {
		random = new Random();
	}

	public void run() {
		while(Main.keepMaking.get()) {
			startTime = System.currentTimeMillis();
			synchronized (Main.headEyeBin) {
				waitingTime += System.currentTimeMillis()-startTime;
				if(!Main.headEyeBin.catPartList.isEmpty()) {	//checking to see if theres any head with eyes in the bin
					head = (Head)Main.headEyeBin.catPartList.get(Main.headEyeBin.catPartList.size()-1);	
					Main.headEyeBin.catPartList.remove(Main.headEyeBin.catPartList.size()-1);
				}
			}
			if(head == null) {	//if theres no heads with eyes
				startTime = System.currentTimeMillis();
				synchronized (Main.headBin) {
					waitingTime += System.currentTimeMillis()-startTime;	//time spent waiting for the lock
					head = (Head)Main.headBin.getCatPart();	//grab a head from the infinite heads bin
				}
				getWhisker();	//repeated to ensure fairness
				getWhisker();
				getWhisker();
				getWhisker();
				getWhisker();
				getWhisker();
				startTime = System.currentTimeMillis();
				synchronized (Main.headWhiskersBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					Main.headWhiskersBin.addPart(head);	//add the head with whiskers to the head whiskers bin
				}
			}else {	//if there was a head with whiskers available
				getWhisker();	//repeated to ensure fairness
				getWhisker();
				getWhisker();
				getWhisker();
				getWhisker();
				getWhisker();
				startTime = System.currentTimeMillis();
				synchronized (Main.completedHeadBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					Main.completedHeadBin.addPart(head);	//add it to the completed head bin and notify the cat maker
					Main.completedHeadBin.notify();
				}
			}
			try {
				Thread.sleep(random.nextInt(41) + 20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getWhisker() {
		startTime = System.currentTimeMillis();
		synchronized (Main.whiskerBin) {
			waitingTime += System.currentTimeMillis()-startTime;
			head.addWhisker((Whisker)Main.whiskerBin.getCatPart());	//get whiskers from the infinite whiskers bin
		}
	}

}
