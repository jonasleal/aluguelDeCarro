package br.ufrpe.aluguelDeCarro.dados.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.FormatoHabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class Gerente extends Pessoa implements IUsuario, Cloneable {

    private String senha;

    public Gerente() {
    }

    public Gerente(String cpf, String nome, LocalDate nascimento, String senha) {
        super(cpf, nome, nascimento);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public void validar() throws PessoaInvalidaException, ClienteInvalidoException {
        super.validar();
    }

    /**
     * @param senha senha para login do usuário
     * @return {@code true} se a senha for igual a senha da instancia,
     * {@code false} caso contrário
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
        return "Gerente{"
                + "cpf='" + this.getCpf() + '\''
                + ", nome='" + this.getNome() + '\''
                + ", nascimento=" + this.getNascimento()
                + '}';
    }
}
