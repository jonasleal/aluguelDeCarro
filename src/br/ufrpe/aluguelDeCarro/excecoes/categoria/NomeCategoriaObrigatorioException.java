package br.ufrpe.aluguelDeCarro.excecoes.categoria;

/**
 * @author Fernando
 */
public class NomeCategoriaObrigatorioException extends CategoriaInvalidaException {
    public NomeCategoriaObrigatorioException() {
        super("O nome da categoria é obrigatorio");
    }
}
