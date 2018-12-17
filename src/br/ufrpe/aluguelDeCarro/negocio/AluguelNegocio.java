/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IAluguelRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICarroRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICategoriaRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IReservaRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.aluguel.*;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroIndisponivelException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioInvalidoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author JonasJr
 */
public class AluguelNegocio {

    private final IAluguelRepositorio aluguelRepositorio;
    private final ICategoriaRepositorio categoriaRepositorio;
    private final ICarroRepositorio carroRepositorio;
    private final IReservaRepositorio reservaRepositorio;

    public AluguelNegocio(IAluguelRepositorio repositorio, ICategoriaRepositorio categoriaRepositorio, ICarroRepositorio carroRepositorio, IReservaRepositorio reservaRepositorio) {
        this.aluguelRepositorio = repositorio;
        this.categoriaRepositorio = categoriaRepositorio;
        this.carroRepositorio = carroRepositorio;
        this.reservaRepositorio = reservaRepositorio;
    }

    private void validarDevolucao(Aluguel aluguel) throws AluguelInvalidoException, IdNaoEncontradoException, UsuarioInvalidoException, PessoaInvalidaException, CarroInvalidoException, CategoriaInvalidaException, ClienteInvalidoException {
        aluguel.validar();
        Aluguel aluguelOriginal = aluguelRepositorio.consultar(aluguel.getId());
        LocalDateTime dataNoBanco = aluguelOriginal.getDevolucaoReal();
        if (dataNoBanco != null) throw new AluguelFinalizadoException();
        dataNoBanco = aluguelOriginal.getDevolucaoEstimada();
        if (!aluguel.getDevolucaoEstimada().equals(dataNoBanco))
            throw new DataEstimadaInconsistenteException(dataNoBanco, aluguel.getDevolucaoEstimada());
        if (!aluguel.getRetirada().equals(aluguelOriginal.getRetirada()))
            throw new DataRetiradaInconsistenteException(dataNoBanco, aluguel.getRetirada());
    }

    private void validarParaAlugar(Aluguel aluguel) throws AluguelInvalidoException, CarroInvalidoException, UsuarioInvalidoException, PessoaInvalidaException, CategoriaInvalidaException, ClienteInvalidoException {
        aluguel.validar();
        if (this.aluguelRepositorio.existe(aluguel.getCliente()))
            throw new AluguelEmAbertoException(aluguel.getCliente().getCpf());
        LocalDate dataNoObjeto = aluguel.getRetirada().toLocalDate();
        if (dataNoObjeto.compareTo(LocalDate.now()) < 0) throw new DataRetiradaPassadoException(dataNoObjeto);
        dataNoObjeto = aluguel.getDevolucaoEstimada().toLocalDate();
        if (dataNoObjeto.compareTo(LocalDate.now()) < 1) throw new DataEstimadaPassado(dataNoObjeto);
        Carro carro = aluguel.getCarro();
        if (!carro.isAtivo() || !carro.isDisponivel()) throw new CarroIndisponivelException(carro.getPlaca());
    }

    /**
     * Verifica os dados da solicitação de aluguel, caso todos os dados
     * obrigatórios tenha sido passado, retorna True, caso contrário levanta uma
     * exceção referente a causa da falha.
     *
     * @param aluguel Instancia a ser cadastrada
     * @throws AluguelInvalidoException - Contem a causa e a mensagem de erro.
     */
    public void cadastrar(Aluguel aluguel) throws AluguelInvalidoException, CarroInvalidoException, PessoaInvalidaException, UsuarioInvalidoException, CategoriaInvalidaException, ClienteInvalidoException {
        if (aluguel == null) throw new AluguelObrigatorioException();
        this.validarParaAlugar(aluguel);
        Carro carro = aluguel.getCarro();
        carro.setDisponivel(false);
        this.carroRepositorio.alterar(carro);
        aluguel.setAtivo(true);
        this.aluguelRepositorio.cadastrar(aluguel);

    }

    public void alterar(Aluguel aluguel) throws AluguelInvalidoException, UsuarioInvalidoException, PessoaInvalidaException, CarroInvalidoException, CategoriaInvalidaException, ClienteInvalidoException {
        if (aluguel == null) throw new AluguelObrigatorioException();
        aluguel.validar();
        this.aluguelRepositorio.alterar(aluguel);
    }

    public Aluguel consultar(int id) throws AluguelNaoEncontradoException, IdNaoEncontradoException {
        return this.aluguelRepositorio.consultar(id);
    }

    /**
     * Calcula o debito do aluguel em aberto e o finaliza no momento da chamada.
     *
     * @param aluguel           - Objeto com os dados do aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância de
     *                          30 minutos.
     */
    private void calcularDebito(Aluguel aluguel, boolean considerarHorario) {
        aluguel.setDevolucaoReal(LocalDateTime.now());
        LocalDateTime dataEstimada = aluguel.getDevolucaoEstimada();
        LocalDateTime dataDevolucao = aluguel.getDevolucaoReal();
        Period periodoTotal;
        if (dataDevolucao.isBefore(dataEstimada)) {
            periodoTotal = Period.ZERO;
        } else {
            periodoTotal = Period.between(dataEstimada.toLocalDate(), dataDevolucao.toLocalDate());
        }

        if (considerarHorario) {
            int minutosTolerancia = 30;
            if (dataDevolucao.compareTo(dataEstimada.plusMinutes(minutosTolerancia)) > 0) {
                periodoTotal = periodoTotal.plusDays(1);
            }
        }
        long dias = periodoTotal.get(ChronoUnit.DAYS);
        BigDecimal adicional = aluguel.getCategoria().getDiaria().multiply(new BigDecimal(dias));

        aluguel.setCustoAdicional(adicional);
    }

