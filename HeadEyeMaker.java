package q2;

public class HeadEyeMaker extends Thread {
	
	Head head;

	public HeadEyeMaker() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		while(Main.keepMaking.get()) {
			synchronized (Main.headWhiskersBin) {
				if(!Main.headWhiskersBin.catPartList.isEmpty()) {
					head = (Head)Main.headWhiskersBin.catPartList.get(Main.headWhiskersBin.catPartList.size()-1);
					Main.headWhiskersBin.catPartList.remove(Main.headWhiskersBin.catPartList.size()-1);
				}
			}
			if(head == null) {
				System.out.println("null head added");
				synchronized (Main.headBin) {
					head = (Head)Main.headBin.getCatPart();
				}
				getEye();	//calling it twice to ensure only one base part acquired at a time
				getEye();
				synchronized (Main.headEyeBin) {
					Main.headEyeBin.addPart(head);
				}
			}else {
				//System.out.println("non null head added");
				getEye();
				getEye();
				synchronized (Main.completedHeadBin) {
					Main.completedHeadBin.addPart(head);
					Main.completedHeadBin.notify();
				}
			}
		}
	}
	
	public void getEye() {
		synchronized (Main.eyeBin) {
			head.addEye((Eye)Main.eyeBin.getCatPart());
		}
	}

}
