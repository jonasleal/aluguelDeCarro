/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface AluguelRepositorioInterface {

    Aluguel buscarPorId(int id);

    Aluguel buscarPorCpf(String cpf);

    Aluguel buscarPorPlaca(String placa);

    boolean cadastrar(Aluguel aluguel);

    boolean alterar(Aluguel aluguelEditado);

    boolean desativar(int id);

    ArrayList<Aluguel> buscarTodos();
}
