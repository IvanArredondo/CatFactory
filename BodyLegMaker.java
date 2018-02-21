package q2;

import java.util.Random;

public class BodyLegMaker extends Thread {
	
	Body body;
	private Random random;

	public BodyLegMaker() {
		random = new Random();
	}

	public void run() {
		while(Main.keepMaking.get()) {
			synchronized (Main.bodyTailBin) {
				if(Main.bodyTailBin.catPartList.size() > 0) {
					body = (Body) Main.bodyTailBin.catPartList.get(Main.bodyTailBin.catPartList.size() -1 );
					Main.bodyTailBin.catPartList.remove(Main.bodyTailBin.catPartList.size() -1);
				}
			}
			if(body == null) {
				synchronized (Main.bodyBin) {
					body = (Body)Main.bodyBin.getCatPart();
				}
				getForeLeg();
				getHindLeg();
				getForeLeg();
				getHindLeg();
				synchronized (Main.bodyLegbin) {
					Main.bodyLegbin.addPart(body);
				}
			}else {
				getForeLeg();
				getHindLeg();
				getForeLeg();
				getHindLeg();
				synchronized (Main.completedBodyBin) {
					Main.completedBodyBin.addPart(body);
				}
			}
			
			
		}
	}
	
	public void getForeLeg() {
		synchronized (Main.foreLegBin) {
			while(Main.foreLegBin.catPartList.size() == 0) {
				try {
					Main.foreLegBin.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			body.foreLegs.add((Leg)Main.foreLegBin.catPartList.get(Main.foreLegBin.catPartList.size()-1));
			Main.foreLegBin.catPartList.remove(Main.foreLegBin.catPartList.size()-1);
		}
	}
	public void getHindLeg() {
		synchronized (Main.hindLegBin) {
			while(Main.hindLegBin.catPartList.size() == 0) {
				try {
					Main.hindLegBin.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			body.hindLegs.add((Leg) Main.hindLegBin.catPartList.get(Main.hindLegBin.catPartList.size()-1));
			Main.hindLegBin.catPartList.remove(Main.hindLegBin.catPartList.size()-1);
		}
	}
}
