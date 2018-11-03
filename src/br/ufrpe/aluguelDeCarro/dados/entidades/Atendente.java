package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class Atendente extends Pessoa implements Cloneable, Usuario {
    private String senha;

    public Atendente() {
    }

    public Atendente(String cpf, String nome, LocalDate nascimento, String senha) {
        super(cpf, nome, nascimento);
        this.senha = senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Atendente clone() {
        try {
            return (Atendente) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone não efetuado");
        }
        return this;
    }

    /**
     * @param senha senha para login do usuário
     * @return {@code true} se a senha for igual a senha da instancia, {@code false} caso contrário
     */
    @Override
    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }
}
