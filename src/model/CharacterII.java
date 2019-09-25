package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CharacterII implements Comparable<CharacterII> , Serializable{
	@Override
	public String toString() {
		return "CharacterII [power=" + power + ", name=" + name + ", personality=" + personality + ", creationDate="
				+ creationDate + ", size=" + size + ", next=" + next + ", before=" + before + ", first=" + first + "]";
	}

	private double power;
	private String name;
	private String personality;
//	private int dayOfCreation;
//	private int monthOfCreation;
//	private int yearOfCreation;
	private Date creationDate;
	int size;

	private CharacterII next;
	private CharacterII before;

	private Technique first; // cabeza de la lista

	public CharacterII(double power, String name, String personality, String creationDate) throws ParseException {

		this.power = power;
		this.name = name;
		this.personality = personality;
//		this.dayOfCreation = dayOfCreation;
//		this.monthOfCreation = monthOfCreation;
//		this.yearOfCreation = yearOfCreation;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.creationDate = sdf.parse(creationDate);
		first = null; // cabeza de la lista
		size = 0;

	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}


	public CharacterII getNext() {
		return next;
	}

	public void setNext(CharacterII next) {
		this.next = next;
	}

	public CharacterII getBefore() {
		return before;
	}

	public void setBefore(CharacterII before) {
		this.before = before;
	}
	
	//DESCONECTAR SIGUIENTE
	public void unLinkNext() {
		next=next.next;
	}

	// retorna el tamaño de la lista de tecnicas
	public int getSize() {
		return size;
	}

	// PARA OBTENER UNA TECNICA DADO EL INDICE
	public Technique toGetATechnique(int index) {
		int contador = 0;
		Technique temp = first;
		while (contador < index) {
			temp = temp.getNext();
			contador++;
		}
		return temp;
	}

	// método para localizar una técnica cualquiera dado su nombre, si no existe
	// retorna null
	public Technique toLocalizeATechniqueGivenItsName(String name) {
		Technique actual = first;
		while (actual != null && !actual.getName().equals(name)) {
			actual = actual.getNext();

		}
		return actual;

	}

	// método para agregar después de una técnica cualquiera
	public void addWhereverAfterOf(String name, Technique t) throws NonExistentTechniqueException {
		Technique beforeOfTheNewOne = toLocalizeATechniqueGivenItsName(name);
		if (beforeOfTheNewOne == null) {// si la lista está vacia lanza una excepción
			throw new NonExistentTechniqueException(name, this.name);

		} else {
			Technique toLinkWithT = beforeOfTheNewOne.getNext();
			t.linkNext(toLinkWithT);
			beforeOfTheNewOne.linkNext(t);
		}
		size++;

	}

	// método para añadir primer tecnica a la lista
	public void addFirstII(Technique t) {
		if (first == null) {// si la lista está vacia
			first = t;

		} else { // cuando la lista no está vacia
			Technique temp = first; // temporal que apunta al primer elemento de la lista
			Technique theNew = t;
			theNew.linkNext(temp); // enlazar el nuevo con la cabeza actual convirtiendose en la cabeza de la lista
			first = theNew;
		}
		size++; // cada vez que agregamos un elemento el tamaño lo aumentamos en uno para saber
				// que tan grande es la lista
	}

	// método para verificar si hay alguna técnica repetida, y si no, agregarla al
	// final
	public String verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(Technique t) {
		Technique temp = first;
		String msj = "";
		boolean repeated = false;

		while (temp != null && !repeated) {
			if (temp.getName().compareTo(t.getName()) == 0) {
				msj = "There's already a technique called: " + t.getName() + " therefore, it can't be added";
				repeated = true;
			} else {
				temp = temp.getNext();
			}
		}
		if (repeated == false) {
			msj = "The technique " + t.getName() + " has been added to the Character " + this.name;
			addFirstII(t);
		}
		return msj;
	}

	// MÉTODO PARA LOCALIZAR LA ÚLTIMA TÉCNICA DE UN PERSONAJE
	public Technique toSearchTheLastThecnique() {
		Technique actual = first;
		if (actual != null) {
			while (actual.getNext() != null) {
				actual = actual.getNext();
			}
		}
		return actual;
	}

	// MÉTODO PARA AÑADIR UNA TÉCNICA DE ÚLTIMA

	public void addEnd(Technique t) {
		if (first == null) { // si la lista está vacia la tecnica queda como la cabeza
			first = t;
		} else {
			Technique theLastOne = toSearchTheLastThecnique();
			theLastOne.linkAfterOf(t); // el siguiente de la tecnica t es el siguiente de theLastOne, osea null y el
										// siguiente de theLastOne es t
		}
		size++;

	}

	// MÉTODO PARA LOCALIZAR LA TÉCNICA ANTERIOR A LA DADA POR PARAMETRO
	public Technique toLocalizeTheBeforeOfActual(String nameTechnique) {
		Technique before = null;
		Technique actual = first;
		while (actual != null && !actual.getName().equals(nameTechnique)) {
			before = actual;
			actual = actual.getNext();
		}
		return actual != null ? before : null;
	}

	// MÉTODO PARA AÑADIR A UNA TÉCNICA ANTES QUE OTRA
	public void addBeforeOf(String name, Technique t) throws NonExistentTechniqueException {
		if (first == null) { // si la lista está vacia entonces no podra añadir nada antes por que está vacia
			throw new NonExistentTechniqueException(name, this.name);

		} else if (name == first.getName()) { // si la técnica quiere ser agregada antes del primer elemento de la lista
			t.changeNext(first);
			first = t;
		} else { // si la técnica está en otra posición de la lista
			Technique before = toLocalizeTheBeforeOfActual(name);
			if (before == null)
				throw new NonExistentTechniqueException(name, this.name);

			Technique toLinkWithT = before.getNext(); // lo que tiene enlazado before
			t.linkNext(toLinkWithT); // t va a enlazar lo que tenía enlazado before
			before.linkNext(t); // ahora before va a enlazar a t

		}
		size++;
	}

	// MÉTODO PARA VERIFICAR SI HAY UNA TECNICA REPETIDA Y SI NO, AGREGARLA AL FINAL
	public String verifyIfTheresAnyRepeatedTechniqueAndADDEND(Technique t) {
		Technique temp = first;
		String msj = "";
		boolean repeated = false;

		while (temp != null && !repeated) {
			if (temp.getName().compareTo(t.getName()) == 0) {
				msj = "There's already a technique called: " + t.getName() + " therefore, it can't be added";
				repeated = true;
			} else {
				temp = temp.getNext();
			}
		}
		if (repeated == false) {
			msj = "The technique " + t.getName() + " has been added to the Character " + this.name;
			addEnd(t);
		}
		return msj;
	}

	// MÉTODO PARA VERIFICAR SI HAY UNA TÉCNICA REPETIDA Y SI NO, AGREGARLA ANTES DE
	// UNA TÉCNICA
	public String verifyIfTheresAnyRepeatedTechniqueAndADDBEFOREOF(String nameTechnique, Technique t)
			throws NonExistentTechniqueException {
		Technique temp = first;
		String msj = "";
		boolean repeated = false;
		while (temp != null && !repeated) {
			if (temp.getName().compareTo(t.getName()) == 0) {
				msj = "There's already a technique called: " + t.getName() + " therefore, it can't be added";
				repeated = true;
			} else {
				temp = temp.getNext();
			}
		}
		if (repeated == false) {
			addBeforeOf(nameTechnique, t);
			msj = "The technique " + t.getName() + " has been added to the Character " + this.name
					+ " before the technique " + nameTechnique;

		}
		return msj;

	}

	// MÉTODO PARA VERIFICAR SI HAY UNA TÉCNICA REPETIDA Y SI NO, AGREGARLA DESPUES
	// DE UNA DADA
	public String verifyIfTheresAnyRepeatedTechniqueAndADDAFERTOF(String nameTechnique, Technique t)
			throws NonExistentTechniqueException {
		Technique temp = first;
		String msj = "";
		boolean repeated = false;
		while (temp != null && !repeated) {
			if (temp.getName().compareTo(t.getName()) == 0) {
				msj = "There's already a technique called: " + t.getName() + " therefore, it can't be added";
				repeated = true;
			} else {
				temp = temp.getNext();
			}
		}
		if (repeated == false) {
			addWhereverAfterOf(nameTechnique, t);
			msj = "The technique " + t.getName() + " has been added to the Character " + this.name
					+ " after the technique " + nameTechnique;
		}
		return msj;

	}

	// MÉTODO PARA VERIFICAR SI HAY UNA TÉCNICA REPETIDA Y SI NO; AGREGARALA EN
	// ORDEN
	public String verifyIfTheresAnyRepeatedTechniqueAndADDINORDER(String nameTechnique, double powerFactor)
			throws AlreadyExistsException {
		Technique temp = first;
		String msj = "";
		boolean repeated=false;
		while(temp!=null && !repeated) {
			if(temp.getName().compareTo(nameTechnique)==0) {
				msj+="There's already a technique called: " + nameTechnique + " therefore, it can't be added";
				repeated= true;
				throw new AlreadyExistsException(nameTechnique);
			}else {
				temp= temp.getNext();
			}
		}if(repeated==false) {
			insertarTechnique(nameTechnique, powerFactor);
			msj+="The technique with the name " + nameTechnique + " has been added to the character " + this.name;
			
		}
		return msj;
	}


	// MOSTRAR ELEMENTOS DE UNA LISTA
	public void mostrarElementos() {
		Technique actual = first;
		while (actual != null) {
			System.out.println(actual.getName());
			actual = actual.getNext();
		}
	}

	public String mostrarElementos2() {
		Technique actual = first;
		String msj = "";
		int contador = 0;
		while (contador < size - 1 && actual.getNext() != null) {
			msj += actual.getName() + "\n";
			actual = actual.getNext();
		}
		msj += actual.getName() + "\n";
		return msj;

	}

	// MÉTODO PARA ELIMINAR UNA TÉCNICA DE UN PERSONAJE DADO SU NOMBRE
	public void toDeleteATechniqueGivenItsName(String name) throws NonExistentTechniqueException {
		if (first == null) { // si la lista está vacia
			throw new NonExistentTechniqueException(name, this.name);

		} else if (name.equalsIgnoreCase(first.getName())) { // la técnica es la primera de la lista
			first = first.getNext();
		} else { // si la técnica está entre la lista
			Technique before = toLocalizeTheBeforeOfActual(name);
			if (before == null)
				throw new NonExistentTechniqueException(name, this.name);

			before.unlinkNext();
		}
		size--;
	}

	// Ordenamiento burbuja
	public void ordenarBurbuja() {

		Technique primera = first;

		while (primera != null) {
			Technique siguiente = primera.getNext();
			while (siguiente != null) {
				if (primera.compare(primera, siguiente) > 0) {
					Technique temp = primera;
					Technique anteriorAPrimera = toLocalizeTheBeforeOfActual(primera.getName());
					anteriorAPrimera.linkNext(siguiente);
					siguiente.linkNext(primera);
				}
				siguiente = siguiente.getNext();
			}
			primera = primera.getNext();
		}
	}

	public void OrdenarB() {
		Technique primera = first;

		while (primera != null) {
			Technique siguiente = primera.getNext();
			while (siguiente != null) {
				if (primera.getName().compareTo(siguiente.getName()) > 0) {
					Technique temp = primera;
					Technique anteriorAPrimera = toLocalizeTheBeforeOfActual(temp.getName());
					if (anteriorAPrimera == null) {
						temp.getNext().linkNext(temp);

						primera.linkNext(temp.getNext().getNext());
						first = temp.getNext();
					} else {
						anteriorAPrimera.linkNext(siguiente);
						temp.linkNext(siguiente.getNext());
						siguiente.linkNext(temp);
					}
					siguiente = siguiente.getNext();

				}
				primera = primera.getNext();

			}
		}
	}

	public void ordenarAgain() {
		Technique primera = first;
		while (primera != null) {
			Technique siguiente = primera.getNext();
			while (siguiente != null) {
				if (primera.getName().compareTo(siguiente.getName()) > 0) {
					Technique temp = primera;
					Technique anteriorDeTemp = toLocalizeTheBeforeOfActual(temp.getName());
					if (anteriorDeTemp == null) {
						first = temp.getNext();
						temp.linkNext(siguiente.getNext());
						first.linkNext(temp);
					} else {
						anteriorDeTemp.linkNext(temp.getNext());
						temp.linkNext(temp.getNext().getNext());
						siguiente.linkNext(temp);
					}
					siguiente = siguiente.getNext();
				}
				primera = primera.getNext();
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------------
	// CHARACTER METHODS

	// MÉTODO PARA INSERTAR UN PERSONAJE DESPUÉS DEL ACTUAL
	public void toLinkAfterActual(CharacterII c) {
		c.next = next; // el siguiente de c será el que es siguiente del actual
		if (next != null)
			next.before = c;
		c.before = this;
		next = c;

	}

	// MÉTODO PARA INSERTAR UN PERSONAJE ANTES DEL ACTUAL
	public void toLinkBeforeActual(CharacterII c) {
		if (before != null) { // si el anterior al actual es diferente de null
			before.next = c; // entonces el siguiente de before va a ser c
		}
		c.before = before; // el anterior de c será el before del actual
		c.next = this; // el siguiente de c será el actual
		before = c; // el anterior del actual será c
	}

	// COMPARAR POR NOMBRES
	@Override
	public int compareTo(CharacterII o) {
		return this.getName().compareTo(o.getName());
		// this es menor: -1
		// this es mayor:1
		// this y o son iguales 0
	}

	// -------------------------------------------------------------------
	// ORDENAMIENTO DE TÉCNICAS
	public void BubbleSort() {
		for (int i = 0; i < size - 1; i++) {
			for (Technique j = first; j.getNext() != null; j.getNext()) {
				if (j.getName().compareTo(j.getNext().getName()) > 0) {
					Technique temp = j;
					Technique beforeOftemp = toLocalizeTheBeforeOfActual(j.getName());
					Technique jnext = j.getNext();
					j.linkNext(j.getNext().getNext());
					beforeOftemp.linkNext(jnext);
					jnext.linkNext(temp);
				}
			}
		}
	}

	public void bubblesort2() {
		for (int i = 0; i < size - 1; i++) {
			for (Technique j = first; j != null; j.getNext()) {
				if (j.getName().compareTo(j.getNext().getName()) > 0) {
					Technique temp = j;
					Technique beforeOftemp = toLocalizeTheBeforeOfActual(j.getName());
					if (beforeOftemp == null) { // es porque temp es el primero en la lista
						j.linkNext(j.getNext().getNext());
						first = j.getNext();
						j.getNext().linkNext(j);
					} else {
						Technique jnext = temp.getNext();
						temp.linkNext(j.getNext().getNext());
						beforeOftemp.linkNext(jnext);
						jnext.linkNext(temp);
					}
				}
			}
		}
	}

	public void ordenaSeleccion() {
		for (int i = 0; i < size - 1; i++) {
			Technique minimo = toGetATechnique(i);
			for (int j = i + 1; j < size; j++) {
				if (toGetATechnique(j).getName().compareTo(minimo.getName()) > 0) {
					minimo = toGetATechnique(j);
				}
			}
			Technique t = toGetATechnique(i);
			Technique antesdet = toLocalizeTheBeforeOfActual(t.getName());
			Technique despuesdet = toLocalizeATechniqueGivenItsName(t.getNext().getName());
			antesdet.linkNext(despuesdet);
			despuesdet.linkNext(t);

		}
	}

	// MÉTODO ORDENAMIENTO DE TECNICAS POR INSERCION
	public void insertarTechnique(String nameT, double powerFactor) {
		Technique theNew = new Technique(nameT, powerFactor);
		Technique swip1;
		Technique swip2;

		// verificar si la lista está vacia, osea, que la cabeza sea nula
		if (first == null) {
			first=theNew;
			theNew.setNext(null); // the new sera la nueva cabeza de la lista;
			size++;
		} else {
			// empezar a barrer la lista
			swip1 = first;
			// recorrer nuestra lista enlazada por medio de un while
			while (swip1 != null) {
				swip2 = swip1.getNext();
				// la tecnica que entra debe ir al inicio de la lista?
				if (theNew.getPowerFactor() <= swip1.getPowerFactor()) {
					theNew.setNext(first);
					first = theNew;
					size++;
					break;
				} else {
					// la tecnica que entra debe ir al final de la lista?
					if (theNew.getPowerFactor() > swip1.getPowerFactor() && swip2 == null) {
						swip1.setNext(theNew); // the new será el final de la lista
						theNew.linkNext(null); // thenew apuntará a null ya que es el nuevo final de la lista
						size++;
						break;
					} else {
						// la tecnica que entra debe ir en medio de dos técnicas?
						if (swip1.getPowerFactor() < theNew.getPowerFactor()
								&& swip2.getPowerFactor() >= theNew.getPowerFactor()) {
							swip1.setNext(theNew);
							theNew.linkNext(swip2);
							size++;
							break;
						}
						// que nuestro ciclo while salte de valor
						else {
							swip1 = swip1.getNext(); // para que vuelva a empezar el ciclo
						}
					}
				}

			}
		}
		
	}

	public void toPrintTechniques() {
		Technique swip;
		swip = first;
		int i=1;
		if(first==null) {
			System.out.println("The character " + this.name + " doesn't have techniques");
		}
		while (swip != null) {
			System.out
					.println("The tehcnique " + i +" of the character " + this.name +" is: " + swip.getName() + " with a power factor of: " + swip.getPowerFactor() + "\n");
			swip = swip.getNext();
			i++;
		}
	}
}
