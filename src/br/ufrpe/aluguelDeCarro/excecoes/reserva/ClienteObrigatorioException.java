package br.ufrpe.aluguelDeCarro.excecoes.reserva;

/**
 * @author Fernando
 */
public class ClienteObrigatorioException extends ReservaInvalidaException {
    public ClienteObrigatorioException() {
        super("Cliente é obrigatorio");
    }
}
