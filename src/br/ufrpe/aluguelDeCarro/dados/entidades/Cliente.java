/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDate;

/**
 *
 * @author JonasJr
 */
public class Cliente extends Pessoa{
    private String habilitacao;

    public Cliente() {
    }

    public Cliente(String cpf, String nome, LocalDate nascimento, String habilitacao) {
        super(cpf, nome, nascimento);
        this.habilitacao = habilitacao;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }
}
