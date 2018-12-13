package br.ufrpe.aluguelDeCarro.excecoes.aluguel;

/**
 * @author Fernando
 */
public class DataDevolucacaoEstimadaObrigatoriaException extends AluguelInvalidoException {
    public DataDevolucacaoEstimadaObrigatoriaException() {
        super("Data de devolução estimada é obrigatoria");
    }
}
