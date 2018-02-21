package q2Monitors;

import java.util.ArrayList;

public class Head extends CatPart {
	
	ArrayList<Whisker> whiskersList = new ArrayList<Whisker>();
	ArrayList<Eye> eyeList = new ArrayList<Eye>();
	
	public Head() {
		// TODO Auto-generated constructor stub
	}
	public void whatAmI() {
		System.out.println("I am a Head");
	}

	public void addWhisker(Whisker whisker) {
		if(this.whiskersList.size() < 6) {
		this.whiskersList.add(whisker);
		}
	}
	
	public void addEye(Eye eye) {
		if(this.eyeList.size() < 2) {
			this.eyeList.add(eye);
		}
	}
}
