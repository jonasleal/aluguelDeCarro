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

    public boolean cadastrar(Usuario usuario) throws ClienteInvalidoException, PessoaInvalidaException {
        if (usuario != null) {
            usuario.validar();
            usuario.setAtivo(true);
            return repositorio.cadastrar(usuario);
        }
        return false;
    }

    public boolean alterar(Usuario usuario) throws PessoaInvalidaException, ClienteInvalidoException {
        if (usuario != null) {
            usuario.validar();
            return this.repositorio.alterar(usuario);
        }
        return false;
    }

    public Usuario consultar(int id) throws UsuarioNaoEncontradoException {
        return repositorio.consultar(id);
    }

    public Usuario consultar(String cpf) throws UsuarioNaoEncontradoException {
        return this.repositorio.consultar(cpf);
    }

    public boolean desativar(int id) throws UsuarioNaoEncontradoException {
        return repositorio.desativar(id);
    }

    public List<Usuario> consultarTodos() {
        return this.repositorio.consultarTodos();
    }
}
