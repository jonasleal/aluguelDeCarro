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
    public Carro buscarPorPlaca(String placa);

    public boolean cadastrar(Carro carro);

    public boolean alterar(Carro carroEditado);

    public boolean deletar(int id);

    public ArrayList<Carro> buscarTodos();
}
