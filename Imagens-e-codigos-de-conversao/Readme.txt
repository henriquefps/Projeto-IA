1 - O arquivo salvar-imagem.py lê um arquivo batch selecionado e alterando o valor do loop do for que chama a função, é possível determinar quantas imagens vão ser transformadas de binário para .png, o valor máximo para o for é 10000, pois há apenas 10000 imagens por arquivo. O nome do arquivo .png está de uma forma que seja organizável em ordem alfabética por categoria.

2 - A base de dados CIFAR-10 possui 10 categorias que são representadas por inteiros de 0-9 - 
	0 - airplane										
	1 - automobile										
	2 - bird										
	3 - cat										
	4 - deer										
	5 - dog										
	6 - frog										
	7 - horse										
	8 - ship										
	9 - truck
mais informações em http://www.cs.toronto.edu/~kriz/cifar.html

3 - O arquivo geraTextoEntrada.py foi feito para gerar todas as linhas de atributos do arquivo entrada.arff, por que eu não queria ter que ajustar manualmente 256 linhas de texto.

4 - O arquivo formataEntrada.c lê o arquivo histogramaDaImagem.txt, e imprime apenas os dados, na forma a ser utilizada no arquivo de entrada.arff

5 - Para fazer as entradas do projeto, será necessário baixar o:
	- ImageJ: https://imagej.nih.gov/ij/download.html
	- Weka: https://sourceforge.net/projects/weka/files/latest/download

6 - No imagej, tendo as imagens já convertidas, que estão na pasta, você vai seguir o passo a passo do slide de walmir(Preparação de Dados) para  gerar o histograma daquela imagem, copiar, colar do arquivo do histograma.txt e rodar o formatarEntrada.c para ajeitar a entrada para o Weka.

7 - Por ultimo, basta adicionar mais uma linha de dados no .arff e salvar tudo. Não precisa salvar as modificaçõe na imagem no ImageJ