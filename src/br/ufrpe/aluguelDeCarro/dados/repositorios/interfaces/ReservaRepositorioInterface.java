package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Reserva;

import java.util.ArrayList;

public interface ReservaRepositorioInterface {

    Reserva consultar(int id);

    boolean cadastrar(Reserva reserva);

    boolean alterar(Reserva reservaEditado);

    boolean desativar(int id);

    ArrayList<Reserva> consultarTodos();
}
