package br.ufrpe.aluguelDeCarro.excecoes;

/**
 * @author Fernando
 */
public class GerenteNaoEncontradoException extends Exception {
    public static String ID = "Gerente com este id não encontrado";
    public static String CPF = "Gerente com este CPF não encontrado";

    public GerenteNaoEncontradoException() {
    }

    public GerenteNaoEncontradoException(String message) {
        super(message);
    }
}
