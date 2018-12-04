/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.Data;

import java.time.LocalDateTime;

/**
 *
 * @author JonasJr
 */
public class DataInvalidaException extends Exception {
    public DataInvalidaException(){
        super("Data invalida");
    }

    public DataInvalidaException(String message) {
        super(message);
    }

    public DataInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

}
