/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.pessoa;

/**
 * @author JonasJr
 */
public class NomeObrigatorioException extends PessoaInvalidaException {

    public NomeObrigatorioException() {
        super("Nome é obrigatorio");
    }

}
