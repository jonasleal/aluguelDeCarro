package br.ufrpe.aluguelDeCarro.excecoes.categoria;

/**
 * @author Fernando
 */
public class CategoriaJaCadastradaException extends CategoriaInvalidaException {
    private String nome;

    public CategoriaJaCadastradaException() {
        super("Categoria já cadastrada");
    }

    public CategoriaJaCadastradaException(String nome) {
        super(String.format("Categoria com o nome '%s' já cadastrada", nome));
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
