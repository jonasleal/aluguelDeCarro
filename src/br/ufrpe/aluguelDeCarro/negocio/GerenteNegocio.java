/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.excecoes.pessoa.CpfObrigatorioException;
import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IGerenteRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.*;
import br.ufrpe.aluguelDeCarro.excecoes.Carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;

/**
 * @author JonasJr
 */
public class GerenteNegocio {

    private final IGerenteRepositorio repositorio;

    public GerenteNegocio(IGerenteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public boolean cadastrar(Gerente gerente) throws  PessoaInvalidaException, HabilitacaoException {
        if (gerente != null) {
            gerente.validar();
            gerente.setAtivo(true);
            return repositorio.cadastrar(gerente);
        }
        return false;
    }

    public boolean alterar(Gerente gerente) throws  PessoaInvalidaException, HabilitacaoException {
        if (gerente != null) {
            gerente.validar();
            return this.repositorio.alterar(gerente);
        }
        return false;
    }

    public Gerente consultar(int id) throws GerenteNaoEncontradoException {
        return repositorio.consultar(id);
    }

    public Gerente consultar(String cpf) throws GerenteNaoEncontradoException {
        return this.repositorio.consultar(cpf);
    }

    public boolean desativar(int id) throws GerenteNaoEncontradoException {
        return repositorio.desativar(id);
    }

}
