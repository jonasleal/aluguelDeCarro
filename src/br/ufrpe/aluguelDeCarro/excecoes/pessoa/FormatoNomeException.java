/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.excecoes.pessoa;

/**
 * @author JonasJr
 */
public class FormatoNomeException extends PessoaInvalidaException {

    private String nome;

    public FormatoNomeException() {
        super("Deve ser informado nome e sobrenome, somento caracteres alfabeticos");
    }

    public FormatoNomeException(String nome) {
        this();
        this.nome = nome;
    }

    @Override
    public String getMessage() {
        if (nome != null && !nome.isEmpty()) {
            return super.getMessage() + ": " + nome;
        }
        return super.getMessage();
    }

}
