package br.ufrpe.aluguelDeCarro.excecoes;

/**
 * @author Fernando
 */
public class ClienteNaoEncontradoException extends Exception {
    public static String ID = "Cliente com este id não encontrado";
    public static String CPF = "Cliente com este CPF não encontrado";

    public ClienteNaoEncontradoException() {
    }

    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
