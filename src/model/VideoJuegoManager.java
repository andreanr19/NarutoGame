package model;

import java.io.Serializable;
import java.util.*;

public class VideoJuegoManager implements Serializable {

	@Override
	public String toString() {
		return "VideoJuegoManager [size=" + size + ", clanes=" + clanes + "]";
	}

	private int size;
	private ArrayList<Clan> clanes;

	public VideoJuegoManager() {
		clanes = new ArrayList<Clan>();
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ArrayList<Clan> getClanes() {
		return clanes;
	}

	public void setClanes(ArrayList<Clan> clanes) {
		this.clanes = clanes;
	}

	// MÉTODO PARA AGREGAR UN CLAN
	public void addClanes(Clan c) throws AlreadyExistentClanException {
		for (int i = 0; i < clanes.size(); i++) {
			if (clanes.get(i).getName().equals(c.getName())) {
				throw new AlreadyExistentClanException(c.getName());
			} else {
				clanes.add(c);
				System.out.println("The clan " + c.getName() + " has been added.");
				size++;
			}
		}
	}

	public void verifyIsTheresAClanAndADD(Clan c) throws AlreadyExistentClanException {
		boolean repeated = false;
		for (int i = 0; i < clanes.size() && !repeated; i++) {
			if (c.getName().equalsIgnoreCase(clanes.get(i).getName())) {
				repeated = true;

				throw new AlreadyExistentClanException(c.getName());

			} 
		}if(repeated==false) {
			addClantwo(c);
		}
	}

	
	public void addClantwo(Clan c) {
		clanes.add(c);
		size++;
	}

	// MÉTODO PARA ELIMINAR UN CLAN
	public void toDeleteAClan(String nameClan) throws NonExistentClanException {
		boolean removed = false;
		for (int i = 0; i < clanes.size() && !removed; i++) {
			if (clanes.get(i).getName().equals(nameClan)) {
				clanes.remove(i);
				size--;
				removed = true;
			}
		}
		if (removed == false) {
			throw new NonExistentClanException(nameClan);
		}
	}

	// MÉTODO PARA BUSCAR UN CLAN DADO SU NOMBRE
	public Clan searchAClan(String name) throws NonExistentClanException {
		boolean found = false;
		Clan togive = null;
		for (int i = 0; i < clanes.size() && !found; i++) {
			if (clanes.get(i).getName().equals(name)) {
				togive = clanes.get(i);
				found = true;
			}

		}
		if (found == false) {
			throw new NonExistentClanException(name);
		}
		return togive;
	}

	// MÉTODO PARA MOSTRAR LOS CLANES
	public String showClanes() {
		String msj = "";
		for (int i = 0; i < clanes.size(); i++) {
			msj += clanes.get(i).getName() + "\n";
		}
		if (size == 0) {
			msj += "There's no clans yet.";
		}
		return msj;
	}

	// ORDENA LOS CLANES POR EL MÉTODO DE BURBUJA POR EL NUMERO DE PERSONAJES QUE
	// TIENE
	public void bubbleSortByCharactersAmount() {
		FromMinorToGreaterComparator comparator = new FromMinorToGreaterComparator();
		for (int i = 0; i < clanes.size(); i++) {
			for (int j = 0; j < clanes.size() - 1; j++) {
				if (comparator.compare(clanes.get(j), clanes.get(j + 1)) > 0) {
					Clan temp = clanes.get(j);
					clanes.set(j, clanes.get(j + 1));
					clanes.set(j + 1, temp);
				}
			}
		}
	}

	// ORDENA LOS CLANES POR EL MÉTODO DE SELECCION POR EL NOMBRE
	public void selectionSort() {
		for (int i = 1; i < clanes.size(); ++i) {
			Clan temp = clanes.get(i);
			int j = i - 1;
			while (j >= 0 && clanes.get(j).compareTo(temp) > 0) {
				clanes.set(j + 1, clanes.get(j));
				j = j - 1;
			}
			clanes.set(j + 1, temp);

		}
	}
	
	//ACTUALIZAR EL NOMBRE DE UN CLAN
	public void toUpdateAClanName(String nameC, String nameUpdated) throws NonExistentClanException{
		boolean found= false;
		System.out.println("El tamaño del arreglo es de :"+clanes.size());
		for(int i=0; i< clanes.size(); i++) {
			System.out.println(clanes.get(i).getName() + " " + nameC);
			if(clanes.get(i).getName().equals(nameC)) {
				clanes.get(i).setName(nameUpdated);
				found=true;
				System.out.println("The name of the clan has been changed to " + nameUpdated);
			}
		}
	}

}
