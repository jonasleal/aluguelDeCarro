package br.ufrpe.aluguelDeCarro.fachada;

import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
import br.ufrpe.aluguelDeCarro.excecoes.aluguel.AluguelInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.aluguel.AluguelNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.reserva.ReservaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioInvalidoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.*;

import java.util.List;

/**
 * @author Fernando
 */
public class FachadaUsuario {

    private final NegociosSingleton singleton = NegociosSingleton.getInstance();

    public void cadastrarCliente(Cliente cliente) throws PessoaInvalidaException, ClienteInvalidoException {
        singleton.getClienteNegocio().cadastrar(cliente);
    }

    public void alterarCliente(Cliente cliente) throws PessoaInvalidaException, ClienteInvalidoException {
        singleton.getClienteNegocio().alterar(cliente);
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

    public Carro consultarCarro(String placa) throws CarroNaoEncontradoException {
        return singleton.getCarroNegocio().consultar(placa);
    }

    public List<Carro> consultarCarros() {
        return singleton.getCarroNegocio().consultarTodos();
    }

    public void cadastrarReserva(Reserva reserva) throws ReservaInvalidaException {
        singleton.getReservaNegocio().cadastrar(reserva);
    }

    public void alterarReserva(Reserva reserva) throws ReservaInvalidaException {
        singleton.getReservaNegocio().alterar(reserva);
    }

    public Reserva consultarReserva(int id) throws ReservaInvalidaException {
        return singleton.getReservaNegocio().consultar(id);
    }

    public void finalizarAluguel(Aluguel aluguel) throws PessoaInvalidaException, AluguelInvalidoException, CarroInvalidoException, IdNaoEncontradoException, UsuarioInvalidoException, CategoriaInvalidaException, ClienteInvalidoException {
        singleton.getAluguelNegocio().finalizar(aluguel);
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

    public List<Categoria> consultarCategoriasDisponiveisParaReserva(Reserva reserva) {
        return singleton.getReservaNegocio().consultarCategoriasDisponiveisParaReserva(reserva);
    }

    public List<Aluguel> consultarAlugueis() {
        return singleton.getAluguelNegocio().consultarTodos();
    }

    public List<Aluguel> consultarAlugueis(Cliente cliente) {
        return singleton.getAluguelNegocio().consultarTodos(cliente);
    }

    public void cadastrarCategoria(Categoria categoria) throws CategoriaInvalidaException {
        singleton.getCategoriaNegocio().cadastrar(categoria);
    }

    public void alterarCategoria(Categoria categoria) throws CategoriaInvalidaException {
        singleton.getCategoriaNegocio().alterar(categoria);
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

}
