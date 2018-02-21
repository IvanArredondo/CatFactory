package q2Monitors;

import java.util.ArrayList;

public class Leg extends CatPart {
	ArrayList<Toe> toeList = new ArrayList<Toe>();
	boolean fourToes = false;

	public Leg() {
		// TODO Auto-generated constructor stub
	}
	public void whatAmI() {
		System.out.println("I am a Tail");
	}

	public void addToe(Toe toe) {
		if(toeList.size()<5) {
			this.toeList.add(toe);
		}
	}

}
