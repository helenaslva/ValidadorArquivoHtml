/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.validador;

import model.validador.Interfaces.IValidador;

/**
 *
 * @author helenas
 */
public class Validador implements IValidador{

    public Importador importador; 
    public Validador() {
        importador = new Importador();
    }

    @Override
    public void validarArquivoHtml() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
