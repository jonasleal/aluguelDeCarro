package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.CarroRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A classe armazena uma lista de instancias de carros
 * @author Fernando
 */
public class CarroRepositorio implements CarroRepositorioInterface{

    private final ArrayList<Carro> carros;

    public CarroRepositorio() {
        this.carros = new ArrayList<>();
    }

    /**
     * busca o carro pelo id, nos já cadastrados
     * @param id identificador do {@code Carro}
     * @return um clone do {@code Carro} ativo que contém o id, {@code null} caso nao encontre
     */
    @Override
    public Carro buscarPorId(int id) {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getId() == id)
                .findFirst()
                .map(Carro::clone)
                .orElse(null);
    }

    /**
     * busca o carro pelo id, nos já cadastrados
     * @param id identificador do {@code Carro}
     * @return o {@code Carro} ativo que contém o id, {@code null} caso nao encontre
     */
    private Carro buscarReferenciaPorId(int id) {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * busca o carro pela placa, nos já cadastrados
     * @param placa identificador do {@code Carro}
     * @return um clone do {@code Carro} ativo que contém a placa, {@code null} caso nao encontre
     */
    @Override
    public Carro buscarPorPlaca(String placa) {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getPlaca().equals(placa))
                .findFirst()
                .map(Carro::clone)
                .orElse(null);
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
     * @param id identificador do {@code Carro}
     * @return {@code true} caso desative com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean desativar(int id) {
        Carro carro = this.buscarReferenciaPorId(id);
        if (carro != null) {
            carro.setAtivo(false);
            return true;
        }
        return false;
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Carro> buscarTodos() {
        return (ArrayList<Carro>) this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .map(Carro::clone)
                .collect(Collectors.toList());
    }

    /**
     * altera o id do carro, o id que ele recebe é o maior até então acrescido de 1
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
