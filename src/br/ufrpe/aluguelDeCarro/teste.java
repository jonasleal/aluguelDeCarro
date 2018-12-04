/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro;

import br.ufrpe.aluguelDeCarro.excecoes.Data.DataInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.Data.DataRetiradaFuturoException;
import java.time.LocalDateTime;

/**
 *
 * @author JonasJr
 */
public class teste {
    public static void main(String[] args) {
        DataInvalidaException erro = new DataRetiradaFuturoException(LocalDateTime.now().plusDays(1));
        System.err.println(erro.getMessage());
    }
    
}
