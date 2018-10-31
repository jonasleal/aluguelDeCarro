package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class GerenteApresentacao {
    public Gerente cadastrarPeloTeclado() {
        Gerente gerente = new Gerente();
        try {
            System.out.println("Informe o nome do gerente:");
            gerente.setNome(InputUtil.getScan().next());
            System.out.println("Informe o cpf do gerente:");
            gerente.setCpf(InputUtil.getScan().next());
            System.out.println("Informe a data de nascimento do gerente(siga o modelo YYYY-MM-DD):");
            gerente.setNascimento(LocalDate.parse(InputUtil.getScan().next()));
            System.out.println("Informe a senha de acesso do gerente:");
            gerente.setSenha(InputUtil.getScan().next());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gerente;
    }
}
