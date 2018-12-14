package br.ufrpe.aluguelDeCarro.fachada;

import br.ufrpe.aluguelDeCarro.excecoes.CategoriaNaoEncontradaException;
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
import br.ufrpe.aluguelDeCarro.negocio.entidades.*;

import java.util.List;

/**
 * @author Fernando
 */
public class FachadaUsuario {

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

    public Carro consultarCarro(String placa) throws CarroNaoEncontradoException {
        return NegociosSingleton.getInstance().getCarroNegocio().consultar(placa);
    }

    public List<Carro> consultarCarros() {
        return NegociosSingleton.getInstance().getCarroNegocio().consultarTodos();
    }

    public void cadastrarReserva(Reserva reserva) {
        NegociosSingleton.getInstance().getReservaNegocio().cadastrar(reserva);
    }

    public void alterarReserva(Reserva reserva) {
        NegociosSingleton.getInstance().getReservaNegocio().alterar(reserva);
    }

    public Reserva consultarReserva(int id) throws ReservaNaoEncontradaException {
        return NegociosSingleton.getInstance().getReservaNegocio().consultar(id);
    }

    public List<Reserva> consultarReservas() {
        return NegociosSingleton.getInstance().getReservaNegocio().consultarTodos();
    }

    public void cadastrarAluguel(Aluguel aluguel) throws CarroInvalidoException, AluguelInvalidoException, ClienteInvalidoException, CategoriaInvalidaException, UsuarioInvalidoException {
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

    public void alterarCategoria(Categoria categoria) throws CategoriaInvalidaException {
        NegociosSingleton.getInstance().getCategoriaNegocio().alterar(categoria);
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

}
