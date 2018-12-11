package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.negocio.entidades.Reserva;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IReservaRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.ReservaNaoEncontradaException;

import java.util.List;

/**
 * @author Fernando
 */
public class ReservaNegocio {
    private final IReservaRepositorio repositorio;

    public ReservaNegocio(IReservaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Reserva reserva){
        reserva.setAtivo(true);
        this.repositorio.cadastrar(reserva);
    }

    public void alterar(Reserva reserva){
        this.repositorio.alterar(reserva);
    }

    public void desativar(int id) throws ReservaNaoEncontradaException {
        this.repositorio.desativar(id);
    }

    public Reserva consultar(int id) throws ReservaNaoEncontradaException {
        return this.repositorio.consultar(id);
    }

    public List<Reserva> consultarTodos(){
        return this.repositorio.consultarTodos();
    }
}
