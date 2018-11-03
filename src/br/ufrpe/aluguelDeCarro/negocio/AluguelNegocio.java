/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.repositorios.CarroRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.AluguelRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.AluguelException;
import br.ufrpe.aluguelDeCarro.excecoes.CarroException;
import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.HabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.MarcaException;
import br.ufrpe.aluguelDeCarro.excecoes.ModeloException;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;
import br.ufrpe.aluguelDeCarro.excecoes.PlacaException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author JonasJr
 */
public class AluguelNegocio {

    private AluguelRepositorioInterface repositorio;

    public AluguelNegocio(AluguelRepositorioInterface repositorio) {
        this.repositorio = repositorio;
    }

    private boolean validar(Aluguel aluguel) throws AluguelException {
        try {
            aluguel.validar();
        } catch (CarroException | CpfException | HabilitacaoException
                | IdadeExcetion | MarcaException | ModeloException
                | NomeException | PlacaException e) {
            throw new AluguelException(e.getMessage(), e.fillInStackTrace());
        }

        return true;
    }

    private boolean validarParaAlugar(Aluguel aluguel) throws AluguelException {
        validar(aluguel);
        if (aluguel.getRetirada().compareTo(LocalDateTime.now()) < 0) {
            throw new AluguelException(AluguelException.DATAINVALIDA);
        }
        if (aluguel.getDevolucaoEstimada().compareTo(LocalDateTime.now()) < 1) {
            throw new AluguelException(AluguelException.DATAINVALIDA);
        }
        Carro carro = aluguel.getCarro();
        if (carro == null || !carro.isAtivo() || !carro.isDisponivel()) {
            throw new AluguelException(AluguelException.INDISPONIVEL);
        }
        return true;
    }


    public boolean cadastrar(Aluguel aluguel) throws AluguelException {
        if (this.validarParaAlugar(aluguel)) {
            aluguel.setAtivo(true);
            return repositorio.cadastrar(aluguel);
        }
        return false;
    }

    public boolean alterar(Aluguel aluguel) throws AluguelException {
        if (this.validar(aluguel))
            return this.repositorio.alterar(aluguel);
        return false;
    }

    public Aluguel recuperarPorId(int id) {
        if (id > 0)
            return this.repositorio.buscarPorId(id);
        return null;
    }

    public Aluguel consultarDebitoPorCpf(String cpf) {
        return null;
    }

    public Aluguel consultarDebitoPorPlaca(String placa) {
        return null;
    }

    public boolean devolucao(Aluguel aluguel) throws AluguelException {
        validar(aluguel);
        aluguel.setDevolucaoReal(LocalDateTime.now());
        return false;
    }

}
