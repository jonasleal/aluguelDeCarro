package br.ufrpe.aluguelDeCarro.dados.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.HabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;
import java.time.LocalDate;

/**
 * @author Fernando
 */

public class Gerente extends Pessoa implements Usuario, Cloneable {
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

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean validar() throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        return super.validar();
    }

    /**
     * @param senha senha para login do usuário
     * @return {@code true} se a senha for igual a senha da instancia, {@code false} caso contrário
     */
    @Override
    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }

    @Override
    public Gerente clone() {
        try {
            return (Gerente) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone não efetuado");
        }
        return this;
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "cpf='" + this.getCpf() + '\'' +
                ", nome='" + this.getNome() + '\'' +
                ", nascimento=" + this.getNascimento() +
                '}';
    }
}
