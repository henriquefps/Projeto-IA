from random import shuffle as embaralhar


def get_datasets():
    file = open("histogramas.txt", "r")
    dataset1 = [[int(num) for num in line.split(',')] for line in file]
    # print("Tamanho total do dataset: " + str(len(dataset1)))
    contar_cavalos = 0
    dataset = []
    for x in range(len(dataset1)):
        if dataset1[x][256] == 1:
            contar_cavalos = contar_cavalos + 1
            dataset.append(dataset1[x])

    contarNC = 0
    for x in range(len(dataset1)):
        if dataset1[x][256] == 0:
            if contarNC <= contar_cavalos:
                contarNC = contarNC + 1
                dataset.append(dataset1[x])
            else:
                break

    embaralhar(dataset)
    embaralhar(dataset)
    embaralhar(dataset)
    treino = dataset[0:int(7*len(dataset)/10)]
    teste = dataset[int(7*len(dataset)/10):]
    return treino, teste
