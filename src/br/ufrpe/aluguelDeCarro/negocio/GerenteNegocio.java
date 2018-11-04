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
 *
 * @author JonasJr
 */
public class GerenteNegocio {

    private final GerenteRepositorioInterface repositorio;

    public GerenteNegocio(GerenteRepositorioInterface repositorio) {
        this.repositorio = repositorio;
    }

    private boolean validar(Gerente gerente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        return gerente != null && gerente.validar();
    }

    public boolean cadastrar(Gerente gerente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        if (validar(gerente)) {
            gerente.setAtivo(true);
            return repositorio.cadastrar(gerente);
        }
        return false;
    }

    public boolean alterar(Gerente gerente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        if (validar(gerente)) {
            return this.repositorio.alterar(gerente);
        }
        return false;
    }

    public Gerente buscarPorId(int id) {
        if (id > 0) {
            return repositorio.buscarPorId(id);
        }
        return null;
    }

    public Gerente buscarPorCpf(String cpf) {
        if (cpf != null && !cpf.isEmpty()) {
            return this.repositorio.buscarPorCpf(cpf);
        }
        return null;
    }

    public boolean desativar(int id) {
        if (id > 0) {
            return repositorio.deletar(id);
        }
        return false;
    }

}
