package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

import java.util.ArrayList;

/**
 * @author Fernando
 */
public class CarroApresentacao {
    private Carro carro;
    private ArrayList<Carro> carros;

    public Carro lerDadosDoTeclado() {
        carro = novo();
        try {
            System.out.println("Informe a placa do carro");
            carro.setPlaca(InputUtil.getScan().next());
            System.out.println("Informe a quantidade de portas do carro");
            carro.setPortas(InputUtil.getScan().nextInt());
            System.out.println("Informe a capacidade de passageiros do carro");
            carro.setOcupantes(InputUtil.getScan().nextInt());
            System.out.println("Informe o tipo do carro\n" + getTipos());
            carro.setCategoria(Categoria.values()[InputUtil.getScan().nextInt()]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carro;
    }

    private String getTipos() {
        Categoria[] tiposCarro = Categoria.values();
        StringBuilder tipos = new StringBuilder();
        for (Categoria tipo : tiposCarro)
            tipos.append(tipo.getValor()).append(" - ").append(tipo.getNome()).append("\n");
        return tipos.deleteCharAt(tipos.length()-1).toString();
    }

    private Carro novo() {
        return new Carro();
    }

    public static void main(String[] args) {
        CarroApresentacao ca = new CarroApresentacao();
        Carro c = ca.lerDadosDoTeclado();
        System.out.println(c.getCategoria().getNome());
    }
}
