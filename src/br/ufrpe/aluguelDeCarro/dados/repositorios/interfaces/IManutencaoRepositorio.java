package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.negocio.entidades.Manutencao;
import br.ufrpe.aluguelDeCarro.excecoes.ManutencaoNaoEncontradaException;

import java.util.ArrayList;

public interface IManutencaoRepositorio {

    Manutencao consultar(int id) throws ManutencaoNaoEncontradaException;

    boolean cadastrar(Manutencao manutencao);

    boolean alterar(Manutencao manutencaoEditado);

    boolean desativar(int id) throws ManutencaoNaoEncontradaException;

    ArrayList<Manutencao> consultarTodos();
}
