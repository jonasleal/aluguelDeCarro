/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.excecoes.aluguel.AluguelNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface IAluguelRepositorio {

    Aluguel consultar(int id) throws AluguelNaoEncontradoException, IdNaoEncontradoException;

    Aluguel consultar(Cliente cliente) throws AluguelNaoEncontradoException;

    Aluguel consultar(Carro carro) throws AluguelNaoEncontradoException;

    boolean cadastrar(Aluguel aluguel);

    boolean alterar(Aluguel aluguelEditado);

    boolean desativar(int id) throws AluguelNaoEncontradoException, IdNaoEncontradoException;

    ArrayList<Aluguel> consultarTodos();

    boolean existe(int id) throws IdNaoEncontradoException;

    boolean existe(Cliente cliente);

    boolean existe(Carro carro);
}
