import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		SLP algoritmo  = SLP.getInstance();
		
		int [][] datasetDeTreino = new int [1500][256];
		
		FileReader fr = new FileReader("src/datasetTreino.txt");
		BufferedReader bf = new BufferedReader(fr);
		String apoio;
		int coluna = 0;
		int linha = 0;
		while (true) {
			apoio = bf.readLine();
			if (apoio == null)
				break;
			for (int i = 0; i < apoio.length(); i++) {
				if (apoio.charAt(i) != '{' && apoio.charAt(i) != ',' && apoio.charAt(i) != '}')
					datasetDeTreino[linha][coluna++] = (int) apoio.charAt(i);
			}
			linha++;
			coluna = 0;
		}
		
		linha = 0;
		
		int [][] datasetDeTestes = new int [500][256];
		
		fr = new FileReader("src/datasetTeste.txt");
		bf = new BufferedReader(fr);
		while (true) {
			apoio = bf.readLine();
			if (apoio == null)
				break;
			for (int i = 0; i < apoio.length(); i++) {
				if (apoio.charAt(i) != '{' && apoio.charAt(i) != ',' && apoio.charAt(i) != '}')
					datasetDeTreino[linha][coluna++] = (int) apoio.charAt(i);
			}
			linha++;
			coluna = 0;
		}
		bf.close();
		fr.close();
		algoritmo.slp(datasetDeTreino, datasetDeTestes);

	}

}
