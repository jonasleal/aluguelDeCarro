package br.ufrpe.aluguelDeCarro.excecoes.reserva;

/**
 * @author Fernando
 */
public class ReservaObrigatoriaException extends ReservaInvalidaException {
    public ReservaObrigatoriaException() {
        super("A reserva é obrigatoria");
    }
}
