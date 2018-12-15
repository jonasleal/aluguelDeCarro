package br.ufrpe.aluguelDeCarro.excecoes.reserva;

/**
 * @author Fernando
 */
public class DataDevolucaoObrigatoriaException extends ReservaInvalidaException {
    public DataDevolucaoObrigatoriaException() {
        super("Data de devolução é obrigatoria");
    }
}
