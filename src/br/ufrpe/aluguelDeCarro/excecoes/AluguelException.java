/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes;

/**
 * @author JonasJr
 */
public class AluguelException extends Exception {

    public static final String INDISPONIVEL = "Veiculo indisponivel";
    public static final String DATA_INVALIDA = "Data invalida";
    public static final String VALOR_INVALIDO = "Valor invalida";
    public static final String ALUGUEL_FINALIZADO = "Aluguel ja finalizado";
    public static final String DATA_ESTIMADA_INCONSISTENTE = "Data de entrega não consistente";
    public static final String DATA_RETIRADA_INCONSISTENTE = "Data de retirada não consistente";
    public static final String CPF_CONTEM_PENDENCIA = "CPF contem aluguem em aberto";

    public AluguelException(String message) {
        super(message);
    }

    public AluguelException(String message, Throwable cause) {
        super(message, cause);
    }

}
