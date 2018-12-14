package br.ufrpe.aluguelDeCarro.fachada;

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
import br.ufrpe.aluguelDeCarro.excecoes.categoria.NomeCategoriaObrigatorioException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.PrecoNegativoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioInvalidoException;
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

    public Usuario getUsuarioLogado() {
        return NegociosSingleton.getInstance().getUsuarioLogado();
    }

    public void setUsuarioLogado(Usuario usuario) {
        NegociosSingleton.getInstance().setUsuarioLogado(usuario);
    }

    public void cadastrarCliente(Cliente cliente) throws PessoaInvalidaException, ClienteInvalidoException {
        NegociosSingleton.getInstance().getClienteNegocio().cadastrar(cliente);
    }

    public void alterarCliente(Cliente cliente) throws PessoaInvalidaException, ClienteInvalidoException {
        NegociosSingleton.getInstance().getClienteNegocio().alterar(cliente);
    }

    public void desativarCliente(int id) throws ClienteNaoEncontradoException {
        NegociosSingleton.getInstance().getClienteNegocio().desativar(id);
    }

    public Cliente consultarCliente(String cpf) throws ClienteNaoEncontradoException {
        return NegociosSingleton.getInstance().getClienteNegocio().consultar(cpf);
    }

    public List<Cliente> consultarClientes() {
        return NegociosSingleton.getInstance().getClienteNegocio().consultarTodos();
    }

    public void cadastrarCarro(Carro carro) throws CarroInvalidoException {
        NegociosSingleton.getInstance().getCarroNegocio().cadastrar(carro);
    }

    public void alterarCarro(Carro carro) throws CarroInvalidoException {
        NegociosSingleton.getInstance().getCarroNegocio().alterar(carro);
    }

    public void desativarCarro(int id) throws IdNaoEncontradoException {
        NegociosSingleton.getInstance().getCarroNegocio().desativar(id);
    }

    public Carro consultarCarro(String placa) throws CarroNaoEncontradoException {
        return NegociosSingleton.getInstance().getCarroNegocio().consultar(placa);
    }

    public List<Carro> consultarCarros() {
        return NegociosSingleton.getInstance().getCarroNegocio().consultarTodos();
    }

    public List<Carro> consultarCarros(Categoria categoria) {
        return NegociosSingleton.getInstance().getCarroNegocio().consultar(categoria);
    }

    public void cadastrarReserva(Reserva reserva) {
        NegociosSingleton.getInstance().getReservaNegocio().cadastrar(reserva);
    }

    public void alterarReserva(Reserva reserva) {
        NegociosSingleton.getInstance().getReservaNegocio().alterar(reserva);
    }

    public void desativarReserva(int id) throws ReservaNaoEncontradaException {
        NegociosSingleton.getInstance().getReservaNegocio().desativar(id);
    }

    public Reserva consultarReserva(int id) throws ReservaNaoEncontradaException {
        return NegociosSingleton.getInstance().getReservaNegocio().consultar(id);
    }

    public List<Reserva> consultarReservas() {
        return NegociosSingleton.getInstance().getReservaNegocio().consultarTodos();
    }

    public void cadastrarAluguel(Aluguel aluguel) throws CarroInvalidoException, AluguelInvalidoException, ClienteInvalidoException, UsuarioInvalidoException, CategoriaInvalidaException {
        NegociosSingleton.getInstance().getAluguelNegocio().cadastrar(aluguel);
    }

    public void alterarAluguel(Aluguel aluguel) throws AluguelInvalidoException {
        NegociosSingleton.getInstance().getAluguelNegocio().alterar(aluguel);
    }

    public Aluguel consultarAluguel(int id) throws AluguelNaoEncontradoException, IdNaoEncontradoException {
        return NegociosSingleton.getInstance().getAluguelNegocio().consultar(id);
    }

    public List<Aluguel> consultarAlugueis() {
        return NegociosSingleton.getInstance().getAluguelNegocio().consultarTodos();
    }

    public void cadastrarCategoria(Categoria categoria) throws CategoriaInvalidaException {
        NegociosSingleton.getInstance().getCategoriaNegocio().cadastrar(categoria);
    }

    public void alterarCategoria(Categoria categoria) throws PrecoNegativoException, NomeCategoriaObrigatorioException {
        NegociosSingleton.getInstance().getCategoriaNegocio().alterar(categoria);
    }

    public void desativarCategoria(int id) throws CategoriaNaoEncontradaException {
        NegociosSingleton.getInstance().getCategoriaNegocio().desativar(id);
    }

    public Categoria consultarCategoria(int id) throws CategoriaNaoEncontradaException {
        return NegociosSingleton.getInstance().getCategoriaNegocio().consultar(id);
    }

    public Categoria consultarCategoria(String nome) throws CategoriaNaoEncontradaException {
        return NegociosSingleton.getInstance().getCategoriaNegocio().consultar(nome);
    }

    public List<Categoria> consultarCategorias() {
        return NegociosSingleton.getInstance().getCategoriaNegocio().consultarTodos();
    }

    public void cadastrarManutencao(Manutencao manutencao) {
        NegociosSingleton.getInstance().getManutencaoNegocio().cadastrar(manutencao);
    }

    public void alterarManutencao(Manutencao manutencao) {
        NegociosSingleton.getInstance().getManutencaoNegocio().alterar(manutencao);
    }

    public void desativarManutencao(int id) throws ManutencaoNaoEncontradaException {
        NegociosSingleton.getInstance().getManutencaoNegocio().desativar(id);
    }

    public Manutencao consultarManutencao(int id) throws ManutencaoNaoEncontradaException {
        return NegociosSingleton.getInstance().getManutencaoNegocio().consultar(id);
    }

    public List<Manutencao> consultarManutencoes() {
        return NegociosSingleton.getInstance().getManutencaoNegocio().consultarTodos();
    }

    public void cadastrarUsuario(Usuario usuario) throws PessoaInvalidaException, ClienteInvalidoException, UsuarioInvalidoException {
        NegociosSingleton.getInstance().getUsuarioNegocio().cadastrar(usuario);
    }

    public void alterarUsuario(Usuario usuario) throws PessoaInvalidaException, ClienteInvalidoException, UsuarioInvalidoException {
        NegociosSingleton.getInstance().getUsuarioNegocio().alterar(usuario);
    }

    public void desativarUsuario(int id) throws UsuarioNaoEncontradoException {
        NegociosSingleton.getInstance().getUsuarioNegocio().desativar(id);
    }

    public Usuario consultarUsuario(int id) throws UsuarioNaoEncontradoException {
        return NegociosSingleton.getInstance().getUsuarioNegocio().consultar(id);
    }

    public Usuario consultarUsuario(String cpf) throws UsuarioNaoEncontradoException {
        return NegociosSingleton.getInstance().getUsuarioNegocio().consultar(cpf);
    }

    public List<Usuario> consultarUsuarios() {
        return NegociosSingleton.getInstance().getUsuarioNegocio().consultarTodos();
    }

    public List<Categoria> verificarCategoriasDisponiveis(Aluguel aluguel) {
        List<Reserva> reservas = NegociosSingleton.getInstance().getReservaNegocio().consultarTodos();
        List<Reserva> reservasComConflito = reservasComConflitoComAluguel(reservas, aluguel);
        Set<Categoria> collect = categoriasDasReservasComConflito(reservasComConflito);
        List<Reserva> reservasSemConflito = reservasSemConflitoComAluguel(reservas, aluguel);
        collect.addAll(categoriasDasReservasSemConflito(reservasSemConflito));
        collect.addAll(categoriasNaoContidasNasReservas(reservas));
        return new ArrayList<>(collect);
    }

    private List<Categoria> categoriasNaoContidasNasReservas(List<Reserva> reservas) {
        List<Categoria> categorias = NegociosSingleton.getInstance().getCategoriaNegocio().consultarTodos();
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
        return NegociosSingleton.getInstance().getCarroNegocio().consultarTodos().stream().filter(carro -> carro.getCategoria().equals(categoria)).count();
    }
}