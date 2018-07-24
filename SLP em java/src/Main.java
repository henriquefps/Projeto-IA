import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		SLP algoritmo  = SLP.getInstance();
		
		int [][] datasetDeTreino = new int [1500][257];
		
		FileReader fr = new FileReader("src/datasetTreino.txt");
		BufferedReader bf = new BufferedReader(fr);
		String apoio;
		int coluna = 0;
		int linha = 0;
		int [] number = new int [3];
		int col = 0;
		while (true) {
			apoio = bf.readLine();
			if (apoio == null)
				break;
			for (int i = 0; i < apoio.length(); i++) {
				if (apoio.charAt(i) == '{')
					continue;
				if (apoio.charAt(i) != ',' && apoio.charAt(i) != '}')
					number[col++] = apoio.charAt(i) - 48;
				else {
					if (col == 1)
						datasetDeTreino[linha][coluna++] = number[0];
					else if (col == 2)
						datasetDeTreino[linha][coluna++] = (number[0] * 10) + (number[1]);
					else if (col == 3)
						datasetDeTreino[linha][coluna++] = (number[0] * 100) + (number[1] * 10) + (number[2]);
					col = 0;
				}
			}
			linha++;
			coluna = 0;
		}
		
		linha = 0;
		
		int [][] datasetDeTestes = new int [500][257];
		
		fr = new FileReader("src/datasetTeste.txt");
		bf = new BufferedReader(fr);
		while (true) {
			apoio = bf.readLine();
			if (apoio == null)
				break;
			for (int i = 0; i < apoio.length(); i++) {
				if (apoio.charAt(i) == '{')
					continue;
				if (apoio.charAt(i) != ',' && apoio.charAt(i) != '}')
					number[col++] = apoio.charAt(i) - 48;
				else {
					if (col == 1)
						datasetDeTestes[linha][coluna++] = number[0];
					else if (col == 2)
						datasetDeTestes[linha][coluna++] = (number[0] * 10) + (number[1]);
					else if (col == 3)
						datasetDeTestes[linha][coluna++] = (number[0] * 100) + (number[1] * 10) + (number[2]);
					col = 0;
				}
			}
			linha++;
			coluna = 0;
		}
		bf.close();
		fr.close();
		algoritmo.slp(datasetDeTreino, datasetDeTestes);

	}

}
