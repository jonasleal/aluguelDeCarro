package br.ufrpe.aluguelDeCarro.excecoes.cliente;

/**
 * @author Fernando
 */
public class ClienteJaCadastradoException extends ClienteInvalidoException {

    private String cpf;

    public ClienteJaCadastradoException(String cpf) {
        super(String.format("Cliente com o cpf '%s' já cadastrado", cpf));
        this.cpf = cpf;
    }

    public ClienteJaCadastradoException() {
        super("Cliente já cadastrado");
    }

    public String getCpf() {
        return cpf;
    }
}
