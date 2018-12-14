package br.ufrpe.aluguelDeCarro.apresentacao.console;

import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.servicos.DataUtil;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

/**
 * Classe de interação com o usuário, para que o mesmo possar gerenciar o carro
 *
 * @author Fernando
 */
class ClienteApresentacao {

    private FachadaGerente fachada = new FachadaGerente();

    /**
     * solicita ao usuário os dados do cliente
     *
     * @return uma instância de {@code Cliente} com os dados preenchidos pelo usuário
     */
    Cliente lerDadosPeloTeclado() {
        Cliente cliente = null;
        try {
            cliente = new Cliente();
            System.out.println("Informe o nome do cliente:");
            cliente.setNome(InputUtil.getScan().nextLine());
            System.out.println("Informe o cpf do cliente:");
            cliente.setCpf(InputUtil.getScan().nextLine());
            System.out.println("Informe a data de nascimento do cliente (siga o modelo dd-MM-yyyy):");
            cliente.setNascimento(DataUtil.transformarStringEmData(InputUtil.getScan().nextLine()));
            System.out.println("Informe o número da hibilitacao do cliente:");
            cliente.setHabilitacao(InputUtil.getScan().nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lerDadosPeloTeclado();
        }
        return cliente;
    }

    void visualizarClientes() {
        fachada.consultarClientes().forEach(System.out::println);
    }
}
