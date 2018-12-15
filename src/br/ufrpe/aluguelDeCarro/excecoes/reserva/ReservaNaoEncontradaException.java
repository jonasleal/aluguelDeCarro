package br.ufrpe.aluguelDeCarro.excecoes.reserva;

/**
 * @author Fernando
 */
public class ReservaNaoEncontradaException extends ReservaInvalidaException {
    public static String ID = "Reserva com este id não encontrado";

    public ReservaNaoEncontradaException() {
        super("Reserva não encontrada");
    }

    public ReservaNaoEncontradaException(String message) {
        super(message);
    }
}
