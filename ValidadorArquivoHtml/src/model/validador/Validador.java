package model.validador;

import model.structures.ListaEncadeada.ListaEncadeada;
import model.structures.ListaEncadeada.NoLista;
import model.structures.Pilha.Pilha;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {
    private Pilha<Tag> pilha;

    public Validador() {
        this.pilha = new Pilha<>();
    }

    public Pilha<Tag> getPilha() {
        return pilha;
    }

    public void populaPilhaComTags(ListaEncadeada<Linha> lista) {
        NoLista<Linha> noAtual = lista.getPrimeiro();

        Pattern pattern = Pattern.compile("</?([a-zA-Z]+).*?/?>");

        while (noAtual != null) {
            String conteudo = noAtual.getInfo().getConteudo();
            Matcher matcher = pattern.matcher(conteudo);

            while (matcher.find()) {
                String tagCompleta = matcher.group();
                String tagNome = matcher.group(1);
                boolean ehFechamento = tagCompleta.startsWith("</");
                boolean ehSingleton = tagCompleta.endsWith("/>") || tagCompleta.matches("<(meta|link|img|br|hr|input|source|area|col|embed|param|track|wbr)(\\s+[^>]*)?>");

                if (ehSingleton) {
                    // Tags singletons são tanto abertura quanto fechamento
                    Tag tag = new Tag(tagNome, noAtual.getInfo().getNumero(), false);
                    pilha.push(tag);
                    Tag tagFechamento = new Tag(tagNome, noAtual.getInfo().getNumero(), true);
                    pilha.push(tagFechamento);
                } else {
                    Tag tag = new Tag(tagNome, noAtual.getInfo().getNumero(), ehFechamento);
                    pilha.push(tag);
                }
            }

            noAtual = noAtual.getProximo();
        }
    }

    public void exibirPilha() {
        while (!pilha.estaVazia()) {
            Tag tag = pilha.pop();
            System.out.println((tag.isEhFechamento() ? "Fechamento" : "Abertura") + " da tag: " + tag.getNome() + " na linha: " + tag.getLinha());
        }
    }

    public boolean validarArquivoHtml() {
        Pilha<Tag> tagsAbertura = new Pilha<>();
        
        // Inverte a pilha original para manter a ordem correta
        Pilha<Tag> pilhaAux = new Pilha<>();
        while (!pilha.estaVazia()) {
            pilhaAux.push(pilha.pop());
        }

        while (!pilhaAux.estaVazia()) {
            Tag tag = pilhaAux.pop();
            if (!tag.isEhFechamento()) {
                tagsAbertura.push(tag);
            } else {
                if (tagsAbertura.estaVazia()) {
                    System.out.println("Erro: tag de fechamento sem correspondente de abertura na linha " + tag.getLinha() + ": " + tag.getNome());
                    return false;
                }
                Tag ultimaAbertura = tagsAbertura.pop();
                if (!ultimaAbertura.getNome().equals(tag.getNome())) {
                    System.out.println("Erro: tag de fechamento " + tag.getNome() + " na linha " + tag.getLinha() + " não corresponde à tag de abertura " + ultimaAbertura.getNome() + " na linha " + ultimaAbertura.getLinha());
                    return false;
                }
            }
        }

        if (!tagsAbertura.estaVazia()) {
            Tag tag = tagsAbertura.pop();
            System.out.println("Erro: tag de abertura sem correspondente de fechamento na linha " + tag.getLinha() + ": " + tag.getNome());
            return false;
        }

        System.out.println("Arquivo HTML está bem formatado.");
        return true;
    }
}
