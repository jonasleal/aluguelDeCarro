package br.ufrpe.aluguelDeCarro.excecoes.reserva;

/**
 * @author Fernando
 */
public class ReservaInvalidaException extends Exception {
    public ReservaInvalidaException(String message) {
        super(message);
    }

    public ReservaInvalidaException() {
        super("Reserva inválida");
    }
}
