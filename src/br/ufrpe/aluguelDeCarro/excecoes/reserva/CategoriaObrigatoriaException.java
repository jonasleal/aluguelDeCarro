package br.ufrpe.aluguelDeCarro.excecoes.reserva;

/**
 * @author Fernando
 */
public class CategoriaObrigatoriaException extends ReservaInvalidaException {
    public CategoriaObrigatoriaException() {
        super("Categoria é obrigatoria");
    }
}
