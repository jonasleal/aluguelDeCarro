package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Manutencao;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ManutencaoRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class ManutencaoRepositorio implements ManutencaoRepositorioInterface {

    private ArrayList<Manutencao> manutencoes;

    public ManutencaoRepositorio() {
        this.manutencoes = new ArrayList<>();
    }

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

    private Manutencao buscarReferenciaPorId(int id) {
        return this.manutencoes
                .stream()
                .filter(Manutencao::isAtivo)
                .filter(manutencao -> manutencao.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean cadastrar(Manutencao manutencao) {
        this.setarId(manutencao);
        return this.manutencoes.add(manutencao.clone());
    }

    @Override
    public boolean alterar(Manutencao manutencaoEditada) {
        int indexOf = this.manutencoes.indexOf(manutencaoEditada);
        if (indexOf != -1) {
            this.manutencoes.set(indexOf, manutencaoEditada.clone());
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        Manutencao manutencao = this.buscarReferenciaPorId(id);
        if (manutencao != null) {
            manutencao.setAtivo(false);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Manutencao> buscarTodos() {
        return (ArrayList<Manutencao>) this.manutencoes
                .stream()
                .filter(Manutencao::isAtivo)
                .map(Manutencao::clone)
                .collect(Collectors.toList());
    }

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
