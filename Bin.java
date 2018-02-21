package q2;

import java.lang.reflect.InvocationTargetException;

public class Bin {
	CatPart catPart;
	public Bin(CatPart catPart) {
		this.catPart = catPart;
	}

	public CatPart getCatPart() {
		if(catPart instanceof Body){
			return new Body();
		}
		else if(catPart instanceof Toe){
			return new Toe();
		}
		else if(catPart instanceof Eye){
			return new Eye();
		}
		else if(catPart instanceof Head){
			return new Head();
		}
		else if(catPart instanceof Leg){
			return new Leg();
		}
		else if(catPart instanceof Whisker){
			return new Whisker();
		}
		else if(catPart instanceof Tail){
			return new Tail();
		}else {
			return null;
		}
	}
}
