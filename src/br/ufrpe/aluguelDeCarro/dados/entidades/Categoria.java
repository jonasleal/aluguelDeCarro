/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.entidades;

/**
 *
 * @author JonasJr
 */
public enum Categoria {
    PICKUP("Pickup", 0),
    HATCH("Hatch", 1),
    SEDA("Seda", 2),
    PERUA("Perua", 3),
    SUV("SUV", 4);

    private String nome;
    private int valor;

    Categoria(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
