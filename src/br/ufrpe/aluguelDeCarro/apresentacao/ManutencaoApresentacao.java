package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Manutencao;
import br.ufrpe.aluguelDeCarro.dados.repositorios.memoria.CarroRepositorio;
import br.ufrpe.aluguelDeCarro.servicos.DataUtil;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author Fernando
 */
class ManutencaoApresentacao {

    /**
     * solicita ao usuário os dados da manutencao
     *
     * @return uma instância de {@code Manutencao} com os dados preenchidos pelo usuário
     */
    Manutencao lerDadosPeloTeclado() {
        Manutencao manutencao = null;
        try {
            manutencao = new Manutencao();
            manutencao.setAfazeres(lerAfazeresDoTeclado());
            System.out.println("Informe a data da manutencao (siga o modelo dd-MM-yyyy):");
            manutencao.setData(DataUtil.transformarStringEmData(InputUtil.getScan().nextLine()));
            System.out.println("Informe o orcamento da manutencao");
            manutencao.setOrcamento(new BigDecimal(InputUtil.solicitarNumeroFlutuante()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lerAfazeresDoTeclado();
        }
        return manutencao;
    }

    private ArrayList<String> lerAfazeresDoTeclado() {
        ArrayList<String> afazeres = new ArrayList<>();
        try {
            System.out.println("Informe uma tarefa");
            afazeres.add(InputUtil.getScan().nextLine());
            int opcao;
            do {
                System.out.println("0 - Continuar o cadastro da manutencao\n1 - Adicionar outra tarefa");
                opcao = InputUtil.solicitarNumeroInteiro();
                if (opcao == 1) {
                    System.out.println("Informe uma tarefa");
                    afazeres.add(InputUtil.getScan().nextLine());
                }
            } while (opcao != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lerAfazeresDoTeclado();
        }
        return afazeres;
    }

    private String getCarros() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Carro> carros = new CarroRepositorio().consultarTodos();
        if (carros != null && !carros.isEmpty()) {
            carros.forEach(carro -> stringBuilder.append(carro.getId()).append(" - ").append(carro));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
