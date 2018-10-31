/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.CarroRepositorioInterface;
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
    
    private boolean validar(Carro carro) throws PlacaException{
        return carro != null && carro.validar();
    }
    
    public boolean cadastrar(Carro carro, Gerente gerente){
        return false;
    }
    
}
