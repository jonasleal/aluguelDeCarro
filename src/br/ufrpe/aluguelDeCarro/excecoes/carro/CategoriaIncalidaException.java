/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.carro;

/**
 * @author JonasJr
 */
public class CategoriaIncalidaException extends CarroInvalidoException {

    public CategoriaIncalidaException() {
        super("Categoria Invalida");
    }

}
