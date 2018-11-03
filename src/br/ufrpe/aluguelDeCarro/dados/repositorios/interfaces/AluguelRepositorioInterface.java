/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;
import java.util.ArrayList;

/**
 *
 * @author JonasJr
 */
public interface AluguelRepositorioInterface {

    public Aluguel buscarPorId(int id);

    public void cadastrar(Aluguel aluguel);

    public void alterar(Aluguel aluguelEditado);

    public void deletar(int id);

    public ArrayList<Aluguel> buscarTodos();
}
