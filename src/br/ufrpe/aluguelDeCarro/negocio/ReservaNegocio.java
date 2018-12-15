package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IAluguelRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICarroRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICategoriaRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IReservaRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.reserva.DataDevolucaoPassadoException;
import br.ufrpe.aluguelDeCarro.excecoes.reserva.DataRetiradaPassadoException;
import br.ufrpe.aluguelDeCarro.excecoes.reserva.ReservaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.reserva.ReservaObrigatoriaException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Reserva;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Fernando
 */
public class ReservaNegocio {
    private final IReservaRepositorio reservaRepositorio;
    private final IAluguelRepositorio aluguelRepositorio;
    private final ICarroRepositorio carroRepositorio;
    private final ICategoriaRepositorio categoriaRepositorio;

    private List<Aluguel> alugueis;

    public ReservaNegocio(IReservaRepositorio reservaRepositorio, IAluguelRepositorio aluguelRepositorio, ICarroRepositorio carroRepositorio, ICategoriaRepositorio categoriaRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
        this.aluguelRepositorio = aluguelRepositorio;
        this.carroRepositorio = carroRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;
    }

    public void cadastrar(Reserva reserva) throws ReservaInvalidaException {
        if (reserva == null) throw new ReservaObrigatoriaException();
        reserva.validar();
        if (reserva.getRetiradaPrevista().isBefore(LocalDateTime.now()))
            throw new DataRetiradaPassadoException(reserva.getRetiradaPrevista());
        if (reserva.getDevolucaoPrevista().isBefore(LocalDateTime.now()))
            throw new DataDevolucaoPassadoException(reserva.getDevolucaoPrevista());
        reserva.setAtivo(true);
        this.reservaRepositorio.cadastrar(reserva);
    }

    public void alterar(Reserva reserva) throws ReservaInvalidaException {
        if (reserva == null) throw new ReservaObrigatoriaException();
        if (reserva.getDevolucaoPrevista().isBefore(LocalDateTime.now()))
            throw new DataDevolucaoPassadoException(reserva.getDevolucaoPrevista());
        this.reservaRepositorio.alterar(reserva);
    }

    public void desativar(int id) throws ReservaInvalidaException {
        this.reservaRepositorio.desativar(id);
    }

    public Reserva consultar(int id) throws ReservaInvalidaException {
        return this.reservaRepositorio.consultar(id);
    }

    public List<Reserva> consultarTodos() {
        return this.reservaRepositorio.consultarTodos();
    }

    public List<Categoria> consultarCategoriasDisponiveisParaReserva(Reserva reserva) {
        ArrayList<Reserva> reservas = this.reservaRepositorio.consultarTodos();
        ArrayList<Aluguel> alugueis = this.aluguelRepositorio.consultarTodos();
        Set<Categoria> categorias = new HashSet<>(this.categoriaRepositorio.consultarTodos());
        List<Categoria> categoriasPorAlugueis = categoriasDisponiveisPorAlugueis(reserva);
        List<Categoria> categoriasPorReservas = categoriasDisponiveisPorReservas(reserva);
        categorias = categorias.stream()
                .filter(categoria -> categoriasPorAlugueis.contains(categoria) && categoriasPorReservas.contains(categoria))
                .collect(Collectors.toSet());
        List<Categoria> categoriasNaoContidasNasReservas = categoriasNaoContidasNasReservas(reservas);
        categoriasNaoContidasNasReservas = categoriasNaoContidasNasReservas.stream()
                .filter(categoriasPorAlugueis::contains)
                .collect(Collectors.toList());
        categorias.addAll(categoriasNaoContidasNasReservas);
        List<Categoria> categoriasNaoContidasNosAlugueis = categoriasNaoContidasNosAlugueis(alugueis);
        categoriasNaoContidasNosAlugueis = categoriasNaoContidasNosAlugueis.stream()
                .filter(categoria -> !categoriasPorReservas.contains(categoria))
                .collect(Collectors.toList());
        categorias.addAll(categoriasNaoContidasNosAlugueis);
        return new ArrayList<>(categorias);
    }

    private List<Categoria> categoriasDisponiveisPorReservas(Reserva reserva) {
        List<Reserva> reservas = this.reservaRepositorio.consultarTodos();
        List<Reserva> reservasComConflito = reservasComConflitoComAluguel(reservas, reserva);
        Set<Categoria> collect = categoriasDasReservasComConflito(reservasComConflito);
        List<Reserva> reservasSemConflito = reservasSemConflitoComAluguel(reservas, reserva);
        collect.addAll(categoriasDasReservasSemConflito(reservasSemConflito));
        return new ArrayList<>(collect);
    }

    private List<Categoria> categoriasNaoContidasNasReservas(List<Reserva> reservas) {
        List<Categoria> categorias = this.categoriaRepositorio.consultarTodos();
        categorias.removeAll(reservas.stream().map(Reserva::getCategoria).collect(Collectors.toSet()));
        return categorias;
    }

    private List<Categoria> categoriasDasReservasSemConflito(List<Reserva> reservasSemConflito) {
        return reservasSemConflito
                .stream()
                .filter(reserva -> quantidadeDeCarrosPor(reserva.getCategoria()) > 0)
                .map(Reserva::getCategoria)
                .collect(Collectors.toList());
    }

