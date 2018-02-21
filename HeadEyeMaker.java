package q2Monitors;

import java.util.Random;

public class HeadEyeMaker extends Thread {
	
	Head head;
	Random random;
	private long startTime;
	public long waitingTime = 0;

	public HeadEyeMaker() {
		random = new Random();
	}

	public void run() {
		while(Main.keepMaking.get()) {
			startTime = System.currentTimeMillis();
			synchronized (Main.headWhiskersBin) {
				waitingTime += System.currentTimeMillis()-startTime;
				if(!Main.headWhiskersBin.catPartList.isEmpty()) {	//check to see if theres any heads with whiskers
					head = (Head)Main.headWhiskersBin.catPartList.get(Main.headWhiskersBin.catPartList.size()-1);	//get and remove such a head
					Main.headWhiskersBin.catPartList.remove(Main.headWhiskersBin.catPartList.size()-1);
				}
			}
			if(head == null) {	//if there are no heads with whiskers
				startTime = System.currentTimeMillis();
				synchronized (Main.headBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					head = (Head)Main.headBin.getCatPart();		//get a head from the infinity bin
				}
				getEye();	//calling it twice to ensure only one base part acquired at a time
				getEye();
				startTime = System.currentTimeMillis();
				synchronized (Main.headEyeBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					Main.headEyeBin.addPart(head);	//add a head with eyes to the head eyes bin
				}
			}else {	//if there was a head with whiskers
				getEye();
				getEye();
				startTime = System.currentTimeMillis();
				synchronized (Main.completedHeadBin) {
					waitingTime += System.currentTimeMillis()-startTime;
					Main.completedHeadBin.addPart(head);	//add head to completed head bin and notify the cat maker
					Main.completedHeadBin.notify();
				}
			}
			try {
				Thread.sleep(random.nextInt(21) + 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getEye() {
		startTime = System.currentTimeMillis();
		synchronized (Main.eyeBin) {
			waitingTime += System.currentTimeMillis()-startTime;
			head.addEye((Eye)Main.eyeBin.getCatPart());	//get an eye from the infinite eyes bin
		}
	}

}
