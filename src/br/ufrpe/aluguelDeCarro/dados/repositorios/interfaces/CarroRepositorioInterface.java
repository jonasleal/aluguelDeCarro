/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import java.util.ArrayList;

/**
 *
 * @author JonasJr
 */
public interface CarroRepositorioInterface {
    public Carro buscarPorId(int id);

    public void cadastrar(Carro carro);

    public void alterar(Carro carroEditado);

    public void deletar(int id);

    public ArrayList<Carro> buscarTodos();
}
