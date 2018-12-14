/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.aluguel;

/**
 * @author JonasJr
 */
public class AluguelInvalidoException extends Exception {

    public AluguelInvalidoException() {
        super("aluguel invalido");
    }

    public AluguelInvalidoException(String message) {
        super(message);
    }

    public AluguelInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }


}
