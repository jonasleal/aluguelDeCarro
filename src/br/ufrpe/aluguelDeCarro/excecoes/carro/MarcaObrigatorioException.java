/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.carro;

/**
 *
 * @author JonasJr
 */
public class MarcaObrigatorioException extends CarroInvalidoException{

    public MarcaObrigatorioException() {
        super("Marca é obrigatorio");
    }

    public MarcaObrigatorioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
