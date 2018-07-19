import java.util.ArrayList;
import java.util.Random;

public class SLP {

	private static SLP instance;
	private int limiar;
	private double taxaDeAprendizado;
	private int numIteracoesTreino;
	private ArrayList<Float> pesos;
	
	private SLP() {
		this.limiar = 3500;
		this.taxaDeAprendizado = 0.1;
		this.numIteracoesTreino = 100;
		this.pesos = new ArrayList<Float>();
	}
	
	public static SLP getInstance() {
		if (instance == null)
			instance = new SLP();
		return instance;
	}
	
	private void inicializaPesos() {
		Random r = new Random();
		for (int i = 0; i < 256; i++)
			pesos.add((float) (r.nextInt(9) + 1));
	}
	
	private int avaliarEntrada(int [] histograma) {
		float valor = 0;
		for (int i = 0; i < 256; i++)
			valor += (pesos.get(i) * histograma[i]);
		return (valor >= limiar)? 1 : 0;
	}
	
	private float calcularNovoPeso(float peso, int saidaEsperada, int saidaReal, int xi) {
		float novoPeso = (float) (peso + (taxaDeAprendizado * (saidaEsperada - saidaReal) * xi));
		return novoPeso;
	}
	
	private void treinarPerceptronComEntrada(int [] histograma) {
		int saidaEsperada = histograma[256];
		int saidaReal = avaliarEntrada(histograma);
		if (saidaReal != saidaEsperada) {
			for (int i = 0; i < 256; i++)
				pesos.set(i, calcularNovoPeso(pesos.get(i), saidaEsperada, saidaReal, histograma[i]));
		}
	}
	
	private void treinarPerceptronComDataset(int [][] dataset) {
		for (int i = 0; i < dataset.length; i++)
			treinarPerceptronComEntrada(dataset[i]);
	}
	
	private int [] testarComDataset(int [][] dataset) {
		int [] resultados = new int [dataset.length];
		for (int i = 0; i < dataset.length; i++)
			resultados[i] = avaliarEntrada(dataset[i]);
		return resultados;
	}
	
	private void iteracoesDeTreino(int [][] dataset) {
		int [] resultadosAntes = new int [dataset.length];
		int [] resultadosDepois = new int [dataset.length];
		boolean iguais = true;
		for (int i = 0; i < numIteracoesTreino; i++) {
			resultadosAntes = testarComDataset(dataset);
			treinarPerceptronComDataset(dataset);
			resultadosDepois = testarComDataset(dataset);
			for (int j = 0; j < dataset.length && iguais; j++) {
				if (resultadosAntes[j] != resultadosDepois[j]) {
					iguais = false;
				}
			}
			if (iguais) {
				System.out.println("Pesos convergiram, iterações necessárias: " + i + ".");
				break;
			}
		}
	}
	
	private String quantificarEntradas(int [][] dataset) {
		int cavalos = 0;
		int naoCavalos = 0;
		for (int i = 0; i < dataset.length; i++) {
			if (dataset[i][256] == 1)
				cavalos++;
			else
				naoCavalos++;
		}
		return "\n\tCavalo: " + cavalos + "\n\tNão Cavalos: " + naoCavalos + "\n";
	}
	
	private float percentualDeAcerto(int [][] datasetTestes, int [] resultados) {
		int acertos = 0;
		for (int i = 0; i < datasetTestes.length; i++) {
			if (datasetTestes[i][256] == resultados[i])
				acertos++;
		}
		return (float) acertos/datasetTestes.length;
	}
	
	public void slp(int [][] datasetDeTreino, int[][] datasetDeTestes) {
		inicializaPesos();
		
		System.out.println("Imagens para treino: " + datasetDeTreino.length);
		System.out.println("Imagens para teste: " + datasetDeTestes.length);

		System.out.println("Quantificar entradas treino: " + quantificarEntradas(datasetDeTreino));
		System.out.println("Quantificar entradas testes: " + quantificarEntradas(datasetDeTestes));
		
		System.out.print("Pesos antes do treino: ");
		for (int i = 0; i < pesos.size() - 1; i++)
			System.out.print(pesos.get(i) + ", ");
		System.out.println(pesos.get(pesos.size() - 1) + ".");
		
		iteracoesDeTreino(datasetDeTreino);
		
		System.out.print("Pesos após o treino: ");
		for (int i = 0; i < pesos.size() - 1; i++)
			System.out.print(pesos.get(i) + ", ");
		System.out.println(pesos.get(pesos.size() - 1) + ".");

		int [] resultados = testarComDataset(datasetDeTestes);
		System.out.print("Resultados: ");
		for (int i = 0; i < resultados.length - 1; i++)
			System.out.print(resultados[i] + ", ");
		System.out.println(resultados[resultados.length - 1] + ".");
		
		System.out.println("Percentual de acerto: " + percentualDeAcerto(datasetDeTestes, resultados) * 100 + "%");
	}
	
}
