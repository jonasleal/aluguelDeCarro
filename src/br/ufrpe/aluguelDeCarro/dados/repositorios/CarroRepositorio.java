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
                .filter(carro -> carro.getId() == id)
                .findFirst()
                .orElse(null);
    }
    @Override
    public Carro buscarPorPlaca(String placa) {
        return this.carros
                .stream()
                .filter(carro -> carro.getPlaca().equals(placa))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void cadastrar(Carro carro) {
        this.setarId(carro);
        carro.setAtivo(true);
        carro.setDisponivel(true);
        this.carros.add(carro);
    }

    @Override
    public void alterar(Carro carroEditado) {
        this.carros
                .stream()
                .filter(carro -> carro.equals(carroEditado))
                .forEach(carro -> carro = carroEditado);
    }

    @Override
    public void deletar(int id) {
        Carro carro = this.buscarPorId(id);
        if (carro != null)
            carro.setAtivo(false);
    }

    @Override
    public ArrayList<Carro> buscarTodos() {
        return (ArrayList<Carro>) this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .collect(Collectors.toList());
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
