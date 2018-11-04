package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.servicos.Criptografia;
import br.ufrpe.aluguelDeCarro.servicos.DataUtil;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

/**
 * Classe de interação com o usuário, para que o mesmo possar gerenciar o gerente
 * @author Fernando
 */
public class GerenteApresentacao {

    /**
     * solicita ao usuário os dados do gerente
     * @return uma instância de {@code Gerente} com os dados preenchidos pelo usuário
     */
    public Gerente lerDadosPeloTeclado() {
        Gerente gerente = null;
        try {
            gerente = new Gerente();
            System.out.println("Informe o nome do gerente:");
            gerente.setNome(InputUtil.getScan().next());
            System.out.println("Informe o cpf do gerente:");
            gerente.setCpf(InputUtil.getScan().next());
            System.out.println("Informe a data de nascimento do gerente(siga o modelo dd-MM-yyyy):");
            gerente.setNascimento(DataUtil.transformarStringEmData(InputUtil.getScan().next()));
            System.out.println("Informe a senha de acesso do gerente:");
            gerente.setSenha(Criptografia.criptografarSenha(InputUtil.getScan().next()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lerDadosPeloTeclado();
        }
        return gerente;
    }
}
