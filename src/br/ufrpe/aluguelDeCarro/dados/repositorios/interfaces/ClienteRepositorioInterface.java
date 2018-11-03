/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import java.util.ArrayList;

/**
 *
 * @author JonasJr
 */
public interface ClienteRepositorioInterface {

    public Cliente buscarPorId(int id);
    public Cliente buscarPorCpf(String cpf);

    public boolean cadastrar(Cliente cliente);

    public boolean alterar(Cliente clienteEditado);

    public boolean deletar(int id);

    public ArrayList<Cliente> buscarTodos();

}
