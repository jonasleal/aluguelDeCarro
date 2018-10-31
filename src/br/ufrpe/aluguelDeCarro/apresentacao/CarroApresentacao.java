package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

/**
 * @author Fernando
 */
public class CarroApresentacao {
    public Carro cadastrarPeloTeclado() {
        Carro carro = new Carro();
        try {
            System.out.println("Informe a placa do carro");
            carro.setPlaca(InputUtil.getScan().next());
            System.out.println("Informe a quantidade de portas do carro");
            carro.setPortas(InputUtil.getScan().nextInt());
            System.out.println("Informe a capacidade de passageiros do carro");
            carro.setOcupantes(InputUtil.getScan().nextInt());
            System.out.println("Informe o tipo do carro\n" + getCategorias());
            carro.setCategoria(Categoria.values()[InputUtil.getScan().nextInt() - 1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carro;
    }

    private String getCategorias() {
        Categoria[] tiposCarro = Categoria.values();
        StringBuilder tipos = new StringBuilder();
        for (Categoria tipo : tiposCarro)
            tipos.append(tipo.getValor()).append(" - ").append(tipo.getNome()).append("\n");
        tipos.deleteCharAt(tipos.length() - 1);
        tipos.deleteCharAt(tipos.length() - 1);
        return tipos.toString();
    }
}
