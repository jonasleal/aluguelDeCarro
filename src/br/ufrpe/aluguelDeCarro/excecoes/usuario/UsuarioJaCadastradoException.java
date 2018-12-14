package br.ufrpe.aluguelDeCarro.excecoes.usuario;

/**
 * @author Fernando
 */
public class UsuarioJaCadastradoException extends UsuarioInvalidoException {
    private String cpf;

    public UsuarioJaCadastradoException(String cpf) {
        super(String.format("Usuario com o cpf '%s' já cadastrado", cpf));
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
