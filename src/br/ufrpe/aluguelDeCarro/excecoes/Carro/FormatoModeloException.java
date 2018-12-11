/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.Carro;

/**
 *
 * @author JonasJr
 */
public class FormatoModeloException extends CarroInvalidoException {

    private String modelo;

    public FormatoModeloException() {
        super("Modelo deve conter ao menos 2 caracteres");
    }

    public FormatoModeloException(String modelo) {
        this();
        this.modelo = modelo;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + modelo;
    }

}
