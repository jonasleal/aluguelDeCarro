/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.cliente;

/**
 * @author JonasJr
 */
public class FormatoHabilitacaoException extends ClienteInvalidoException {

    private String habilitacao;

    public FormatoHabilitacaoException() {
        super("Habilitação deve conter 11 caracteres");
    }

    public FormatoHabilitacaoException(String habilitacao) {
        this();
        this.habilitacao = habilitacao;
    }

    @Override
    public String getMessage() {
        if (habilitacao != null && !habilitacao.isEmpty()) {
            return super.getMessage() + ": " + habilitacao;
        }
        return super.getMessage();
    }

}
