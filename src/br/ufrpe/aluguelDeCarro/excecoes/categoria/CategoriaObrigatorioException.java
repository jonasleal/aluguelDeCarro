package br.ufrpe.aluguelDeCarro.excecoes.categoria;

/**
 * @author Fernando
 */
public class CategoriaObrigatorioException extends CategoriaInvalidaException {
    public CategoriaObrigatorioException() {
        super("Categoria é obrigatorio");
    }
}
