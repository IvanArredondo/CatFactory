package q2;

public class HeadWhiskerMaker extends Thread {

	Head head;

	public HeadWhiskerMaker() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		while(Main.keepMaking.get()) {
			synchronized (Main.headEyeBin) {
				if(!Main.headEyeBin.catPartList.isEmpty()) {
					head = (Head)Main.headEyeBin.catPartList.get(Main.headEyeBin.catPartList.size()-1);
					Main.headEyeBin.catPartList.remove(Main.headEyeBin.catPartList.size()-1);
				}
			}
			if(head == null) {
				synchronized (Main.headBin) {
					head = (Head)Main.headBin.getCatPart();
				}
				getWhisker();	//repeated to ensure fairness
				getWhisker();
				getWhisker();
				getWhisker();
				getWhisker();
				getWhisker();
				synchronized (Main.headWhiskersBin) {
					Main.headWhiskersBin.addPart(head);
				}
			}else {
				getWhisker();	//repeated to ensure fairness
				getWhisker();
				getWhisker();
				getWhisker();
				getWhisker();
				getWhisker();
				synchronized (Main.completedHeadBin) {
					Main.completedHeadBin.addPart(head);
					Main.completedHeadBin.notify();
				}
			}
		}
	}
	
	public void getWhisker() {
		synchronized (Main.whiskerBin) {
			head.addWhisker((Whisker)Main.whiskerBin.getCatPart());
		}
	}

}
