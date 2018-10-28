package br.ufrpe.aluguelDeCarro.negocio;

/**
 * @author Fernando
 */
public class ClienteNegocio {

    public static boolean verificarStringData(String data){
        if(data != null)
            return data.matches("\\d{4}-\\d{2}-\\d{2}");
        return false;
    }
}
