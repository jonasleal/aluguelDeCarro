/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.entidades.Cliente;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IAluguelRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * @author JonasJr
 */
public class AluguelNegocio {

    private final IAluguelRepositorio repositorio;

    public AluguelNegocio(IAluguelRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    private void validacaoBasica(Aluguel aluguel) throws AluguelException {
        try {
            aluguel.validar();
        } catch (CarroException | CpfException | HabilitacaoException
                | IdadeExcetion | MarcaException | ModeloException
                | NomeException | PlacaException e) {
            throw new AluguelException(e.getMessage(), e.fillInStackTrace());
        }
    }

    private void validarDevolucao(Aluguel aluguel) throws AluguelException, AluguelNaoEncontradoException {
        validacaoBasica(aluguel);
        Aluguel aluguelOriginal = repositorio.consultar(aluguel.getId());
        if (aluguelOriginal.getDevolucaoReal() != null) {
            throw new AluguelException(AluguelException.ALUGUEL_FINALIZADO);
        }
        if (!aluguel.getDevolucaoEstimada().equals(aluguelOriginal.getDevolucaoEstimada())) {
            throw new AluguelException(AluguelException.DATA_ESTIMADA_INCONSISTENTE);
        }
        if (!aluguel.getRetirada().equals(aluguelOriginal.getRetirada())) {
            throw new AluguelException(AluguelException.DATA_RETIRADA_INCONSISTENTE);
        }
    }

    private void validarParaAlugar(Aluguel aluguel) throws AluguelException {
        validacaoBasica(aluguel);
        if (this.repositorio.existe(aluguel.getCliente())) {
            throw new AluguelException(AluguelException.CPF_CONTEM_PENDENCIA);
        }
        if (aluguel.getRetirada().toLocalDate().compareTo(LocalDate.now()) < 0) {
            throw new AluguelException(AluguelException.DATA_INVALIDA);
        }
        if (aluguel.getDevolucaoEstimada().toLocalDate().compareTo(LocalDate.now()) < 1) {
            throw new AluguelException(AluguelException.DATA_INVALIDA);
        }
        Carro carro = aluguel.getCarro();
        if (carro == null || !carro.isAtivo() || !carro.isDisponivel()) {
            throw new AluguelException(AluguelException.INDISPONIVEL);
        }
    }

    /**
     * Verifica os dados da solicitação de aluguel, caso todos os dados
     * obrigatórios tenha sido passado, retorna True, caso contrário levanta uma
     * exceção referente a causa da falha.
     *
     * @param aluguel Instancia a ser cadastrada
     * @return True - Se concluído com sucesso.
     * @throws AluguelException - Contem a causa e a mensagem de erro.
     */
    public boolean cadastrar(Aluguel aluguel) throws AluguelException {
        if (aluguel != null) {
            this.validarParaAlugar(aluguel);
            aluguel.setAtivo(true);
            aluguel.getCarro().setDisponivel(false);
            return repositorio.cadastrar(aluguel);
        }
        return false;
    }

    public boolean alterar(Aluguel aluguel) throws AluguelException {
        if (aluguel != null) {
            this.validacaoBasica(aluguel);
            return this.repositorio.alterar(aluguel);
        }
        return false;
    }

    public Aluguel consultar(int id) throws AluguelNaoEncontradoException {
        return this.repositorio.consultar(id);
    }

    /**
     * Calcula o debito do aluguel em aberto e o finaliza no momento da chamada.
     *
     * @param aluguel            - Objeto com os dados do aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância
     *                           de 30 minutos.
     */
    private void calcularDebito(Aluguel aluguel, boolean considerarHorario) {
        aluguel.setDevolucaoReal(LocalDateTime.now());
        LocalDateTime dataEstimada = aluguel.getDevolucaoEstimada();
        LocalDateTime dataDevolucao = aluguel.getDevolucaoReal();
        Period periodoTotal = Period.between(dataEstimada.toLocalDate(), dataDevolucao.toLocalDate());

        if (considerarHorario) {
            int minutosTolerancia = 30;
            if (dataDevolucao.compareTo(dataEstimada.plusMinutes(minutosTolerancia)) > 0) {
                periodoTotal = periodoTotal.plusDays(1);
            }
        }
        long dias = periodoTotal.get(ChronoUnit.DAYS);
        BigDecimal adicional = aluguel.getCategoria().getDiaria().multiply(new BigDecimal(dias));
        aluguel.setCustoAdicional(adicional);
    }

    /**
     * Busca e calcula o debito de um aluguel em aberto para um determinado cliente
     * entrega um aluguel finalizado no momento da chamada.
     *
     * @param cliente           - cliente registrado no aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância de
     *                          30 minutos.
     * @return Objeto Aluguel no estado finalizado.
     */
    public Aluguel consultarDebito(Cliente cliente, boolean considerarHorario) throws AluguelNaoEncontradoException {
        Aluguel aluguel = consultar(cliente);
        if (aluguel != null) {
            calcularDebito(aluguel, considerarHorario);
        }
        return aluguel;
    }

    /**
     * Busca e calcula o debito de um aluguel em aberto para um determinado carro
     * entrega um aluguel finalizado no momento da chamada.
     *
     * @param carro             - carro registrado no aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância de
     *                          30 minutos.
     * @return Objeto Aluguel no estado finalizado.
     */
    public Aluguel consultarDebito(Carro carro, boolean considerarHorario) throws AluguelNaoEncontradoException {
        Aluguel aluguel = consultar(carro);
        if (aluguel != null) {
            calcularDebito(aluguel, considerarHorario);
        }
        return aluguel;
    }

    /**
     * Busca aluguel em aberto para um determinado cliente.
     *
     * @param cliente - cliente registrado no aluguel em aberto
     * @return Intancia do aluguel aberto para este cliente.
     */
    public Aluguel consultar(Cliente cliente) throws AluguelNaoEncontradoException {
        return this.repositorio.consultar(cliente);
    }

    /**
     * Busca aluguel em aberto para um determinado carro.
     *
     * @param carro - carro registrado no aluguel em aberto
     * @return Intancia do aluguel aberto para este carro.
     */
    public Aluguel consultar(Carro carro) throws AluguelNaoEncontradoException {
        return repositorio.consultar(carro);
    }

    /**
     * Verifica a consistência datas passados com as já cadastradas, se forem
     * iguais registra a devolução, caso contrário levanta exceção com a causa.
     *
     * @param aluguel - Aluguel no estado finalizado.
     * @return True - Se registrado com sucesso.
     * @throws AluguelException - Contem a mensagem e causa do erro.
     */
    public boolean devolucao(Aluguel aluguel) throws AluguelException, AluguelNaoEncontradoException {
        validarDevolucao(aluguel);
        return this.alterar(aluguel);
    }

    public ArrayList<Aluguel> consultarTodos() {
        return this.repositorio.consultarTodos();
    }

}
