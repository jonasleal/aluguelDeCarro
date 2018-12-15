package br.ufrpe.aluguelDeCarro.excecoes.reserva;

import java.time.LocalDateTime;

/**
 * @author Fernando
 */
public class DataDevolucaoPassadoException extends ReservaInvalidaException {
    private LocalDateTime devolucao;

    public DataDevolucaoPassadoException(LocalDateTime devolucao) {
        super(String.format("A data de devolução informada '%s' está no passado", devolucao));
        this.devolucao = devolucao;
    }

    public LocalDateTime getDevolucao() {
        return devolucao;
    }
}
