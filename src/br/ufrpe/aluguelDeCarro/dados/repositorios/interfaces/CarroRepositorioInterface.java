/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.excecoes.CarroNaoEncontradoException;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface CarroRepositorioInterface {

    Carro consultar(int id) throws CarroNaoEncontradoException;

    Carro consultar(String placa) throws CarroNaoEncontradoException;

    boolean cadastrar(Carro carro);

    boolean alterar(Carro carroEditado);

    boolean desativar(int id) throws CarroNaoEncontradoException;

    ArrayList<Carro> consultarTodos();
}
