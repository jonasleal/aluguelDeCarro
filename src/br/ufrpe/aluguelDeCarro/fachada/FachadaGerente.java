package br.ufrpe.aluguelDeCarro.fachada;

import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.ManutencaoNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.ReservaNaoEncontradaException;
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
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.*;

import java.util.List;

/**
 * Esta classe serve para centralizar todas classes de negócio
 *
 * @author Fernando
 */
public class FachadaGerente {

    private final NegociosSingleton singleton = NegociosSingleton.getInstance();

    public void cadastrarCliente(Cliente cliente) throws PessoaInvalidaException, ClienteInvalidoException {
        singleton.getClienteNegocio().cadastrar(cliente);
    }

    public void alterarCliente(Cliente cliente) throws PessoaInvalidaException, ClienteInvalidoException {
        singleton.getClienteNegocio().alterar(cliente);
    }

    public void desativarCliente(int id) throws ClienteNaoEncontradoException {
        singleton.getClienteNegocio().desativar(id);
    }

    public Cliente consultarCliente(String cpf) throws ClienteNaoEncontradoException {
        return singleton.getClienteNegocio().consultar(cpf);
    }

    public List<Cliente> consultarClientes() {
        return singleton.getClienteNegocio().consultarTodos();
    }

    public void cadastrarCarro(Carro carro) throws CarroInvalidoException {
        singleton.getCarroNegocio().cadastrar(carro);
    }

    public void alterarCarro(Carro carro) throws CarroInvalidoException {
        singleton.getCarroNegocio().alterar(carro);
    }

    public void desativarCarro(int id) throws IdNaoEncontradoException {
        singleton.getCarroNegocio().desativar(id);
    }

    public Carro consultarCarro(String placa) throws CarroNaoEncontradoException {
        return singleton.getCarroNegocio().consultar(placa);
    }

    public List<Carro> consultarCarros() {
        return singleton.getCarroNegocio().consultarTodos();
    }

    public List<Carro> consultarCarros(Categoria categoria) {
        return singleton.getCarroNegocio().consultar(categoria);
    }

    public void cadastrarReserva(Reserva reserva) {
        singleton.getReservaNegocio().cadastrar(reserva);
    }

    public void alterarReserva(Reserva reserva) {
        singleton.getReservaNegocio().alterar(reserva);
    }

    public void desativarReserva(int id) throws ReservaNaoEncontradaException {
        singleton.getReservaNegocio().desativar(id);
    }

    public Reserva consultarReserva(int id) throws ReservaNaoEncontradaException {
        return singleton.getReservaNegocio().consultar(id);
    }

    public List<Reserva> consultarReservas() {
        return singleton.getReservaNegocio().consultarTodos();
    }

    public void cadastrarAluguel(Aluguel aluguel) throws AluguelInvalidoException, UsuarioInvalidoException, PessoaInvalidaException, CarroInvalidoException, CategoriaInvalidaException, ClienteInvalidoException {
        singleton.getAluguelNegocio().cadastrar(aluguel);
    }

    public void alterarAluguel(Aluguel aluguel) throws AluguelInvalidoException, UsuarioInvalidoException, PessoaInvalidaException, CarroInvalidoException, CategoriaInvalidaException, ClienteInvalidoException {
        singleton.getAluguelNegocio().alterar(aluguel);
    }

    public Aluguel consultarAluguel(int id) throws AluguelNaoEncontradoException, IdNaoEncontradoException {
        return singleton.getAluguelNegocio().consultar(id);
    }

    public List<Categoria> consultarCategoriasDisponiveisParaAluguel(Aluguel aluguel) {
        return singleton.getAluguelNegocio().consultarCategoriasDisponiveisParaAluguel(aluguel);
    }

    public List<Aluguel> consultarAlugueis() {
        return singleton.getAluguelNegocio().consultarTodos();
    }

    public void cadastrarCategoria(Categoria categoria) throws CategoriaInvalidaException {
        singleton.getCategoriaNegocio().cadastrar(categoria);
    }

    public void alterarCategoria(Categoria categoria) throws CategoriaInvalidaException {
        singleton.getCategoriaNegocio().alterar(categoria);
    }

    public void desativarCategoria(int id) throws CategoriaNaoEncontradaException {
        singleton.getCategoriaNegocio().desativar(id);
    }

    public Categoria consultarCategoria(int id) throws CategoriaNaoEncontradaException {
        return singleton.getCategoriaNegocio().consultar(id);
    }

    public Categoria consultarCategoria(String nome) throws CategoriaNaoEncontradaException {
        return singleton.getCategoriaNegocio().consultar(nome);
    }

    public List<Categoria> consultarCategorias() {
        return singleton.getCategoriaNegocio().consultarTodos();
    }

    public void cadastrarManutencao(Manutencao manutencao) {
        singleton.getManutencaoNegocio().cadastrar(manutencao);
    }

    public void alterarManutencao(Manutencao manutencao) {
        singleton.getManutencaoNegocio().alterar(manutencao);
    }

    public void desativarManutencao(int id) throws ManutencaoNaoEncontradaException {
        singleton.getManutencaoNegocio().desativar(id);
    }

    public Manutencao consultarManutencao(int id) throws ManutencaoNaoEncontradaException {
        return singleton.getManutencaoNegocio().consultar(id);
    }

    public List<Manutencao> consultarManutencoes() {
        return singleton.getManutencaoNegocio().consultarTodos();
    }

    public void cadastrarUsuario(Usuario usuario) throws PessoaInvalidaException, UsuarioInvalidoException, ClienteInvalidoException {
        singleton.getUsuarioNegocio().cadastrar(usuario);
    }

    public void alterarUsuario(Usuario usuario) throws PessoaInvalidaException, UsuarioInvalidoException, ClienteInvalidoException {
        singleton.getUsuarioNegocio().alterar(usuario);
    }

    public void desativarUsuario(int id) throws UsuarioNaoEncontradoException {
        singleton.getUsuarioNegocio().desativar(id);
    }

    public Usuario consultarUsuario(int id) throws UsuarioNaoEncontradoException {
        return singleton.getUsuarioNegocio().consultar(id);
    }

    public Usuario consultarUsuario(String cpf) throws UsuarioNaoEncontradoException {
        return singleton.getUsuarioNegocio().consultar(cpf);
    }

    public void login(String cpf, String senha) throws UsuarioInvalidoException {
        singleton.getUsuarioNegocio().login(cpf, senha);
    }

    public Usuario getUsuarioLogado() {
        return singleton.getUsuarioNegocio().getUsuarioLogado();
    }

    public List<Usuario> consultarUsuarios() {
        return singleton.getUsuarioNegocio().consultarTodos();
    }

    public void logout() {
        this.singleton.getUsuarioNegocio().logout();
    }
}