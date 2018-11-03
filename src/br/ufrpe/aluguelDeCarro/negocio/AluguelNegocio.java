/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.AluguelRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.AluguelException;
import br.ufrpe.aluguelDeCarro.excecoes.CarroException;
import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.HabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.MarcaException;
import br.ufrpe.aluguelDeCarro.excecoes.ModeloException;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;
import br.ufrpe.aluguelDeCarro.excecoes.PlacaException;
import br.ufrpe.aluguelDeCarro.servicos.CpfUtil;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * @author JonasJr
 */
public class AluguelNegocio {

    private final AluguelRepositorioInterface repositorio;

    public AluguelNegocio(AluguelRepositorioInterface repositorio) {
        this.repositorio = repositorio;
    }

    private boolean validacaoBasica(Aluguel aluguel) throws AluguelException {
        try {
            aluguel.validar();
        } catch (CarroException | CpfException | HabilitacaoException
                | IdadeExcetion | MarcaException | ModeloException
                | NomeException | PlacaException e) {
            throw new AluguelException(e.getMessage(), e.fillInStackTrace());
        }

        return true;
    }

    private boolean validarDevolucao(Aluguel aluguel) throws AluguelException {
        validacaoBasica(aluguel);
        Aluguel aluguelOriginal = repositorio.buscarPorId(aluguel.getId());

        if (aluguelOriginal.getDevolucaoReal() != null) {
            throw new AluguelException(AluguelException.ALUGUELFINALIZADO);
        }

        if (!aluguel.getDevolucaoEstimada().equals(aluguelOriginal.getDevolucaoEstimada())) {
            throw new AluguelException(AluguelException.DATAESTIMADAINCONSISTENTE);
        }

        if (!aluguel.getRetirada().equals(aluguelOriginal.getRetirada())) {
            throw new AluguelException(AluguelException.DATARETIRADAINCONSISTENTE);
        }

        return true;
    }

    private boolean validarParaAlugar(Aluguel aluguel) throws AluguelException {
        validacaoBasica(aluguel);
        if (aluguel.getRetirada().toLocalDate().compareTo(LocalDate.now()) < 0) {
            throw new AluguelException(AluguelException.DATAINVALIDA);
        }

        if (aluguel.getDevolucaoEstimada().toLocalDate().compareTo(LocalDate.now()) < 1) {
            throw new AluguelException(AluguelException.DATAINVALIDA);
        }

        Carro carro = aluguel.getCarro();
        if (carro == null || !carro.isAtivo() || !carro.isDisponivel()) {
            throw new AluguelException(AluguelException.INDISPONIVEL);
        }

        return true;
    }

    public boolean cadastrar(Aluguel aluguel) throws AluguelException {
        if (this.validarParaAlugar(aluguel)) {
            aluguel.setAtivo(true);
            return repositorio.cadastrar(aluguel);
        }
        return false;
    }

    public boolean alterar(Aluguel aluguel) throws AluguelException {
        if (this.validacaoBasica(aluguel)) {
            return this.repositorio.alterar(aluguel);
        }
        return false;
    }

    public Aluguel recuperarPorId(int id) {
        if (id > 0) {
            return this.repositorio.buscarPorId(id);
        }
        return null;
    }

    /**
     * Calcula o debito de um aluguel em aberto entrega um aluguel finalizado no
     * moemnto da chamada.
     *
     * @param aluguel - Objeto com os dados do aluguel em aberto
     * @param conseiderarHorario - Considerar a hora da entrega com tolerancia
     * de 30 minutos.
     * @return Objeto Aluguel no estado finalizado.
     */
    public Aluguel calcularDebito(Aluguel aluguel, boolean conseiderarHorario) {
        aluguel.setDevolucaoReal(LocalDateTime.now());
        LocalDateTime dataEstimada = aluguel.getDevolucaoEstimada();
        LocalDateTime dataDevolucao = aluguel.getDevolucaoReal();
        Period periodoTotal = Period.between(dataEstimada.toLocalDate(), dataDevolucao.toLocalDate());

        if (conseiderarHorario) {
            int minutosTolerancia = 30;
            if (dataDevolucao.compareTo(dataEstimada.plusMinutes(minutosTolerancia)) > 0) {
                periodoTotal.plusDays(1);
            }
        }
        long dias = periodoTotal.get(ChronoUnit.DAYS);
        BigDecimal adicional = aluguel.getCarro().getPreco().multiply(new BigDecimal(dias));
        aluguel.setCustoAdicional(adicional);
        return aluguel;
    }

    /**
     * Busca alugueis abertos para o cpf informado.
     *
     * @param cpf - CPF do aluguel em abero
     * @return Retorna uma coleção de alugueis em aberto.
     */
    public Aluguel consultarDebitoPorCpf(String cpf) throws CpfException {
        Aluguel aluguel = null;
//        if (CpfUtil.validarCPF(cpf)) {
//            aluguel = this.repositorio.buscarPorCpf(cpf);
//            
//
//        }
        return aluguel;
    }

    public Aluguel consultarDebitoPorPlaca(String placa, boolean considerarHorario) {
        Aluguel aluguel = repositorio.buscarPorPlaca(placa);
        if (aluguel != null) {
            aluguel = calcularDebito(aluguel, considerarHorario);
        }
        return aluguel;
    }

    public boolean devolucao(Aluguel aluguel) throws AluguelException {
        if (validarDevolucao(aluguel)) {
            return this.alterar(aluguel);
        }

        return false;
    }

}
