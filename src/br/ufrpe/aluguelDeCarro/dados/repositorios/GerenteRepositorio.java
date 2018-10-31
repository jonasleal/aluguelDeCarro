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
                .filter(gerente -> gerente.getId() == id)
                .findFirst()
                .orElse(null);
    }
    @Override
    public Gerente buscarPorCpf(String cpf) {
        return this.gerentes
                .stream()
                .filter(gerente -> gerente.getCpf()== cpf)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void cadastrar(Gerente gerente) {
        this.setarId(gerente);
        gerente.setAtivo(true);
        this.gerentes.add(gerente);
    }

    @Override
    public void alterar(Gerente gerenteEditado) {
        this.gerentes
                .stream()
                .filter(gerente -> gerente.equals(gerenteEditado))
                .forEach(gerente -> gerente = gerenteEditado);
    }

    @Override
    public void deletar(int id) {
        Gerente gerente = this.buscarPorId(id);
        if (gerente != null)
            gerente.setAtivo(false);
    }

    @Override
    public ArrayList<Gerente> buscarTodos() {
        return (ArrayList<Gerente>) this.gerentes
                .stream()
                .filter(Gerente::isAtivo)
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
