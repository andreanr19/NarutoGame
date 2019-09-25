package model;

public class NonExistentClanException extends Exception {

	public NonExistentClanException() {
		
	}
	public NonExistentClanException(String namec) {
		super("The clan with the name " + namec + " doesn't exist.");
	}
}
