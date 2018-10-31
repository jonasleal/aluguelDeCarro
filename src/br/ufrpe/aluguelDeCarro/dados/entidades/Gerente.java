package br.ufrpe.aluguelDeCarro.dados.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;
import java.time.LocalDate;

/**
 * @author Fernando
 */
public class Gerente extends Pessoa {

    private Usuario usuario;

    public Gerente() {
    }

    public Gerente(String cpf, String nome, LocalDate nascimento, Usuario usuario) {
        super(cpf, nome, nascimento);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean validar() throws CpfException, IdadeExcetion, NomeException {
        return super.valirdar();
    }
}
