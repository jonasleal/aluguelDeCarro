package br.ufrpe.aluguelDeCarro.fachada;

import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.*;
import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.ManutencaoNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.ReservaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.UsuarioNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.aluguel.AluguelInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.aluguel.AluguelNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioInvalidoException;
import br.ufrpe.aluguelDeCarro.negocio.*;
import br.ufrpe.aluguelDeCarro.negocio.entidades.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Esta classe serve para centralizar todas classes de negócio
 *
 * @author Fernando
 */
public class FachadaGerente {

    private static FachadaGerente myself = null;

    private final CarroNegocio carroNegocio;
    private final ClienteNegocio clienteNegocio;
    private final UsuarioNegocio usuarioNegocio;
    private final AluguelNegocio aluguelNegocio;
    private final CategoriaNegocio categoriaNegocio;
    private final ManutencaoNegocio manutencaoNegocio;
    private final ReservaNegocio reservaNegocio;

    private Usuario usuarioLogado;

    private FachadaGerente() {
        this.carroNegocio = new CarroNegocio(new CarroRepositorio());
        this.clienteNegocio = new ClienteNegocio(new ClienteRepositorio());
        this.usuarioNegocio = new UsuarioNegocio(new UsuarioRepositorio());
        this.aluguelNegocio = new AluguelNegocio(new AluguelRepositorio());
        this.categoriaNegocio = new CategoriaNegocio(new CategoriaRepositorio());
        this.manutencaoNegocio = new ManutencaoNegocio(new ManutencaoRepositorio());
        this.reservaNegocio = new ReservaNegocio(new ReservaRepositorio());
    }

    /**
     * @return uma instancia da classe, caso já tenha sido inicializada simplementes a retorna, caso contrário cria uma
     * nova
     */
    public static FachadaGerente getInstance() {
        if (myself == null)
            myself = new FachadaGerente();
        return myself;
    }

    public void cadastrarCliente(Cliente cliente) throws PessoaInvalidaException, ClienteInvalidoException {
        this.clienteNegocio.cadastrar(cliente);
    }

    public void alterarCliente(Cliente cliente) throws PessoaInvalidaException, ClienteInvalidoException {
        this.clienteNegocio.alterar(cliente);
    }

    public void desativarCliente(int id) throws ClienteNaoEncontradoException, IdNaoEncontradoException {
        this.clienteNegocio.desativar(id);
    }

    public Cliente consultarCliente(String cpf) throws ClienteNaoEncontradoException {
        return this.clienteNegocio.consultar(cpf);
    }

    public List<Cliente> consultarClientes() {
        return this.clienteNegocio.consultarTodos();
    }

    public void cadastrarCarro(Carro carro) throws CarroInvalidoException {
        this.carroNegocio.cadastrar(carro);
    }

    public void alterarCarro(Carro carro) throws CarroInvalidoException {
        this.carroNegocio.alterar(carro);
    }

    public void desativarCarro(int id) throws IdNaoEncontradoException {
        this.carroNegocio.desativar(id);
    }

    public Carro consultarCarro(String placa) throws CarroNaoEncontradoException {
        return this.carroNegocio.consultar(placa);
    }

    public List<Carro> consultarCarros() {
        return this.carroNegocio.consultarTodos();
    }

    public List<Carro> consultarCarros(Categoria categoria) {
        return this.carroNegocio.consultar(categoria);
    }

    public void cadastrarReserva(Reserva reserva) {
        this.reservaNegocio.cadastrar(reserva);
    }

    public void alterarReserva(Reserva reserva) {
        this.reservaNegocio.alterar(reserva);
    }

    public void desativarReserva(int id) throws ReservaNaoEncontradaException {
        this.reservaNegocio.desativar(id);
    }

    public Reserva consultarReserva(int id) throws ReservaNaoEncontradaException {
        return this.reservaNegocio.consultar(id);
    }

    public List<Reserva> consultarReservas() {
        return this.reservaNegocio.consultarTodos();
    }

