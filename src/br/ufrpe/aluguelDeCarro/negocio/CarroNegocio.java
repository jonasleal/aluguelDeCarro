/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.CarroRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.CarroException;
import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.HabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.MarcaException;
import br.ufrpe.aluguelDeCarro.excecoes.ModeloException;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;
import br.ufrpe.aluguelDeCarro.excecoes.PlacaException;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public class CarroNegocio {

    private final CarroRepositorioInterface repositorio;

    public CarroNegocio(CarroRepositorioInterface repositorio) {
        this.repositorio = repositorio;
    }

    private boolean validar(Carro carro) throws PlacaException, MarcaException, ModeloException, CarroException {
        return carro != null && carro.validar();
    }

    private boolean validar(Gerente gerente) throws CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        return gerente != null && gerente.validar();
    }

    public boolean cadastrar(Carro carro, Gerente gerente) throws PlacaException, CpfException,
            IdadeExcetion, NomeException, MarcaException, ModeloException, CarroException, HabilitacaoException {
        if (this.validar(carro) && this.validar(gerente)) {
            carro.setAtivo(true);
            carro.setDisponivel(true);
            return this.repositorio.cadastrar(carro);
        }
        return false;
    }

    public boolean alterar(Carro carro, Gerente gerente) throws PlacaException, MarcaException,
            ModeloException, CarroException, CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        if (this.validar(carro) && this.validar(gerente)) {
            return this.repositorio.alterar(carro);
        }
        return false;
    }

    public Carro buscarPorId(int id) {
        if (id > 0) {
            return this.repositorio.buscarPorId(id);
        }
        return null;
    }

    public Carro buscarPorPlaca(String placa) {
        if (placa != null && placa.length() != 7) {
            return this.repositorio.buscarPorPlaca(placa);
        }
        return null;
    }

    public boolean desativar(int id) {
        if (id > 0) {
            return this.repositorio.deletar(id);
        }
        return false;
    }

    public ArrayList<Carro> buscarTodos(){
        return this.repositorio.buscarTodos();
    }

}
