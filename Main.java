package q2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	static Bin bodyBin;
	static Bin eyeBin;
	static Bin headBin;
	static Bin legBin;
	static Bin tailBin;
	static Bin toeBin;
	static Bin whiskerBin;
	static Bin catBin;

	static Bin foreLegBin;
	static Bin hindLegBin;
	static Bin bodyTailBin;
	static Bin bodyLegBin;
	static Bin completedBodyBin;
	static Bin headWhiskersBin;
	static Bin headEyeBin;
	static Bin completedHeadBin;
	
	static Bin completedCatBin;
	
	

	static AtomicInteger counter;
	static AtomicBoolean keepMaking; 

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		keepMaking = new AtomicBoolean(true);
		counter = new AtomicInteger(0);


		bodyBin = new Bin(new Body());
		eyeBin = new Bin(new Eye());
		headBin = new Bin(new Head());
		legBin = new Bin(new Leg());
		tailBin = new Bin(new Tail());
		toeBin = new Bin(new Toe());
		whiskerBin = new Bin(new Whisker());
		catBin = new Bin(new Cat());

		foreLegBin = new Bin(new Leg());
		hindLegBin = new Bin(new Leg());
		bodyTailBin = new Bin(new Body());
		bodyLegBin = new Bin(new Body());
		completedBodyBin = new Bin(new Body());
		headWhiskersBin = new Bin(new Head());
		headEyeBin = new Bin(new Head());
		completedHeadBin = new Bin(new Head());
		
		completedCatBin = new Bin(new Cat());
		
		LegMaker legMaker1 = new LegMaker();
		LegMaker legMaker2 = new LegMaker();

		BodyLegMaker bodyLegMaker1 = new BodyLegMaker();
		BodyLegMaker bodyLegMaker2 = new BodyLegMaker();
		
		BodyTailMaker bodyTailMaker1 = new BodyTailMaker();
		BodyTailMaker bodyTailMaker2 = new BodyTailMaker();

		HeadEyeMaker headEyeMaker1 = new HeadEyeMaker();
		HeadEyeMaker headEyeMaker2 = new HeadEyeMaker();
		
		HeadWhiskerMaker headWhiskerMaker1 = new HeadWhiskerMaker();
		HeadWhiskerMaker headWhiskerMaker2 = new HeadWhiskerMaker();
		
		CatMaker catMaker = new CatMaker();
		
		legMaker1.start();
		legMaker2.start();
		bodyLegMaker1.start();
		bodyLegMaker2.start();
		bodyTailMaker1.start();
		bodyTailMaker2.start();
		headEyeMaker1.start();
		headEyeMaker2.start();
		headWhiskerMaker1.start();
		headWhiskerMaker2.start();
		catMaker.start();
	
				try {
					Thread.sleep(2000);
					keepMaking.set(false);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		try {
			legMaker1.join();
			legMaker2.join();
			bodyLegMaker1.join();
			bodyLegMaker2.join();
			bodyTailMaker1.join();
			bodyTailMaker2.join();
			headEyeMaker1.join();
			headEyeMaker2.join();
			headWhiskerMaker1.join();
			headWhiskerMaker2.join();
			catMaker.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("bodies : " + completedCatBin.catPartList.size());
		for (int i = 0; i < completedCatBin.catPartList.size(); i++) {
			System.out.println(i);
		}
	}
	
}
