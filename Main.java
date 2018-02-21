package q2Monitors;

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
	
	public static long startTime;
	public static long totalTime;

	public Main() {
	}

	public static void main(String[] args) {

		keepMaking = new AtomicBoolean(true);
		counter = new AtomicInteger(0);

		//creating all the bins
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
		
		//creating all the threads/robots
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
		
		startTime = System.currentTimeMillis();
		
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
		
		totalTime = System.currentTimeMillis()-startTime;
		
		System.out.println("Number of cats made: " + completedCatBin.catPartList.size());
		
		System.out.println("Program total time: " + totalTime);
		
		System.out.println("legMaker1 percent of total time idle: " + (double)legMaker1.waitingTime/totalTime*100.0);
		System.out.println("legMaker2 percent of total time idle: " + (double)legMaker2.waitingTime/totalTime*100.0);
		System.out.println("bodyLegMaker1 percent of total time idle: " + (double)bodyLegMaker1.waitingTime/totalTime*100.0);
		System.out.println("bodyLegMaker2 percent of total time idle: " + (double)bodyLegMaker2.waitingTime/totalTime*100.0);
		System.out.println("bodyTailMaker1 percent of total time idle: " + (double)bodyTailMaker1.waitingTime/totalTime*100.0);
		System.out.println("bodyTailMaker2 percent of total time idle: " + (double)bodyTailMaker2.waitingTime/totalTime*100.0);
		System.out.println("headEyeMaker1 percent of total time idle: " + (double)headEyeMaker1.waitingTime/totalTime*100.0);
		System.out.println("headEyeMaker2 percent of total time idle: " + (double)headEyeMaker2.waitingTime/totalTime*100.0);
		System.out.println("headWhiskerMaker1 percent of total time idle: " + (double)headWhiskerMaker1.waitingTime/totalTime*100.0);
		System.out.println("headWhiskerMaker2 percent of total time idle: " + (double)headWhiskerMaker2.waitingTime/totalTime*100.0);
		System.out.println("catMaker percent of total time idle: " + (double)catMaker.waitingTime/totalTime*100.0);
		
	}
	
}
