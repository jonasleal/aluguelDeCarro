/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICarroRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroJaCadastradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroObrigatorioException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Categoria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JonasJr
 */
public class CarroNegocio {

    private final ICarroRepositorio repositorio;

    public CarroNegocio(ICarroRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Carro carro) throws CarroInvalidoException {
        if (carro == null) throw new CarroObrigatorioException();
        String placa = carro.getPlaca();
        if (this.repositorio.existe(placa)) throw new CarroJaCadastradoException(placa);
        carro.validar();
        carro.setAtivo(true);
        carro.setDisponivel(true);
        this.repositorio.cadastrar(carro);
    }

    public void alterar(Carro carro) throws CarroInvalidoException {
        if (carro == null) throw new CarroObrigatorioException();
        carro.validar();
        this.repositorio.alterar(carro);
    }

    public Carro consultar(int id) throws IdNaoEncontradoException {
        return this.repositorio.consultar(id);
    }

    public Carro consultar(String placa) throws CarroNaoEncontradoException {
        return this.repositorio.consultar(placa);
    }

    public List<Carro> consultar(Categoria categoria) {
        return this.repositorio.consultar(categoria);
    }

    public void desativar(int id) throws IdNaoEncontradoException {
        this.repositorio.desativar(id);
    }

    public ArrayList<Carro> consultarTodos() {
        return this.repositorio.consultarTodos();
    }

}
