/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.carro;

/**
 *
 * @author JonasJr
 */
public class FormatoMarcaException extends CarroInvalidoException {

    private String marca;

    public FormatoMarcaException() {
        super("Marca deve conter ao menos 2 caracteres");
    }

    public FormatoMarcaException(String marca) {
        this();
        this.marca = marca;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + marca;
    }

}
