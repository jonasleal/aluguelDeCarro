/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes;

/**
 * @author JonasJr
 */
public class CarroException extends Exception {
    public static final String NUMERO_PORTAS = "Número de portas é invalido";
    public static final String NUMERO_OCUPANTES = "Número de ocupantes é invalido";
    public static final String CAMBIO_INVALIDO = "Cambio invalido";
    public static final String DIRECAO_INVALIDA = "Cambio invalido";
    public static final String CATEGORIA_INVALIDA = "Categoria invalido";
    public static final String PRECO_INVALIDO = "Preço invalido";

    public CarroException(String message) {
        super(message);
    }

    public CarroException(String message, Throwable cause) {
        super(message, cause);
    }

}
