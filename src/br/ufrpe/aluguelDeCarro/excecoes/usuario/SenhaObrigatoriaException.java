package br.ufrpe.aluguelDeCarro.excecoes.usuario;

/**
 * @author Fernando
 */
public class SenhaObrigatoriaException extends UsuarioInvalidoException {
    public SenhaObrigatoriaException() {
        super("A senha é obrigatória");
    }
}
