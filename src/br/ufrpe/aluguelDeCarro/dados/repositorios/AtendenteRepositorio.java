package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Atendente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.AtendenteRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class AtendenteRepositorio implements AtendenteRepositorioInterface {

    private ArrayList<Atendente> atendentes;

    public AtendenteRepositorio() {
        this.atendentes = new ArrayList<>();
    }

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

    private Atendente buscarReferenciaPorId(int id) {
        return this.atendentes
                .stream()
                .filter(Atendente::isAtivo)
                .filter(atendente -> atendente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean cadastrar(Atendente atendente) {
        this.setarId(atendente);
        return this.atendentes.add(atendente.clone());
    }

    @Override
    public boolean alterar(Atendente atendenteEditado) {
        int indexOf = this.atendentes.indexOf(atendenteEditado);
        if (indexOf != -1) {
            this.atendentes.set(indexOf, atendenteEditado.clone());
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        Atendente atendente = this.buscarReferenciaPorId(id);
        if (atendente != null) {
            atendente.setAtivo(false);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Atendente> buscarTodos() {
        return (ArrayList<Atendente>) this.atendentes
                .stream()
                .filter(Atendente::isAtivo)
                .map(Atendente::clone)
                .collect(Collectors.toList());
    }

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
