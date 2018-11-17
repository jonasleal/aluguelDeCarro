/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface CarroRepositorioInterface {

    Carro buscarPorId(int id);

    Carro buscarPorPlaca(String placa);

    boolean cadastrar(Carro carro);

    boolean alterar(Carro carroEditado);

    boolean desativar(int id);

    ArrayList<Carro> buscarTodos();
}
