package br.ufrpe.aluguelDeCarro.apresentacao.console;

import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.CategoriaRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.ClienteRepositorio;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Reserva;
import br.ufrpe.aluguelDeCarro.servicos.DataUtil;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

import java.util.List;

/**
 * @author Fernando
 */
class ReservaApresentacao {

    /**
     * solicita ao usuário os dados da reserva
     *
     * @return uma instância de {@code Reserva} com os dados preenchidos pelo usuário
     */
    Reserva lerDadosPeloTeclado() {
        Reserva reserva = new Reserva();
        try {
            System.out.println("Informe a data para retirada do veiculo (siga o modelo dd-MM-yyyy HH:mm):");
            reserva.setRetiradaPrevista(DataUtil.transformarStringEmDataTime(InputUtil.getScan().nextLine()));
            System.out.println("Selecione um carro para fazer a reserva\n" + getCarros());
            reserva.setCategoria(new CategoriaRepositorio().consultarTodos().get(InputUtil.solicitarNumeroInteiro()));
            System.out.println("Selecione o cliente\n" + getClientes());
            reserva.setCliente(new ClienteRepositorio().consultarTodos().get(InputUtil.solicitarNumeroInteiro()));
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
}
