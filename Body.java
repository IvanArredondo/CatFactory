package q2;

import java.util.ArrayList;

public class Body extends CatPart {
	
	ArrayList<Leg> hindLegs = new ArrayList<Leg>();
	ArrayList<Leg> foreLegs = new ArrayList<Leg>();
	Tail tail;

	public Body() {
		// TODO Auto-generated constructor stub
	}
	public void whatAmI() {
		System.out.println("I am a Body");
	}
	
	public void addLeg(Leg leg) {
		if(leg.fourToes && hindLegs.size() < 2) {
			hindLegs.add(leg);
		}else if(!leg.fourToes && foreLegs.size() < 2) {
			foreLegs.add(leg);
		}else {
			System.out.println("this body is full of legs already");
		}
	}
	
	public void addTail(Tail tail) {
		this.tail = tail;
	}

}
