package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class Gerente extends Pessoa implements Usuario {
    private String senha;

    public Gerente() {
    }

    public Gerente(String cpf, String nome, LocalDate nascimento, String senha) {
        super(cpf, nome, nascimento);
        this.senha = senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean login(String senha) {
        return this.senha.equals(senha);
    }
}
