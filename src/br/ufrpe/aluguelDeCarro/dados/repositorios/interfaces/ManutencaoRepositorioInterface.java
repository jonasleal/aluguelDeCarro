package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Manutencao;

import java.util.ArrayList;

public interface ManutencaoRepositorioInterface {

    Manutencao buscarPorId(int id);

    boolean cadastrar(Manutencao manutencao);

    boolean alterar(Manutencao manutencaoEditado);

    boolean deletar(int id);

    ArrayList<Manutencao> buscarTodos();
}
