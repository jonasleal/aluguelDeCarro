/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.Carro;

/**
 *
 * @author JonasJr
 */
public class ModeloObrigatorioException extends CarroInvalidoException{

    public ModeloObrigatorioException() {
        super("Modelo é obrigatorio");
    }

    public ModeloObrigatorioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
