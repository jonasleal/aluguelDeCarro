/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.categoria;

/**
 *
 * @author JonasJr
 */
public class CategoriaInvalidaException extends Exception{

    public CategoriaInvalidaException() {
        super("Categoria invalida");
    }

    public CategoriaInvalidaException(String message) {
        super(message);
    }

    public CategoriaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
