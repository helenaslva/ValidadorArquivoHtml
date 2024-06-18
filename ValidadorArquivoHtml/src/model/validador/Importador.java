/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.validador;

import model.validador.Interfaces.IImportador;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.structures.ListaEncadeada.ListaEncadeada;


/**
 *
 * @author helenas
 */
public class Importador implements IImportador{
    private ListaEncadeada<String> lista; 
    
    public Importador() {
        this.lista = new ListaEncadeada<>(); 
    }

    /**
     *
     * @param arquivo
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public void carregarArquivo(File arquivo) throws FileNotFoundException, IOException {
         try(Scanner sc = new Scanner(arquivo, "UTF-8")){  
          
            while(sc.hasNextLine()){
                String linha = sc.nextLine();
                if(!linha.equals("")){
                   lista.inserir(linha); 
                }  
            }
        } 
    }
    
    public ListaEncadeada getLista(){
        return lista; 
    }
    
}
