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
public class PlacaObrigatorioException extends CarroInvalidoException{

    public PlacaObrigatorioException() {
        super("Placa é obrigatoria");
    }
    
    
    
    
}
