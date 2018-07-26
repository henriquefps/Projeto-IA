import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ij.*;

public class ImageLoader {

	public static void main(String[] args) throws IOException {

		String pathFile = "src/Imagens/Imagens-Para-Histograma/myImage-";
		
		File folder = new File("src/Imagens/Imagens-de-treino-em-PNG/");
		File [] listaDeArquivos = folder.listFiles();
		String[] nomeDosArquivos = new String[listaDeArquivos.length];
		
		FileWriter saidas = new FileWriter("histogramas.txt");
		
		for (int i = 0; i < nomeDosArquivos.length; i++) {
			nomeDosArquivos[i] = listaDeArquivos[i].getName().toString();
		}
		
		for (int j = 0; j < listaDeArquivos.length; j++) {
			String classeDaImagem = null;
			for (int i = 0; i < nomeDosArquivos.length; i++) {
				if (nomeDosArquivos[i].contains("myImage-" + j + "l")) {
					classeDaImagem = String.valueOf(nomeDosArquivos[i].charAt(nomeDosArquivos[i].length() - 5));
					break;
				}
			}
			ImagePlus imp = IJ.openImage(pathFile + j + ".png");
			int array[] = imp.getProcessor().getHistogram();
			String histograma = "";
			for (int i = 0; i < array.length; i++) {
				histograma += array[i] + ",";
			}
			if (classeDaImagem.equals("7")) {
				histograma += "1";
			} else {
				histograma += "0";
			}
			saidas.write(""+histograma+"\n");
		}
		
		saidas.close();
	}

}
