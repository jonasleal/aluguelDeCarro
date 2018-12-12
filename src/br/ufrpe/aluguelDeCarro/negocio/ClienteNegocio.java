package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IClienteRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.FormatoHabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;

import java.util.ArrayList;

/**
 * @author Fernando
 */
public class ClienteNegocio {

    private final IClienteRepositorio repositorio;

    public ClienteNegocio(IClienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public boolean cadastrar(Cliente cliente) throws PessoaInvalidaException, ClienteInvalidoException {
        if (cliente != null) {
            cliente.validar();
            cliente.setAtivo(true);
            return repositorio.cadastrar(cliente);
        }
        return false;
    }

    public boolean alterar(Cliente cliente) throws PessoaInvalidaException, ClienteInvalidoException {
        if (cliente != null) {
            cliente.validar();
            return repositorio.alterar(cliente);
        }
        return false;
    }

    public Cliente consultar(int id) throws ClienteNaoEncontradoException, IdNaoEncontradoException {
        return this.repositorio.consultar(id);
    }

    public Cliente consultar(String cpf) throws ClienteNaoEncontradoException {
        return this.repositorio.consultar(cpf);
    }

    public boolean desativar(int id) throws ClienteNaoEncontradoException, IdNaoEncontradoException {
        return repositorio.desativar(id);
    }

    public ArrayList<Cliente> consultarTodos() {
        return this.repositorio.consultarTodos();
    }
}
