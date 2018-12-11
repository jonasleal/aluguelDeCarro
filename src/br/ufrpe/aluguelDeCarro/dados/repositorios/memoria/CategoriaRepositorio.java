package br.ufrpe.aluguelDeCarro.dados.repositorios.memoria;

import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICategoriaRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Fernando
 */
public class CategoriaRepositorio implements ICategoriaRepositorio {

    private final List<Categoria> categorias;

    public CategoriaRepositorio() {
        this.categorias = new ArrayList<>();
    }

    @Override
    public Categoria consultar(String nome) throws CategoriaNaoEncontradaException {
        return this.categorias
                .stream()
                .filter(Categoria::isAtivo)
                .filter(categoria -> categoria.getNome().equals(nome))
                .findFirst()
                .map(Categoria::clone)
                .orElseThrow(CategoriaNaoEncontradaException::new);
    }

    /**
     * busca o categoria pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Categoria}
     * @return um clone do {@code Categoria} ativo que contém o id, {@code null} caso nao encontre
     */
    @Override
    public Categoria consultar(int id) throws CategoriaNaoEncontradaException {
        return this.categorias
                .stream()
                .filter(Categoria::isAtivo)
                .filter(categoria -> categoria.getId() == id)
                .findFirst()
                .map(Categoria::clone)
                .orElseThrow(CategoriaNaoEncontradaException::new);
    }

    /**
     * busca o categoria pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Categoria}
     * @return o {@code Categoria} ativo que contém o id, {@code null} caso nao encontre
     */
    private Categoria consultarReferencia(int id) throws CategoriaNaoEncontradaException {
        return this.categorias
                .stream()
                .filter(Categoria::isAtivo)
                .filter(categoria -> categoria.getId() == id)
                .findFirst()
                .orElseThrow(CategoriaNaoEncontradaException::new);
    }

    /**
     * @param categoria instancia a ser cadastrada
     * @return {@code true} caso cadastre com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean cadastrar(Categoria categoria) {
        this.setarId(categoria);
        return this.categorias.add(categoria.clone());
    }

    /**
     * @param categoriaEditada instancia a ser editada
     * @return {@code true} caso altere com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean alterar(Categoria categoriaEditada) {
        int indexOf = this.categorias.indexOf(categoriaEditada);
        if (indexOf != -1) {
            this.categorias.set(indexOf, categoriaEditada.clone());
            return true;
        }
        return false;
    }

    /**
     * altera o atributo {@code ativo} do categoria para false
     *
     * @param id identificador do {@code Categoria}
     * @return {@code true} caso desative com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean desativar(int id) throws CategoriaNaoEncontradaException {
        Categoria categoria = this.consultarReferencia(id);
        categoria.setAtivo(false);
        return true;
    }

    @Override
    public boolean existe(String nome) {
        try {
            consultar(nome);
            return true;
        } catch (CategoriaNaoEncontradaException e) {
            return false;
        }
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Categoria> consultarTodos() {
        return (ArrayList<Categoria>) this.categorias
                .stream()
                .filter(Categoria::isAtivo)
                .map(Categoria::clone)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existe(int id) {
        try {
            this.consultar(id);
            return true;
        } catch (CategoriaNaoEncontradaException e) {
            return false;
        }
    }

    /**
     * altera o id do categoria, o id que ele recebe é o maior até então acrescido de 1
     *
     * @param categoria instancia a ter o id alterado
     */
    private void setarId(Categoria categoria) {
        if (this.categorias.isEmpty())
            categoria.setId(1);
        else
            categoria.setId(this.categorias
                    .stream()
                    .mapToInt(Categoria::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
