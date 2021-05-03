package Source;

import java.util.Scanner;

public class Main {

	public static Scanner scanner = new Scanner(System.in);
	public static SlangWordFunction slangWordFunction = SlangWordFunction.getInstance();

	public static void showMenu() {

		while (true) {
			System.out.println("\n");
			System.out.println("=======Menu=========");
			System.out.println("1. Search by SlangWord ");
			System.out.println("2. Search by Definition ");
			System.out.println("3. Show history ");
			System.out.println("4. Add Slangword ");
			System.out.println("5. Edit Slangword ");
			System.out.println("6. Delete Slangword ");
			System.out.println("7. Reset Slangword ");
			System.out.println("8. Random Slangword ");
			System.out.println("9. Minigame to find Definition ");
			System.out.println("10. Minigame to find Slangword ");
			System.out.println("11. Exit ");
			System.out.println("Your choice:  ");
			String choice = scanner.nextLine();

			switch (choice) {
			case "1": {
				System.out.println("=====Search by SlangWord=====");
				System.out.print("Enter the slangWord: ");
				String a = scanner.nextLine();
				slangWordFunction.FindBySlangWord(a);
				break;
			}

			case "2": {
				System.out.println("======Search by Definition=====");
				System.out.print("Enter the definition: ");
				String a = scanner.nextLine();
				slangWordFunction.FindByDefinition(a);
				break;
			}

			case "3": {
				System.out.println("=======Show history=======");
				slangWordFunction.ShowHistories();
				break;
			}

			case "4": {
				System.out.println("======= Add Slangword=======");
				System.out.print("Enter the slangWord: ");
				String a = scanner.nextLine();
				System.out.print("Enter the defintion: ");
				String b = scanner.nextLine();
				slangWordFunction.AddSlangWord(a, b);
				break;

			}

			case "5": {
				System.out.println("=======Edit Slangword=======");
				System.out.print("Enter the slangWord: ");
				String a = scanner.nextLine();
				slangWordFunction.EditSlangWord(a);
				break;

			}

			case "6": {
				System.out.println("=======Delete Slangword=======");
				System.out.print("Enter the slangWord: ");
				String a = scanner.nextLine();
				boolean existSlangWord = slangWordFunction.CheckExistSlangWord(a);
				if (existSlangWord) {
					System.out.println("Are you remove it: (Y/N) ");
					String confirm = scanner.nextLine();
					if (confirm.equals("Y") || confirm.equals("y")) {
						slangWordFunction.DeleteSlangWord(a);
					}

				} else {
					System.out.print("Slangword not exist");
				}
				break;
			}

			case "7": {
				System.out.println("=======Reset Slangword=======");
				slangWordFunction.ResetSlangWord();
				break;

			}

			case "8": {
				System.out.println("=======Random Slangword=======");
				String result = slangWordFunction.RandomSlangWord();
				System.out.println("Slangword today is: " + result);

				break;

			}
			
			case "9": {
				System.out.println("=======Minigame to find Definition=======");
				slangWordFunction.MinigameFindDefinition();
				break;

			}
			
			case "10": {
				System.out.println("=======Minigame to find SlangWord=======");
				slangWordFunction.MinigameFindSlangWord();
				break;

			}

			case "11":
				System.out.print("You are exist !!!");
				return;

			default:
				System.out.println("Please choose again! Enter to continue...");
				scanner.nextLine();

			}
		}

	}

	public static void cls() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception E) {
			System.out.println(E);
		}
	}

	public static void main(String[] args) {
		showMenu();

	}

}
