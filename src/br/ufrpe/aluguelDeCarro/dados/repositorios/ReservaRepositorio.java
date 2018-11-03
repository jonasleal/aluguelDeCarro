package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Reserva;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ReservaRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class ReservaRepositorio implements ReservaRepositorioInterface {

    private ArrayList<Reserva> reservas;

    public ReservaRepositorio() {
        this.reservas = new ArrayList<>();
    }

    @Override
    public Reserva buscarPorId(int id) {
        return this.reservas
                .stream()
                .filter(Reserva::isAtivo)
                .filter(reserva -> reserva.getId() == id)
                .findFirst()
                .map(Reserva::clone)
                .orElse(null);
    }

    private Reserva buscarReferenciaPorId(int id) {
        return this.reservas
                .stream()
                .filter(Reserva::isAtivo)
                .filter(reserva -> reserva.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean cadastrar(Reserva reserva) {
        this.setarId(reserva);
        return this.reservas.add(reserva.clone());
    }

    @Override
    public boolean alterar(Reserva reservaEditada) {
        int indexOf = this.reservas.indexOf(reservaEditada);
        if (indexOf != -1) {
            this.reservas.set(indexOf, reservaEditada.clone());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        Reserva reserva = this.buscarReferenciaPorId(id);
        if (reserva != null) {
            reserva.setAtivo(false);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Reserva> buscarTodos() {
        return (ArrayList<Reserva>) this.reservas
                .stream()
                .filter(Reserva::isAtivo)
                .collect(Collectors.toList());
    }

    private void setarId(Reserva reserva) {
        if (this.reservas.isEmpty())
            reserva.setId(1);
        else
            reserva.setId(this.reservas
                    .stream()
                    .mapToInt(Reserva::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
