/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.aluguel;

/**
 * @author JonasJr
 */
public class DataRetiradaObrigatoriaException extends AluguelInvalidoException {

    public DataRetiradaObrigatoriaException() {
        super("Data de retirada é obrigatoria");
    }
}
