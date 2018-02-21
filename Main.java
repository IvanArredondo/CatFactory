package q2;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CatPart leg = new Leg();
		System.out.println("bla1" + leg.getClass());
		leg.whatAmI();
		Bin bin = new Bin(leg);
		CatPart newLeg = bin.getCatPart();
		newLeg.whatAmI();
	}

}