    private Set<Categoria> categoriasDasReservasComConflito(List<Reserva> reservasComConflito) {
        return reservasComConflito
                .stream()
                .filter(reserva -> quantidadeDeCarrosPor(reserva.getCategoria()) > quantidadeDeReservasPor(reservasComConflito, reserva.getCategoria()))
                .map(Reserva::getCategoria)
                .collect(Collectors.toSet());
    }

    private List<Reserva> reservasComConflitoComAluguel(List<Reserva> reservas, Reserva reserva) {
        return reservas
                .stream()
                .filter(reserva1 -> conflitoAluguelReserva(
                        reserva.getRetiradaPrevista(), reserva.getDevolucaoPrevista(), reserva1.getRetiradaPrevista(), reserva1.getDevolucaoPrevista()))
                .collect(Collectors.toList());
    }

    private List<Reserva> reservasSemConflitoComAluguel(List<Reserva> reservas, Reserva reserva) {
        return reservas
                .stream()
                .filter(reserva1 -> !conflitoAluguelReserva(
                        reserva.getRetiradaPrevista(), reserva.getDevolucaoPrevista(), reserva1.getRetiradaPrevista(), reserva1.getDevolucaoPrevista()))
                .collect(Collectors.toList());
    }

    private long quantidadeDeReservasPor(List<Reserva> reservas, Categoria categoria) {
        return reservas.stream().filter(reserva -> reserva.getCategoria().equals(categoria)).count();
    }

    private List<Categoria> categoriasDisponiveisPorAlugueis(Reserva reserva) {
        List<Aluguel> alugueis = this.aluguelRepositorio.consultarTodos();
        List<Aluguel> alugueisComConflito = this.alugueisComConflitoComReserva(alugueis, reserva);
        Set<Categoria> collect = categoriasDosAlugueisComConflito(alugueisComConflito);
        List<Aluguel> alugueisSemConflito = alugueisSemConflitoComAluguel(alugueis, reserva);
        collect.addAll(categoriasDosAlugueisSemConflito(alugueisSemConflito));
        return new ArrayList<>(collect);
    }

    private List<Categoria> categoriasNaoContidasNosAlugueis(List<Aluguel> alugueis) {
        List<Categoria> categorias = this.categoriaRepositorio.consultarTodos();
        categorias.removeAll(alugueis.stream().map(Aluguel::getCategoria).collect(Collectors.toSet()));
        return categorias;
    }

    private Set<Categoria> categoriasDosAlugueisSemConflito(List<Aluguel> alugueisSemConflito) {
        return alugueisSemConflito.stream()
                .filter(aluguel -> quantidadeDeCarrosPor(aluguel.getCategoria()) > 0)
                .map(Aluguel::getCategoria)
                .collect(Collectors.toSet());
    }

    private List<Aluguel> alugueisSemConflitoComAluguel(List<Aluguel> alugueis, Reserva reserva) {
        return alugueis.stream()
                .filter(aluguel -> conflitoAluguelReserva(
                        aluguel.getRetirada(), aluguel.getDevolucaoEstimada(), reserva.getRetiradaPrevista(), reserva.getDevolucaoPrevista()
                ))
                .collect(Collectors.toList());
    }

    private Set<Categoria> categoriasDosAlugueisComConflito(List<Aluguel> alugueisComConflito) {
        return alugueisComConflito.stream()
                .filter(aluguel -> quantidadeDeCarrosPor(aluguel.getCategoria()) > quantidadeDeAlugueisPorCategoria(alugueisComConflito, aluguel.getCategoria()))
                .map(Aluguel::getCategoria)
                .collect(Collectors.toSet());
    }

    private List<Aluguel> alugueisComConflitoComReserva(List<Aluguel> alugueis, Reserva reserva) {
        return alugueis.stream()
                .filter(aluguel -> conflitoAluguelReserva(
                        aluguel.getRetirada(), aluguel.getDevolucaoEstimada(), reserva.getRetiradaPrevista(), reserva.getDevolucaoPrevista()
                ))
                .collect(Collectors.toList());
    }

    private boolean conflitoAluguelReserva(LocalDateTime aluguelRetirada, LocalDateTime aluguelDevolucao, LocalDateTime reservaRetirada, LocalDateTime reservaDevolucao) {
        return estaNoIntervalo(reservaRetirada, aluguelRetirada, aluguelDevolucao) ||
                estaNoIntervalo(reservaDevolucao, aluguelRetirada, aluguelDevolucao) ||
                estaNoIntervalo(aluguelRetirada, reservaRetirada, reservaDevolucao);
    }

    /**
     * Verifica se o parametro time está no intervalo (incluso) end e time
     *
     * @param start data inicial do intervalo
     * @param end   data final do intervalo
     * @param time  data a ser avaliada
     * @return {@code true} se a data estiver contida no intervalo, {@code false} caso contrário
     */
    private boolean estaNoIntervalo(LocalDateTime time, LocalDateTime start, LocalDateTime end) {
        return !time.isBefore(start) && !time.isAfter(end);
    }

    private long quantidadeDeCarrosPor(Categoria categoria) {
        return this.carroRepositorio.consultarTodos().stream().filter(carro -> carro.getCategoria().equals(categoria)).count();
    }

    private long quantidadeDeAlugueisPorCategoria(List<Aluguel> alugueis, Categoria categoria) {
        return alugueis.stream().filter(aluguel -> aluguel.getCategoria().equals(categoria)).count();
    }
}
