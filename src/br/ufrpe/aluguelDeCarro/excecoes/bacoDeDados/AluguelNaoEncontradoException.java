/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.bacoDeDados;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.excecoes.Aluguel.AluguelInvalidoException;

/**
 *
 * @author JonasJr
 */
public class AluguelNaoEncontradoException extends AluguelInvalidoException {

    private Cliente cliente;
    private int id;
    private String placa;
    
    public AluguelNaoEncontradoException() {
        super("Aluguel não encontrado");
    }

    public AluguelNaoEncontradoException(Throwable cause) {
        
    }
    

    public AluguelNaoEncontradoException(Cliente cliente) {
        this();
        this.cliente = cliente;
    }

    public AluguelNaoEncontradoException(int id) {
        this();
        this.id = id;
    }

}
