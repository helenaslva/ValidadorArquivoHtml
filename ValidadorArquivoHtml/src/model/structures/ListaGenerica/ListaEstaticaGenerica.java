/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.structures.ListaGenerica;

/**
 *
 * @author helenas
 * @param <T>
 */
public class ListaEstaticaGenerica<T> {
    private T[] info;
    private int tamanho;

    public ListaEstaticaGenerica() {
        info = (T[]) new Object[10];

        //serve para controlar o tamanho do vetor
        tamanho = 0;
    }

    private void redimendionar(){
        // cria um novo vetor com mais 10 posições
        T[] novoInfo = (T[]) new Object[info.length + 10];

        //Copia o valor do inidice do vetor atual para o novo vetor na mesma posição
        for(int i = 0; i < info.length; i++){
            novoInfo[i] = info[i];
        }
        //Atualiza a variavel info para o novo info que tem mais capacidade.
        info = novoInfo;
    }

    public void inserir(T valor){
        //Se o tamanho atual do vetor for igual a capacidade do vetor, ele redimensiona para 10 posições a mais.
        if(tamanho == info.length){
            redimendionar();
        }

        //Insere o elemento na próxima posição disponível
        info[tamanho] = valor;

        //incrementa a variavel do controle de tamanho;
        tamanho++;
    }
//1 - 0, 2 - 1, 3 - 2, 4 - 3...11 - 10
    //
    private void exibir(){

        for(int i = 0; i < info.length; i ++){
            //Obtém o valor do indice
            T valor = info[i];
            //Imprime esse valor no console de acordo com o indice;
            System.out.println("[" + i + "]: " + valor );
        }
    }

    /**
     * Busca por um valor na lista, se encontrar retorna o indice do mesmo no vetor.
     * Caso não encontre, retornará -1.
     * @param valor - o elemento que será pesquisado.
     * @return um int que representa o indice do elemento no vetor.
     */
    public int buscar(T valor) {
        // Percorre o vetor.
        for (int indice = 0; indice < tamanho; indice++) {
            // Obtém o valor do indice.
            T valorLista = info[indice];
            // Verifica se o valor é igual ao valor informado.
            if (valorLista.equals(valor)) {
                // Caso encontar o valor, retorna o indice do mesmo.
                return indice;
            }
        }
        // Se não encontrou nenhum elemento igual, retorna -1.
        return -1;
    }
    /**
     * Remove um elemento da lista.
     * @param valor - o elemento que será removido.
     */
    public void retirar(T valor){
        int indiceRetirada = buscar(valor);

        if(indiceRetirada > -1){
            // Percorre o vetor a partir do indice do elemento que deve ser removido.
            //A razão pela qual é subtraído 1 do tamanho é que os índices de arrays/listas
            // começam do 0. Portanto, se a lista tiver um tamanho de, por exemplo, 5,
            //os índices válidos estarão entre 0 e 4.
            for(int i = indiceRetirada; i < tamanho - 1; i++){
                // Move todos os valores para a esquerda
                info[i] = info[i + 1];

            }
            // Decrementa a variável de tamanho da lista.
            tamanho --;
        }
    }

    /**
     * Realiza a liberação da lista.
     */
    public void liberar() {
        // Cria um novo vetor e atribui para a variável info.
        info = (T[]) new Object[10];
        // Atualiza a variável de controle do tamanho da lista para zero elementos.
        tamanho = 0;
    }

    /**
     * Retorna o elemento baseado no indice do vetor.
     * @param posicao - o indice que será utilizado para encontrar o elemento.
     * @return o elemento da posição informada se existir a posição na lista, caso contrário irá lançar uma exceção.
     */
    public T obterElemento(int posicao){
        //Verifica se a posicao informada é maior que 0 e menor que o tamanho da lista
        if(posicao >= 0 && posicao < tamanho){
            //Se a posição estiver contida na lista, retorna o elemento
            return info[posicao];

        }
        // Caso contrário lança a exceção.
        throw new IndexOutOfBoundsException();
    }
    /**
     * Retorna se a lista está vazia.
     * @return um valor verdadeiro se a lista estiver vazia, e um valor falso caso a lista possua algum elemento.
     */
    public boolean estaVazia() {
        return tamanho == 0;
    }

    /**
     * Retorna o tamanho da lista
     * @return um inteiro com a quantidade de elementos da lista.
     */
    public int getTamanho() {
        return tamanho;
    }

    @Override
    public String toString(){
        String resultado = "";

        for(int i = 0; i < tamanho; i ++){
            // Verifica se é o primeiro elemento.
            if (i > 0) {
                // Se não for o primeiro elemento, adiciona uma ",".
                resultado += ", ";
            }

            //Obtém o valor do indice
            T valor = info[i];

            //Concatena a variavel de resultado
            resultado+= valor.toString();
        }

        return resultado;


    }

    public void inverter(){
        //Inicia a variavel esquerda com 0;
        int esquerda = 0;

        //Inicia a variavel direita com o ultimo inidice do vetor, tamanho - 1 pois o vetor inicia as posições no 0;
        int direita = tamanho - 1;

        //Enquanto a esquerda for menor que a direita, executa
        while (esquerda < direita){

            //Copia o valor do indice da esquerda para uma variavel temporaria
            T temp = info[esquerda];

            //Atribui o valor do indice da direita ao indice da esquerda
            info[esquerda] = info[direita];

            //Atribui o valor da esquerda anterior que estava armazenado na variavel temporaria ao valor da direita;
            info[direita] = temp;

            esquerda ++;
            direita --;
        }
    }
}