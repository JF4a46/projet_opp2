package ui_projet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DbFileSystem {

	public static void writeToFile(String pathDb, String info) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(pathDb, "UTF-8");
			writer.println(info);
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<String> loadFromFile(String path) {
		//Find a way to check if line is null []
		String line = null;
		ArrayList<String> array = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			while ((line = br.readLine()) != null)
				array.add(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

}
