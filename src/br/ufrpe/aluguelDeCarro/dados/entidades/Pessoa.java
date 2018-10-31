/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;
import br.ufrpe.aluguelDeCarro.servicos.CpfUtil;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author JonasJr
 */
public abstract class Pessoa extends Entidade {

    private String cpf;
    private String nome;
    private LocalDate nascimento;

    Pessoa() {
    }

    Pessoa(String cpf, String nome, LocalDate nascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
    }

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

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public int getIdade() {
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

    public boolean valirdar() throws CpfException, IdadeExcetion, NomeException {
        CpfUtil.validarCPF(this.cpf);
        if (getIdade() >= 18) {
            throw new IdadeExcetion(IdadeExcetion.MENOR);
        }
        if(!this.nome.matches("[a-zA-Z]{2,}")){
            throw new NomeException(NomeException.INVALIDO);
        }
        return true;
    }
}
