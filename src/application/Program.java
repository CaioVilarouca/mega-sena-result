package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {
	public static void main (String[] args) {
				
		File path = new File("/home/caio/eclipse-workspace/megaSenaResult/bda.csv");
		List <String> file = new ArrayList<>();		
																// Abrindo o arquivo a ser lido
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            	// Le a primeira linha do arquivo
				String line = bufferedReader.readLine();
				while (line != null) {
					file.add(line);
					line = bufferedReader.readLine();// Le a proxima linha
				}
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		} 
		for (String string : file) {
			System.out.println(string);
		}
		System.out.println("Resutado de " + file.size() / 6 + " jogos feito");
	}
}
