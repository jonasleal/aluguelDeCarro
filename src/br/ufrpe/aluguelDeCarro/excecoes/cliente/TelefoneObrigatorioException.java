package br.ufrpe.aluguelDeCarro.excecoes.cliente;

/**
 * @author Fernando
 */
public class TelefoneObrigatorioException extends ClienteInvalidoException {
    public TelefoneObrigatorioException() {
        super("Telefone obrigatório");
    }
}
