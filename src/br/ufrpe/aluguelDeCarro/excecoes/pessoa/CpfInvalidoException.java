/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.pessoa;

/**
 *
 * @author JonasJr
 */
public class CpfInvalidoException extends PessoaInvalidaException {

    public CpfInvalidoException() {
        super("CPF não é valido");
    }

    public CpfInvalidoException(Throwable cause) {
        this();
        this.addSuppressed(cause);
    }
}
