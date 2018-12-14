/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.pessoa;

/**
 * @author JonasJr
 */
public class PessoaInvalidaException extends Exception {

    public PessoaInvalidaException() {
    }

    public PessoaInvalidaException(String message) {
        super(message);
    }

    public PessoaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

}
