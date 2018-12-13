/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IUsuarioRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.UsuarioNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioObrigatorioException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Usuario;

import java.util.List;

/**
 * @author JonasJr
 */
public class UsuarioNegocio {

    private final IUsuarioRepositorio repositorio;

    public UsuarioNegocio(IUsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Usuario usuario) throws ClienteInvalidoException, PessoaInvalidaException, UsuarioObrigatorioException {
        if (usuario == null) throw new UsuarioObrigatorioException();
        usuario.validar();
        usuario.setAtivo(true);
        repositorio.cadastrar(usuario);
    }

    public void alterar(Usuario usuario) throws PessoaInvalidaException, ClienteInvalidoException, UsuarioObrigatorioException {
        if (usuario == null) throw new UsuarioObrigatorioException();
        usuario.validar();
        this.repositorio.alterar(usuario);
    }

    public Usuario consultar(int id) throws UsuarioNaoEncontradoException {
        return repositorio.consultar(id);
    }

    public Usuario consultar(String cpf) throws UsuarioNaoEncontradoException {
        return this.repositorio.consultar(cpf);
    }

    public void desativar(int id) throws UsuarioNaoEncontradoException {
        repositorio.desativar(id);
    }

    public List<Usuario> consultarTodos() {
        return this.repositorio.consultarTodos();
    }
}
