import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ij.*;

public class ImageLoader {
	
	public static void main(String[] args) throws IOException {
		
		ImagePlus imp = IJ.openImage("src/Imagens/Imagens de treino em PNG/myImage0label-6.png");
		int array[] = imp.getProcessor().getHistogram();
		String histograma = "";
		for (int i = 0; i < array.length; i++) {
			histograma += array[i] + ",";
		}
		FileWriter file = new FileWriter("src/Imagens/Histogramas/histograma_" + imp.getTitle() + ".txt");
		PrintWriter prt = new PrintWriter(file);
		prt.print(histograma);
		file.close();
		
	}
	
}
