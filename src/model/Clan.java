package model;

import java.util.ArrayList;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;

public class Clan implements Comparable<Clan>, Serializable {

	@Override
	public String toString() {
		return "Clan [name=" + name + ", first=" + first + ", size=" + size + "]";
	}

	private String name;

	private CharacterII first;
	int size;

	public Clan(String name) {

		this.name = name;
		first = null;
		size = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	// MÉTODO PARA AGREGAR UN PERSONAJE A UN CLAN Y DEJARLO ORDENADO POR NOMBRE DE
	// PERSONAJES
	public void addCharacter(double power, String name, String personality, String date)
			throws ClanException, ParseException {

		CharacterII theNew = new CharacterII(power, name, personality, date);
		if (first == null) { // si la lista está vacia
			first = theNew; // entonces el primero va a ser theNew
			size++;

		} else if (first.compareTo(theNew) > 0) { // si el nombre de first es mayor al de theNew
			first.toLinkBeforeActual(theNew); // entonces enlazar theNew atras de first
			first = theNew; // theNew quedará como el nuevo first
			size++;
		} else if (first.compareTo(theNew) < 0) { // si el nombre de first en menos al de theNew
			CharacterII aux0 = null;
			CharacterII aux1 = first;
			while (aux1 != null && (aux1.compareTo(theNew) < 0)) { // mientras aux no sea null y el nombre de aux sea
																	// menor al de theNew
				aux0 = aux1;
				aux1 = aux1.getNext();
			}
			aux0.toLinkAfterActual(theNew); // insertar theNew despues de aux0
			size++;
		} else {
			throw new ClanException("The character with the name " + name + " already exists in the clan " + this.name);
		}

	}

	// MÉTODO PARA BUSCAR A UN PERSONAJE DEL CLAN DADO SU NOMBRE
	// SI NO EXISTE, LANZA UNA EXCEPCION
	public CharacterII toSearchCharacter(String name) throws NonExistentCharacter {
		CharacterII toreturn = null;
		for (CharacterII c = first; c != null; c = c.getNext()) {
			if (c.getName().equalsIgnoreCase(name)) {
				toreturn = c;
			}
		}
		return toreturn;

	}
	
	//MÉTODO PARA ELIMINAR UN PERSONAJE
	public void toDeleteACharacterGivenItsName(String nameC) throws NonExistentCharacter{
		CharacterII toDelete= toSearchCharacter(nameC);
		CharacterII theBefore = toDelete.getBefore();
		theBefore.unLinkNext();
		System.out.println("The character " + nameC+ " has been deleted from the clan " + this.name);
		
	}

	// MÉTODO PARA DAR EL PRIMER PERSONAJE DEL CLAN
	public CharacterII getTheFirstCharacter() {
		return first;
	}

	public void mostrarPersonajes() {
		CharacterII actual = first;
		String msj = "";
		int i = 1;
		while (actual != null) {
			System.out.println("The character number " + i + " of the clan " + this.name + " is " + actual.getName());
			actual = actual.getNext();
			i++;
		}

	}

	@Override
	public int compareTo(Clan o) {
		return this.getName().compareTo(o.getName());

	}

}
