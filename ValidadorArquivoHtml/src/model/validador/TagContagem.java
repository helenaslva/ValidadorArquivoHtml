/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.validador;

/**
 *
 * @author helenas
 */
public class TagContagem {
    private String nome;
    private int contagem;

    public TagContagem(String nome) {
        this.nome = nome;
        this.contagem = 1;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getContagem() {
        return contagem;
    }

    public void setContagem(int contagem) {
        this.contagem = contagem;
    }
    
    public void incrementar() {
        this.contagem++;
    }

}
