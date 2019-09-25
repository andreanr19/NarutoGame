package model;

import java.io.Serializable;
import java.util.Comparator;

public class Technique implements Comparator<Technique>, Serializable {

	@Override
	public String toString() {
		return "Technique [name=" + name + ", powerFactor=" + powerFactor + ", next=" + next + "]";
	}

	private String name;
	private double powerFactor;

	private Technique next;

	public Technique(String name, double powerFactor) {
		this.name = name;
		this.powerFactor = powerFactor;
		this.next = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPowerFactor() {
		return powerFactor;
	}

	public void setPowerFactor(double powerFactor) {
		this.powerFactor = powerFactor;
	}

	// si es null es porque no hay lista
	public Technique getNext() {
		return next;
	}

	public void setNext(Technique next) {
		this.next = next;
	}

	// método para desconectar el siguiente elemento
	public void disconnected() {

		if (next != null) {
			next = next.getNext();
		} else {
			System.out.println("This is the last technique of the character " + name);
		}
	}

	// enlazar al Siguiente
	public void linkNext(Technique t) {
		next = t;
	}
	
	
	//ENLAZAR DESPUÉS DEL ACTUAL
	public void linkAfterOf(Technique t) {
		t.next= next; //el siguiente de la técnica t es el siguiente del actual
		next= t; // el siguiente de la técnica actual es t
	}

	//desconectar siguiente
	public void unlinkNext() {
		next= next.next;
	}
	
	//cambiar siguiente (cambia el paciente que le sigue al actual)
	public void changeNext(Technique t) {
		next=t;
	}

	//menor a mayor
	@Override
	public int compare(Technique t1, Technique t2) {

		if(t1.getPowerFactor()>t2.getPowerFactor()) return 1; //cuando t1 es mayor, devuelve 1 para ponerla adelante
		if(t1.getPowerFactor()<t2.getPowerFactor()) return -1; // cuando t2 es mayor, devuelve -1 para ponerla de siguiente
		return 0;
	}
}
