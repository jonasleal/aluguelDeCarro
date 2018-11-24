package br.ufrpe.aluguelDeCarro.excecoes;

/**
 * @author Fernando
 */
public class ManutencaoNaoEncontradaException extends Exception {
    public static String ID = "Manutenção com este id não encontrado";

    public ManutencaoNaoEncontradaException() {
    }

    public ManutencaoNaoEncontradaException(String message) {
        super(message);
    }
}
