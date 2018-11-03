package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Atendente;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class AtendenteApresentacao {

    public Atendente cadastrarPeloTeclado() {
        Atendente atendente = new Atendente();
        try {
            System.out.println("Informe o nome do atendente:");
            atendente.setNome(InputUtil.getScan().next());
            System.out.println("Informe o cpf do atendente:");
            atendente.setCpf(InputUtil.getScan().next());
            System.out.println("Informe a data de nascimento do atendente(siga o modelo YYYY-MM-DD):");
            atendente.setNascimento(LocalDate.parse(InputUtil.getScan().next()));
            System.out.println("Informe a senha de acesso do atendente:");
            atendente.setSenha(InputUtil.getScan().next());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atendente;
    }
}