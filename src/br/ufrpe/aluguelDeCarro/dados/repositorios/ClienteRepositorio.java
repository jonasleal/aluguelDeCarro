package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ClienteRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A classe armazena uma lista de instancias de clientes
 *
 * @author Fernando
 */
public class ClienteRepositorio implements ClienteRepositorioInterface {

    private final ArrayList<Cliente> clientes;

    public ClienteRepositorio() {
        this.clientes = new ArrayList<>();
    }

    /**
     * busca o cliente pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Cliente}
     * @return um clone do {@code Cliente} ativo que contém o id, {@code null} caso nao encontre
     */
    @Override
    public Cliente consultar(int id) {
        return this.clientes
                .stream()
                .filter(Cliente::isAtivo)
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .map(Cliente::clone)
                .orElse(null);
    }

    /**
     * busca o cliente pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Cliente}
     * @return o {@code Cliente} ativo que contém o id, {@code null} caso nao encontre
     */
    private Cliente buscarReferenciaPorId(int id) {
        return this.clientes
                .stream()
                .filter(Cliente::isAtivo)
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * busca o cliente pelo cpf, nos já cadastrados
     *
     * @param cpf identificador do {@code Cliente}
     * @return um clone do {@code Cliente} ativo que contém o cpf, {@code null} caso nao encontre
     */
    @Override
    public Cliente consultar(String cpf) {
        return this.clientes
                .stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .map(Cliente::clone)
                .orElse(null);
    }

    /**
     * @param cliente instancia a ser cadastrada
     * @return {@code true} caso cadastre com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean cadastrar(Cliente cliente) {
        this.setarId(cliente);
        return this.clientes.add(cliente.clone());
    }

    /**
     * @param clienteEditado instancia a ser editada
     * @return {@code true} caso altere com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean alterar(Cliente clienteEditado) {
        int indexOf = this.clientes.indexOf(clienteEditado);
        if (indexOf != -1) {
            this.clientes.set(indexOf, clienteEditado.clone());
            return true;
        }
        return false;
    }

    /**
     * altera o atributo {@code ativo} do cliente para false
     *
     * @param id identificador do {@code Cliente}
     * @return {@code true} caso desative com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean desativar(int id) {
        Cliente cliente = this.buscarReferenciaPorId(id);
        if (cliente != null) {
            cliente.setAtivo(false);
            return true;
        }
        return false;
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Cliente> consultarTodos() {
        return (ArrayList<Cliente>) this.clientes
                .stream()
                .filter(Cliente::isAtivo)
                .map(Cliente::clone)
                .collect(Collectors.toList());
    }

    /**
     * altera o id do cliente, o id que ele recebe é o maior até então acrescido de 1
     *
     * @param cliente instancia a ter o id alterado
     */
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
