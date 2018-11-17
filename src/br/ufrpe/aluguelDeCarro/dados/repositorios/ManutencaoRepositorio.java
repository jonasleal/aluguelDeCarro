package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Manutencao;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ManutencaoRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A classe armazena uma lista de instancias de manutencoes
 *
 * @author Fernando
 */
public class ManutencaoRepositorio implements ManutencaoRepositorioInterface {

    private final ArrayList<Manutencao> manutencoes;

    public ManutencaoRepositorio() {
        this.manutencoes = new ArrayList<>();
    }

    /**
     * busca o manutencao pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Manutencao}
     * @return um clone do {@code Manutencao} ativo que contém o id, {@code null} caso nao encontre
     */
    @Override
    public Manutencao buscarPorId(int id) {
        return this.manutencoes
                .stream()
                .filter(Manutencao::isAtivo)
                .filter(manutencao -> manutencao.getId() == id)
                .findFirst()
                .map(Manutencao::clone)
                .orElse(null);
    }

    /**
     * busca o manutencao pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Manutencao}
     * @return o {@code Manutencao} ativo que contém o id, {@code null} caso nao encontre
     */
    private Manutencao buscarReferenciaPorId(int id) {
        return this.manutencoes
                .stream()
                .filter(Manutencao::isAtivo)
                .filter(manutencao -> manutencao.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * @param manutencao instancia a ser cadastrada
     * @return {@code true} caso cadastre com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean cadastrar(Manutencao manutencao) {
        this.setarId(manutencao);
        return this.manutencoes.add(manutencao.clone());
    }

    /**
     * @param manutencaoEditada instancia a ser editada
     * @return {@code true} caso altere com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean alterar(Manutencao manutencaoEditada) {
        int indexOf = this.manutencoes.indexOf(manutencaoEditada);
        if (indexOf != -1) {
            this.manutencoes.set(indexOf, manutencaoEditada.clone());
            return true;
        }
        return false;
    }

    /**
     * altera o atributo {@code ativo} do manutencao para false
     *
     * @param id identificador do {@code Manutencao}
     * @return {@code true} caso desative com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean desativar(int id) {
        Manutencao manutencao = this.buscarReferenciaPorId(id);
        if (manutencao != null) {
            manutencao.setAtivo(false);
            return true;
        }
        return false;
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Manutencao> buscarTodos() {
        return (ArrayList<Manutencao>) this.manutencoes
                .stream()
                .filter(Manutencao::isAtivo)
                .map(Manutencao::clone)
                .collect(Collectors.toList());
    }

    /**
     * altera o id do manutencao, o id que ele recebe é o maior até então acrescido de 1
     *
     * @param manutencao instancia a ter o id alterado
     */
    private void setarId(Manutencao manutencao) {
        if (this.manutencoes.isEmpty())
            manutencao.setId(1);
        else
            manutencao.setId(this.manutencoes
                    .stream()
                    .mapToInt(Manutencao::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
