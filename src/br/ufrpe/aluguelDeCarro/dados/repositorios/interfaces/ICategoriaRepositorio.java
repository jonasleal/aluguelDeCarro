package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;

import java.util.List;

public interface ICategoriaRepositorio {
    Categoria consultar(String nome) throws CategoriaNaoEncontradaException;

    Categoria consultar(int id) throws CategoriaNaoEncontradaException;

    void cadastrar(Categoria categoria);

    void alterar(Categoria categoria);

    void desativar(int id) throws CategoriaNaoEncontradaException;

    boolean existe(String nome);

    boolean existe(int id);

    List<Categoria> consultarTodos();
}
