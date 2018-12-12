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
public class FormatoPlacaInvalidoException extends CarroInvalidoException {

    private String placa;

    public FormatoPlacaInvalidoException() {
        super("A placa deve conter 3 letras e 4 digitos");
    }

    
    public FormatoPlacaInvalidoException(String placa) {
        this();
        this.placa = placa;
    }

    @Override
    public String getMessage() {
        if(placa.isEmpty()){
            return super.getMessage();
        }
        return super.getMessage() + ": " + placa;
    }
    
    
    
    

}
