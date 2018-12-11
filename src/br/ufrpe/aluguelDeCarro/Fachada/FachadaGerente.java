package br.ufrpe.aluguelDeCarro.Fachada;

import br.ufrpe.aluguelDeCarro.excecoes.Carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.Carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.HabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.ReservaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.bacoDeDados.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.bacoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Reserva;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.*;
import br.ufrpe.aluguelDeCarro.negocio.*;

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

    public void cadastrarCliente(Cliente cliente) throws PessoaInvalidaException, HabilitacaoException {
        this.clienteNegocio.cadastrar(cliente);
    }

    public void alterarCliente(Cliente cliente) throws PessoaInvalidaException, HabilitacaoException {
        this.clienteNegocio.alterar(cliente);
    }

    public void desativarCliente(int id) throws ClienteNaoEncontradoException {
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



    public ManutencaoNegocio getManutencaoNegocio() {
        return manutencaoNegocio;
    }

    public ReservaNegocio getReservaNegocio() {
        return reservaNegocio;
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