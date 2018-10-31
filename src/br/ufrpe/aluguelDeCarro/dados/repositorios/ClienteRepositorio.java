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
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .orElse(null);
    }
    @Override
    public Cliente buscarPorCpf(String cpf) {
        return this.clientes
                .stream()
                .filter(cliente -> cliente.getCpf()== cpf)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void cadastrar(Cliente cliente) {
        this.setarId(cliente);
        cliente.setAtivo(true);
        this.clientes.add(cliente);
    }

    @Override
    public void alterar(Cliente clienteEditado) {
        this.clientes
                .stream()
                .filter(cliente -> cliente.equals(clienteEditado))
                .forEach(cliente -> cliente = clienteEditado);
    }

    @Override
    public void deletar(int id) {
        Cliente cliente = this.buscarPorId(id);
        if (cliente != null) {
            cliente.setAtivo(false);
        }
    }

    @Override
    public ArrayList<Cliente> buscarTodos() {
        return (ArrayList<Cliente>) this.clientes
                .stream()
                .filter(Cliente::isAtivo)
                .collect(Collectors.toList());
    }

    private void setarId(Cliente cliente) {
        if (this.clientes.isEmpty()) {
            cliente.setId(0);
        } else {
            cliente.setId(this.clientes
                    .stream()
                    .mapToInt(Cliente::getId)
                    .max()
                    .getAsInt() + 1);
        }
    }
}
