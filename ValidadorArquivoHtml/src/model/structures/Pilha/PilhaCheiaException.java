/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.structures.Pilha;

/**
 *
 * @author helenas
 */
public class PilhaCheiaException extends RuntimeException {

	public PilhaCheiaException() {
		super("Capacidade esgotada da pilha");
	}
	
}
