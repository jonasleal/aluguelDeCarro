/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.cliente;

/**
 *
 * @author JonasJr
 */
public class ClienteInvalidoException extends Exception {

    public ClienteInvalidoException() {
        super();
    }

    public ClienteInvalidoException(String message) {
        super(message);
    }

    public ClienteInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

}
