package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.dados.entidades.Reserva;
import br.ufrpe.aluguelDeCarro.dados.repositorios.CarroRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.ClienteRepositorio;
import br.ufrpe.aluguelDeCarro.servicos.DataUtil;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

import java.util.ArrayList;

/**
 * @author Fernando
 */
public class ReservaApresentacao {
    public Reserva cadastrarPeloTeclado() {
        Reserva reserva = new Reserva();
        try {
            System.out.println("Informe a data para retirada do veiculo (siga o modelo YYYY-MM-DD):");
            reserva.setRetiradaPrevista(DataUtil.transformarStringEmData(InputUtil.getScan().next()));
            System.out.println("Selecione um carro para fazer a reserva\n" + getCarros());
            reserva.setCarro(new CarroRepositorio().buscarTodos().get(InputUtil.getScan().nextInt()));
            System.out.println("Selecione o cliente\n"+ getClientes());
            reserva.setCliente(new ClienteRepositorio().buscarTodos().get(InputUtil.getScan().nextInt()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reserva;
    }

    private String getCarros() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Carro> carros = new CarroRepositorio().buscarTodos();
        if (carros != null && !carros.isEmpty()) {
            carros.forEach(carro -> stringBuilder.append(carro.getId()).append(" - ").append(carro));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    private String getClientes() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Cliente> clientes = new ClienteRepositorio().buscarTodos();
        if (clientes != null && !clientes.isEmpty()) {
            clientes.forEach(cliente -> stringBuilder.append(cliente.getId()).append(" - ").append(cliente));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
