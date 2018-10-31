package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class Atendente extends Pessoa implements Usuario{
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
    public boolean login(String senha) {
        return this.senha.equals(senha);
    }
}
