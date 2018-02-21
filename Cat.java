package q2;

public class Cat extends CatPart {
	
	Body body;
	Head head;

	public Cat() {
	}

	public void whatAmI() {
		System.out.println("I am a cat");
	}
	
	public void addBody(Body body) {
		this.body = body;
	}
	
	public void addHead(Head head) {
		this.head = head;
	}
}
