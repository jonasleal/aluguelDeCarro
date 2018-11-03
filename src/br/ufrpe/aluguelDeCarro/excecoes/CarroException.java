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
public class CarroException extends Exception{
    public static final String NUMPORTAS = "Número de portas é invalido";
    public static final String NUMOCUPANTES = "Número de ocupantes é invalido";
    public static final String CAMBIOINVALIDO = "Cambio invalido";
    public static final String DIRECAOINVALIDO = "Cambio invalido";
    public static final String CATEGORIAINVALIDO = "Categoria invalido";
    public static final String PRECOINVALIDO = "Preço invalido";
    public CarroException(String message) {
        super(message);
    }

    public CarroException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
