/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.pessoa;

/**
 * @author JonasJr
 */
public class MenorDeIdadeException extends PessoaInvalidaException {

    private int idade;

    public MenorDeIdadeException() {
        super("Menor de idade");
    }

    public MenorDeIdadeException(int idade) {
        this();
        this.idade = idade;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + this.idade;
    }

}
