/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.validador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.structures.ListaEncadeada.ListaEncadeada;
import model.structures.Pilha.Pilha;

/**
 *
 * @author helenas
 */
public class main {
    
    public static void main(String[] args) {
        Importador imp = new Importador();
        
        String caminhoDoArquivo = "C:\\Users\\helenas\\Downloads\\teste.html";
        
        // Criação do novo arquivo
        File novoArquivo = new File(caminhoDoArquivo);
        
        try {
            imp.carregarArquivo(novoArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de E/S: " + e.getMessage());
        }
        
        Pilha<Linha> pilha = imp.getPilha();
        
        
        Validador valid = new Validador();
        
     
        System.out.println(valid.validarHtml(pilha));
        //valid.exibirPilha();
        //System.out.println();
        valid.exibirPilhaTagsInvalidas();
       
        
        
      
    }
}
