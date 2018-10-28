package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Reserva;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class ReservaRepositorio {

    private ArrayList<Reserva> reservas;

    public ReservaRepositorio() {
        this.reservas = new ArrayList<>();
    }

    private Reserva buscarPorId(int id) {
        return this.reservas
                .stream()
                .filter(reserva -> reserva.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void cadastrar(Reserva reserva) {
        this.setarId(reserva);
        reserva.setAtivo(true);
        this.reservas.add(reserva);
    }

    public void alterar(Reserva reservaEditada) {
        this.reservas
                .stream()
                .filter(reserva -> reserva.equals(reservaEditada))
                .forEach(reserva -> reserva = reservaEditada);
    }

    public void deletar(int id) {
        Reserva reserva = this.buscarPorId(id);
        if (reserva != null)
            reserva.setAtivo(false);
    }

    public ArrayList<Reserva> buscarTodos() {
        return (ArrayList<Reserva>) this.reservas
                .stream()
                .filter(Reserva::isAtivo)
                .collect(Collectors.toList());
    }

    private void setarId(Reserva reserva) {
        if (this.reservas.isEmpty())
            reserva.setId(0);
        else
            reserva.setId(this.reservas
                    .stream()
                    .mapToInt(Reserva::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
