package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.CarroRepositorioInterface;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class CarroRepositorio implements CarroRepositorioInterface{

    private final ArrayList<Carro> carros;

    public CarroRepositorio() {
        this.carros = new ArrayList<>();
    }

    @Override
    public Carro buscarPorId(int id) {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getId() == id)
                .findFirst()
                .map(Carro::clone)
                .orElse(null);
    }

    @Override
    public Carro buscarPorPlaca(String placa) {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getPlaca().equals(placa))
                .findFirst()
                .map(Carro::clone)
                .orElse(null);
    }

    @Override
    public boolean cadastrar(Carro carro) {
        this.setarId(carro);
        return this.carros.add(carro.clone());
    }

    @Override
    public boolean alterar(Carro carroEditado) {
        int indexOf = this.carros.indexOf(carroEditado);
        if (indexOf != -1) {
            this.carros.set(indexOf, carroEditado.clone());
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        Carro carro = this.buscarReferenciaPorId(id);
        if (carro != null) {
            carro.setAtivo(false);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Carro> buscarTodos() {
        return (ArrayList<Carro>) this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .map(Carro::clone)
                .collect(Collectors.toList());
    }

    private Carro buscarReferenciaPorId(int id) {
        return this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .filter(carro -> carro.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void setarId(Carro carro) {
        if (this.carros.isEmpty())
            carro.setId(1);
        else
            carro.setId(this.carros
                    .stream()
                    .mapToInt(Carro::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
