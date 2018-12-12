/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.carro;

/**
 *
 * @author JonasJr
 */
public class NumeroDePortasException extends CarroInvalidoException {

    private int portas;

    public NumeroDePortasException() {
        super("O numero de portas é invalido");
    }

    public NumeroDePortasException(int portas) {
        this();
        this.portas = portas;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + portas;
    }

}
