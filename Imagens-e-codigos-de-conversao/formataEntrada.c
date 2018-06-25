#include <stdio.h>
#include <stdlib.h>

int main(){
	FILE* f = fopen("/Users/henrique/Downloads/cifar-10-batches-py/historgramaDeImagem.txt", "r");

	while(!feof(f)){
		int x = 0, y = 0;
		fscanf(f, "%d", &x);
		fscanf(f, "%d", &y);
		printf("%d,", y);
	} 
	fclose(f);
}