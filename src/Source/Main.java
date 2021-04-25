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
			System.out.println("YOUR CHOICE:  ");
			String choice = scanner.nextLine();

			switch (choice) {
			case "0":
				return;
			case "1": {
				System.out.println("=====Search by SlangWord=====");
				String a = scanner.nextLine();
				slangWordFunction.FindBySlangWord(a);
				break;
			}

			case "2": {
				System.out.println("======Search by Defition=====");
				String a = scanner.nextLine();
				slangWordFunction.FindByDefinition(a);
				break;
			}
			
			case "3":{
				System.out.println("=======Show history=======");
				slangWordFunction.ShowHistories();
				break;
			}

			case "11":
				return;

			default:
				System.out.println("Fail! Please choose again! Enter to continue...");
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
