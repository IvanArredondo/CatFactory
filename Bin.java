package q2Monitors;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Bin {
	CatPart catPart;
	ArrayList catPartList;
	
	public Bin(CatPart catPart) {
		this.catPart = catPart;

		//creating a bin with an arraylist of the type of the bin being created
		if(catPart instanceof Body){
			catPartList = new ArrayList<Body>();
		}
		else if(catPart instanceof Toe){
			catPartList = new ArrayList<Toe>();
		}
		else if(catPart instanceof Eye){
			catPartList = new ArrayList<Eye>();
		}
		else if(catPart instanceof Head){
			catPartList = new ArrayList<Head>();
		}
		else if(catPart instanceof Leg){
			catPartList = new ArrayList<Leg>();
		}
		else if(catPart instanceof Whisker){
			catPartList = new ArrayList<Whisker>();
		}
		else if(catPart instanceof Tail){
			catPartList = new ArrayList<Tail>();
		}else if(catPart instanceof Cat){
			catPartList = new ArrayList<Cat>();
		}else {
			System.out.println("please dont put the catpart superclass");
		}
	}

	public CatPart getCatPart() {
		//returns an infinite number of new instances depending on what type the bin is
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
		}
		else if(catPart instanceof Cat){
			return new Cat();
		}else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addPart(CatPart catPart) {
		//used to add to the list of intermediate cat part objects
		this.catPartList.add(catPart);
	}
}
