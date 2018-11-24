/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.excecoes.AluguelNaoEncontradoException;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface AluguelRepositorioInterface {

    Aluguel consultar(int id) throws AluguelNaoEncontradoException;

    Aluguel consultar(Cliente cliente) throws AluguelNaoEncontradoException;

    Aluguel consultar(Carro carro) throws AluguelNaoEncontradoException;

    boolean cadastrar(Aluguel aluguel);

    boolean alterar(Aluguel aluguelEditado);

    boolean desativar(int id) throws AluguelNaoEncontradoException;

    ArrayList<Aluguel> consultarTodos();

    boolean existe(int id);

    boolean existe(Cliente cliente);

    boolean existe(Carro carro);
}
