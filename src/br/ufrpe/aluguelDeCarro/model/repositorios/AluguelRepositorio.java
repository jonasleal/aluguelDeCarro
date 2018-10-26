package br.ufrpe.aluguelDeCarro.model.repositorios;

import br.ufrpe.aluguelDeCarro.model.Aluguel;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class AluguelRepositorio {
    private ArrayList<Aluguel> alugueis;

    public AluguelRepositorio() {
        this.alugueis = new ArrayList<>();
    }

    private Aluguel buscarPorId(int id) {
        return this.alugueis
                .stream()
                .filter(aluguel -> aluguel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void cadastrar(Aluguel aluguel) {
        this.setarId(aluguel);
        aluguel.setAtivo(true);
        this.alugueis.add(aluguel);
    }

    public void alterar(Aluguel aluguelEditado) {
        this.alugueis
                .stream()
                .filter(aluguel -> aluguel.equals(aluguelEditado))
                .forEach(aluguel -> aluguel = aluguelEditado);
    }

    public void deletar(int id) {
        Aluguel aluguel = this.buscarPorId(id);
        if (aluguel != null)
            aluguel.setAtivo(false);
    }

    public ArrayList<Aluguel> buscarTodos() {
        return (ArrayList<Aluguel>) this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .collect(Collectors.toList());
    }

    private void setarId(Aluguel aluguel) {
        if (this.alugueis.isEmpty())
            aluguel.setId(0);
        else
            aluguel.setId(this.alugueis
                    .stream()
                    .mapToInt(Aluguel::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
