package br.ufrpe.aluguelDeCarro.excecoes;

/**
 * @author Fernando
 */
public class AluguelNaoEncontradoException extends Exception {
    public static String CARRO = "Aluguel com este carro não encontrado";
    public static String CLIENTE = "Aluguel com este cliente não encontrado";
    public static String ID = "Aluguel com este id não encontrado";

    public AluguelNaoEncontradoException() {
    }

    public AluguelNaoEncontradoException(String message) {
        super(message);
    }
}
