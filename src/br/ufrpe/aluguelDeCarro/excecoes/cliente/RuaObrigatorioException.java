/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.cliente;

/**
 *
 * @author JonasJr
 */
public class RuaObrigatorioException extends ClienteInvalidoException {

    public RuaObrigatorioException() {
        super("Rua é obrigatorio");
    }

}
