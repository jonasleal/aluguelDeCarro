/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes;

/**
 * @author JonasJr
 */
public class ModeloException extends Exception {

    public static final String NULL = "Modelo é obrigatória";
    public static final String INVALIDA = "Modelo não é valida";

    public ModeloException(String message) {
        super(message);
    }

    public ModeloException(String message, Throwable cause) {
        super(message, cause);
    }

}
