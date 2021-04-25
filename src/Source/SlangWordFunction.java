package Source;

import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.sun.tools.classfile.CharacterRangeTable_attribute.Entry;

public class SlangWordFunction {
	private static String SLANGWORD_URL = "src/slang_word.txt";
	private static String HISTORY_URL = "src/history.txt";
	public static HashMap<String, List<String>> slangWordHashMap = new HashMap<>();
	public static List<String> slangWordHistories = new ArrayList<String>();
	private static SlangWordFunction obj = new SlangWordFunction();

	private SlangWordFunction() {
		try {
			ReadFile();
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

	public void ReadFile() {
		try {

			File file = new File(SLANGWORD_URL);
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

	public static void SaveHistory() {
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

}
