package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICategoriaRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.*;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;

import java.util.List;

/**
 * @author Fernando
 */
public class CategoriaNegocio {
    private final ICategoriaRepositorio repositorio;

    public CategoriaNegocio(ICategoriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Categoria categoria) throws CategoriaInvalidaException {
        validarParaCadastrar(categoria);
        categoria.setAtivo(true);
        this.repositorio.cadastrar(categoria);
    }

    private void validarParaCadastrar(Categoria categoria) throws NomeCategoriaObrigatorioException, CategoriaJaCadastradaException, PrecoNegativoException, CategoriaObrigatorioException {
        if (categoria == null) throw new CategoriaObrigatorioException();
        categoria.validar();
        String nome = categoria.getNome();
        if (this.repositorio.existe(nome)) throw new CategoriaJaCadastradaException(nome);
    }

    public void alterar(Categoria categoria) throws CategoriaInvalidaException {
        categoria.validar();
        this.repositorio.alterar(categoria);
    }

    public void desativar(int id) throws CategoriaNaoEncontradaException {
        this.repositorio.desativar(id);
    }

    public Categoria consultar(int id) throws CategoriaNaoEncontradaException {
        return this.repositorio.consultar(id);
    }

    public Categoria consultar(String nome) throws CategoriaNaoEncontradaException {
        return this.repositorio.consultar(nome);
    }

    public List<Categoria> consultarTodos() {
        return this.repositorio.consultarTodos();
    }
}
