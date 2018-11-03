package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;

import java.util.*;
import java.util.stream.Collectors;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.AluguelRepositorioInterface;

/**
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

    private Aluguel buscarReferenciaPorId(int id) {
        return this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .filter(aluguel -> aluguel.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean cadastrar(Aluguel aluguel) {
        this.setarId(aluguel);
        return this.alugueis.add(aluguel.clone());
    }

    @Override
    public boolean alterar(Aluguel aluguelEditado) {
        int indexOf = this.alugueis.indexOf(aluguelEditado);
        if (indexOf != -1) {
            this.alugueis.set(indexOf, aluguelEditado.clone());
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        Aluguel aluguel = this.buscarReferenciaPorId(id);
        if (aluguel != null) {
            aluguel.setAtivo(false);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Aluguel> buscarTodos() {
        return (ArrayList<Aluguel>) this.alugueis
                .stream()
                .filter(Aluguel::isAtivo)
                .map(Aluguel::clone)
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
