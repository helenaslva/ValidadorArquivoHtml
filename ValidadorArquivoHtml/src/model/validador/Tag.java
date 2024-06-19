/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.validador;

/**
 *
 * @author helenas
 */
public class Tag {

    public Tag(String nome, int linha, boolean ehFechamento) {
        this.nome = nome;
        this.linha = linha;
        this.ehFechamento = ehFechamento;
    }
    private String nome;
    private int linha;
    private boolean ehFechamento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public boolean isEhFechamento() {
        return ehFechamento;
    }

    public void setEhFechamento(boolean ehFechamento) {
        this.ehFechamento = ehFechamento;
    }
    
    
}
