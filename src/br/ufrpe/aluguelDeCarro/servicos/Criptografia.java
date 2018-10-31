/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.servicos;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author JonasJr
 */
public class Criptografia {
    private static MessageDigest instancia;
    static{
        try{
            instancia = MessageDigest.getInstance("MD5");
            
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
    
    public static String criptografarSenha(String senha){
        String saida = null;
        if(senha != null && !senha.isEmpty()){
            instancia.update(senha.getBytes(), 0, senha.length());
            saida = new BigInteger(1,instancia.digest()).toString(16);
        }
        return saida;
    }
    
}
