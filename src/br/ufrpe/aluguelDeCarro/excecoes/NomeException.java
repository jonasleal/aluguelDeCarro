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
public class NomeException extends Exception {

    public static final String NULL = "Nome é obrigatório";
    public static final String INVALIDO = "Nome deve conter apenas letras";

    public NomeException(String message) {
        super(message);
    }

    public NomeException(String message, Throwable cause) {
        super(message, cause);
    }

}
