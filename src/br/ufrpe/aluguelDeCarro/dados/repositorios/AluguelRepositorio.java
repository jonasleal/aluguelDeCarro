package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.AluguelRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A classe armazena uma lista de instancias de alugueis
 *
 * @author Fernando
 */
public class AluguelRepositorio implements AluguelRepositorioInterface {

    private final ArrayList<Aluguel> alugueis;

    public AluguelRepositorio() {
        this.alugueis = new ArrayList<>();
    }

    /**
     * busca o aluguel pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Aluguel}
     * @return um clone do {@code Aluguel} ativo que contém o id, {@code null} caso nao encontre
     */
    @Override
    public Aluguel buscarPorId(int id) {
        return this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .filter(aluguel -> aluguel.getId() == id)
                .findFirst()
                .map(Aluguel::clone)
                .orElse(null);
    }

    @Override
    public Aluguel buscarPorCpf(String cpf) {
        return this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .filter(aluguel -> aluguel.getDevolucaoReal() == null)
                .filter(aluguel -> aluguel.getCliente().getCpf().equals(cpf))
                .findFirst()
                .map(Aluguel::clone)
                .orElse(null);
    }

    @Override
    public Aluguel buscarPorPlaca(String placa) {
        return this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .filter(aluguel -> aluguel.getDevolucaoReal() == null)
                .filter(aluguel -> aluguel.getCarro().getPlaca().equals(placa))
                .findFirst()
                .map(Aluguel::clone)
                .orElse(null);
    }

    /**
     * busca o aluguel pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Aluguel}
     * @return o {@code Aluguel} ativo que contém o id, {@code null} caso nao encontre
     */
    private Aluguel buscarReferenciaPorId(int id) {
        return this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .filter(aluguel -> aluguel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * @param aluguel instancia a ser cadastrada
     * @return {@code true} caso cadastre com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean cadastrar(Aluguel aluguel) {
        this.setarId(aluguel);
        return this.alugueis.add(aluguel.clone());
    }

    /**
     * @param aluguelEditado instancia a ser editada
     * @return {@code true} caso altere com sucesso, {@code false} caso contrário
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
     * @param id identificador do {@code Aluguel}
     * @return {@code true} caso desative com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean desativar(int id) {
        Aluguel aluguel = this.buscarReferenciaPorId(id);
        if (aluguel != null) {
            aluguel.setAtivo(false);
            return true;
        }
        return false;
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Aluguel> buscarTodos() {
        return (ArrayList<Aluguel>) this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .map(Aluguel::clone)
                .collect(Collectors.toList());
    }

    /**
     * altera o id do aluguel, o id que ele recebe é o maior até então acrescido de 1
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
