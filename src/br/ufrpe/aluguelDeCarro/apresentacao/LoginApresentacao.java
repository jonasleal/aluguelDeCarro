package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.dados.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.servicos.Criptografia;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;
import br.ufrpe.aluguelDeCarro.servicos.Singleton;

/**
 * @author Fernando
 */
public class LoginApresentacao {
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
