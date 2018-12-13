package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.excecoes.ManutencaoNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Manutencao;

import java.util.ArrayList;

public interface IManutencaoRepositorio {

    Manutencao consultar(int id) throws ManutencaoNaoEncontradaException;

    void cadastrar(Manutencao manutencao);

    void alterar(Manutencao manutencaoEditado);

    void desativar(int id) throws ManutencaoNaoEncontradaException;

    ArrayList<Manutencao> consultarTodos();
}
