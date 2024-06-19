/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.structures.Pilha;

/**
 *
 * @author helenas
 */
public class PilhaVaziaException extends RuntimeException {
    public PilhaVaziaException() {
        super("A pilha está vazia.");
    }
}
