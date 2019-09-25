package model;

public class AlreadyExistentClanException extends Exception {

	public AlreadyExistentClanException() {
		
	}
	public AlreadyExistentClanException(String name) {
		super("The clan with the name " + name + " already exists");
	}
}
