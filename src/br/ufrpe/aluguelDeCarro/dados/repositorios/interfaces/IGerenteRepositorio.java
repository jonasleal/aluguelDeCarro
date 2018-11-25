/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.excecoes.GerenteNaoEncontradoException;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface IGerenteRepositorio {

    Gerente consultar(int id) throws GerenteNaoEncontradoException;

    Gerente consultar(String cpf) throws GerenteNaoEncontradoException;

    boolean cadastrar(Gerente gerente);

    boolean alterar(Gerente gerenteEditado);

    boolean desativar(int id) throws GerenteNaoEncontradoException;

    ArrayList<Gerente> consultarTodos();

}
