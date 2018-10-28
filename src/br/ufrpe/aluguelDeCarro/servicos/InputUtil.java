package br.ufrpe.aluguelDeCarro.servicos;

import java.util.Scanner;

/**
 * @author Fernando
 */
public class InputUtil {
    private static Scanner scan;

    private InputUtil(){}

    public static Scanner getScan() {
        if(scan == null)
            scan = new Scanner(System.in);
        return scan;
    }

    public static void close(){
        scan.close();
    }
}
