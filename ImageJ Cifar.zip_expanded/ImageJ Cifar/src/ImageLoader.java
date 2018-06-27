import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ij.*;

public class ImageLoader {

	public static void main(String[] args) throws IOException {

		String pathFile = "src/Imagens/Imagens-Para-Histograma/myImage-";
		
		for (int j = 150; j < 151; j++) {
			ImagePlus imp = IJ.openImage(pathFile + j + ".png");
			int array[] = imp.getProcessor().getHistogram();
			String histograma = "";
			for (int i = 0; i < array.length; i++) {
				histograma += array[i] + ",";
			}
			//FileWriter file = new FileWriter("src/Imagens/Histogramas/histograma_" + imp.getTitle() + ".txt");
			//PrintWriter prt = new PrintWriter(file);
			//prt.print(histograma);
			if (j == 11 || j == 12 || j == 37 || j == 43 || j == 52 || j == 68 || j == 73 || j == 84 || j == 85 || j == 87 || j == 113 || j == 114 || j == 131 || j == 133 || j == 152 || j == 163 || j == 172 || j == 178 || j == 181 || j == 191 || j == 211 || j == 230 || j == 237 || j == 256 || j == 267 || j == 289 || j == 294) {
				histograma += "Cavalo";
			} else {
				histograma += "Nao_Cavalo";
			}
			System.out.println(histograma);
			//file.close();

		}
	}

}
