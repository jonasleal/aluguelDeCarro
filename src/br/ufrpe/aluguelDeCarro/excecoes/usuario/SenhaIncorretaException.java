package br.ufrpe.aluguelDeCarro.excecoes.usuario;

/**
 * @author Fernando
 */
public class SenhaIncorretaException extends UsuarioInvalidoException {
    public SenhaIncorretaException() {
        super("Senha incorreta");
    }
}
