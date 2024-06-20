package model.validador;


import model.structures.Pilha.Pilha;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {
    private Pilha<Tag> pilha;
    private Pilha<Tag> tagsInvalidas;
    public Validador() {
        this.pilha = new Pilha<>();
        this.tagsInvalidas = new Pilha<>();
    }

    public Pilha<Tag> getPilha() {
        return pilha;
    }

    public String validarHtml (Pilha<Linha> pilhaLinhaBlablabla) {
        String mensagem = " ";
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
            }
        }
        
        if(tagsInvalidas.estaVazia()){
            mensagem = "Arquivo bem formatado, não encontramos nenhum erro!" ;
        }
        else{
            while(!tagsInvalidas.estaVazia()){
                Tag tag = tagsInvalidas.pop();
                mensagem += "Tag " + tag.getNome() + " inválida na linha " + tag.getLinha() + "\n";
            }
        }
        
        return mensagem;
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
    
    public Pilha inverterPilha(Pilha<Linha> pilha) {
        Pilha<Linha> pilhaInvertida = new Pilha<>();

        while (!pilha.estaVazia()) {
            pilhaInvertida.push(pilha.pop());
        }

        return pilhaInvertida;

    }

   
}
