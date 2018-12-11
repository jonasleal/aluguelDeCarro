package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.negocio.entidades.Manutencao;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IManutencaoRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.ManutencaoNaoEncontradaException;

import java.util.List;

/**
 * @author Fernando
 */
public class ManutencaoNegocio {
    private final IManutencaoRepositorio repositorio;

    public ManutencaoNegocio(IManutencaoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Manutencao manutencao){
        this.repositorio.cadastrar(manutencao);
    }

    public void alterar(Manutencao manutencao){
        this.repositorio.alterar(manutencao);
    }

    public void desativar(int id) throws ManutencaoNaoEncontradaException {
        this.repositorio.desativar(id);
    }

    public Manutencao consultar(int id) throws ManutencaoNaoEncontradaException {
        return this.repositorio.consultar(id);
    }

    public List<Manutencao> consultarTodos(){
        return this.repositorio.consultarTodos();
    }
}
