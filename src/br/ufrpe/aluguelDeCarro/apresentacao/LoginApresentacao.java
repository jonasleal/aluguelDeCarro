package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.IUsuario;
import br.ufrpe.aluguelDeCarro.servicos.Criptografia;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;

/**
 * Classe de interação com o usuário, para que o mesmo possar efetuar o login
 *
 * @author Fernando
 */
class LoginApresentacao {

    /**
     * solicita ao usuário os dados de login
     *
     * @return uma instância de {@code IUsuario} com os dados preenchidos pelo usuário
     */
    IUsuario lerDadosPeloTeclado() {
        IUsuario usuario;
        try {
            System.out.println("Informe o numero do cpf");
            String cpf = InputUtil.getScan().nextLine();
            usuario = Singleton.getInstance().getGerenteNegocio().consultar(cpf);
            if (usuario != null) {
                System.out.println("Informe a senha");
                if (usuario.validarSenha(Criptografia.criptografarSenha(InputUtil.getScan().nextLine())))
                    return usuario;
            }
            System.out.println("Nao encontrado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lerDadosPeloTeclado();
        }
        return null;
    }
}
