package br.ufrpe.aluguelDeCarro.apresentacao;

import br.ufrpe.aluguelDeCarro.negocio.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.servicos.Criptografia;
import br.ufrpe.aluguelDeCarro.servicos.DataUtil;
import br.ufrpe.aluguelDeCarro.servicos.InputUtil;

/**
 * Classe de interação com o usuário, para que o mesmo possar gerenciar o usuario
 *
 * @author Fernando
 */
class UsuarioApresentacao {

    /**
     * solicita ao usuário os dados do usuario
     *
     * @return uma instância de {@code Usuario} com os dados preenchidos pelo usuário
     */
    Usuario lerDadosPeloTeclado() {
        Usuario usuario = null;
        try {
            usuario = new Usuario();
            System.out.println("Informe o nome do usuario:");
            usuario.setNome(InputUtil.getScan().nextLine());
            System.out.println("Informe o cpf do usuario:");
            usuario.setCpf(InputUtil.getScan().nextLine());
            System.out.println("Informe a data de nascimento do usuario(siga o modelo dd-MM-yyyy):");
            usuario.setNascimento(DataUtil.transformarStringEmData(InputUtil.getScan().nextLine()));
            System.out.println("Informe a senha de acesso do usuario:");
            usuario.setSenha(Criptografia.criptografarSenha(InputUtil.getScan().nextLine()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lerDadosPeloTeclado();
        }
        return usuario;
    }
}
