/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.math.BigDecimal;

/**
 * @author JonasJr
 */
public class Categoria implements Cloneable {

    private int id;
    private boolean ativo;
    private String nome;
    private BigDecimal diaria;

    public Categoria() {
    }

    public Categoria(String nome, BigDecimal diaria) {
        this.nome = nome;
        this.diaria = diaria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getDiaria() {
        return diaria;
    }

    public void setDiaria(BigDecimal diaria) {
        this.diaria = diaria;
    }

    @Override
    public Categoria clone() {
        try {
            return (Categoria) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone não efetuado");
            return this;
        }
    }
}
