/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface IClienteRepositorio {

    Cliente consultar(int id) throws ClienteNaoEncontradoException;

    Cliente consultar(String cpf) throws ClienteNaoEncontradoException;

    void cadastrar(Cliente cliente);

    void alterar(Cliente clienteEditado);

    void desativar(int id) throws ClienteNaoEncontradoException;

    ArrayList<Cliente> consultarTodos();

    boolean existe(int id);

    boolean existe(String cpf);

}
