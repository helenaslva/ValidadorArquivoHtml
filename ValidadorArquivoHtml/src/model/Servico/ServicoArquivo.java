/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Servico;

import java.io.File;
import java.io.IOException;
import model.structures.ListaEncadeada.ListaEncadeada;
import model.structures.Pilha.Pilha;
import model.validador.Importador;
import model.validador.Linha;
import model.validador.Validador;

/**
 *
 * @author Usuario
 */
public class ServicoArquivo {
    
    private String arquivo;
    private Importador importador; 
    private Validador validaArquivo;
    
    public ServicoArquivo(String arquivo) {
        this.arquivo = arquivo;
        this.importador = new Importador();
        this.validaArquivo = new Validador();
    }

    public String processarArquivo() throws IOException {
        File file = new File(arquivo);
        Pilha<Linha> pilha = importador.getPilha();
        importador.carregarArquivo(file);
        return validaArquivo.validarHtml(pilha);
    }
    
    public ListaEncadeada retornarContador(){
        return validaArquivo.retornarListaTags();
    }
}
