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

    ECONOMICO("Enconomico", 1),
    COMPACTO("Compacto", 2),
    STANDARD("Standard", 3),
    SUV("SUV", 4),
    UTILITARIO("Utilirário", 5),
    MINIVAN("Minivan", 6),
    LUXO("Luxo", 7),
    ESPECIAL("Especial", 8);

    private String nome;
    private int valor;

    Categoria(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

}
