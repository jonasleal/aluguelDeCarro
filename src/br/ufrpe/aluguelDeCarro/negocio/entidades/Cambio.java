/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio.entidades;

/**
 * @author JonasJr
 */
public enum Cambio {
    AUTOMATICO("Automático", 1),
    MANUAL("Manual", 2),
    CVT("CVT", 3);

    private String nome;
    private int valor;

    Cambio(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return getNome();
    }}
