package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.servicos.DataUtil;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;

/**
 * @author Fernando
 */
public class ClienteApresentacao {

    public ClienteApresentacao() {
    }

    public Cliente lerDadosPeloTeclado() {
        Cliente cliente = new Cliente();
        try {
            System.out.println("Informe o nome do cliente:");
            cliente.setNome(InputUtil.getScan().next());
            System.out.println("Informe o cpf do cliente:");
            cliente.setCpf(InputUtil.getScan().next());
            System.out.println("Informe a data de nascimento do cliente (siga o modelo dd-MM-yyyy):");
            cliente.setNascimento(DataUtil.transformarStringEmData(InputUtil.getScan().next()));
            System.out.println("Informe o número da hibilitacao do cliente:");
            cliente.setHabilitacao(InputUtil.getScan().next());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }

    public void visualizarClientes() {
        Singleton.getInstance().getClienteNegocio().buscarTodos().forEach(System.out::println);
    }
}
