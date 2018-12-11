/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.Aluguel;

/**
 *
 * @author JonasJr
 */
public class AluguelFinalizadoException extends AluguelInvalidoException {
    
    public AluguelFinalizadoException() {
        super("Este aluguel esta finalizado");
    }
    
}
