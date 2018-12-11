/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.Aluguel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author JonasJr
 */
public class DataEstimadaInconsistenteException extends AluguelInvalidoException {

    private LocalDateTime dataNoBanco;
    private LocalDateTime dataNoSistema;

    public DataEstimadaInconsistenteException() {
        super("Inconsistência na data de devolução estimada com o banco de dados");
    }

    public DataEstimadaInconsistenteException(LocalDateTime dataNoBanco, LocalDateTime dataNoSistema) {
        this();
        this.dataNoBanco = dataNoBanco;
        this.dataNoSistema = dataNoSistema;
    }

    public String getDataNoBanco() {
        return dataNoBanco.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getDataNoSistema() {
        return dataNoSistema.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String getMessage() {
        if (dataNoBanco != null && dataNoSistema != null) {
            return super.getMessage() + ": Data no banco " + getDataNoBanco()
                    + " , data no sistema " + getDataNoSistema();
        }
        return super.getMessage();
    }
}
