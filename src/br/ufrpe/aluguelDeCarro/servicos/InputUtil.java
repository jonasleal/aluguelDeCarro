package br.ufrpe.aluguelDeCarro.servicos;

import java.util.InputMismatchException;
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
        return new Scanner(System.in);
    }

    public static void close(){
        scan.close();
    }

    public static int solicitarNumeroInteiro(){
        try{
            return getScan().nextInt();
        } catch (InputMismatchException e){
            System.out.println("Inválido, digite novamente");
            return solicitarNumeroInteiro();
        }
    }

    public static double solicitarNumeroFlutuante(){
        try{
            return getScan().nextDouble();
        } catch (InputMismatchException e){
            System.out.println("Inválido, digite novamentse");
            return solicitarNumeroInteiro();
        }
    }
}
