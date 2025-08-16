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
					System.out.println(line);
					file.add(line);
					line = bufferedReader.readLine();// Le a proxima linha
				}
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		} 

		// Manipulando os dados na list 
		System.out.println("Resutado de " + file.size() / 6 + " jogos feito");

		
		// Como Mega-Sena vai de 1 a 60, cria um array de tamanho 61
        int[] counter = new int[61]; // índice 0 não será usado

        // Percorre cada linha
        for (String line : file) {
            String[] number = line.split("[,;\\s]+");
            for (String numStr : number) {
                if (!numStr.isEmpty()) {
                    int num = Integer.parseInt(numStr);
                    if (num >= 1 && num <= 60) {
                    	counter[num]++; // incrementa a contagem no índice correspondente
                    }
                }
            }
        }

        // Mostra o resultado
        /*
         for (int i = 1; i < counter.length; i++) {
            if (counter[i] > 0) {
                System.out.println("Número " + i + " apareceu " + counter[i] + " vezes");
            }
        }*/    

     // Criar lista com [numero, quantidade]
     List<int[]> lista = new ArrayList<>();
     for (int i = 1; i < counter.length; i++) {
         lista.add(new int[]{i, counter[i]});
     }

     // Ordenar do mais frequente para o menos
     lista.sort((a, b) -> Integer.compare(b[1], a[1]));

     // Pegar top 12
     List<int[]> top12 = lista.subList(0, Math.min(12, lista.size()));

     // Descobrir o valor máximo para escalar as barras
     int max = 0;
     for (int[] nc : top12) {
         if (nc[1] > max) max = nc[1];
     }

     // Tamanho máximo da barra
     int larguraMax = 50;
		

     System.out.println("\n Gráfico");
     for (int[] nc : top12) {
         int numero = nc[0];
         int qtd = nc[1];

         // Calcular quantos caracteres a barra terá
         int barras = (max == 0) ? 0 : (int) Math.round((qtd * 1.0 / max) * larguraMax);

         // Número e barra
         System.out.printf("%2d | ", numero);
         for (int j = 0; j < barras; j++) {
             System.out.print('#');
         }
         System.out.printf(" (%d)%n", qtd);
     }
   }
}
