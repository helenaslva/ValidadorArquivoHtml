/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servico;

import java.io.File;
import java.io.IOException;
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
    
    public ServicoArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String processarArquivo() throws IOException {
        File file = new File(arquivo);
        Importador importador = new Importador();
        Pilha<Linha> pilha = importador.getPilha();
        Validador validaArquivo = new Validador();
        validaArquivo.validarHtml(pilha);
        return validaArquivo.validarHtml(pilha);
    }
}
