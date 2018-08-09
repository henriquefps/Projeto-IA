from random import randint
from datasets import *


def executar_programa(limiar_de_ativacao, tx_de_aprendizado, iteracoes, comentarios):

    def iniciar_pesos():
        return [randint(1, 10) for x in range(0, 256)]

    def avaliar_entrada(histograma, pesos, limiar):  # Funcao ativacao
        valor = 0
        for y in range(256):
            valor = valor + pesos[y] * histograma[y]

        if valor >= limiar:
            return 1
        else:
            return 0

    def iteracoes_de_treino(dataset, pesos, taxa_de_aprendizado, limiar, iteracoes): # Repete o treino por n iteracoes, ou ate os pesos convergirem

        def calcular_novo_peso(peso, saida_esperada, saida_real, taxa_de_aprendizado, xi):  # Atualiza o um peso caso haja erro durante o treino
            novo_peso = peso + (taxa_de_aprendizado * (saida_esperada - saida_real) * xi)
            return novo_peso

        def treinar_perceptron_com_entrada(histograma, pesos, taxa_de_aprendizado, limiar):  # Executa o treino para uma entrada
            saida_esperada = histograma[256]
            saida_real = avaliar_entrada(histograma, pesos, limiar)
            if saida_real != saida_esperada:  # Recalcula o peso se houver erro
                for x in range(len(pesos)):
                    pesos[x] = calcular_novo_peso(pesos[x], saida_esperada, saida_real, taxa_de_aprendizado, histograma[x])
            return pesos

        def treinar_perceptron_com_dataset(dataset, pesos, taxa_de_aprendizado, limiar):  # treina o perceptron com um dataset inteiro
            for x in range(len(dataset)):
                pesos = treinar_perceptron_com_entrada(dataset[x], pesos, taxa_de_aprendizado, limiar)
            return pesos

        x = 0
        while x < iteracoes:
            resutados_antes = testar_com_dataset(dataset, pesos, limiar)
            pesos = treinar_perceptron_com_dataset(dataset, pesos, taxa_de_aprendizado, limiar)
            resultados_depois = testar_com_dataset(dataset, pesos, limiar)
            if resutados_antes == resultados_depois:
                print("Pesos convergiram, iteracoes necessarias: " + str(x))
                break
            x = x + 1
        return pesos

    def testar_com_dataset(dataset, pesos, limiar): # gera as classificacoes de um dataset
        resultados = []
        for x in range(len(dataset)):
            resultados.append(avaliar_entrada(dataset[x], pesos, limiar))
        return resultados

    def percentual_de_acerto(dataset_testes, resultados):  # Calcula o percentual de acertos de um dataset com os resultados do teste
        acertos = 0
        verdadeiro_negativo = 0
        falso_negativo = 0
        verdadeiro_positivo = 0
        falso_positivo = 0
        for x in range(len(dataset_testes)):
            if dataset_testes[x][256] == resultados[x]:
                acertos = acertos + 1
                if dataset_testes[x][256] == 1:
                    verdadeiro_positivo = verdadeiro_positivo + 1
                else:
                    verdadeiro_negativo = verdadeiro_negativo + 1
            elif dataset_testes[x][256] != resultados[x]:
                if dataset_testes[x][256] == 1:
                    falso_positivo = falso_positivo + 1
                else:
                    falso_negativo = falso_negativo + 1

        print("Matriz de Confusao")
        print("Cavalos(Acertos: " + str(verdadeiro_positivo) + " | Erros: " + str(falso_positivo) + ")")
        print("Nao Cavalos(Acertos: " + str(verdadeiro_negativo) + " | Erros: " + str(falso_negativo) + ")")
        print("Acertos totais: " + str(acertos))
        return acertos/len(dataset_testes)

    def quantificar_entradas(dataset):
        cavalos = 0
        nao_cavalos = 0
        for x in range(len(dataset)):
            if dataset[x][256] == 1:
                cavalos = cavalos + 1
            else:
                nao_cavalos = nao_cavalos + 1
        return "Cavalos: " + str(cavalos) + " | Nao Cavalos: " + str(nao_cavalos), cavalos, nao_cavalos

    # dataset[n][256] == classe da imagem n, 0 == Nao_Cavalo, 1 == Cavalo
    dataset_de_treino, dataset_de_testes = get_datasets()
    limiar = limiar_de_ativacao  # Valor escolhido para ser comparado na funcao de aticavacao
    taxa_de_aprendizado = tx_de_aprendizado
    num_iteracoes_treino = iteracoes
    pesos = iniciar_pesos()
    if comentarios == 1:
        print("Imagens para treino: " + str(len(dataset_de_treino)))
        print("Imagens para testes: " + str(len(dataset_de_testes)))
        print("Dataset de Treino(" + quantificar_entradas(dataset_de_treino)[0] + ")")
        print("Dataset de Testes(" + quantificar_entradas(dataset_de_testes)[0] + ")")
        print("Pesos antes do treino: " + str(pesos) + "\nTreinando...")
        print("Pesos apos o treino: " + str(iteracoes_de_treino(dataset_de_treino, pesos, taxa_de_aprendizado, limiar, num_iteracoes_treino)))
        print("Avaliacoes apos Treino: " + str(testar_com_dataset(dataset_de_testes, pesos, limiar)))
        print("% de acerto: " + str(percentual_de_acerto(dataset_de_testes, testar_com_dataset(dataset_de_testes, pesos, limiar))))
    elif comentarios == 0:
        iteracoes_de_treino(dataset_de_treino, pesos, taxa_de_aprendizado, limiar, num_iteracoes_treino)
        print("% de acerto: " + str(percentual_de_acerto(dataset_de_testes, testar_com_dataset(dataset_de_testes, pesos, limiar))))


def main():
    limiar_de_ativacao = 3000
    for i in range(6):
        for j in range(4):
            for k in range(5):
                print("Limiar de ativaçao: " + str(limiar_de_ativacao))
                if j == 0:
                    tx_de_aprendizado = 0.01
                elif j == 1:
                    tx_de_aprendizado = 0.1
                elif j == 2:
                    tx_de_aprendizado = 1
                elif j == 3:
                    tx_de_aprendizado = 5
                print("Taxa de aprendizado: " + str(tx_de_aprendizado))
                executar_programa(limiar_de_ativacao, tx_de_aprendizado, iteracoes=100, comentarios=1)
                print("\n")
            print("---------------------------------------------------------------------------------------")
        limiar_de_ativacao += 200

main()
