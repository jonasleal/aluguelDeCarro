package br.ufrpe.aluguelDeCarro.excecoes;

/**
 * @author Fernando
 */
public class AtendenteNaoEncontradoException extends Exception {
    public static String ID = "Atendente com este id não encontrado";

    public AtendenteNaoEncontradoException() {
    }

    public AtendenteNaoEncontradoException(String message) {
        super(message);
    }
}
