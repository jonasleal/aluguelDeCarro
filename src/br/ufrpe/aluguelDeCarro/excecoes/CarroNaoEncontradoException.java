package br.ufrpe.aluguelDeCarro.excecoes;

/**
 * @author Fernando
 */
public class CarroNaoEncontradoException extends Exception {
    public static String ID = "Carro com este id não encontrado";
    public static String PLACA = "Carro com esta placa não encontrado";

    public CarroNaoEncontradoException() {
    }

    public CarroNaoEncontradoException(String message) {
        super(message);
    }
}
