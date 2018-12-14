/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.aluguel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author JonasJr
 */
public class DataEstimadaPassado extends AluguelInvalidoException {

    private LocalDate data;

    public DataEstimadaPassado() {
        super("Data de devolução estimada não esta no futuro");
    }

    public DataEstimadaPassado(LocalDate data) {
        this();
        this.data = data;
    }

    @Override
    public String getMessage() {
        if (data != null) {
            return super.getMessage() + " " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return getMessage();
    }

}
