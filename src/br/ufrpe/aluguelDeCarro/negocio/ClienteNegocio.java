package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ClienteRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.*;

import java.util.ArrayList;

/**
 * @author Fernando
 */
public class ClienteNegocio {

    private final ClienteRepositorioInterface repositorio;

    public ClienteNegocio(ClienteRepositorioInterface repositorio) {
        this.repositorio = repositorio;
    }

    public boolean cadastrar(Cliente cliente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        if (cliente != null) {
            cliente.validar();
            cliente.setAtivo(true);
            return repositorio.cadastrar(cliente);
        }
        return false;
    }

    public boolean alterar(Cliente cliente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        if (cliente != null) {
            cliente.validar();
            return repositorio.alterar(cliente);
        }
        return false;
    }

    public Cliente consultar(int id) throws ClienteNaoEncontradoException {
        return this.repositorio.consultar(id);
    }

    public Cliente consultar(String cpf) throws ClienteNaoEncontradoException {
        return this.repositorio.consultar(cpf);
    }

    public boolean desativar(int id) throws ClienteNaoEncontradoException {
        return repositorio.desativar(id);
    }

    public ArrayList<Cliente> consultarTodos() {
        return this.repositorio.consultarTodos();
    }
}
