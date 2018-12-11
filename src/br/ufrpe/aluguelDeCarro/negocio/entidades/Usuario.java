/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio.entidades;

import java.time.LocalDate;

/**
 * @author JonasJr
 */
public class Usuario extends Pessoa implements Cloneable {

    private String senha;
    private boolean gerente;

    public Usuario() {
        this.senha = "";
    }

    public Usuario(String cpf, String nome, LocalDate nascimento, String senha, boolean gerente) {
        super(cpf, nome, nascimento);
        this.senha = senha;
        this.gerente = gerente;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isGerente() {
        return gerente;
    }

    public void setGerente(boolean gerente) {
        this.gerente = gerente;
    }

    /**
     * @param senha senha para login do usuário
     * @return {@code true} se a senha for igual a senha da instância, {@code false} caso contrário
     */
    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }

    @Override
    public Usuario clone() {
        try {
            return (Usuario) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone não efetuado");
            return this;
        }
    }
}
