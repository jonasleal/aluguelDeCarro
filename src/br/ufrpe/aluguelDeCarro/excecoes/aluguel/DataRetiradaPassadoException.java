/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.aluguel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author JonasJr
 */
public class DataRetiradaPassadoException extends AluguelInvalidoException {

    private LocalDate data;

    public DataRetiradaPassadoException() {
        super("Data de retirada esta no passado");
    }

    public DataRetiradaPassadoException(LocalDate data) {
        this();
        this.data = data;
    }

    @Override
    public String getMessage() {
        if(data != null){
            return super.getMessage() + " " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return getMessage();
    }
    
    
    public DataRetiradaPassadoException(String message, Throwable cause) {
        super(message, cause);
    }

}
