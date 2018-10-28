package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class Atendente extends Pessoa {
    private Usuario usuario;

    public Atendente() {
    }

    public Atendente(String cpf, String nome, LocalDate nascimento, Usuario usuario) {
        super(cpf, nome, nascimento);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
