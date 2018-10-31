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
public class HabilitacaoException extends Exception {
    public static final String NULL = "Habilitação é obrigatório";
    public static final String TAMANHO = "Habilitação deve conter 11 caracteres.";
    public HabilitacaoException(String message) {
        super(message);
    }

    public HabilitacaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
