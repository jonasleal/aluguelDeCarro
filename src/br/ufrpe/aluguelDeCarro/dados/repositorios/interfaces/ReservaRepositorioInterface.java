package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Reserva;

import java.util.ArrayList;

public interface ReservaRepositorioInterface {

    Reserva buscarPorId(int id);

    boolean cadastrar(Reserva reserva);

    boolean alterar(Reserva reservaEditado);

    boolean deletar(int id);

    ArrayList<Reserva> buscarTodos();
}
