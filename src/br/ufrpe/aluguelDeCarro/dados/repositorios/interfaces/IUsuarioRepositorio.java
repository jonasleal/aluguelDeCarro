/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Usuario;
import br.ufrpe.aluguelDeCarro.excecoes.UsuarioNaoEncontradoException;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public interface IUsuarioRepositorio {

    Usuario consultar(int id) throws UsuarioNaoEncontradoException;

    Usuario consultar(String cpf) throws UsuarioNaoEncontradoException;

    boolean cadastrar(Usuario usuario);

    boolean alterar(Usuario usuarioEditado);

    boolean desativar(int id) throws UsuarioNaoEncontradoException;

    ArrayList<Usuario> consultarTodos();

}
