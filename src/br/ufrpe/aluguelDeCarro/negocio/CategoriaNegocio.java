package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICategoriaRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;

import java.util.List;

/**
 * @author Fernando
 */
public class CategoriaNegocio {
    private final ICategoriaRepositorio repositorio;

    public CategoriaNegocio(ICategoriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Categoria categoria) {
        this.repositorio.cadastrar(categoria);
    }

    public void alterar(Categoria categoria) {
        this.repositorio.alterar(categoria);
    }

    public void desativar(int id) throws CategoriaNaoEncontradaException {
        this.repositorio.desativar(id);
    }

    public Categoria consultar(int id) throws CategoriaNaoEncontradaException {
        return this.repositorio.consultar(id);
    }

    public List<Categoria> consultarTodos() {
        return this.repositorio.consultarTodos();
    }
}
