package br.ufrpe.aluguelDeCarro.dados.repositorios.memoria;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IManutencaoRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.ManutencaoNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Manutencao;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static br.ufrpe.aluguelDeCarro.excecoes.ManutencaoNaoEncontradaException.ID;

/**
 * A classe armazena uma lista de instancias de manutencoes
 *
 * @author Fernando
 */
public class ManutencaoRepositorio implements IManutencaoRepositorio {

    private final ArrayList<Manutencao> manutencoes;

    public ManutencaoRepositorio() {
        this.manutencoes = new ArrayList<>();
    }

    /**
     * busca o manutencao pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Manutencao}
     * @return um clone do {@code Manutencao} ativo que contém o id, {@code null} caso nao encontre
     * @throws ManutencaoNaoEncontradaException
     */
    @Override
    public Manutencao consultar(int id) throws ManutencaoNaoEncontradaException {
        return this.manutencoes
                .stream()
                .filter(Manutencao::isAtivo)
                .filter(manutencao -> manutencao.getId() == id)
                .findFirst()
                .map(Manutencao::clone)
                .orElseThrow(() -> new ManutencaoNaoEncontradaException(ID));
    }

    /**
     * busca o manutencao pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Manutencao}
     * @return o {@code Manutencao} ativo que contém o id, {@code null} caso nao encontre
     */
    private Manutencao consultarReferencia(int id) throws ManutencaoNaoEncontradaException {
        return this.manutencoes
                .stream()
                .filter(Manutencao::isAtivo)
                .filter(manutencao -> manutencao.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ManutencaoNaoEncontradaException(ID));
    }

    /**
     * @param manutencao instancia a ser cadastrada
     */
    @Override
    public void cadastrar(Manutencao manutencao) {
        this.setarId(manutencao);
        this.manutencoes.add(manutencao.clone());
    }

    /**
     * @param manutencaoEditada instancia a ser editada
     */
    @Override
    public void alterar(Manutencao manutencaoEditada) {
        int indexOf = this.manutencoes.indexOf(manutencaoEditada);
        if (indexOf != -1)
            this.manutencoes.set(indexOf, manutencaoEditada.clone());
    }

    /**
     * altera o atributo {@code ativo} do manutencao para false
     *
     * @param id identificador do {@code Manutencao}
     * @throws ManutencaoNaoEncontradaException
     */
    @Override
    public void desativar(int id) throws ManutencaoNaoEncontradaException {
        Manutencao manutencao = this.consultarReferencia(id);
        manutencao.setAtivo(false);
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Manutencao> consultarTodos() {
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
