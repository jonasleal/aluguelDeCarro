package br.ufrpe.aluguelDeCarro.dados.repositorios.memoria;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IClienteRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A classe armazena uma lista de instancias de clientes
 *
 * @author Fernando
 */
public class ClienteRepositorio implements IClienteRepositorio {

    private final ArrayList<Cliente> clientes;

    public ClienteRepositorio() {
        this.clientes = new ArrayList<>();
    }

    /**
     * busca o cliente pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Cliente}
     * @return um clone do {@code Cliente} ativo que contém o id, {@code null}
     * caso nao encontre
     * @throws ClienteNaoEncontradoException
     */
    @Override
    public Cliente consultar(int id) throws ClienteNaoEncontradoException {
        return this.clientes
                .stream()
                .filter(Cliente::isAtivo)
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .map(Cliente::clone)
                .orElseThrow(ClienteNaoEncontradoException::new);
    }

    /**
     * busca o cliente pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Cliente}
     * @return o {@code Cliente} ativo que contém o id, {@code null} caso nao
     * encontre
     */
    private Cliente consultarReferencia(int id) throws ClienteNaoEncontradoException {
        return this.clientes
                .stream()
                .filter(Cliente::isAtivo)
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .orElseThrow(ClienteNaoEncontradoException::new);
    }

    /**
     * busca o cliente pelo cpf, nos já cadastrados
     *
     * @param cpf identificador do {@code Cliente}
     * @return um clone do {@code Cliente} ativo que contém o cpf, {@code null}
     * caso nao encontre
     * @throws ClienteNaoEncontradoException
     */
    @Override
    public Cliente consultar(String cpf) throws ClienteNaoEncontradoException {
        return this.clientes
                .stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .map(Cliente::clone)
                .orElseThrow(() -> new ClienteNaoEncontradoException(cpf));
    }

    /**
     * @param cliente instancia a ser cadastrada
     */
    @Override
    public void cadastrar(Cliente cliente) {
        this.setarId(cliente);
        this.clientes.add(cliente.clone());
    }

    /**
     * @param clienteEditado instancia a ser editada
     */
    @Override
    public void alterar(Cliente clienteEditado) {
        int indexOf = this.clientes.indexOf(clienteEditado);
        if (indexOf != -1)
            this.clientes.set(indexOf, clienteEditado.clone());
    }

    /**
     * altera o atributo {@code ativo} do cliente para false
     *
     * @param id identificador do {@code Cliente}
     * @throws ClienteNaoEncontradoException
     */
    @Override
    public void desativar(int id) throws ClienteNaoEncontradoException {
        Cliente cliente = this.consultarReferencia(id);
        cliente.setAtivo(false);
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

    @Override
    public boolean existe(int id) {
        try {
            this.consultar(id);
            return true;
        } catch (ClienteNaoEncontradoException e) {
            return false;
        }
    }

    @Override
    public boolean existe(String cpf) {
        try {
            this.consultar(cpf);
            return true;
        } catch (ClienteNaoEncontradoException e) {
            return false;
        }
    }

    /**
     * altera o id do cliente, o id que ele recebe é o maior até então acrescido
     * de 1
     *
     * @param cliente instancia a ter o id alterado
     */
    private void setarId(Cliente cliente) {
        if (this.clientes.isEmpty()) {
            cliente.setId(1);
        } else {
            cliente.setId(this.clientes
                    .stream()
                    .mapToInt(Cliente::getId)
                    .max()
                    .getAsInt() + 1);
        }
    }
}
