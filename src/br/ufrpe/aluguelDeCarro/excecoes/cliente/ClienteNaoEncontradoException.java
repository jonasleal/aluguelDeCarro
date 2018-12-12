package br.ufrpe.aluguelDeCarro.excecoes.cliente;

/**
 * @author Fernando
 */
public class ClienteNaoEncontradoException extends ClienteInvalidoException {

    private String cpf;

    public ClienteNaoEncontradoException() {
        super("Cliente não encontrado");
    }

    public ClienteNaoEncontradoException(String cpf) {
        this();
        this.cpf = cpf;
    }

    @Override
    public String getMessage() {
        String saida = super.getMessage();
        if (cpf != null && !cpf.isEmpty()) {

            saida += ": " + cpf;
        }
        return saida;
    }

}
