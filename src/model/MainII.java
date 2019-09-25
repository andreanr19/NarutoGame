package model;

import java.text.ParseException;
import java.util.*;
public class MainII {

	public static void main(String[] args) {
		VideoJuegoManager videojuego = new VideoJuegoManager();

		try {
		CharacterII lista = new CharacterII(300, "Naruto", "Dulce", "2019-09-22");
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Ascuas", 50)));
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Ascuas", 40)));
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Cut", 500)));
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Doble Patada", 40)));
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDEND(new Technique("Aranazo", 34)));
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDBEFOREOF("Ascuas", new Technique("Surf", 50)));
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDAFERTOF("Ascuas", new Technique("Lanza llamas", 43)));
//		
//		lista.toDeleteATechniqueGivenItsName("Ascuas");
		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDINORDER("DOBLE PATADA", 34));
		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDINORDER("CUT", 20));
		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDINORDER("Ascuas", 15));
		
		
//		System.out.println("PRIMER ELEMENTO: "+lista.toGetATechnique(0).getName());
//		System.out.println("TERCER ELEMENTO: " +lista.toGetATechnique(3).getName());
		System.out.println("TAMAÑO DE LA LISTA: " +lista.getSize());
		System.out.print("Elementos de la lista: \n");
		lista.toPrintTechniques();
//		System.out.println(lista.mostrarElementos2());
		
//		lista.ordenarBurbuja();
		
//		lista.bubblesort2();
//		lista.OrdenarB();
//		System.out.println("ORDENADO");
//		lista.ordenaSeleccion();
//
//		System.out.println(lista.mostrarElementos2());
		}catch(Exception e) {
			e.printStackTrace();
		}
//
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Ascuas", 50)));
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Ascuas", 40)));
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Cut", 500)));
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Doble Patada", 40)));
//		System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDEND(new Technique("Aranazo", 34)));
//		try {
//			System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Ascuas", 50)));
//			System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Ascuas", 40)));
//			System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Cut", 500)));
//			System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDFIRST(new Technique("Doble Patada", 40)));
//			System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDEND(new Technique("Aranazo", 34)));
//			System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDBEFOREOF("Ascuas", new Technique("Surf", 50)));
//			System.out.println(lista.verifyIfTheresAnyRepeatedTechniqueAndADDAFERTOF("Ascuas", new Technique("Lanza llamas", 43)));
//			
//			lista.toDeleteATechniqueGivenItsName("Ascuas");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		System.out.println("PRIMER ELEMENTO: "+lista.toGetATechnique(0).getName());
//		System.out.println("TERCER ELEMENTO: " +lista.toGetATechnique(3).getName());
//		System.out.println("TAMAÑO DE LA LISTA: " +lista.getSize());
//		System.out.print("Elementos de la lista: \n");
//		System.out.println(lista.mostrarElementos2());
//		
//		lista.ordenarBurbuja();
//		System.out.println(lista.mostrarElementos2());
		
		try {
		
		Clan myclan = new Clan("Clansito");
		myclan.addCharacter(44, "Charmander", "fuego", "2019-09-22");
		myclan.addCharacter(43, "Rayquaza", "agua", "2019-09-22");
		myclan.addCharacter(3, "Groudon", "fuego", "2019-09-22");
		myclan.mostrarPersonajes();
		CharacterII c = myclan.toSearchCharacter("Groudon");
		c.verifyIfTheresAnyRepeatedTechniqueAndADDINORDER("fuego", 200);
		c.toPrintTechniques();
		Technique t=c.toLocalizeATechniqueGivenItsName("fuego");
		c.toDeleteATechniqueGivenItsName("fuego");
		c.toPrintTechniques();
		myclan.mostrarPersonajes();
		videojuego.verifyIsTheresAClanAndADD(myclan);
		

		videojuego.toUpdateAClanName("Clansito", "Clansote");
		
		Clan myclan2 = new Clan("Clandos");
		myclan2.addCharacter(34, "pikachu", "doble patada", "2019-09-22");
		
		Clan myclan3 = new Clan("Z");
		myclan3.addCharacter(55, "andrea", "dulce", "2019-09-22");
		
		Clan myclan4 = new Clan("Gigglypuff");
		
		myclan2.mostrarPersonajes();
		videojuego.addClanes(myclan);
		videojuego.addClanes(myclan2);
		videojuego.addClantwo(myclan);
		videojuego.addClantwo(myclan2);
		
		videojuego.verifyIsTheresAClanAndADD(myclan);
		videojuego.verifyIsTheresAClanAndADD(myclan2);
		videojuego.verifyIsTheresAClanAndADD(myclan3);
		videojuego.verifyIsTheresAClanAndADD(myclan4);
		System.out.println(videojuego.showClanes());
		
		videojuego.bubbleSortByCharactersAmount();
		System.out.println(videojuego.showClanes());
		
		videojuego.selectionSort();
		System.out.println("ORDEN POR NOMBRE");
		System.out.println(videojuego.showClanes());
		videojuego.toDeleteAClan("Clansito");
		
		System.out.println(videojuego.showClanes());
		
		
//		System.out.println(myclan.toSearchCharacter("r").getName());;
		
		}catch(ClanException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
