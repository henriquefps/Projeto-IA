import numpy as np
from scipy.misc import imsave
from PIL import Image
import numpy as np
import _pickle as cPickle

def load_batch():
    path = '/Users/henrique/git/Projeto-IA/Imagens-e-codigos-de-conversao/' # Especifica o cominho da pasta do cifar
    file = 'data_batch_1' # Especifica qual arquivo sera lido
    f = open(path+file, 'rb') # abre o arquivo
    dict = cPickle.load(f, encoding='latin1') # Eu coloquei o enconding como latin1 pq achei em uma 
    										  # pagina dizendo que funcionava, antes dele dava erro
    images = dict['data']
    #images = np.reshape(images, (10000, 3, 32, 32))
    labels = dict['labels']
    imagearray = np.array(images)   # gera um array 10000 x 3072 com as informacoes RGB de cada pixel de cada imagem
    labelarray = np.array(labels)   # gera um array de 10000 com a classe de cada imagem
    
    return imagearray, labelarray # retorna o array deformado de imagens e o array de lablels

images , labels = load_batch()
# Reformata o array para um formato mais proximo do que precisamos (n, RGB, pixel, pixel)
images = np.reshape(images, (10000, 3, 32, 32))
# Mas, para o Image.from array, precisamos de (pixel, pixel, RGB), a função transpose faz essa conversão
def saveImage(image, fileName):
	array = image.transpose(1,2,0) #Image[n](rgb, pixel, pixel) -> Image[n](pixel, pixel, rgb)
	img = Image.fromarray(array, 'RGB')
	img.save(fileName)

def showImage(image):
	array = image.transpose(1,2,0)
	img = Image.fromarray(array, 'RGB')
	img.show()
	pass

# Essa função eu fiz de teste, para o OpenCV utiliza-se BGR, não RGB, aí fiz a função que faz essa transformação
def invertImageColors(image):
	for x in range(32):
		for y in range(32):
			aux0 = image[x][y][0]
			aux1 = image[x][y][1]
			aux2 = image[x][y][2]
			image[x][y][0] = aux2
			image[x][y][1] = aux1
			image[x][y][2] = aux0
	return image


for x in range(2000, 6000):
	#saveImage(images[x],"myImage-" + str(x) + "label-"+ str(labels[x]) +".png")
	saveImage(images[x],"myImage-" + str(x) + ".png")
	#saveImage(images[x],"label-"+ str(labels[x]) + "myImage-" + str(x) + ".png")

	#showImage(images[x])

