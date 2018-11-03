package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ClienteRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class ClienteRepositorio implements ClienteRepositorioInterface {

    private ArrayList<Cliente> clientes;

    public ClienteRepositorio() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public Cliente buscarPorId(int id) {
        return this.clientes
                .stream()
                .filter(Cliente::isAtivo)
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .map(Cliente::clone)
                .orElse(null);
    }

    private Cliente buscarReferenciaPorId(int id) {
        return this.clientes
                .stream()
                .filter(Cliente::isAtivo)
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        return this.clientes
                .stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .map(Cliente::clone)
                .orElse(null);
    }

    @Override
    public boolean cadastrar(Cliente cliente) {
        this.setarId(cliente);
        return this.clientes.add(cliente.clone());
    }

    @Override
    public boolean alterar(Cliente clienteEditado) {
        int indexOf = this.clientes.indexOf(clienteEditado);
        if (indexOf != -1) {
            this.clientes.set(indexOf, clienteEditado.clone());
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        Cliente cliente = this.buscarReferenciaPorId(id);
        if (cliente != null){
            cliente.setAtivo(false);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Cliente> buscarTodos() {
        return (ArrayList<Cliente>) this.clientes
                .stream()
                .filter(Cliente::isAtivo)
                .map(Cliente::clone)
                .collect(Collectors.toList());
    }

    private void setarId(Cliente cliente) {
        if (this.clientes.isEmpty())
            cliente.setId(1);
        else
            cliente.setId(this.clientes
                    .stream()
                    .mapToInt(Cliente::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
