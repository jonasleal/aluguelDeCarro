package br.ufrpe.aluguelDeCarro.model.repositorios;

import br.ufrpe.aluguelDeCarro.model.Atendente;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class AtendenteRepositorio {

    private ArrayList<Atendente> atendentes;

    public AtendenteRepositorio() {
        this.atendentes = new ArrayList<>();
    }

    private Atendente buscarPorId(int id) {
        return this.atendentes
                .stream()
                .filter(atendente -> atendente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void cadastrar(Atendente atendente) {
        this.setarId(atendente);
        atendente.setAtivo(true);
        this.atendentes.add(atendente);
    }

    public void alterar(Atendente atendenteEditado) {
        this.atendentes
                .stream()
                .filter(atendente -> atendente.equals(atendenteEditado))
                .forEach(atendente -> atendente = atendenteEditado);
    }

    public void deletar(int id) {
        Atendente atendente = this.buscarPorId(id);
        if (atendente != null)
            atendente.setAtivo(false);
    }

    public ArrayList<Atendente> buscarTodos() {
        return (ArrayList<Atendente>) this.atendentes
                .stream()
                .filter(Atendente::isAtivo)
                .collect(Collectors.toList());
    }

    private void setarId(Atendente atendente) {
        if (this.atendentes.isEmpty())
            atendente.setId(0);
        else
            atendente.setId(this.atendentes
                    .stream()
                    .mapToInt(Atendente::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
