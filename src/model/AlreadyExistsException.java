package model;

public class AlreadyExistsException extends Exception {
	
	public AlreadyExistsException(String nameTechnique) {
		super("The tehcnique with the name " + nameTechnique + " already exists");
	}

}
