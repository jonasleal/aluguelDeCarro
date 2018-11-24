/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface GerenteRepositorioInterface {

    Gerente consultar(int id);

    Gerente consultar(String cpf);

    boolean cadastrar(Gerente gerente);

    boolean alterar(Gerente gerenteEditado);

    boolean desativar(int id);

    ArrayList<Gerente> consultarTodos();

}
