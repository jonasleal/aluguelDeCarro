/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Usuario;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface IUsuarioRepositorio {

    Usuario consultar(int id) throws UsuarioNaoEncontradoException;

    Usuario consultar(String cpf) throws UsuarioNaoEncontradoException;

    void cadastrar(Usuario usuario);

    void alterar(Usuario usuarioEditado);

    void desativar(int id) throws UsuarioNaoEncontradoException;

    boolean existe(String cpf);

    ArrayList<Usuario> consultarTodos();

}
