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
public class NumeroDeOcupantesException extends CarroInvalidoException {

    private int ocupantes;

    public NumeroDeOcupantesException() {
        super("O numero de ocupantes é invalido");
    }

    public NumeroDeOcupantesException(int ocupantes) {
        this();
        this.ocupantes = ocupantes;
    }

}
