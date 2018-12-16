/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.cliente.*;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;

import java.time.LocalDate;

/**
 * @author JonasJr
 */
public class Cliente extends Pessoa implements Cloneable {

    private String habilitacao;
    private String email;
    private String telefone;
    private Endereco endereco;

    public Cliente() {
        this.habilitacao = "";
        this.endereco = new Endereco();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return this.endereco.getRua();
    }

    public void setRua(String rua) {
        this.endereco.setRua(rua);
    }

    public String getCidade() {
        return this.endereco.getCidade();
    }

    public void setCidade(String cidade) {
        this.endereco.setCidade(cidade);
    }

    public String getEstado() {
        return this.endereco.getEstado();
    }

    public void setEstado(String estado) {
        this.endereco.setEstado(estado);
    }

    public String getComplemento() {
        return this.endereco.getComplemento();
    }

    public void setComplemento(String complemento) {
        this.endereco.setComplemento(complemento);
    }

    public int getNumero() {
        return this.endereco.getNumero();
    }

    public void setNumero(int numero) {
        this.endereco.setNumero(numero);
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
    public void validar() throws PessoaInvalidaException, ClienteInvalidoException {
        if (this.habilitacao == null) throw new HabilitacaoObrigatoriException();
        if (this.email == null || this.email.trim().isEmpty()) throw new EmailObrigatorioException();
        if (this.telefone == null || this.telefone.trim().isEmpty()) throw new TelefoneObrigatorioException();
        if (this.habilitacao.isEmpty() || this.habilitacao.length() != 11)
            throw new FormatoHabilitacaoException(habilitacao);
        super.validar();
        endereco.validar();
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
