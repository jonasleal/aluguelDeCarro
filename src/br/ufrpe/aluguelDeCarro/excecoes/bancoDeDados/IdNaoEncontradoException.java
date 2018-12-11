/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados;

/**
 *
 * @author JonasJr
 */
public class IdNaoEncontradoException extends Exception {

    private int id;

    public IdNaoEncontradoException() {
        super("Objeto referente a esse id não foi encontada");
    }

    public IdNaoEncontradoException(int id) {
        this();
        this.id = id;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + id;
    }

}
