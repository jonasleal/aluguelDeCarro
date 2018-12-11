/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface IClienteRepositorio {

    Cliente consultar(int id) throws ClienteNaoEncontradoException, IdNaoEncontradoException;

    Cliente consultar(String cpf) throws ClienteNaoEncontradoException;

    boolean cadastrar(Cliente cliente);

    boolean alterar(Cliente clienteEditado);

    boolean desativar(int id) throws ClienteNaoEncontradoException, IdNaoEncontradoException;

    ArrayList<Cliente> consultarTodos();

    boolean existe(int id) throws IdNaoEncontradoException;

    boolean existe(String cpf);

}
