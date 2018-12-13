package br.ufrpe.aluguelDeCarro.excecoes.usuario;

/**
 * @author Fernando
 */
public class UsuarioObrigatorioException extends UsuarioInvalidoException {
    public UsuarioObrigatorioException() {
        super("Usuario é obrigatorio");
    }
}
