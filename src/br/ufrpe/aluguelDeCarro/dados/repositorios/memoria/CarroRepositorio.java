package br.ufrpe.aluguelDeCarro.dados.repositorios.memoria;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICarroRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A classe armazena uma lista de instancias de carros
 *
 * @author Fernando
 */
public class CarroRepositorio implements ICarroRepositorio {

    private final ArrayList<Carro> carros;

    public CarroRepositorio() {
        this.carros = new ArrayList<>();
    }

    /**
     * busca o carro pelo id, nos já cadastrados
     *
     * @param id identificador do {@code carro}
     * @return um clone do {@code carro} ativo que contém o id, {@code null} caso nao encontre
     */
    @Override
    public Carro consultar(int id) throws IdNaoEncontradoException {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getId() == id)
                .findFirst()
                .map(Carro::clone)
                .orElseThrow(() -> new IdNaoEncontradoException(id));
    }

    /**
     * busca o carro pelo id, nos já cadastrados
     *
     * @param id identificador do {@code carro}
     * @return o {@code carro} ativo que contém o id, {@code null} caso nao encontre
     */
    private Carro consultarReferencia(int id) throws IdNaoEncontradoException {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IdNaoEncontradoException(id));
    }

    /**
     * busca o carro pela placa, nos já cadastrados
     *
     * @param placa identificador do {@code carro}
     * @return um clone do {@code carro} ativo que contém a placa, {@code null} caso nao encontre
     */
    @Override
    public Carro consultar(String placa) throws CarroNaoEncontradoException {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getPlaca().equals(placa))
                .findFirst()
                .map(Carro::clone)
                .orElseThrow(() -> new CarroNaoEncontradoException(placa));
    }

    @Override
    public List<Carro> consultar(Categoria categoria) {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getCategoria().equals(categoria))
                .map(Carro::clone)
                .collect(Collectors.toList());
    }

    @Override
    public List<Carro> consultarAtivos(Categoria categoria) {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(carro -> carro.getCategoria().equals(categoria))
                .map(Carro::clone)
                .collect(Collectors.toList());
    }

    /**
     * @param carro instancia a ser cadastrada
     */
    @Override
    public void cadastrar(Carro carro) {
        this.setarId(carro);
        this.carros.add(carro.clone());
    }

    /**
     * @param carroEditado instancia a ser editada
     */
    @Override
    public void alterar(Carro carroEditado) {
        int indexOf = this.carros.indexOf(carroEditado);
        if (indexOf != -1)
            this.carros.set(indexOf, carroEditado.clone());
    }

    /**
     * altera o atributo {@code ativo} do carro para false
     *
     * @param id identificador do {@code carro}
     */
    @Override
    public void desativar(int id) throws IdNaoEncontradoException {
        Carro carro = this.consultarReferencia(id);
        carro.setAtivo(false);
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Carro> consultarTodos() {
        return (ArrayList<Carro>) this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .map(Carro::clone)
                .collect(Collectors.toList());
    }

    @Override
    public ArrayList<Carro> consultarTodosAtivos() {
        return (ArrayList<Carro>) this.carros
                .stream()
                .filter(Carro::isAtivo)
                .map(Carro::clone)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existe(int id) {
        try {
            this.consultar(id);
            return true;
        } catch (IdNaoEncontradoException e) {
            return false;
        }
    }

    @Override
    public boolean existe(String placa) {
        try {
            this.consultar(placa);
            return true;
        } catch (CarroNaoEncontradoException e) {
            return false;
        }
    }

    /**
     * altera o id do carro, o id que ele recebe é o maior até então acrescido de 1
     *
     * @param carro instancia a ter o id alterado
     */
    private void setarId(Carro carro) {
        if (this.carros.isEmpty())
            carro.setId(1);
        else
            carro.setId(this.carros
                    .stream()
                    .mapToInt(Carro::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
