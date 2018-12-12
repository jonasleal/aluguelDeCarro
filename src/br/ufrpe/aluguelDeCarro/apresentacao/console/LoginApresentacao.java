package br.ufrpe.aluguelDeCarro.apresentacao.console;

import br.ufrpe.aluguelDeCarro.excecoes.UsuarioNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.fachada.FachadaGerente;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.servicos.Criptografia;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

/**
 * Classe de interação com o usuário, para que o mesmo possar efetuar o login
 *
 * @author Fernando
 */
class LoginApresentacao {

    /**
     * solicita ao usuário os dados de login
     *
     * @return uma instância de {@code Usuario} com os dados preenchidos pelo usuário
     */
    Usuario lerDadosPeloTeclado() {
        Usuario usuario;
        try {
            System.out.println("Informe o numero do cpf");
            String cpf = InputUtil.getScan().nextLine();
            usuario = FachadaGerente.getInstance().consultarUsuario(cpf);
            if (usuario != null) {
                System.out.println("Informe a senha");
                if (usuario.validarSenha(Criptografia.criptografarSenha(InputUtil.getScan().nextLine())))
                    return usuario;
            }
            System.out.println("Nao encontrado");
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println(e.getMessage());
            lerDadosPeloTeclado();
        }
        return null;
    }
}
