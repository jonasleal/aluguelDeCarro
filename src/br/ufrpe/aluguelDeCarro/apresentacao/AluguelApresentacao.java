package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.servicos.DataUtil;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * @author Fernando
 */
public class AluguelApresentacao {
    public Aluguel lerDadosPeloTeclado(){
        Aluguel aluguel = new Aluguel();
        System.out.println("Informe a data de devolucao (siga o modelo dd-MM-yyyy HH:mm):");
        InputUtil.getScan().nextLine();
        aluguel.setDevolucaoEstimada(DataUtil.transformarStringEmDataTime(InputUtil.getScan().nextLine()));
        System.out.println("Informe o carro\n"+getCarros());
        aluguel.setCarro(Singleton.getInstance().getCarroNegocio().buscarPorId(InputUtil.getScan().nextInt()));
        System.out.println("Informe o cliente\n"+getClientes());
        aluguel.setCliente(Singleton.getInstance().getClienteNegocio().buscarPorId(InputUtil.getScan().nextInt()));
        aluguel.setUsuario(Singleton.getInstance().getUsuarioLogado());
        aluguel.setRetirada(LocalDateTime.now());
        aluguel.calcularValorEstimado();
        return aluguel;
    }

    private String getCarros() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Carro> carros = Singleton.getInstance().getCarroNegocio().buscarTodos();
        if (carros != null && !carros.isEmpty()) {
            carros.forEach(carro -> stringBuilder.append(carro.getId()).append(" - ").append(carro));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    private String getClientes() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Cliente> clientes = Singleton.getInstance().getClienteNegocio().buscarTodos();
        if (clientes != null && !clientes.isEmpty()) {
            clientes.forEach(cliente -> stringBuilder.append(cliente.getId()).append(" - ").append(cliente));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public void visualizarAlugueis(){
        Singleton.getInstance().getAluguelNegocio().buscarTodos().forEach(System.out::println);
    }
}
