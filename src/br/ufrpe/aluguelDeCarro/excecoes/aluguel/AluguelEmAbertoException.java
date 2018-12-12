/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.aluguel;

/**
 *
 * @author JonasJr
 */
public class AluguelEmAbertoException extends AluguelInvalidoException {

    private String cpf;

    public AluguelEmAbertoException() {
        super("Esse CPF contem um aluguel em aberto");
    }

    public AluguelEmAbertoException(String cpf) {
        this();
        this.cpf = cpf;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + cpf;
    }
    
    
    public AluguelEmAbertoException(String message, Throwable cause) {
        super(message, cause);
    }

}
