package q2;

import java.util.Random;

public class BodyTailMaker extends Thread {
	
	Body body;

	public BodyTailMaker() {
		
	}

	public void run() {
		while(Main.keepMaking.get()) {
			synchronized (Main.bodyLegBin) {
				if(!Main.bodyLegBin.catPartList.isEmpty()) {
					body = (Body)Main.bodyLegBin.catPartList.get(Main.bodyLegBin.catPartList.size()-1);
					Main.bodyLegBin.catPartList.remove(Main.bodyLegBin.catPartList.size()-1);
				}
			}
			if(body == null) {
				synchronized (Main.bodyBin) {
					body = (Body)Main.bodyBin.getCatPart();
				}
				getTail();
				synchronized (Main.bodyTailBin) {
					Main.bodyTailBin.addPart(body);
				}
			}else {
				getTail();
				synchronized (Main.completedBodyBin) {
					Main.completedBodyBin.addPart(body);
					Main.completedBodyBin.notify();
				}
			}
		}
	}
	
	public void getTail() {
		synchronized (Main.tailBin) {
			body.tail = (Tail)Main.tailBin.getCatPart();
		}
	}

}
