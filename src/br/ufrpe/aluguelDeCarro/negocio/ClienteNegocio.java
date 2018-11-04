package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ClienteRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.HabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;

import java.util.ArrayList;

/**
 * @author Fernando
 */
public class ClienteNegocio {

    private final ClienteRepositorioInterface repositorio;

    public ClienteNegocio(ClienteRepositorioInterface repositorio) {
        this.repositorio = repositorio;
    }

    public static boolean verificarStringData(String data) {
        if (data != null) {
            return data.matches("\\d{4}-\\d{2}-\\d{2}");
        }
        return false;
    }

    private boolean validar(Cliente cliente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        return cliente != null && cliente.validar();
    }

    public boolean cadastrar(Cliente cliente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        if (validar(cliente)) {
            cliente.setAtivo(true);
            return repositorio.cadastrar(cliente);
        }
        return false;
    }

    public boolean alterar(Cliente cliente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        if (validar(cliente)) {
            return repositorio.alterar(cliente);
        }
        return false;
    }

    public Cliente buscarPorId(int id) {
        if (id > 0) {
            return this.repositorio.buscarPorId(id);
        }
        return null;
    }

    public Cliente buscarPorCpf(String cpf) {
        if (cpf != null && !cpf.isEmpty()) {
            return this.repositorio.buscarPorCpf(cpf);
        }
        return null;
    }

    public boolean desativar(int id) {
        if (id > 0) {
            return repositorio.deletar(id);
        }
        return false;
    }

    public ArrayList<Cliente> buscarTodos(){
        return this.repositorio.buscarTodos();
    }
}
