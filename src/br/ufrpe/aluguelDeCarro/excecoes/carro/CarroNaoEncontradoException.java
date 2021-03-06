/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.carro;

/**
 * @author JonasJr
 */
public class CarroNaoEncontradoException extends CarroInvalidoException {

    private String placa;

    public CarroNaoEncontradoException() {
        super("carro com essa placa não foi encontada");
    }

    public CarroNaoEncontradoException(String placa) {
        this();
        this.placa = placa;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + placa;
    }

}
