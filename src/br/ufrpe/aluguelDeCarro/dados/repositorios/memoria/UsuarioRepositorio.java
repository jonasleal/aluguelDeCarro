package br.ufrpe.aluguelDeCarro.dados.repositorios.memoria;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IUsuarioRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Usuario;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A classe armazena uma lista de instancias de usuarios
 *
 * @author Fernando
 */
public class UsuarioRepositorio implements IUsuarioRepositorio {

    private final ArrayList<Usuario> usuarios;

    public UsuarioRepositorio() {
        this.usuarios = new ArrayList<>();
    }

    /**
     * busca o usuario pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Usuario}
     * @return um clone do {@code Usuario} ativo que contém o id, {@code null} caso nao encontre
     * @throws UsuarioNaoEncontradoException
     */
    @Override
    public Usuario consultar(int id) throws UsuarioNaoEncontradoException {
        return this.usuarios
                .stream()
                .filter(Usuario::isAtivo)
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .map(Usuario::clone)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    /**
     * busca o usuario pelo id, nos já cadastrados
     *
     * @param id identificador do {@code Usuario}
     * @return o {@code Usuario} ativo que contém o id, {@code null} caso nao encontre
     */
    private Usuario consultarReferencia(int id) throws UsuarioNaoEncontradoException {
        return this.usuarios
                .stream()
                .filter(Usuario::isAtivo)
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    /**
     * busca o usuario pelo cpf, nos já cadastrados
     *
     * @param cpf identificador do {@code Usuario}
     * @return um clone do {@code Usuario} ativo que contém o cpf, {@code null} caso nao encontre
     * @throws UsuarioNaoEncontradoException
     */
    @Override
    public Usuario consultar(String cpf) throws UsuarioNaoEncontradoException {
        return this.usuarios
                .stream()
                .filter(usuario -> usuario.getCpf().equals(cpf))
                .filter(Usuario::isAtivo)
                .findFirst()
                .map(Usuario::clone)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    /**
     * @param usuario instancia a ser cadastrada
     */
    @Override
    public void cadastrar(Usuario usuario) {
        this.setarId(usuario);
        this.usuarios.add(usuario.clone());
    }

    /**
     * @param usuarioEditado instancia a ser editada
     */
    @Override
    public void alterar(Usuario usuarioEditado) {
        int indexOf = this.usuarios.indexOf(usuarioEditado);
        if (indexOf != -1)
            this.usuarios.set(indexOf, usuarioEditado.clone());
    }

    /**
     * altera o atributo {@code ativo} do usuario para false
     *
     * @param id identificador do {@code Usuario}
     * @throws UsuarioNaoEncontradoException
     */
    @Override
    public void desativar(int id) throws UsuarioNaoEncontradoException {
        Usuario usuario = this.consultarReferencia(id);
        usuario.setAtivo(false);
    }

    @Override
    public boolean existe(String cpf) {
        try {
            this.consultar(cpf);
            return true;
        } catch (UsuarioNaoEncontradoException e) {
            return false;
        }
    }

    /**
     * @return clones dos alugueis ativos e cadastrados
     */
    @Override
    public ArrayList<Usuario> consultarTodos() {
        return (ArrayList<Usuario>) this.usuarios
                .stream()
                .filter(Usuario::isAtivo)
                .map(Usuario::clone)
                .collect(Collectors.toList());
    }

    /**
     * altera o id do usuario, o id que ele recebe é o maior até então acrescido de 1
     *
     * @param usuario instancia a ter o id alterado
     */
    private void setarId(Usuario usuario) {
        if (this.usuarios.isEmpty())
            usuario.setId(1);
        else
            usuario.setId(this.usuarios
                    .stream()
                    .mapToInt(Usuario::getId)
                    .max()
                    .getAsInt() + 1);
    }


}
