package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cambio;
import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Categoria;
import br.ufrpe.aluguelDeCarro.dados.entidades.Direcao;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;

import java.math.BigDecimal;

/**
 * Classe de interação com o usuário, para que o mesmo possar gerenciar o carro
 *
 * @author Fernando
 */
class CarroApresentacao {

    /**
     * solicita ao usuário os dados do carro
     *
     * @return uma instância de {@code Carro} com os dados preenchidos pelo usuário
     */
    public Carro lerDadosPeloTeclado() {
        Carro carro = null;
        try {
            carro = new Carro();
            System.out.println("Informe a placa do carro");
            carro.setPlaca(InputUtil.getScan().nextLine());
            System.out.println("Informe a marca do carro");
            carro.setMarca(InputUtil.getScan().nextLine());
            System.out.println("Informe o modelo do carro");
            carro.setModelo(InputUtil.getScan().nextLine());
            System.out.println("Informe a quantidade de portas do carro");
            carro.setPortas(InputUtil.solicitarNumeroInteiro());
            System.out.println("Informe a capacidade de passageiros do carro");
            carro.setOcupantes(InputUtil.solicitarNumeroInteiro());
            System.out.println("Informe o valor da diaria do carro");
            carro.setPreco(new BigDecimal(InputUtil.solicitarNumeroFlutuante()));
            System.out.println("Informe a categoria do carro\n" + getCategorias());
            carro.setCategoria(Categoria.values()[InputUtil.solicitarNumeroInteiro() - 1]);
            System.out.println("Informe o tipo do cambio carro\n" + getCambios());
            carro.setCambio(Cambio.values()[InputUtil.solicitarNumeroInteiro() - 1]);
            System.out.println("Informe o tipo de direcao do carro\n" + getDirecoes());
            carro.setDirecao(Direcao.values()[InputUtil.solicitarNumeroInteiro() - 1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lerDadosPeloTeclado();
        }
        return carro;
    }

    private String getCategorias() {
        Categoria[] categorias = Categoria.values();
        StringBuilder stringBuilder = new StringBuilder();
        for (Categoria categoria : categorias)
            stringBuilder.append(categoria.getValor()).append(" - ").append(categoria.getNome()).append("\n");
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private String getCambios() {
        Cambio[] cambios = Cambio.values();
        StringBuilder stringBuilder = new StringBuilder();
        for (Cambio cambio : cambios)
            stringBuilder.append(cambio.getValor()).append(" - ").append(cambio.getNome()).append("\n");
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private String getDirecoes() {
        Direcao[] direcoes = Direcao.values();
        StringBuilder stringBuilder = new StringBuilder();
        for (Direcao direcao : direcoes)
            stringBuilder.append(direcao.getValor()).append(" - ").append(direcao.getNome()).append("\n");
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    void visualizarCarros() {
        Singleton.getInstance().getCarroNegocio().consultarTodos().forEach(System.out::println);

    }
}
