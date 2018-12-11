package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.negocio.entidades.Reserva;
import br.ufrpe.aluguelDeCarro.excecoes.ReservaNaoEncontradaException;

import java.util.ArrayList;

public interface IReservaRepositorio {

    Reserva consultar(int id) throws ReservaNaoEncontradaException;

    boolean cadastrar(Reserva reserva);

    boolean alterar(Reserva reservaEditado);

    boolean desativar(int id) throws ReservaNaoEncontradaException;

    ArrayList<Reserva> consultarTodos();
}
