/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.FormatoNomeException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.MenorDeIdadeException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.servicos.CpfUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * @author JonasJr
 */
public abstract class Pessoa implements Cloneable {

    private int id;
    private boolean ativo;
    private String cpf;
    private String nome;
    private LocalDate nascimento;

    Pessoa() {
        this.cpf = "";
        this.nome = "";
        this.nascimento = LocalDate.now();
    }

    Pessoa(String cpf, String nome, LocalDate nascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
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

    private int getIdade() {
        return Period.between(nascimento, LocalDate.now()).getYears();
    }

    public void validar() throws PessoaInvalidaException, ClienteInvalidoException {
        CpfUtil.validarCPF(this.cpf);
        int idade = getIdade();
        if (idade <= 18) {
            throw new MenorDeIdadeException(idade);
        }
        if (!this.nome.matches("[a-zA-Z]{2,}")) {
            throw new FormatoNomeException(nome);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pessoa pessoa = (Pessoa) o;
        return id == pessoa.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
