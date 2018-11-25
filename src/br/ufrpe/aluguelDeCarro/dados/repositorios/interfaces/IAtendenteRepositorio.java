package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Atendente;
import br.ufrpe.aluguelDeCarro.excecoes.AtendenteNaoEncontradoException;

import java.util.ArrayList;

public interface IAtendenteRepositorio {

    Atendente consultar(int id) throws AtendenteNaoEncontradoException;

    boolean cadastrar(Atendente atendente);

    boolean alterar(Atendente atendenteEditado);

    boolean desativar(int id) throws AtendenteNaoEncontradoException;

    ArrayList<Atendente> consultarTodos();
}
