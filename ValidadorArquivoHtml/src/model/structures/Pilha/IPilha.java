/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.structures.Pilha;

/**
 *
 * @author helenas
 */
public interface IPilha<T> {
        void push(T v);
	
	T pop ();
	
	T peek();
	
	boolean estaVazia();
	
	void liberar();
}