    public void cadastrarAluguel(Aluguel aluguel) throws CarroInvalidoException, AluguelInvalidoException, ClienteInvalidoException, UsuarioInvalidoException, CategoriaInvalidaException {
        this.aluguelNegocio.cadastrar(aluguel);
    }

    public void alterarAluguel(Aluguel aluguel) throws AluguelInvalidoException, ClienteInvalidoException {
        this.aluguelNegocio.alterar(aluguel);
    }

    public Aluguel consultarAluguel(int id) throws AluguelNaoEncontradoException, IdNaoEncontradoException {
        return this.aluguelNegocio.consultar(id);
    }

    public List<Aluguel> consultarAlugueis() {
        return this.aluguelNegocio.consultarTodos();
    }

    public void cadastrarCategoria(Categoria categoria) throws CategoriaInvalidaException {
        this.categoriaNegocio.cadastrar(categoria);
    }

    public void alterarCategoria(Categoria categoria) {
        this.categoriaNegocio.alterar(categoria);
    }

    public void desativarCategoria(int id) throws CategoriaNaoEncontradaException {
        this.categoriaNegocio.desativar(id);
    }

    public Categoria consultarCategoria(int id) throws CategoriaNaoEncontradaException {
        return this.categoriaNegocio.consultar(id);
    }

    public Categoria consultarCategoria(String nome) throws CategoriaNaoEncontradaException {
        return this.categoriaNegocio.consultar(nome);
    }

    public List<Categoria> consultarCategorias() {
        return this.categoriaNegocio.consultarTodos();
    }

    public void cadastrarManutencao(Manutencao manutencao) {
        this.manutencaoNegocio.cadastrar(manutencao);
    }

    public void alterarManutencao(Manutencao manutencao) {
        this.manutencaoNegocio.alterar(manutencao);
    }

    public void desativarManutencao(int id) throws ManutencaoNaoEncontradaException {
        this.manutencaoNegocio.desativar(id);
    }

    public Manutencao consultarManutencao(int id) throws ManutencaoNaoEncontradaException {
        return this.manutencaoNegocio.consultar(id);
    }

    public List<Manutencao> consultarManutencoes() {
        return this.manutencaoNegocio.consultarTodos();
    }

    public void cadastrarUsuario(Usuario usuario) throws PessoaInvalidaException, ClienteInvalidoException {
        this.usuarioNegocio.cadastrar(usuario);
    }

    public void alterarUsuario(Usuario usuario) throws PessoaInvalidaException, ClienteInvalidoException {
        this.usuarioNegocio.alterar(usuario);
    }

    public void desativarUsuario(int id) throws UsuarioNaoEncontradoException {
        this.usuarioNegocio.desativar(id);
    }

    public Usuario consultarUsuario(int id) throws UsuarioNaoEncontradoException {
        return this.usuarioNegocio.consultar(id);
    }

    public Usuario consultarUsuario(String cpf) throws UsuarioNaoEncontradoException {
        return this.usuarioNegocio.consultar(cpf);
    }

    public List<Usuario> consultarUsuarios() {
        return this.usuarioNegocio.consultarTodos();
    }

    /**
     * @return o usuário que está utilizando o sistema no momemnto
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado o usuário que está utilizando o sistema no momento
     */
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<Categoria> verificarCategoriasDisponiveis(Aluguel aluguel) {
        List<Reserva> reservas = this.reservaNegocio.consultarTodos();
        List<Reserva> reservasComConflito = reservasComConflitoComAluguel(reservas, aluguel);
        Set<Categoria> collect = categoriasDasReservasComConflito(reservasComConflito);
        List<Reserva> reservasSemConflito = reservasSemConflitoComAluguel(reservas, aluguel);
        collect.addAll(categoriasDasReservasSemConflito(reservasSemConflito));
        collect.addAll(categoriasNaoContidasNasReservas(reservas));
        return new ArrayList<>(collect);
    }

    private List<Categoria> categoriasNaoContidasNasReservas(List<Reserva> reservas) {
        List<Categoria> categorias = this.categoriaNegocio.consultarTodos();
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
        return this.carroNegocio.consultarTodos().stream().filter(carro -> carro.getCategoria().equals(categoria)).count();
    }
}