package model.validador;


import model.structures.Pilha.Pilha;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.structures.ListaEncadeada.ListaEncadeada;
import model.structures.ListaEncadeada.NoLista;

public class Validador {
    private Pilha<Tag> pilha;
    private Pilha<Tag> tagsInvalidas;
    private ListaEncadeada<TagContagem> todasTags; 
    public Validador() {
        this.pilha = new Pilha<>();
        this.tagsInvalidas = new Pilha<>();
        this.todasTags = new ListaEncadeada();
    }

    public Pilha<Tag> getPilha() {
        return pilha;
    }

    public String validarHtml (Pilha<Linha> pilhaLinhaBlablabla) {
        StringBuilder mensagem = new StringBuilder();
        Pattern pattern = Pattern.compile("</?([a-zA-Z]+).*?/?>");
        Pilha<Linha> pilhaLinha = inverterPilha(pilhaLinhaBlablabla);
        
        while (!pilhaLinha.estaVazia()) {
            Linha linhaAtual = pilhaLinha.pop();
            String conteudo = linhaAtual.getConteudo();
            Matcher matcher = pattern.matcher(conteudo);


            while (matcher.find()) {
                String tagCompleta = matcher.group();
                String tagNome = matcher.group(1);
                boolean ehFechamento = tagCompleta.startsWith("</");
                boolean ehSingleton = tagCompleta.endsWith("/>") || tagCompleta.matches("<(meta|link|img|br|hr|input|source|area|col|embed|param|track|wbr)(\\s+[^>]*)?>");

                if (ehSingleton) {
                    // Tags singleton são tanto abertura quanto fechamento
                    Tag tag = new Tag(tagNome, linhaAtual.getNumero(), false);
                     validarTags(false, tag, pilha);
                    Tag tagFechamento = new Tag(tagNome, linhaAtual.getNumero(), true);
                     validarTags(true, tag, pilha);
                } else {
                    Tag tag = new Tag(tagNome, linhaAtual.getNumero(), ehFechamento);
                    validarTags(ehFechamento, tag, pilha);
                }
                
                atualizarContagemDeTags(tagNome);
            }
        }
        
        if(tagsInvalidas.estaVazia()){
            mensagem.append("Arquivo bem formatado, não encontramos nenhum erro!");
        }
        else{
            mensagem.append("Arquivo mal formatado. Encontramos os seguintes erros:\n");
            while(!tagsInvalidas.estaVazia()){
                Tag tag = tagsInvalidas.pop();
                mensagem.append("Tag ").append(tag.getNome()).append(" inválida na linha ").append(tag.getLinha()).append("\n");
            }
        }
        
        return mensagem.toString();
    }
    
    public void validarTags(boolean ehFechamento, Tag tag, Pilha<Tag> pilha){
        
        if(ehFechamento){
            if(pilha.peek().getNome().equals(tag.getNome())){
                pilha.pop();
            }
            else{
                tagsInvalidas.push(pilha.pop());
                validarTags(ehFechamento, tag, pilha);
            }
        }else{
            pilha.push(tag);
        }
    }
    
     private void atualizarContagemDeTags(String tagNome) {
        NoLista<TagContagem> atual = todasTags.getPrimeiro();
        while (atual != null) {
            if (atual.getInfo().getNome().equals(tagNome)) {
                atual.getInfo().incrementar();
                return;
            }
            atual = atual.getProximo();
        }
        todasTags.inserir(new TagContagem(tagNome));
    }

    public void exibirPilha() {
        while (!pilha.estaVazia()) {
            Tag tag = pilha.pop();
            System.out.println((tag.isEhFechamento() ? "Fechamento" : "Abertura") + " da tag: " + tag.getNome() + " na linha: " + tag.getLinha());
        }
    }
    
    public void exibirPilhaTagsInvalidas() {
        while (!tagsInvalidas.estaVazia()) {
            Tag tag = tagsInvalidas.pop();
            System.out.println((tag.isEhFechamento() ? "Fechamento" : "Abertura") + " da tag: " + tag.getNome() + " na linha: " + tag.getLinha());
        }
    }
    
    public ListaEncadeada retornarListaTags(){
        return this.todasTags;
    }
    
    public Pilha inverterPilha(Pilha<Linha> pilha) {
        Pilha<Linha> pilhaInvertida = new Pilha<>();

        while (!pilha.estaVazia()) {
            pilhaInvertida.push(pilha.pop());
        }

        return pilhaInvertida;

    }

   
}
