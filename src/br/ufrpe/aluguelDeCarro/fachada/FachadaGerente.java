package br.ufrpe.aluguelDeCarro.fachada;

import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.*;
import br.ufrpe.aluguelDeCarro.excecoes.Aluguel.AluguelInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.Aluguel.AluguelNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.Carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.Carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.ManutencaoNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.ReservaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.UsuarioNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.negocio.*;
import br.ufrpe.aluguelDeCarro.negocio.entidades.*;

import java.util.List;

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

    public void cadastrarAluguel(Aluguel aluguel) throws CarroInvalidoException, AluguelInvalidoException, ClienteInvalidoException {
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

    public void cadastrarCategoria(Categoria categoria) {
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

    public void cadastrarUsuario(Usuario usuario) throws PessoaInvalidaException {
        this.usuarioNegocio.cadastrar(usuario);
    }

    public void alterarUsuario(Usuario usuario) throws PessoaInvalidaException {
        this.usuarioNegocio.alterar(usuario);
    }

    public void desativarUsuario(int id) throws UsuarioNaoEncontradoException {
        this.usuarioNegocio.desativar(id);
    }

    public Usuario consultarUsuario(int id) throws UsuarioNaoEncontradoException {
        return this.usuarioNegocio.consultar(id);
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
}