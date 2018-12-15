package br.ufrpe.aluguelDeCarro.excecoes.reserva;

import java.time.LocalDateTime;

/**
 * @author Fernando
 */
public class DataRetiradaPassadoException extends ReservaInvalidaException {
    private LocalDateTime retirada;

    public DataRetiradaPassadoException(LocalDateTime retirada) {
        super(String.format("A data de retirada informada '%s' está no passado", retirada));
        this.retirada = retirada;
    }

    public LocalDateTime getRetirada() {
        return retirada;
    }
}
