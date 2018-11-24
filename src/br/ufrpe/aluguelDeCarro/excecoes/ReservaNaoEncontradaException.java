package br.ufrpe.aluguelDeCarro.excecoes;

/**
 * @author Fernando
 */
public class ReservaNaoEncontradaException extends Exception {
    public static String ID = "Reserva com este id não encontrado";

    public ReservaNaoEncontradaException() {
    }

    public ReservaNaoEncontradaException(String message) {
        super(message);
    }
}
