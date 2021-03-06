/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JonasJr
 */
public interface ICarroRepositorio {

    Carro consultar(int id) throws IdNaoEncontradoException;

    Carro consultar(String placa) throws CarroNaoEncontradoException;

    List<Carro> consultar(Categoria categoria);

    List<Carro> consultarAtivos(Categoria categoria);

    void cadastrar(Carro carro);

    void alterar(Carro carroEditado);

    void desativar(int id) throws IdNaoEncontradoException;

    ArrayList<Carro> consultarTodos();

    ArrayList<Carro> consultarTodosAtivos();

    boolean existe(int id);

    boolean existe(String placa);
}
