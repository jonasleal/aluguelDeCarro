/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.Carro;

/**
 *
 * @author JonasJr
 */
public class CarroInvalidoException extends Exception {

    public CarroInvalidoException() {
        super("Carro invalido");
    }

    public CarroInvalidoException(String message) {
        super(message);
    }

    public CarroInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

}
