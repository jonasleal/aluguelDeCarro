package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IGerenteRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.GerenteNaoEncontradoException;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static br.ufrpe.aluguelDeCarro.excecoes.GerenteNaoEncontradoException.CPF;
import static br.ufrpe.aluguelDeCarro.excecoes.GerenteNaoEncontradoException.ID;

/**
 * A classe armazena uma lista de instancias de gerentes
 *
 * @author Fernando
 */
public class GerenteRepositorio implements IGerenteRepositorio {

    private final ArrayList<Gerente> gerentes;

    public GerenteRepositorio() {
        this.gerentes = new ArrayList<>();
    }

    /**
     * busca o gerente pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Gerente}
     * @return um clone do {@code Gerente} ativo que contém o id, {@code null} caso nao encontre
     */
    @Override
    public Gerente consultar(int id) throws GerenteNaoEncontradoException {
        return this.gerentes
                .stream()
                .filter(Gerente::isAtivo)
                .filter(gerente -> gerente.getId() == id)
                .findFirst()
                .map(Gerente::clone)
                .orElseThrow(() -> new GerenteNaoEncontradoException(ID));
    }

    /**
     * busca o gerente pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Gerente}
     * @return o {@code Gerente} ativo que contém o id, {@code null} caso nao encontre
     */
    private Gerente consultarReferencia(int id) throws GerenteNaoEncontradoException {
        return this.gerentes
                .stream()
                .filter(Gerente::isAtivo)
                .filter(gerente -> gerente.getId() == id)
                .findFirst()
                .orElseThrow(() -> new GerenteNaoEncontradoException(ID));
    }

    /**
     * busca o gerente pelo cpf, nos já cadastrados
     *
     * @param cpf identificador do {@code Gerente}
     * @return um clone do {@code Gerente} ativo que contém o cpf, {@code null} caso nao encontre
     */
    @Override
    public Gerente consultar(String cpf) throws GerenteNaoEncontradoException {
        return this.gerentes
                .stream()
                .filter(gerente -> gerente.getCpf().equals(cpf))
                .filter(Gerente::isAtivo)
                .findFirst()
                .map(Gerente::clone)
                .orElseThrow(() -> new GerenteNaoEncontradoException(CPF));
    }

    /**
     * @param gerente instancia a ser cadastrada
     * @return {@code true} caso cadastre com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean cadastrar(Gerente gerente) {
        this.setarId(gerente);
        return this.gerentes.add(gerente.clone());
    }

    /**
     * @param gerenteEditado instancia a ser editada
     * @return {@code true} caso altere com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean alterar(Gerente gerenteEditado) {
        int indexOf = this.gerentes.indexOf(gerenteEditado);
        if (indexOf != -1) {
            this.gerentes.set(indexOf, gerenteEditado.clone());
            return true;
        }
        return false;
    }

    /**
     * altera o atributo {@code ativo} do gerente para false
     *
     * @param id identificador do {@code Gerente}
     * @return {@code true} caso desative com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean desativar(int id) throws GerenteNaoEncontradoException {
        Gerente gerente = this.consultarReferencia(id);
        gerente.setAtivo(false);
        return true;
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Gerente> consultarTodos() {
        return (ArrayList<Gerente>) this.gerentes
                .stream()
                .filter(Gerente::isAtivo)
                .map(Gerente::clone)
                .collect(Collectors.toList());
    }

    /**
     * altera o id do gerente, o id que ele recebe é o maior até então acrescido de 1
     *
     * @param gerente instancia a ter o id alterado
     */
    private void setarId(Gerente gerente) {
        if (this.gerentes.isEmpty())
            gerente.setId(1);
        else
            gerente.setId(this.gerentes
                    .stream()
                    .mapToInt(Gerente::getId)
                    .max()
                    .getAsInt() + 1);
    }


}
