package br.ufrpe.aluguelDeCarro.dados.repositorios.memoria;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IAluguelRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.aluguel.AluguelNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A classe armazena uma lista de instancias de alugueis
 *
 * @author Fernando
 */
public class AluguelRepositorio implements IAluguelRepositorio {

    private final ArrayList<Aluguel> alugueis;

    public AluguelRepositorio() {
        this.alugueis = new ArrayList<>();
    }

    /**
     * consulta o aluguel pelo id
     *
     * @param id identificador do {@code aluguel}
     * @return um clone do {@code aluguel} ativo que contém o id, {@code null}
     * caso nao encontre
     */
    @Override
    public Aluguel consultar(int id) throws AluguelNaoEncontradoException, IdNaoEncontradoException {
        return this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .filter(aluguel -> aluguel.getId() == id)
                .findFirst()
                .map(Aluguel::clone)
                .orElseThrow(() -> new IdNaoEncontradoException(id));
    }

    /**
     * consulta o aluguel pelo cliente
     *
     * @param cliente cliente que realizou o aluguel
     * @return {@code aluguel} ativo e não finalizado que contém o cliente
     * {@code null} caso nao encontre
     */
    @Override
    public Aluguel consultar(Cliente cliente) throws AluguelNaoEncontradoException {
        return this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .filter(aluguel -> aluguel.getDevolucaoReal() == null)
                .filter(aluguel -> aluguel.getCliente().getCpf().equals(cliente.getCpf()))
                .findFirst()
                .map(Aluguel::clone)
                .orElseThrow(() -> new AluguelNaoEncontradoException(new ClienteNaoEncontradoException()));
    }

    /**
     * consulta o aluguel pelo carro
     *
     * @param carro carro contido no aluguel
     * @return aluguel ativo e não finalizado que contém o carro, null caso não
     * encontre
     */
    @Override
    public Aluguel consultar(Carro carro) throws AluguelNaoEncontradoException {
        return this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .filter(aluguel -> aluguel.getDevolucaoReal() == null)
                .filter(aluguel -> aluguel.getCarro().getPlaca().equals(carro.getPlaca()))
                .findFirst()
                .map(Aluguel::clone)
                .orElseThrow(() -> new AluguelNaoEncontradoException(carro));
    }

    /**
     * consulta o aluguel pelo id
     *
     * @param id identificador do {@code aluguel}
     * @return o {@code aluguel} ativo que contém o id, {@code null} caso nao
     * encontre
     */
    private Aluguel consultarReferencia(int id) throws AluguelNaoEncontradoException, IdNaoEncontradoException {
        return this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .filter(aluguel -> aluguel.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IdNaoEncontradoException(id));
    }

    /**
     * @param aluguel instancia a ser cadastrada
     * @return {@code true} caso cadastre com sucesso, {@code false} caso
     * contrário
     */
    @Override
    public boolean cadastrar(Aluguel aluguel) {
        this.setarId(aluguel);
        return this.alugueis.add(aluguel.clone());
    }

    /**
     * @param aluguelEditado instancia a ser editada
     * @return {@code true} caso altere com sucesso, {@code false} caso
     * contrário
     */
    @Override
    public boolean alterar(Aluguel aluguelEditado) {
        int indexOf = this.alugueis.indexOf(aluguelEditado);
        if (indexOf != -1) {
            this.alugueis.set(indexOf, aluguelEditado.clone());
            return true;
        }
        return false;
    }

    /**
     * altera o atributo {@code ativo} do aluguel para false
     *
     * @param id identificador do {@code aluguel}
     * @return {@code true} caso desative com sucesso, {@code false} caso
     * contrário
     */
    @Override
    public boolean desativar(int id) throws AluguelNaoEncontradoException, IdNaoEncontradoException {
        Aluguel aluguel = this.consultarReferencia(id);
        aluguel.setAtivo(false);
        return true;
    }

    /**
     * @return clones dos alugueis ativos
     */
    @Override
    public ArrayList<Aluguel> consultarTodos() {
        return (ArrayList<Aluguel>) this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .map(Aluguel::clone)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existe(int id) throws IdNaoEncontradoException {
        try {
            this.consultar(id);
            return true;
        } catch (AluguelNaoEncontradoException e) {
            return false;
        }
    }

    @Override
    public boolean existe(Cliente cliente) {
        try {
            this.consultar(cliente);
            return true;
        } catch (AluguelNaoEncontradoException e) {
            return false;
        }
    }

    @Override
    public boolean existe(Carro carro) {
        try {
            this.consultar(carro);
            return true;
        } catch (AluguelNaoEncontradoException e) {
            return false;
        }
    }

    /**
     * altera o id do aluguel, o id que ele recebe é o maior até então acrescido
     * de 1
     *
     * @param aluguel instancia a ter o id alterado
     */
    private void setarId(Aluguel aluguel) {
        if (this.alugueis.isEmpty()) {
            aluguel.setId(1);
        } else {
            aluguel.setId(this.alugueis
                    .stream()
                    .mapToInt(Aluguel::getId)
                    .max()
                    .getAsInt() + 1);
        }
    }
}