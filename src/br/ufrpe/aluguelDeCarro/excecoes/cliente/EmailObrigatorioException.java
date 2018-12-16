package br.ufrpe.aluguelDeCarro.excecoes.cliente;

/**
 * @author Fernando
 */
public class EmailObrigatorioException extends ClienteInvalidoException {
    public EmailObrigatorioException() {
        super("Email é obrigatório");
    }
}
