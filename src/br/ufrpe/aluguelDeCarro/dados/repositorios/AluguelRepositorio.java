package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;

import java.util.*;
import java.util.stream.Collectors;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.AluguelRepositorioInterface;

/**
 *
 * @author Fernando
 */
public class AluguelRepositorio implements AluguelRepositorioInterface {

    private final ArrayList<Aluguel> alugueis;

    public AluguelRepositorio() {
        this.alugueis = new ArrayList<>();
    }

    @Override
    public Aluguel buscarPorId(int id) {
        return this.alugueis
                .stream()
                .filter(aluguel -> aluguel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void cadastrar(Aluguel aluguel) {
        this.setarId(aluguel);
        aluguel.setAtivo(true);
        this.alugueis.add(aluguel);
    }

    @Override
    public void alterar(Aluguel aluguelEditado) {
        this.alugueis
                .stream()
                .filter(aluguel -> aluguel.equals(aluguelEditado))
                .forEach(aluguel -> aluguel = aluguelEditado);
    }

    @Override
    public void deletar(int id) {
        Aluguel aluguel = this.buscarPorId(id);
        if (aluguel != null) {
            aluguel.setAtivo(false);
        }
    }

    @Override
    public ArrayList<Aluguel> buscarTodos() {
        return (ArrayList<Aluguel>) this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .collect(Collectors.toList());
    }

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
