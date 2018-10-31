package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.ClienteRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ClienteRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;

/**
 * @author Fernando
 */
public class ClienteNegocio {

    private final ClienteRepositorioInterface repositorio;
    
    public ClienteNegocio(){
        this.repositorio = new ClienteRepositorio();
    }

    public static boolean verificarStringData(String data) {
        if (data != null) {
            return data.matches("\\d{4}-\\d{2}-\\d{2}");
        }
        return false;
    }

    private boolean validar(Cliente cliente) throws CpfException, IdadeExcetion, NomeException {
        return cliente != null && cliente.valirdar();
    }

    public boolean cadastrar(Cliente cliente) throws CpfException, IdadeExcetion, NomeException {
        boolean saida = false;
        if (validar(cliente)) {
            repositorio.cadastrar(cliente);
            saida = true;
        }

        return saida;
    }

    public boolean alterar(Cliente cliente) throws CpfException, IdadeExcetion, NomeException {
        boolean saida = false;
        if (validar(cliente)) {
            repositorio.alterar(cliente);
            saida = true;
        }
        return saida;
    }

    public Cliente buscarPorId(int id) {
        Cliente saida = null;
        if (id > 0) {
            saida = this.repositorio.buscarPorId(id);
        }
        return saida;
    }

    public Cliente buscarPorCpf(String cpf) {
        Cliente saida = null;
        if (cpf != null && !cpf.isEmpty()) {
            saida = this.repositorio.buscarPorCpf(cpf);
        }
        return saida;
    }

    public boolean desativar(int id) {
        boolean saida = false;
        if (id > 0) {
            repositorio.deletar(id);
            saida = true;
        }
        return saida;
    }

}
