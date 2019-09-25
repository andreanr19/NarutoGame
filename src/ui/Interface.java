package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.*;
import java.util.*;

public class Interface {

	private static final String ARCHIVO_VIDEOJUEGO = "C:/Users/Usuario/Desktop/CURSO DE JAVA/LaboratorioNaruto";

	// clase principal del mundo
	private static VideoJuegoManager videojuego;
	private Scanner input;

	public Interface() {
		videojuego = new VideoJuegoManager();
		input = new Scanner(System.in);
	}

	public static void main(String[] args) {
		Interface interfaz = new Interface();
		try {
			interfaz.showMenu();
			ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(
					new FileOutputStream("C:/Users/Usuario/Desktop/CURSO DE JAVA/LaboratorioNaruto/data/serializado.dat"));
			escribiendo_fichero.writeObject(videojuego);
			escribiendo_fichero.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// salvar la información del videojuego
	public void saveData() {
		File archivoVideoJuego = new File(ARCHIVO_VIDEOJUEGO);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoVideoJuego));
			oos.writeObject(videojuego);
			oos.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error saving the data: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//
	public void loadInfo() {
		try {
			ObjectInputStream recuperando_fichero = new ObjectInputStream(new FileInputStream(ARCHIVO_VIDEOJUEGO));
			VideoJuegoManager vj = (VideoJuegoManager) recuperando_fichero.readObject();
			recuperando_fichero.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void showMenu() {
		int read = 0;

		while (read != 11) {
			showOptions();
			read = input.nextInt();
			input.nextLine();

			switch (read) {

			case 1:
				boolean continueCicle = true;
				do {
					try {
						System.out.println("Enter the name of the Clan you want to add");
						String nameClan = input.nextLine();
						Clan c = new Clan(nameClan);
						videojuego.verifyIsTheresAClanAndADD(c);

						System.out.println("Would you like to add a character to the created clan?");
						System.out.println("1.Yes \n 2.No");
						int answer = input.nextInt();
						input.nextLine();
						if (answer == 1) {
							System.out.println("Enter the name of the character");
							String nameCharacter = input.nextLine();
							System.out.println("Enter the amount of power the character's gonna have");
							double power = Double.parseDouble(input.nextLine());
							System.out.println("Enter the personality of the character you want to add");
							String personality = input.nextLine();
							System.out.println(
									"Enter date of creation of the character with the following format: yyyy-MM-dd");
							String creationDate = input.nextLine();
							DateFormat formato = new SimpleDateFormat("yyy-MM-dd");
							Date bornCharacter = formato.parse(creationDate);

							CharacterII thenew = new CharacterII(power, nameCharacter, personality, creationDate);
							c.addCharacter(power, nameCharacter, personality, creationDate);
							continueCicle = false;
						} else {
							continueCicle = false;
						}
					} catch (ParseException e) {
						System.err.printf("\nException: %s\n", e);
						System.out.println("It was impossible to convert from String to Date. Try Again");
						continueCicle = false;
					} catch (InputMismatchException e) {
						System.err.printf("\nException: %s\n", e);
						System.out.println("The date you ingressed is not correct. Try Again.");
					} catch (ClanException e) {
						System.err.printf("\nException: %s\n", e);
					} catch (AlreadyExistentClanException e) {
						e.printStackTrace();
					}
				} while (continueCicle == true);
				break;
			case 2:

				boolean continueCicle2 = true;
				do {
					try {

						if (videojuego.getSize() == 0) {
							System.out.println(
									"You can't create a character because there aren't clans to fit the character in");
						} else {
							System.out.println("These are the clans existents at the moment: \n");
							System.out.println(videojuego.showClanes());
							System.out.println(
									"Wich of the previous clans you want to add your character at. Enter the name");
							String nameClan = input.nextLine();
							System.out.println("These are the characters the clan has at the moment");
							Clan theclan = videojuego.searchAClan(nameClan);
							theclan.mostrarPersonajes();
							System.out.println(
									"When you add a character to the clan, it will be added in an ascendent way, ordered by the name of the character");
							System.out.println("Give us the info of the character");
							System.out.println("Enter the amount of power of the character");
							double power = Double.parseDouble(input.nextLine());
							System.out.println("Enter the name of the character");
							String namec = input.nextLine();
							System.out.println("Enter the personality of the character");
							String personalityC = input.nextLine();
							System.out.println(
									"Enter date of creation of the character with the following format: yyyy-MM-dd");
							String creationDate = input.nextLine();
							DateFormat formato = new SimpleDateFormat("yyy-MM-dd");
							CharacterII c = new CharacterII(power, namec, personalityC, creationDate);

							Clan cl = videojuego.searchAClan(nameClan);
							cl.addCharacter(power, namec, personalityC, creationDate);
							System.out
									.println("The character " + namec + " has been added to the clan " + cl.getName());
							continueCicle2 = false;

						}
					} catch (ParseException e) {
						System.err.printf("\nException: %s\n", e);
						System.out.println("It was impossible to convert from String to Date. Try Again");
						continueCicle = false;
					} catch (ClanException e) {
						e.printStackTrace();
					} catch (InputMismatchException e) {
						System.err.printf("\nException: %s\n", e);
						System.out.println("The date you ingressed is not correct. Try Again.");
					} catch (NonExistentClanException e) {
						System.err.printf("\nException: %s\n", e);
					}
				} while (continueCicle2 == true);
				break;
			case 3:
				boolean continueCicle3 = true;
				do {
					try {
						System.out.println(
								"psdt: at the moment you add a new tehcnique to a character, it will be added in an ascendent way.");
						System.out.println("These are the clans existents at the moment");
						System.out.println("Enter the name of the clan the character is at");
						String nameClan = input.nextLine();
						Clan c = videojuego.searchAClan(nameClan);
						if (c.getSize() == 0) {
							System.out.println("There are no characters in the clan " + c.getName() + " yet");
						} else {
							System.out.println("These are the characters of the clan " + c.getName());
							c.mostrarPersonajes();
							System.out.println("Enter the name of the character you wanna add a technique");
							String nameCharacter = input.nextLine();
							CharacterII ch = c.toSearchCharacter(nameCharacter);

							if (ch.getSize() == 0) {
								System.out.println("This character doesn't have techniques yet. Add the first one");
								System.out.println("Enter the name of the technique");
								String nt = input.nextLine();
								System.out.println("Enter the amount of power of this technique");
								double pf = Double.parseDouble(input.nextLine());
								Technique t = new Technique(nt, pf);
								System.out.println(ch.verifyIfTheresAnyRepeatedTechniqueAndADDINORDER(nt, pf));
								continueCicle3 = false;

							} else {
								System.out.println("These are the techniques of this character");
								ch.toPrintTechniques();
								System.out.println("Enter the name of the technique");
								String nt = input.nextLine();
								System.out.println("Enter the amount of power of this technique");
								double pf = Double.parseDouble(input.nextLine());
								System.out.println(ch.verifyIfTheresAnyRepeatedTechniqueAndADDINORDER(nt, pf));
								continueCicle3 = false;

							}
						}

					} catch (NonExistentCharacter e) {
						e.printStackTrace();
					} catch (InputMismatchException e) {
						e.printStackTrace();

					} catch (NonExistentClanException e) {
						e.printStackTrace();
					} catch (AlreadyExistsException e) {
						e.printStackTrace();
					}
				} while (continueCicle3 == true);
				break;
			case 4:
				boolean continueCicle4 = false;
				do {
					try {
						System.out.println("Choose the way you want to order the clans. Enter the number");
						System.out.println("1.In a lexicographic order \n 2.By the amout of characthers the clan has");
						String answer = input.nextLine();
						if (answer.equalsIgnoreCase(answer)) {
							System.out.println("These are the clanes existents at the moment");
							System.out.println(videojuego.showClanes());
							System.out.println("These are the clanes ordered by lexicographin order");
							videojuego.selectionSort();
							System.out.println(videojuego.showClanes());
							continueCicle = false;
						} else {
							System.out.println("These are the clanes existents at the moment");
							System.out.println(videojuego.showClanes());
							System.out.println("These are the clanes ordered by the amount of the charaters");
							videojuego.bubbleSortByCharactersAmount();
							System.out.println(videojuego.showClanes());
							continueCicle = false;
						}
					} catch (InputMismatchException e) {
						System.err.printf("\nException: %s\n", e);
						System.out.println("The data you ingressed is not correct. Try Again.");
					}
				} while (continueCicle4 == true);
				break;
			case 5:
				boolean continueCicle5 = false;
				do {
					try {
						System.out.println("These are the clanes existents at the moment");
						System.out.println(videojuego.showClanes());
						System.out.println("Enter the name of the clan you want to delete");
						String namec = input.nextLine();
						videojuego.toDeleteAClan(namec);
						System.out.println("The clan has been sucesfully deleted");
						System.out.println("The clans existents are");
						System.out.println(videojuego.showClanes());
					} catch (NonExistentClanException e) {
						e.printStackTrace();

					}
				} while (continueCicle5 == true);
				break;
			case 6:
				boolean continueCicle6 = false;
				do {
					try {
						System.out.println("These are the clans actually existents in the system");
						System.out.println(videojuego.showClanes());
						System.out.println("Enter the name of the clan you want to delete a character from");
						String clanName = input.nextLine();
						Clan theClan = videojuego.searchAClan(clanName);
						System.out.println("These are the character that the clan " + clanName + " actually has ");
						theClan.mostrarPersonajes();
						System.out.println(
								"Enter the name of the character you want to delete from the clan " + clanName);
						String nameCharacter = input.nextLine();
						theClan.toDeleteACharacterGivenItsName(nameCharacter);

					} catch (NonExistentCharacter e) {
						e.printStackTrace();
					} catch (NonExistentClanException e) {
						e.printStackTrace();
					}
				} while (continueCicle6 == true);
				break;
			case 7:
				boolean continueCicle7 = false;
				do {
					try {
						System.out.println("These are the clans existents at the moment");
						System.out.println(videojuego.showClanes());
						System.out.println("Enter the name of the clan you need the charactert from");
						String nameClan = input.nextLine();
						Clan c = videojuego.searchAClan(nameClan);
						System.out.println("These are the characters the clan actually has");
						c.mostrarPersonajes();
						System.out.println("Enter the name of the character you want to delete a technique to");
						String nameCharacter = input.nextLine();
						CharacterII ch = c.toSearchCharacter(nameCharacter);
						System.out.println("These are the thecnique the character " + nameCharacter + " actually has");
						ch.toPrintTechniques();
						System.out.println("Enter the name of the technique you want to delete from the character "
								+ ch.getName());
						String nameT = input.nextLine();
						ch.toDeleteATechniqueGivenItsName(nameT);
						System.out.println("The technique " + nameT + " has been sucesfully deleted");
					} catch (NonExistentTechniqueException e) {
						e.printStackTrace();

					} catch (NonExistentCharacter e) {
						e.printStackTrace();
					} catch (NonExistentClanException e) {
						e.printStackTrace();
					}
				} while (continueCicle7 == true);
				break;
			case 8:
				boolean continueCicle8 = false;
				do {
					try {
						System.out.println("These are the clans exixtents in the system");
						videojuego.showClanes();
						System.out.println("Enter the name of the clan you want to update its name");
						String nameClan = input.nextLine();
						Clan clanToUpdate = videojuego.searchAClan(nameClan);
						System.out.println("Enter the new name of the clan");
						String newName = input.nextLine();

						videojuego.toUpdateAClanName(clanToUpdate.getName(), newName);
					} catch (InputMismatchException e) {
						e.printStackTrace();
					} catch (NonExistentClanException e) {
						e.printStackTrace();
					}
				} while (continueCicle8 == true);
				break;
			case 9:
				boolean continueCicle9 = false;
				do {
					try {
						System.out.println("These are the clans exixtents in the system");
						videojuego.showClanes();
						System.out.println("Enter the name of the clan you want to update a character's name");
						String nameClan = input.nextLine();
						Clan clanToUpdate = videojuego.searchAClan(nameClan);
						System.out.println("These are the characters of the clan " + nameClan);
						clanToUpdate.mostrarPersonajes();
						System.out.println("Enter the name of the character you want to change the name");
						String nameCharacter = input.nextLine();
						CharacterII c = clanToUpdate.toSearchCharacter(nameCharacter);
						System.out.println("Enter the new name for the character");
						String newName = input.nextLine();
						c.setName(newName);
						System.out.println("The name of the character has been updated to " + newName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} while (continueCicle9 == true);
				break;

			}
			if (read > 9) {
				loadInfo();
			}
		}
	}

	public void showOptions() {
		System.out.println("Welcome, enter the option you want");
		System.out.println("1.To register a new clan");
		System.out.println("2.To register a new character in a specific clan");
		System.out.println("3.To add a technique to a character of a specific clan");
		System.out.println("4.To sort the clans");
		System.out.println("5.To delete a clan from the system");
		System.out.println("6.To delete a character from a specific clan");
		System.out.println("7.To delete a technique from a character");
		System.out.println("8.To change the name of a clan");
		System.out.println("9.To change the name of a character");
	}
}
