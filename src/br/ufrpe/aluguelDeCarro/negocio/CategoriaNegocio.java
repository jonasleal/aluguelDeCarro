package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICategoriaRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaJaCadastradaException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.NomeCategoriaObrigatorioException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.PrecoNegativoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;

import java.math.BigDecimal;
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
        String nome = categoria.getNome();
        if (nome == null || nome.isEmpty()) throw new NomeCategoriaObrigatorioException();
        if (this.repositorio.existe(nome)) throw new CategoriaJaCadastradaException(nome);
        if (categoria.getDiaria().compareTo(BigDecimal.ZERO) < 1)
            throw new PrecoNegativoException(categoria.getDiaria());
        categoria.setAtivo(true);
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

    public Categoria consultar(String nome) throws CategoriaNaoEncontradaException {
        return this.repositorio.consultar(nome);
    }

    public List<Categoria> consultarTodos() {
        return this.repositorio.consultarTodos();
    }
}
