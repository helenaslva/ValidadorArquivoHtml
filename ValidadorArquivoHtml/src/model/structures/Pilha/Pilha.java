/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.structures.Pilha;

import model.structures.ListaEncadeada.ListaEncadeada;

/**
 *
 * @author helenas
 */
public class Pilha<T> implements IPilha<T>{
    private ListaEncadeada<T> lista = new ListaEncadeada<>();

    @Override
    public void push(T info) {
        lista.inserir(info);
    }

    @Override
    public T pop() {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }
        T valor = peek();
        lista.retirar(valor);
        return valor;
    }

    @Override
    public T peek() {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }
        return lista.getPrimeiro().getInfo();
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public void liberar() {
        lista = new ListaEncadeada<>();
    }

    @Override
    public String toString() {
        return lista.toString();
    }
    
    public Pilha inverterPilha() {
        Pilha<T> pilhaInvertida = new Pilha<>();

        while (!this.estaVazia()) {
            pilhaInvertida.push((T)this.pop());
        }

        return pilhaInvertida;

    }
	
}

