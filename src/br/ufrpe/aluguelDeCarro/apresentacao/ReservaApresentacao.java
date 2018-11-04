package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.dados.entidades.Reserva;
import br.ufrpe.aluguelDeCarro.dados.repositorios.CarroRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.ClienteRepositorio;
import br.ufrpe.aluguelDeCarro.servicos.DataUtil;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;

import java.util.ArrayList;

/**
 * @author Fernando
 */
public class ReservaApresentacao {

    /**
     * solicita ao usuário os dados da reserva
     * @return uma instância de {@code Reserva} com os dados preenchidos pelo usuário
     */
    public Reserva lerDadosPeloTeclado() {
        Reserva reserva = new Reserva();
        try {
            System.out.println("Informe a data para retirada do veiculo (siga o modelo dd-MM-yyyy HH:mm):");
            reserva.setRetiradaPrevista(DataUtil.transformarStringEmDataTime(InputUtil.getScan().nextLine()));
            System.out.println("Selecione um carro para fazer a reserva\n" + getCarros());
            reserva.setCarro(new CarroRepositorio().buscarTodos().get(InputUtil.getScan().nextInt()));
            System.out.println("Selecione o cliente\n"+ getClientes());
            reserva.setCliente(new ClienteRepositorio().buscarTodos().get(InputUtil.getScan().nextInt()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lerDadosPeloTeclado();
        }
        return reserva;
    }

    /**
     * @return todos os carros cadastrados no sistema
     */
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

    /**
     * @return todos os clientes cadastrados no sistema
     */
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
}
