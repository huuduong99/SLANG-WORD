package Source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SlangWordFunction {
	private static String SLANGWORD_URL = "src/slang_word.txt";
	private static String SLANGWORD_ORIGIN_URL = "src/slang_word_origin.txt";
	private static String HISTORY_URL = "src/history.txt";
	public static HashMap<String, List<String>> slangWordHashMap = new HashMap<>();
	public static List<String> slangWordHistories = new ArrayList<String>();
	private static SlangWordFunction obj = new SlangWordFunction();
	private static Scanner scanner = new Scanner(System.in);

	private SlangWordFunction() {
		try {
			ReadFile(SLANGWORD_URL);
			ReadHistory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SlangWordFunction getInstance() {
		if (obj == null) {
			synchronized (SlangWordFunction.class) {
				if (obj == null) {
					obj = new SlangWordFunction();// instance will be created at request time
				}
			}
		}
		return obj;
	}

	public void ReadFile(String url) {
		try {

			slangWordHashMap.clear();

			File file = new File(url);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains("`")) {
					String[] data = line.split("`");
					String[] defintionItem = data[1].split("\\|");
					List<String> defintions = Arrays.asList(defintionItem);
					slangWordHashMap.put(data[0], defintions);
				}
			}
			fileReader.close();
			bufferedReader.close();
		} catch (Exception e) {
			System.out.println("ERROR" + e);
		}
	}

	public static void SaveSlangWord() {
		try {
			File file = new File(SLANGWORD_URL);
			FileWriter fileWriter = new FileWriter(file);
			for (String key : slangWordHashMap.keySet()) {
				fileWriter.write(key + "`");
				List<String> defintions = slangWordHashMap.get(key);
				for (int i = 0; i < defintions.size(); i++) {
					fileWriter.write(defintions.get(i));
					if (i + 1 < defintions.size())
						fileWriter.write("|");
				}
				fileWriter.write("\n");
			}
			fileWriter.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	public void FindBySlangWord(String slangWord) {
		List<String> results = slangWordHashMap.get(slangWord.toUpperCase());
		System.out.println("This is results:");
		System.out.println(results);

		slangWordHistories.add(slangWord);
		SaveHistory();
	}

	public void FindByDefinition(String definition) {
		List<String> results = new ArrayList<>();

		for (var item : slangWordHashMap.keySet()) {
			slangWordHashMap.get(item).forEach((value) -> {
				if (value.toUpperCase().contains(definition.toUpperCase())) {
					results.add(item);
					return;
				}
			});
		}
		System.out.println("This is results:");
		System.out.print(results);

		slangWordHistories.add(definition);
		SaveHistory();
	}

	public void ReadHistory() {
		try {

			File file = new File(HISTORY_URL);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				slangWordHistories.add(line);
			}
			fileReader.close();
			bufferedReader.close();

		} catch (Exception e) {
			System.out.println("ERROR" + e);
		}
	}

	public void ShowHistories() {
		System.out.println("This is history:");
		slangWordHistories.forEach((history) -> {
			System.out.println(history);
		});
	}

	public void SaveHistory() {
		try {
			File file = new File(HISTORY_URL);
			FileWriter fileWriter = new FileWriter(file);
			for (String history : slangWordHistories) {
				fileWriter.write(history + "\n");
			}
			fileWriter.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	public boolean CheckExistSlangWord(String slangWord) {
		boolean result = slangWordHashMap.containsKey(slangWord.toUpperCase());
		return result;

	}

	public void DeleteSlangWord(String slangWord) {
		slangWordHashMap.remove(slangWord.toUpperCase());
		System.out.println("Delete successfully");
		SaveSlangWord();
	}

	public void AddSlangWord(String slangWord, String defintion) {
		List<String> defitions = new ArrayList<String>();
		defitions.add(defintion);
		if (CheckExistSlangWord(slangWord)) {
			while (true) {
				System.out.println("Slang word exist, choose your option");
				System.out.println("A. Overwrite");
				System.out.println("B. Duplicate");
				System.out.println("Your choice: ");
				String option = scanner.nextLine();
				switch (option.toUpperCase()) {
				case "A": {
					slangWordHashMap.put(slangWord.toUpperCase(), defitions);
					System.out.println("Overwrite successfully");
					SaveSlangWord();
					return;

				}
				case "B": {
					List<String> defintionList = slangWordHashMap.get(slangWord.toUpperCase());
					for (String i : defintionList) {
						defitions.add(i);
					}
					slangWordHashMap.put(slangWord.toUpperCase(), defitions);
					System.out.println("Duplicate successfully");
					SaveSlangWord();
					return;

				}
				default:
					System.out.println("Please choose again! Enter to continue...");
					scanner.nextLine();
					break;
				}
			}

		} else {
			slangWordHashMap.put(slangWord.toUpperCase(), defitions);
			System.out.println("Add successfully");
			SaveSlangWord();
		}

	}

	public void EditSlangWord(String slangWord) {
		List<String> defitions = new ArrayList<String>();

		if (CheckExistSlangWord(slangWord)) {
			while (true) {
				System.out.println("Choose your option");
				System.out.println("A. Add definition");
				System.out.println("B. Replace definiton");
				System.out.println("Your choice: ");
				String option = scanner.nextLine();
				switch (option.toUpperCase()) {
				case "A": {
					System.out.println("New definition to add:");
					String a = scanner.nextLine();
					defitions.add(a);

					List<String> defintionList = slangWordHashMap.get(slangWord.toUpperCase());
					for (String i : defintionList) {
						defitions.add(i);
					}

					slangWordHashMap.put(slangWord.toUpperCase(), defitions);
					System.out.println("Add definition successfully");
					SaveSlangWord();
					return;

				}
				case "B": {
					System.out.println("New definition to replace:");
					String a = scanner.nextLine();
					defitions.add(a);

					slangWordHashMap.put(slangWord.toUpperCase(), defitions);
					System.out.println("Replace successfully");
					SaveSlangWord();
					return;

				}
				default:
					System.out.println("Please choose again! Enter to continue...");
					scanner.nextLine();
					break;
				}
			}

		} else {
			System.out.println("SlangWord is not exist !!!");
		}
	}

	public void ResetSlangWord() {
		ReadFile(SLANGWORD_ORIGIN_URL);
		System.out.println("Reset successfull !!!");

		SaveSlangWord();
	}

	public String RandomSlangWord() {
		Random random = new Random();
		int number = random.nextInt(slangWordHashMap.size() - 1);
		int index = 0;
		String slangWord = "";

		for (String key : slangWordHashMap.keySet()) {
			if (index == number) {
				slangWord = key;
				break;
			}
			index++;
		}
		return slangWord;
	}

}
