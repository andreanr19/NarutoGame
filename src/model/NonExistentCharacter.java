package model;

public class NonExistentCharacter extends Exception {

	public NonExistentCharacter() {
		
	}
	public NonExistentCharacter(String nameC, String clanName) {
		super("The character with the name " + nameC + " doesn't exist in the clan " +clanName );
	}
	
}
