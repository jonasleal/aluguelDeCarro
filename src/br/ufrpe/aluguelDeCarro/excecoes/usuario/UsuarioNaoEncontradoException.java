package br.ufrpe.aluguelDeCarro.excecoes.usuario;

/**
 * @author Fernando
 */
public class UsuarioNaoEncontradoException extends UsuarioInvalidoException {
    public UsuarioNaoEncontradoException() {
        super("Usuario não encontrado");
    }
}
