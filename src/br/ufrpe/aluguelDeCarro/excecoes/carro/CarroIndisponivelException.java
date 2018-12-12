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
public class CarroIndisponivelException extends CarroInvalidoException{
    private String placa;

    public CarroIndisponivelException() {
        super("Veiculo indisponivel");
    }

    public CarroIndisponivelException(String placa) {
        this();
        this.placa = placa;
    }

    public CarroIndisponivelException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
