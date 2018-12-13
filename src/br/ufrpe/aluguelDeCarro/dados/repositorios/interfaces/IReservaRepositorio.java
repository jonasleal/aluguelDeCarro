package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.excecoes.ReservaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Reserva;

import java.util.ArrayList;

public interface IReservaRepositorio {

    Reserva consultar(int id) throws ReservaNaoEncontradaException;

    void cadastrar(Reserva reserva);

    void alterar(Reserva reservaEditado);

    void desativar(int id) throws ReservaNaoEncontradaException;

    ArrayList<Reserva> consultarTodos();
}
