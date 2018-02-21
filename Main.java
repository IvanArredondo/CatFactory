package q2;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
	
	static Bin bodyBin;
	static Bin eyeBin;
	static Bin headBin;
	static Bin legBin;
	static Bin tailBin;
	static Bin toeBin;
	static Bin whiskerBin;
	
	static Bin foreLegBin;
	static Bin hindLegBin;
	static Bin bodyTailBin;
	static Bin bodyLegbin;
	static Bin completedBodyBin;
	static Bin headBodyBin;
	static Bin eyeBodyBin;
	static Bin completedHeadBin;
	
	
	
	public static AtomicBoolean keepMaking; 

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		keepMaking = new AtomicBoolean(true);
		
		bodyBin = new Bin(new Body());
		eyeBin = new Bin(new Eye());
		headBin = new Bin(new Head());
		legBin = new Bin(new Leg());
		tailBin = new Bin(new Tail());
		toeBin = new Bin(new Toe());
		whiskerBin = new Bin(new Whisker());
		
		foreLegBin = new Bin(new Leg());
		hindLegBin = new Bin(new Leg());
		bodyTailBin = new Bin(new Body());
		bodyLegbin = new Bin(new Body());
		completedBodyBin = new Bin(new Body());
		headBodyBin = new Bin(new Head());
		eyeBodyBin = new Bin(new Head());
		completedHeadBin = new Bin(new Head());
		
		BodyLegMaker bodyLegMaker1 = new BodyLegMaker();
		BodyLegMaker bodyLegMaker2 = new BodyLegMaker();
		BodyTailMaker bodyTailMaker1 = new BodyTailMaker();
		BodyTailMaker bodyTailMaker2 = new BodyTailMaker();
		
		CatMaker catMaker = new CatMaker();
		
		HeadEyeMaker headEyeMaker1 = new HeadEyeMaker();
		HeadEyeMaker headEyeMaker2 = new HeadEyeMaker();
		HeadWhiskerMaker headWhiskerMaker1 = new HeadWhiskerMaker();
		HeadWhiskerMaker headWhiskerMaker2 = new HeadWhiskerMaker();
		LegMaker legMaker = new LegMaker();
		
		CatPart leg = new Leg();
		System.out.println("bla1" + leg.getClass());
		leg.whatAmI();
		Bin bin = new Bin(leg);
		CatPart newLeg = bin.getCatPart();
		newLeg.whatAmI();
	}

}
