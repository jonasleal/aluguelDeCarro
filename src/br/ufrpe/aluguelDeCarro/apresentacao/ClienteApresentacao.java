package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class ClienteApresentacao {

    public ClienteApresentacao() {
    }

    public Cliente escanearDadosDoTeclado(){
        Cliente cliente = novo();
        try {
            System.out.println("Informe o nome do cliente:");
            cliente.setNome(InputUtil.getScan().next());
            System.out.println("Informe o cpf do cliente:");
            cliente.setCpf(InputUtil.getScan().next());
            System.out.println("Informe a data de nascimento do cliente(siga o modelo YYYY-MM-DD):");
            cliente.setNascimento(LocalDate.parse(InputUtil.getScan().next()));
            System.out.println("Informe o número da hibilitacao do cliente:");
            cliente.setHabilitacao(InputUtil.getScan().next());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return cliente;
    }

    public static void main(String[] args) {
        ClienteApresentacao cp =  new ClienteApresentacao();
        Cliente c = cp.escanearDadosDoTeclado();
        System.out.println(c.getNascimento());
    }

    private Cliente novo() {
        return new Cliente();
    }
}
