package br.ufrpe.aluguelDeCarro.servicos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe que auxilia ao se trabalhar com datas
 * @author Fernando
 */
public class DataUtil {
    /**
     * @param string com a data no formato dd-MM-yyyy HH:mm
     */
    public static LocalDateTime transformarStringEmDataTime(String string){
        if(string != null && string.trim().length() > 0){
            return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        }
        return null;
    }

    /**
     * @param string com a data no formato dd-MM-yyyy
     */
    public static LocalDate transformarStringEmData(String string){
        if(string != null && string.trim().length() > 0){
            return LocalDate.parse(string, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        return null;
    }
}
