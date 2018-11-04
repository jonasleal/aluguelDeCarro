package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.servicos.Criptografia;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;

/**
 * Classe de interação com o usuário, para que o mesmo possar efetuar o login
 * @author Fernando
 */
public class LoginApresentacao {

    /**
     * solicita ao usuário os dados de login
     * @return uma instância de {@code Usuario} com os dados preenchidos pelo usuário
     */
    public Usuario lerDadosPeloTeclado() {
        System.out.println("Informe o numero do cpf");
        String cpf = InputUtil.getScan().next();
        Usuario usuario = Singleton.getInstance().getGerenteNegocio().buscarPorCpf(cpf);
        if (usuario != null) {
            System.out.println("Informe a senha");
            if (usuario.validarSenha(Criptografia.criptografarSenha(InputUtil.getScan().next())))
                return usuario;
        }
        System.out.println("Nao encontrado");
        return null;
    }
}
