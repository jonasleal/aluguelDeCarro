/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.HabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;

/**
 * @author JonasJr
 */
public interface IUsuario {

    void validar() throws PessoaInvalidaException, HabilitacaoException;

    boolean validarSenha(String senha);
}
