/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces;

import br.ufrpe.aluguelDeCarro.dados.entidades.Gerente;
import java.util.ArrayList;

/**
 *
 * @author JonasJr
 */
public interface GerenteRepositorioInterface {

    public boolean cadastrar(Gerente gerente);

    public boolean alterar(Gerente gerenteEditado);

    public Gerente buscarPorId(int id);

    public Gerente buscarPorCpf(String cpf);

    public boolean deletar(int id);

    public ArrayList<Gerente> buscarTodos();

}
