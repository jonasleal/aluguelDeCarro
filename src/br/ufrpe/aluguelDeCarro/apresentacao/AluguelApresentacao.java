package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.servicos.DataUtil;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe de interação com o usuário, para que o mesmo possar gerenciar o aluguel
 *
 * @author Fernando
 */
class AluguelApresentacao {

    /**
     * solicita ao usuário os dados do aluguel
     *
     * @return uma instância de {@code Aluguel} com os dados preenchidos pelo usuário
     */
    Aluguel lerDadosPeloTeclado() {
        Aluguel aluguel = null;
        try {
            aluguel = new Aluguel();
            System.out.println("Informe a data de devolucao (siga o modelo dd-MM-yyyy HH:mm):");
            aluguel.setDevolucaoEstimada(DataUtil.transformarStringEmDataTime(InputUtil.getScan().nextLine()));
            System.out.println("Informe o carro\n" + getCarros());
//            aluguel.setCarro(FachadaGerente.getInstance().consultarCarro(InputUtil.solicitarNumeroInteiro()));
            System.out.println("Informe o cliente\n" + getClientes());
//            aluguel.setCliente(FachadaGerente.getInstance().consultarCliente(InputUtil.solicitarNumeroInteiro()));
            aluguel.setUsuario(FachadaGerente.getInstance().getUsuarioLogado());
            aluguel.setRetirada(LocalDateTime.now().plusHours(1));
            aluguel.calcularValorEstimado();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lerDadosPeloTeclado();
        }
        return aluguel;
    }

    /**
     * @return todos os carros cadastrados no sistema
     */
    private String getCarros() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Carro> carros = FachadaGerente.getInstance().consultarCarros();
        if (carros != null && !carros.isEmpty()) {
            carros.forEach(carro -> stringBuilder.append(carro.getId()).append(" - ").append(carro));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    /**
     * @return todos os clientes cadastrados no sistema
     */
    private String getClientes() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Cliente> clientes = FachadaGerente.getInstance().consultarClientes();
        if (clientes != null && !clientes.isEmpty()) {
            clientes.forEach(cliente -> stringBuilder.append(cliente.getId()).append(" - ").append(cliente));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    /**
     * mostra ao usuário os alugueis cadastrados
     */
    void visualizarAlugueis() {
        FachadaGerente.getInstance().consultarAlugueis().forEach(System.out::println);
    }
}
