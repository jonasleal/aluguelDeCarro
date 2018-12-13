package br.ufrpe.aluguelDeCarro.excecoes.carro;

/**
 * @author Fernando
 */
public class CarroJaCadastradoException extends CarroInvalidoException {
    private String placa;

    public CarroJaCadastradoException(String placa) {
        super(String.format("Carro com a placa '%s' já cadastrado", placa));
        this.placa = placa;
    }

    public CarroJaCadastradoException() {
        super("Carro já cadastrado");
    }

    public String getPlaca() {
        return placa;
    }
}
