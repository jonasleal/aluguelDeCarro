/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.dados.entidades.Carro;
import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.AluguelRepositorioInterface;
import br.ufrpe.aluguelDeCarro.excecoes.*;
import br.ufrpe.aluguelDeCarro.servicos.CpfUtil;

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

    private final AluguelRepositorioInterface repositorio;

    public AluguelNegocio(AluguelRepositorioInterface repositorio) {
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

    private void validarDevolucao(Aluguel aluguel) throws AluguelException {
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
    }

    private void validarParaAlugar(Aluguel aluguel) throws AluguelException, CpfException {
        validacaoBasica(aluguel);
        if (buscarAbertoPorCpf(aluguel.getCliente().getCpf()) != null) {
            throw new AluguelException(AluguelException.CPFCONTEPENDENCIA);
        }
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
    }

    /**
     * Verifica os dados da solicitação de aluguel, caso todos os dados
     * obrigatórios tenha sido passado, retorna True, caso contrário levanta uma
     * exceção referente a causa da falha.
     *
     * @param aluguel Instancia a ser cadastrada
     * @return True - Se concluído com sucesso.
     * @throws AluguelException - Contem a causa e a mensagem de erro.
     * @throws CpfException     - Se o CPF não for passado ou valido.
     */
    public boolean cadastrar(Aluguel aluguel) throws AluguelException, CpfException {
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

    public Aluguel buscarPorId(int id) {
        return this.repositorio.buscarPorId(id);
    }

    /**
     * Calcula o debito de um aluguel em aberto e entrega um aluguel finalizado
     * no moemnto da chamada.
     *
     * @param aluguel            - Objeto com os dados do aluguel em aberto
     * @param conseiderarHorario - Considerar a hora da entrega com tolerância
     *                           de 30 minutos.
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
                periodoTotal = periodoTotal.plusDays(1);
            }
        }
        long dias = periodoTotal.get(ChronoUnit.DAYS);
        BigDecimal adicional = aluguel.getCarro().getPreco().multiply(new BigDecimal(dias));
        aluguel.setCustoAdicional(adicional);
        return aluguel;
    }

    /**
     * Busca e calcula o debito de um aluguel em aberto para um determinado cpf
     * entrega um aluguel finalizado no momento da chamada.
     *
     * @param cpf               - CPF registrado no aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância de
     *                          30 minutos.
     * @return Objeto Aluguel no estado finalizado.
     * @throws CpfException - Se o CPF não for passado ou valido.
     */
    public Aluguel consultarDebitoPorCpf(String cpf, boolean considerarHorario) throws CpfException {
        Aluguel aluguel = buscarAbertoPorCpf(cpf);

        if (aluguel != null) {
            aluguel = calcularDebito(aluguel, considerarHorario);
        }
        return aluguel;
    }

    /**
     * Busca debito de um aluguel em aberto para um determinado cpf.
     *
     * @param cpf - CPF registrado no aluguel em aberto
     * @return Intancia do aluguel aberto para este CPF.
     * @throws CpfException - Se o CPF não for passado ou valido.
     */
    public Aluguel buscarAbertoPorCpf(String cpf) throws CpfException {
        CpfUtil.validarCPF(cpf);
        return this.repositorio.buscarPorCpf(cpf);
    }

    /**
     * Busca debito de um aluguel em aberto para uma determinada placa.
     *
     * @param placa - CPF registrado no aluguel em aberto
     * @return Intancia do aluguel aberto para esta placa.
     */
    public Aluguel buscarAbertoPorPlaca(String placa) {
        return repositorio.buscarPorPlaca(placa);
    }

    /**
     * Busca e calcula o debito de um aluguel em aberto para um determinado cpf
     * entrega um aluguel finalizado no momento da chamada.
     *
     * @param placa             - CPF registrado no aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância de
     *                          30 minutos.
     * @return Objeto Aluguel no estado finalizado.
     */
    public Aluguel consultarDebitoPorPlaca(String placa, boolean considerarHorario) {
        Aluguel aluguel = buscarAbertoPorPlaca(placa);
        if (aluguel != null) {
            aluguel = calcularDebito(aluguel, considerarHorario);
        }
        return aluguel;
    }

    /**
     * Verifica a consistência datas passados com as já cadastradas, se forem
     * iguais registra a devolução, caso contrário levanta exceção com a causa.
     *
     * @param aluguel - Aluguel no estado finalizado.
     * @return True - Se registrado com sucesso.
     * @throws AluguelException - Contem a mensagem e causa do erro.
     */
    public boolean devolucao(Aluguel aluguel) throws AluguelException {
        validarDevolucao(aluguel);
        return this.alterar(aluguel);
    }

    public ArrayList<Aluguel> buscarTodos() {
        return this.repositorio.buscarTodos();
    }

}
