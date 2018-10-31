package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Manutencao;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class ManutencaoRepositorio {

    private ArrayList<Manutencao> manutencoes;

    public ManutencaoRepositorio() {
        this.manutencoes = new ArrayList<>();
    }

    private Manutencao buscarPorId(int id) {
        return this.manutencoes
                .stream()
                .filter(manutencao -> manutencao.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void cadastrar(Manutencao manutencao) {
        this.setarId(manutencao);
        manutencao.setAtivo(true);
        this.manutencoes.add(manutencao);
    }

    public void alterar(Manutencao manutencaoEditada) {
        this.manutencoes
                .stream()
                .filter(manutencao -> manutencao.equals(manutencaoEditada))
                .forEach(manutencao -> manutencao = manutencaoEditada);
    }

    public void deletar(int id) {
        Manutencao manutencao = this.buscarPorId(id);
        if (manutencao != null)
            manutencao.setAtivo(false);
    }

    public ArrayList<Manutencao> buscarTodos() {
        return (ArrayList<Manutencao>) this.manutencoes
                .stream()
                .filter(Manutencao::isAtivo)
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
