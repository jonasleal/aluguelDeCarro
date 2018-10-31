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
public class PlacaException extends Exception{
    public static final String NULL = "Placa é obrigatório";
    public static final String INVALIDA = "Placa deve conter 3 letras e 4 números";
    
    public PlacaException(String message) {
        super(message);
    }

    public PlacaException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
