/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface ClienteRepositorioInterface {

    Cliente buscarPorId(int id);

    Cliente buscarPorCpf(String cpf);

    boolean cadastrar(Cliente cliente);

    boolean alterar(Cliente clienteEditado);

    boolean desativar(int id);

    ArrayList<Cliente> buscarTodos();

}
