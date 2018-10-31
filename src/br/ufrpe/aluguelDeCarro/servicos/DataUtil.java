package br.ufrpe.aluguelDeCarro.servicos;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class DataUtil {
    public static LocalDate transformarStringEmData(String string){
        if(string != null && string.trim().length() > 0){
            return LocalDate.parse(string);
        }
        return null;
    }
}
