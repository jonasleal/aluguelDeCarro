/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import java.util.ArrayList;

/**
 *
 * @author JonasJr
 */
public interface GerenteRepositorioInterface {

    boolean cadastrar(Gerente gerente);

    boolean alterar(Gerente gerenteEditado);

    Gerente buscarPorId(int id);

    Gerente buscarPorCpf(String cpf);

    boolean deletar(int id);

    ArrayList<Gerente> buscarTodos();

}
