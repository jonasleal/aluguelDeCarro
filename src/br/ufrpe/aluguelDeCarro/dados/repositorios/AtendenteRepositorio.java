package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Atendente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.AtendenteRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A classe armazena uma lista de instancias de atendentes
 *
 * @author Fernando
 */
public class AtendenteRepositorio implements AtendenteRepositorioInterface {

    private final ArrayList<Atendente> atendentes;

    public AtendenteRepositorio() {
        this.atendentes = new ArrayList<>();
    }

    /**
     * busca o atendente pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Atendente}
     * @return um clone do {@code Atendente} ativo que contém o id, {@code null} caso nao encontre
     */
    @Override
    public Atendente buscarPorId(int id) {
        return this.atendentes
                .stream()
                .filter(Atendente::isAtivo)
                .filter(atendente -> atendente.getId() == id)
                .findFirst()
                .map(Atendente::clone)
                .orElse(null);
    }

    /**
     * busca o atendente pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Atendente}
     * @return o {@code Atendente} ativo que contém o id, {@code null} caso nao encontre
     */
    private Atendente buscarReferenciaPorId(int id) {
        return this.atendentes
                .stream()
                .filter(Atendente::isAtivo)
                .filter(atendente -> atendente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * @param atendente instancia a ser cadastrada
     * @return {@code true} caso cadastre com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean cadastrar(Atendente atendente) {
        this.setarId(atendente);
        return this.atendentes.add(atendente.clone());
    }

    /**
     * @param atendenteEditado instancia a ser editada
     * @return {@code true} caso altere com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean alterar(Atendente atendenteEditado) {
        int indexOf = this.atendentes.indexOf(atendenteEditado);
        if (indexOf != -1) {
            this.atendentes.set(indexOf, atendenteEditado.clone());
            return true;
        }
        return false;
    }

    /**
     * altera o atributo {@code ativo} do atendente para false
     *
     * @param id identificador do {@code Atendente}
     * @return {@code true} caso desative com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean desativar(int id) {
        Atendente atendente = this.buscarReferenciaPorId(id);
        if (atendente != null) {
            atendente.setAtivo(false);
            return true;
        }
        return false;
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Atendente> buscarTodos() {
        return (ArrayList<Atendente>) this.atendentes
                .stream()
                .filter(Atendente::isAtivo)
                .map(Atendente::clone)
                .collect(Collectors.toList());
    }

    /**
     * altera o id do atendente, o id que ele recebe é o maior até então acrescido de 1
     *
     * @param atendente instancia a ter o id alterado
     */
    private void setarId(Atendente atendente) {
        if (this.atendentes.isEmpty())
            atendente.setId(1);
        else
            atendente.setId(this.atendentes
                    .stream()
                    .mapToInt(Atendente::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
