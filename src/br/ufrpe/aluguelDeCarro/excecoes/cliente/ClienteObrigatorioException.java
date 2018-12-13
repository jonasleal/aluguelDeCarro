package br.ufrpe.aluguelDeCarro.excecoes.cliente;

/**
 * @author Fernando
 */
public class ClienteObrigatorioException extends ClienteInvalidoException {
    public ClienteObrigatorioException() {
        super("Cliente é obrigatorio");
    }
}
