/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.GerenteRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.HabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;

/**
 * @author JonasJr
 */
public class GerenteNegocio {

    private final GerenteRepositorioInterface repositorio;

    public GerenteNegocio(GerenteRepositorioInterface repositorio) {
        this.repositorio = repositorio;
    }

    public boolean cadastrar(Gerente gerente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        if (gerente != null) {
            gerente.validar();
            gerente.setAtivo(true);
            return repositorio.cadastrar(gerente);
        }
        return false;
    }

    public boolean alterar(Gerente gerente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        if (gerente != null) {
            gerente.validar();
            return this.repositorio.alterar(gerente);
        }
        return false;
    }

    public Gerente buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    public Gerente buscarPorCpf(String cpf) {
        return this.repositorio.buscarPorCpf(cpf);
    }

    public boolean desativar(int id) {
        return repositorio.desativar(id);
    }

}
