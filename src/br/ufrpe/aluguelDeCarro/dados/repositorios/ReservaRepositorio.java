package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Reserva;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ReservaRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.ReservaNaoEncontradaException;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static br.ufrpe.aluguelDeCarro.excecoes.ReservaNaoEncontradaException.ID;

/**
 * A classe armazena uma lista de instancias de reservas
 *
 * @author Fernando
 */
public class ReservaRepositorio implements ReservaRepositorioInterface {

    private final ArrayList<Reserva> reservas;

    public ReservaRepositorio() {
        this.reservas = new ArrayList<>();
    }

    /**
     * busca o reserva pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Reserva}
     * @return um clone do {@code Reserva} ativo que contém o id, {@code null} caso nao encontre
     */
    @Override
    public Reserva consultar(int id) throws ReservaNaoEncontradaException {
        return this.reservas
                .stream()
                .filter(Reserva::isAtivo)
                .filter(reserva -> reserva.getId() == id)
                .findFirst()
                .map(Reserva::clone)
                .orElseThrow(() -> new ReservaNaoEncontradaException(ID));
    }

    /**
     * busca o reserva pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Reserva}
     * @return o {@code Reserva} ativo que contém o id, {@code null} caso nao encontre
     */
    private Reserva consultarReferencia(int id) throws ReservaNaoEncontradaException {
        return this.reservas
                .stream()
                .filter(Reserva::isAtivo)
                .filter(reserva -> reserva.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ReservaNaoEncontradaException(ID));
    }

    /**
     * @param reserva instancia a ser cadastrada
     * @return {@code true} caso cadastre com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean cadastrar(Reserva reserva) {
        this.setarId(reserva);
        return this.reservas.add(reserva.clone());
    }

    /**
     * @param reservaEditada instancia a ser editada
     * @return {@code true} caso altere com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean alterar(Reserva reservaEditada) {
        int indexOf = this.reservas.indexOf(reservaEditada);
        if (indexOf != -1) {
            this.reservas.set(indexOf, reservaEditada.clone());
            return true;
        }
        return false;
    }

    /**
     * altera o atributo {@code ativo} do reserva para false
     *
     * @param id identificador do {@code Reserva}
     * @return {@code true} caso desative com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean desativar(int id) throws ReservaNaoEncontradaException {
        Reserva reserva = this.consultarReferencia(id);
        reserva.setAtivo(false);
        return true;
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Reserva> consultarTodos() {
        return (ArrayList<Reserva>) this.reservas
                .stream()
                .filter(Reserva::isAtivo)
                .collect(Collectors.toList());
    }

    /**
     * altera o id do reserva, o id que ele recebe é o maior até então acrescido de 1
     *
     * @param reserva instancia a ter o id alterado
     */
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
