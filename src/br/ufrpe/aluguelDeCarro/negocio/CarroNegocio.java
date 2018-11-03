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

/**
 *
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
        boolean saida = false;
        if (validar(carro) && validar(gerente)) {
            repositorio.cadastrar(carro);
        }
        return saida;
    }

    public boolean alterar(Carro carro, Gerente gerente) throws PlacaException, MarcaException,
            ModeloException, CarroException, CpfException, IdadeExcetion, NomeException, HabilitacaoException {
        boolean saida = false;
        if (validar(carro) && validar(gerente)) {
            repositorio.alterar(carro);
        }
        return saida;
    }
    
    public Carro buscarPorId(int id){
        Carro carro = null;
        if(id > 0){
            carro = repositorio.buscarPorId(id);
        }
        return carro;
    }
    
    public Carro buscarPorPlaca(String placa){
        Carro carro = null;
        if(placa != null && placa.length() != 7){
            carro = repositorio.buscarPorPlaca(placa);
        }
        return carro;
    }
    
    public boolean desativar(int id){
        boolean saida = false;
        if(id > 0){
            repositorio.deletar(id);
            saida = true;
        }
        return saida;
    }

}