    /**
     * Busca e calcula o debito de um aluguel em aberto para um determinado
     * cliente entrega um aluguel finalizado no momento da chamada.
     *
     * @param cliente           - cliente registrado no aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância de
     *                          30 minutos.
     * @return Objeto aluguel no estado finalizado.
     */
    private Aluguel consultarDebito(Cliente cliente, boolean considerarHorario) throws AluguelNaoEncontradoException {
        Aluguel aluguel = consultar(cliente);
        if (aluguel != null) {
            calcularDebito(aluguel, considerarHorario);
        }
        return aluguel;
    }

    public void finalizar(Aluguel aluguel) throws PessoaInvalidaException, AluguelInvalidoException, CarroInvalidoException, IdNaoEncontradoException, UsuarioInvalidoException, CategoriaInvalidaException, ClienteInvalidoException {
        aluguel = consultarDebito(aluguel.getCliente(), true);
        devolucao(aluguel);
        Carro carro = aluguel.getCarro();
        carro.setDisponivel(true);
        this.carroRepositorio.alterar(carro);
    }

    /**
     * Busca e calcula o debito de um aluguel em aberto para um determinado
     * carro entrega um aluguel finalizado no momento da chamada.
     *
     * @param carro             - carro registrado no aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância de
     *                          30 minutos.
     * @return Objeto aluguel no estado finalizado.
     */
    public Aluguel consultarDebito(Carro carro, boolean considerarHorario) throws AluguelNaoEncontradoException {
        Aluguel aluguel = consultar(carro);
        if (aluguel != null) {
            calcularDebito(aluguel, considerarHorario);
        }
        return aluguel;
    }

    /**
     * Busca aluguel em aberto para um determinado cliente.
     *
     * @param cliente - cliente registrado no aluguel em aberto
     * @return Intancia do aluguel aberto para este cliente.
     */
    public Aluguel consultar(Cliente cliente) throws AluguelNaoEncontradoException {
        return this.aluguelRepositorio.consultar(cliente);
    }

    /**
     * Busca aluguel em aberto para um determinado carro.
     *
     * @param carro - carro registrado no aluguel em aberto
     * @return Intancia do aluguel aberto para este carro.
     */
    public Aluguel consultar(Carro carro) throws AluguelNaoEncontradoException {
        return aluguelRepositorio.consultar(carro);
    }

    /**
     * Verifica a consistência datas passados com as já cadastradas, se forem
     * iguais registra a devolução, caso contrário levanta exceção com a causa.
     *
     * @param aluguel - aluguel no estado finalizado.
     *                //     * @throws AluguelException - Contem a mensagem e causa do erro.
     */
    private void devolucao(Aluguel aluguel) throws AluguelInvalidoException, IdNaoEncontradoException, CategoriaInvalidaException, PessoaInvalidaException, CarroInvalidoException, UsuarioInvalidoException, ClienteInvalidoException {
        validarDevolucao(aluguel);
        this.alterar(aluguel);
    }

    public ArrayList<Aluguel> consultarTodos() {
        return this.aluguelRepositorio.consultarTodos();
    }

    public ArrayList<Aluguel> consultarTodos(Cliente cliente) {
        return this.aluguelRepositorio.consultarTodos(cliente);
    }

    public List<Categoria> consultarCategoriasDisponiveisParaAluguel(Aluguel aluguel) {
        List<Reserva> reservas = this.reservaRepositorio.consultarTodos();
        List<Reserva> reservasComConflito = reservasComConflitoComAluguel(reservas, aluguel);
        Set<Categoria> collect = categoriasDasReservasComConflito(reservasComConflito);
        List<Reserva> reservasSemConflito = reservasSemConflitoComAluguel(reservas, aluguel);
        collect.addAll(categoriasDasReservasSemConflito(reservasSemConflito));
        collect.addAll(categoriasNaoContidasNasReservas(reservas));
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
                .filter(reserva -> quantidadeDeCarrosPor(reserva.getCategoria()) > 1)
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

    private List<Reserva> reservasComConflitoComAluguel(List<Reserva> reservas, Aluguel aluguel) {
        return reservas
                .stream()
                .filter(reserva -> conflitoAluguelReserva(
                        aluguel.getRetirada(), aluguel.getDevolucaoEstimada(), reserva.getRetiradaPrevista(), reserva.getDevolucaoPrevista()))
                .collect(Collectors.toList());
    }

    private List<Reserva> reservasSemConflitoComAluguel(List<Reserva> reservas, Aluguel aluguel) {
        return reservas
                .stream()
                .filter(reserva -> !conflitoAluguelReserva(
                        aluguel.getRetirada(), aluguel.getDevolucaoEstimada(), reserva.getRetiradaPrevista(), reserva.getDevolucaoPrevista()))
                .collect(Collectors.toList());
    }

    private long quantidadeDeReservasPor(List<Reserva> reservas, Categoria categoria) {
        return reservas.stream().filter(reserva -> reserva.getCategoria().equals(categoria)).count();
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

}
