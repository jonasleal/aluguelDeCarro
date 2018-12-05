package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;

import java.util.List;

public interface ICategoriaRepositorio {
    Categoria consultar(String nome) throws CategoriaNaoEncontradaException;

    Categoria consultar(int id) throws CategoriaNaoEncontradaException;

    boolean cadastrar(Categoria categoria);

    boolean alterar(Categoria categoria);

    boolean desativar(int id) throws CategoriaNaoEncontradaException;

    boolean existe(String nome);

    boolean existe(int id);

    List<Categoria> consultarTodos();
}
