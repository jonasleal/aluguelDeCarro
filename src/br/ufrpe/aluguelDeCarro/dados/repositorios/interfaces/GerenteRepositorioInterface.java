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

    public void cadastrar(Gerente gerente);

    public void alterar(Gerente gerenteEditado);

    public Gerente buscarPorId(int id);

    public Gerente buscarPorCpf(String cpf);

    public void deletar(int id);

    public ArrayList<Gerente> buscarTodos();

}
