/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.NomeCategoriaObrigatorioException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.PrecoNegativoException;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author JonasJr
 */
public class Categoria implements Cloneable {

    private int id;
    private boolean ativo;
    private String nome;
    private BigDecimal diaria;

    public Categoria() {
        this.nome = "";
        this.diaria = BigDecimal.ZERO;
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

    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                ", diaria=" + diaria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id;
    }

    public void validar() throws CategoriaInvalidaException {
        if (this.nome == null || this.nome.isEmpty()) throw new NomeCategoriaObrigatorioException();
        if (this.diaria.compareTo(BigDecimal.ZERO) < 1) throw new PrecoNegativoException(this.diaria);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
