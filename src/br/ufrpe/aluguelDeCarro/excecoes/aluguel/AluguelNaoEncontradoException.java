/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.aluguel;

import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.servicos.CpfUtil;

/**
 * @author JonasJr
 */
public class AluguelNaoEncontradoException extends AluguelInvalidoException {

    private Cliente cliente;
    private Carro carro;

    public AluguelNaoEncontradoException() {
        super("aluguel não encontrado");
    }

    public AluguelNaoEncontradoException(Throwable cause) {

    }

    public AluguelNaoEncontradoException(Cliente cliente) {
        this();
        this.cliente = cliente;
    }

    public AluguelNaoEncontradoException(Carro carro) {
        this();
        this.carro = carro;
    }

    @Override
    public String getMessage() {
        String saida = super.getMessage();
        if (cliente != null) {
            saida += ": Cpf " + CpfUtil.cpfToString(cliente.getCpf());
        } else if (carro != null) {
            saida += ": Placa " + carro.getPlaca();
        }
        return saida;
    }

}
