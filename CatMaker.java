package q2;

public class CatMaker extends Thread {
	
	Cat cat;

	public CatMaker() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		while(Main.counter.getAndIncrement() < 250) {
			synchronized (Main.catBin) {	//Unnecessary since 1 thread, but to ensure scaleability
				cat = (Cat)Main.catBin.getCatPart();
			}
			getHead();
			getBody();
			synchronized (Main.completedCatBin) {	//also a bit unneccessary
				Main.completedCatBin.addPart(cat);
			}
			
		}
		Main.keepMaking.set(false);
	}

	private void getHead() {
		synchronized (Main.completedHeadBin) {
//			System.out.println("it thinks empty is: " + Main.completedHeadBin.catPartList.isEmpty() + " with size: " +Main.completedHeadBin.catPartList.size()
//			+ "  here is one of them: " + Main.completedHeadBin.catPartList.get(0).getClass().toString());
			while(Main.completedHeadBin.catPartList.isEmpty()) {
				try {
					System.out.println("waiting for complete cat head");
					Main.completedHeadBin.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			cat.addHead((Head)Main.completedHeadBin.catPartList.get(Main.completedHeadBin.catPartList.size()-1));
			Main.completedHeadBin.catPartList.remove(Main.completedHeadBin.catPartList.size()-1);
		}
		
	}

	private void getBody() {

		synchronized (Main.completedBodyBin) {
			while(Main.completedBodyBin.catPartList.isEmpty()) {
				try {
					System.out.println("waiting");
					Main.completedBodyBin.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			cat.addBody((Body)Main.completedBodyBin.catPartList.get(Main.completedBodyBin.catPartList.size()-1));
			Main.completedBodyBin.catPartList.remove(Main.completedBodyBin.catPartList.size()-1);
		}
		
	}

}
