/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio;

import br.ufrpe.aluguelDeCarro.dados.repositorios.interfaces.IAluguelRepositorio;
import br.ufrpe.aluguelDeCarro.excecoes.aluguel.*;
import br.ufrpe.aluguelDeCarro.excecoes.bancoDeDados.IdNaoEncontradoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroIndisponivelException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroObrigatorioException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Aluguel;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Carro;
import br.ufrpe.aluguelDeCarro.negocio.entidades.Cliente;

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

    private void validacaoBasica(Aluguel aluguel) throws AluguelInvalidoException {
        try {
            aluguel.validar();
        } catch (CarroInvalidoException | PessoaInvalidaException | ClienteInvalidoException e) {
            throw new AluguelInvalidoException(e.getMessage(), e.fillInStackTrace());
        }
    }

    private void validarDevolucao(Aluguel aluguel) throws AluguelInvalidoException, IdNaoEncontradoException, ClienteInvalidoException {
        validacaoBasica(aluguel);
        Aluguel aluguelOriginal = repositorio.consultar(aluguel.getId());

        LocalDateTime dataNoBanco = aluguelOriginal.getDevolucaoReal();

        if (dataNoBanco != null) {
            throw new AluguelFinalizadoException();
        }

        dataNoBanco = aluguelOriginal.getDevolucaoEstimada();

        if (!aluguel.getDevolucaoEstimada().equals(dataNoBanco)) {
            throw new DataEstimadaInconsistenteException(dataNoBanco, aluguel.getDevolucaoEstimada());
        }
        if (!aluguel.getRetirada().equals(aluguelOriginal.getRetirada())) {
            throw new DataRetiradaInconsistenteException(dataNoBanco, aluguel.getRetirada());
        }
    }

    private void validarParaAlugar(Aluguel aluguel) throws AluguelInvalidoException, CarroInvalidoException, ClienteInvalidoException {
        validacaoBasica(aluguel);
        if (this.repositorio.existe(aluguel.getCliente())) {
            throw new AluguelEmAbertoException(aluguel.getCliente().getCpf());
        }
        LocalDate dataNoObjeto = aluguel.getRetirada().toLocalDate();
        if (dataNoObjeto.compareTo(LocalDate.now()) < 0) {
            throw new DataRetiradaPassadoException(dataNoObjeto);
        }
        dataNoObjeto = aluguel.getDevolucaoEstimada().toLocalDate();
        if (dataNoObjeto.compareTo(LocalDate.now()) < 1) {
            throw new DataEstimadaPassado(dataNoObjeto);
        }
        Carro carro = aluguel.getCarro();
        if (carro == null) {
            throw new CarroObrigatorioException();
        }
        if (!carro.isAtivo() || !carro.isDisponivel()) {
            throw new CarroIndisponivelException(carro.getPlaca());
        }
    }

    /**
     * Verifica os dados da solicitação de aluguel, caso todos os dados
     * obrigatórios tenha sido passado, retorna True, caso contrário levanta uma
     * exceção referente a causa da falha.
     *
     * @param aluguel Instancia a ser cadastrada
     * @return True - Se concluído com sucesso.
     * @throws AluguelInvalidoException - Contem a causa e a mensagem de erro.
     */
    public boolean cadastrar(Aluguel aluguel) throws AluguelInvalidoException, CarroInvalidoException, ClienteInvalidoException {
        if (aluguel != null) {
            this.validarParaAlugar(aluguel);
            aluguel.setAtivo(true);
            aluguel.getCarro().setDisponivel(false);
            return repositorio.cadastrar(aluguel);
        }
        return false;
    }

    public boolean alterar(Aluguel aluguel) throws AluguelInvalidoException, ClienteInvalidoException {
        if (aluguel != null) {
            this.validacaoBasica(aluguel);
            return this.repositorio.alterar(aluguel);
        }
        return false;
    }

    public Aluguel consultar(int id) throws AluguelNaoEncontradoException, IdNaoEncontradoException {
        return this.repositorio.consultar(id);
    }

    /**
     * Calcula o debito do aluguel em aberto e o finaliza no momento da chamada.
     *
     * @param aluguel - Objeto com os dados do aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância de
     * 30 minutos.
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
     * Busca e calcula o debito de um aluguel em aberto para um determinado
     * cliente entrega um aluguel finalizado no momento da chamada.
     *
     * @param cliente - cliente registrado no aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância de
     * 30 minutos.
     * @return Objeto aluguel no estado finalizado.
     */
    public Aluguel consultarDebito(Cliente cliente, boolean considerarHorario) throws AluguelNaoEncontradoException {
        Aluguel aluguel = consultar(cliente);
        if (aluguel != null) {
            calcularDebito(aluguel, considerarHorario);
        }
        return aluguel;
    }

    /**
     * Busca e calcula o debito de um aluguel em aberto para um determinado
     * carro entrega um aluguel finalizado no momento da chamada.
     *
     * @param carro - carro registrado no aluguel em aberto
     * @param considerarHorario - Considerar a hora da entrega com tolerância de
     * 30 minutos.
     * @return Objeto aluguel no estado finalizado.
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
     * @param aluguel - aluguel no estado finalizado.
     * @return True - Se registrado com sucesso.
//     * @throws AluguelException - Contem a mensagem e causa do erro.
     */
    public boolean devolucao(Aluguel aluguel) throws AluguelInvalidoException, IdNaoEncontradoException, ClienteInvalidoException {
        validarDevolucao(aluguel);
        return this.alterar(aluguel);
    }

    public ArrayList<Aluguel> consultarTodos() {
        return this.repositorio.consultarTodos();
    }

}
