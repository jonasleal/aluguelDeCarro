package br.ufrpe.aluguelDeCarro.servicos;

import java.util.Scanner;

/**
 * Classe que auxilia ao se trabalhar com a Classe scanner
 * @author Fernando
 */
public class InputUtil {
    private static Scanner scan;

    private InputUtil(){}

    /**
     * @return uma instancia de {@code Scanner}, caso já esteja inicializada, simplementes retorna, caso contrário cria
     * uma nova
     */
    public static Scanner getScan() {
        if(scan == null)
            scan = new Scanner(System.in);
        return scan;
    }

    public static void close(){
        scan.close();
    }
}
