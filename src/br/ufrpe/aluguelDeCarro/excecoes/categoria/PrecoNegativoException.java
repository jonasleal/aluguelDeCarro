/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.categoria;

import java.math.BigDecimal;

/**
 * @author JonasJr
 */
public class PrecoNegativoException extends CategoriaInvalidaException {

    private BigDecimal preco;

    public PrecoNegativoException() {
        super("O preço deve ser positivo");
    }

    public PrecoNegativoException(BigDecimal preco) {
        this();
        this.preco = preco;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": R$" + preco;
    }


}
