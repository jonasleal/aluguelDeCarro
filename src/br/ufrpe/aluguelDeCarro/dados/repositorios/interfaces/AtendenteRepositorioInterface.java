package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Atendente;

import java.util.ArrayList;

public interface AtendenteRepositorioInterface {

    Atendente buscarPorId(int id);

    boolean cadastrar(Atendente atendente);

    boolean alterar(Atendente atendenteEditado);

    boolean deletar(int id);

    ArrayList<Atendente> buscarTodos();
}
