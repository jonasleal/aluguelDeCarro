/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes;

/**
 *
 * @author JonasJr
 */
public class IdadeExcetion extends Exception {

    public static String MENOR = "Menor de idade.";

    public IdadeExcetion(String message) {
        super(message);
    }

    public IdadeExcetion(String message, Throwable cause) {
        super(message, cause);
    }

}
