/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.excecoes.cliente.FormatoHabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.CpfObrigatorioException;
import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.ICarroRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.*;
import br.ufrpe.aluguelDeCarro.excecoes.Carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.Carro.CarroNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;

import java.util.ArrayList;

/**
 * @author JonasJr
 */
public class CarroNegocio {

    private final ICarroRepositorio repositorio;

    public CarroNegocio(ICarroRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public boolean cadastrar(Carro carro, Gerente gerente) throws CarroInvalidoException, PessoaInvalidaException, ClienteInvalidoException {
        if (carro != null && gerente != null) {
            carro.validar();
            gerente.validar();
            carro.setAtivo(true);
            carro.setDisponivel(true);
            return this.repositorio.cadastrar(carro);
        }
        return false;
    }

    public boolean alterar(Carro carro, Gerente gerente) throws CarroInvalidoException, PessoaInvalidaException, ClienteInvalidoException {
        if (carro != null && gerente != null) {
            carro.validar();
            gerente.validar();
            return this.repositorio.alterar(carro);
        }
        return false;
    }

    public Carro consultar(int id) throws CarroNaoEncontradoException, IdNaoEncontradoException {
        return this.repositorio.consultar(id);
    }

    public Carro consultar(String placa) throws CarroNaoEncontradoException {
        return this.repositorio.consultar(placa);
    }

    public boolean desativar(int id) throws IdNaoEncontradoException {
        return this.repositorio.desativar(id);
    }

    public ArrayList<Carro> consultarTodos() {
        return this.repositorio.consultarTodos();
    }

}
