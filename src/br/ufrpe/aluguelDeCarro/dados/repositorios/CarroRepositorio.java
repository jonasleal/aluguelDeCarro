package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.CarroRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.CarroNaoEncontradoException;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static br.ufrpe.aluguelDeCarro.excecoes.CarroNaoEncontradoException.ID;
import static br.ufrpe.aluguelDeCarro.excecoes.CarroNaoEncontradoException.PLACA;

/**
 * A classe armazena uma lista de instancias de carros
 *
 * @author Fernando
 */
public class CarroRepositorio implements CarroRepositorioInterface {

    private final ArrayList<Carro> carros;

    public CarroRepositorio() {
        this.carros = new ArrayList<>();
    }

    /**
     * busca o carro pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Carro}
     * @return um clone do {@code Carro} ativo que contém o id, {@code null} caso nao encontre
     */
    @Override
    public Carro consultar(int id) throws CarroNaoEncontradoException {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getId() == id)
                .findFirst()
                .map(Carro::clone)
                .orElseThrow(() -> new CarroNaoEncontradoException(ID));
    }

    /**
     * busca o carro pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Carro}
     * @return o {@code Carro} ativo que contém o id, {@code null} caso nao encontre
     */
    private Carro consultarReferencia(int id) throws CarroNaoEncontradoException {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CarroNaoEncontradoException(ID));
    }

    /**
     * busca o carro pela placa, nos já cadastrados
     *
     * @param placa identificador do {@code Carro}
     * @return um clone do {@code Carro} ativo que contém a placa, {@code null} caso nao encontre
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
                .orElseThrow(() -> new CarroNaoEncontradoException(PLACA));
    }

    /**
     * @param carro instancia a ser cadastrada
     * @return {@code true} caso cadastre com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean cadastrar(Carro carro) {
        this.setarId(carro);
        return this.carros.add(carro.clone());
    }

    /**
     * @param carroEditado instancia a ser editada
     * @return {@code true} caso altere com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean alterar(Carro carroEditado) {
        int indexOf = this.carros.indexOf(carroEditado);
        if (indexOf != -1) {
            this.carros.set(indexOf, carroEditado.clone());
            return true;
        }
        return false;
    }

    /**
     * altera o atributo {@code ativo} do carro para false
     *
     * @param id identificador do {@code Carro}
     * @return {@code true} caso desative com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean desativar(int id) throws CarroNaoEncontradoException {
        Carro carro = this.consultarReferencia(id);
        carro.setAtivo(false);
        return true;
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
    public boolean existe(int id) {
        try {
            this.consultar(id);
            return true;
        } catch (CarroNaoEncontradoException e) {
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
