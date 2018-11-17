/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.CarroRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.*;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public class CarroNegocio {

    private final CarroRepositorioInterface repositorio;

    public CarroNegocio(CarroRepositorioInterface repositorio) {
        this.repositorio = repositorio;
    }

    public boolean cadastrar(Carro carro, Gerente gerente) throws PlacaException, CpfException,
            IdadeExcetion, NomeException, MarcaException, ModeloException, CarroException, HabilitacaoException {
        if (carro != null && gerente != null) {
            carro.validar();
            gerente.validar();
            carro.setAtivo(true);
            carro.setDisponivel(true);
            return this.repositorio.cadastrar(carro);
        }
        return false;
    }

    public boolean alterar(Carro carro, Gerente gerente) throws PlacaException, MarcaException,
            ModeloException, CarroException, CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        if (carro != null && gerente != null) {
            carro.validar();
            gerente.validar();
            return this.repositorio.alterar(carro);
        }
        return false;
    }

    public Carro buscarPorId(int id) {
        return this.repositorio.buscarPorId(id);
    }

    public Carro buscarPorPlaca(String placa) {
        return this.repositorio.buscarPorPlaca(placa);
    }

    public boolean desativar(int id) {
        return this.repositorio.desativar(id);
    }

    public ArrayList<Carro> buscarTodos() {
        return this.repositorio.buscarTodos();
    }

}
