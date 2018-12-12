/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.aluguel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author JonasJr
 */
public class DataRetiradaFuturoException extends AluguelInvalidoException {

    private LocalDateTime data;

    public DataRetiradaFuturoException() {
        super("Data de retirada esta no futuro");
    }

    public DataRetiradaFuturoException(LocalDateTime data) {
        this();
        this.data = data;
    }

    @Override
    public String getMessage() {
        if(data != null){
            return super.getMessage() + " " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }
        return getMessage();
    }
    
    
    public DataRetiradaFuturoException(String message, Throwable cause) {
        super(message, cause);
    }

}
