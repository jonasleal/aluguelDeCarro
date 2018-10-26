/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.model.genericas;

import java.time.LocalTime;

/**
 *
 * @author JonasJr
 */
public abstract class Pessoa extends Entidade {

    private String cpf;
    private String nome;
    private LocalTime dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
