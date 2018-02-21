package q2Monitors;

import java.util.ArrayList;

public class Body extends CatPart {
	
	ArrayList<Leg> hindLegs = new ArrayList<Leg>();
	ArrayList<Leg> foreLegs = new ArrayList<Leg>();
	private Tail tail;	//will be added by robot

	public Body() {
		// TODO Auto-generated constructor stub
	}
	public void whatAmI() {
		System.out.println("I am a Body");
	}
	
	public void addTail(Tail tail) {
		this.tail = tail;
	}

}
