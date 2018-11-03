package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.GerenteRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class GerenteRepositorio implements GerenteRepositorioInterface{

    private ArrayList<Gerente> gerentes;

    public GerenteRepositorio() {
        this.gerentes = new ArrayList<>();
    }

    @Override
    public Gerente buscarPorId(int id) {
        return this.gerentes
                .stream()
                .filter(Gerente::isAtivo)
                .filter(gerente -> gerente.getId() == id)
                .findFirst()
                .map(Gerente::clone)
                .orElse(null);
    }

    private Gerente buscarReferenciaPorId(int id) {
        return this.gerentes
                .stream()
                .filter(Gerente::isAtivo)
                .filter(gerente -> gerente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Gerente buscarPorCpf(String cpf) {
        return this.gerentes
                .stream()
                .filter(gerente -> gerente.getCpf().equals(cpf))
                .filter(Gerente::isAtivo)
                .findFirst()
                .map(Gerente::clone)
                .orElse(null);
    }

    @Override
    public boolean cadastrar(Gerente gerente) {
        this.setarId(gerente);
        return this.gerentes.add(gerente.clone());
    }

    @Override
    public boolean alterar(Gerente gerenteEditado) {
        int indexOf = this.gerentes.indexOf(gerenteEditado);
        if (indexOf != -1) {
            this.gerentes.set(indexOf, gerenteEditado.clone());
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        Gerente gerente = this.buscarReferenciaPorId(id);
        if (gerente != null){
            gerente.setAtivo(false);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Gerente> buscarTodos() {
        return (ArrayList<Gerente>) this.gerentes
                .stream()
                .filter(Gerente::isAtivo)
                .map(Gerente::clone)
                .collect(Collectors.toList());
    }

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
