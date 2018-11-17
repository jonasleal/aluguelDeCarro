/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.HabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;

import java.time.LocalDate;

/**
 * @author JonasJr
 */
public class Cliente extends Pessoa implements Cloneable {

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

    @Override
    public boolean validar() throws CpfException, IdadeExcetion, HabilitacaoException, NomeException {

        if (this.habilitacao == null) {
            throw new HabilitacaoException(HabilitacaoException.NULL);
        }
        if (this.habilitacao.isEmpty() || this.habilitacao.length() != 11) {
            throw new HabilitacaoException(HabilitacaoException.TAMANHO);
        }

        return super.validar();
    }

    @Override
    public Cliente clone() {
        try {
            return (Cliente) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone não efetuado");
        }
        return this;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + this.getCpf() + '\'' +
                ", nome='" + this.getNome() + '\'' +
                ", nascimento=" + this.getNascimento() +
                ", habilitacao='" + this.getHabilitacao() + '\'' +
                '}';
    }
}
