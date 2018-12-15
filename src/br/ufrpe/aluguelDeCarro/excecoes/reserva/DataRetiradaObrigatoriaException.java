package br.ufrpe.aluguelDeCarro.excecoes.reserva;

/**
 * @author Fernando
 */
public class DataRetiradaObrigatoriaException extends ReservaInvalidaException {
    public DataRetiradaObrigatoriaException() {
        super("Data de retirada é obrigatoria");
    }
}
