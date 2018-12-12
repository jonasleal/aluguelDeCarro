/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.aluguel;

import java.math.BigDecimal;

/**
 *
 * @author JonasJr
 */
public class ValorEstimadoNegativoException extends AluguelInvalidoException {

    private BigDecimal valor;

    public ValorEstimadoNegativoException() {
        super("Valor estimado deve ser positivo");
    }
// fazer duas excessoes

    public ValorEstimadoNegativoException(BigDecimal valor) {
        this();
        this.valor = valor;
    }

    @Override
    public String getMessage() {
        if (valor != null) {
            return super.getMessage() + ": R$" + valor.toString();
        }

        return super.getMessage();
    }

}
