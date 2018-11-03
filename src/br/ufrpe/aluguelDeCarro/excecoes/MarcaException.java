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
public class MarcaException extends Exception {

    public static final String NULL = "Marca é obrigatória";
    public static final String INVALIDA = "Marca não é valida";

    public MarcaException(String message) {
        super(message);
    }

    public MarcaException(String message, Throwable cause) {
        super(message, cause);
    }

}
