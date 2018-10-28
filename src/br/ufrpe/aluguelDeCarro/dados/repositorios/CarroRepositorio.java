package br.ufrpe.aluguelDeCarro.dados.repositorios;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Fernando
 */
public class CarroRepositorio {

    private ArrayList<Carro> carros;

    public CarroRepositorio() {
        this.carros = new ArrayList<>();
    }

    private Carro buscarPorId(int id) {
        return this.carros
                .stream()
                .filter(carro -> carro.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void cadastrar(Carro carro) {
        this.setarId(carro);
        carro.setAtivo(true);
        this.carros.add(carro);
    }

    public void alterar(Carro carroEditado) {
        this.carros
                .stream()
                .filter(carro -> carro.equals(carroEditado))
                .forEach(carro -> carro = carroEditado);
    }

    public void deletar(int id) {
        Carro carro = this.buscarPorId(id);
        if (carro != null)
            carro.setAtivo(false);
    }

    public ArrayList<Carro> buscarTodos() {
        return (ArrayList<Carro>) this.carros
                .stream()
                .filter(Carro::isAtivo)
                .filter(Carro::isDisponivel)
                .collect(Collectors.toList());
    }

    private void setarId(Carro carro) {
        if (this.carros.isEmpty())
            carro.setId(0);
        else
            carro.setId(this.carros
                    .stream()
                    .mapToInt(Carro::getId)
                    .max()
                    .getAsInt() + 1);
    }
}
