package model;

public class NonExistentTechniqueException extends Exception {
	public NonExistentTechniqueException() {
	
	}
	public NonExistentTechniqueException(String nameT, String namecharacter) {
		super("The thecnique with the name " + nameT + " doesn't belong to the character " + namecharacter);
	}

}
