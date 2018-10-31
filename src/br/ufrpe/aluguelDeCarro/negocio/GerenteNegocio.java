/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.GerenteRepositorio;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.GerenteRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;

/**
 *
 * @author JonasJr
 */
public class GerenteNegocio {

    private final GerenteRepositorioInterface repositorio;
    
    public GerenteNegocio(){
        this.repositorio = new GerenteRepositorio();
    }
    
    private boolean validar(Gerente gerente) throws CpfException, IdadeExcetion, NomeException{
        return gerente != null && gerente.validar();
    }

    public boolean cadastrar(Gerente gerente) throws CpfException, IdadeExcetion, NomeException {
        boolean saida = false;
        if(validar(gerente)){
            repositorio.cadastrar(gerente);
            saida = true;
        }
        return saida;
    }
    
    public boolean alterar(Gerente gerente) throws CpfException, IdadeExcetion, NomeException{
        boolean saida = false;
        if(validar(gerente)){
            this.repositorio.alterar(gerente);
            saida = true;
        }
        return saida;
    }
    
    public Gerente buscarPorId(int id){
        Gerente saida = null;
        if(id > 0){
            saida = repositorio.buscarPorId(id);
        }
        return saida;
    }
    public Gerente buscarPorCpr(String cpf){
        Gerente saida = null;
        if(cpf != null && !cpf.isEmpty()){
            saida = buscarPorCpr(cpf);
        }
        return saida;
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
