/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.FormatoHabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.HabilitacaoObrigatoriException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;

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
    public void validar() throws ClienteInvalidoException, PessoaInvalidaException {
        if (this.habilitacao == null) {
            throw new HabilitacaoObrigatoriException();
        }
        if (this.habilitacao.isEmpty() || this.habilitacao.length() != 11) {
            throw new FormatoHabilitacaoException(habilitacao);
        }
        super.validar();
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
        return "Cliente{"
                + "cpf='" + this.getCpf() + '\''
                + ", nome='" + this.getNome() + '\''
                + ", nascimento=" + this.getNascimento()
                + ", habilitacao='" + this.getHabilitacao() + '\''
                + '}';
    }
}
