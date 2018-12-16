package br.ufrpe.aluguelDeCarro.excecoes.aluguel;

/**
 * @author Fernando
 */
public class AluguelObrigatorioException extends AluguelInvalidoException {
    public AluguelObrigatorioException() {
        super("Aluguel é obrigatorio");
    }
}
