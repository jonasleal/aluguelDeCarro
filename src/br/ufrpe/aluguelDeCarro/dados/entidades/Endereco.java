/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.cliente.CidadeObrigatorioException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.EstadoObrigatorioException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.RuaObrigatorioException;

/**
 *
 * @author JonasJr
 */
public class Endereco {

    private String rua;
    private String cidade;
    private String estado;
    private String complemento;
    private int numero;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void validar() throws ClienteInvalidoException {
        if (this.cidade == null || this.cidade.isEmpty()) {
            throw new CidadeObrigatorioException();
        }
        if (this.rua == null || this.rua.isEmpty()) {
            throw new RuaObrigatorioException();
        }
        if (this.estado == null || this.estado.isEmpty()) {
            throw new EstadoObrigatorioException();
        }
    }
}
