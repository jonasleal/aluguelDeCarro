package br.ufrpe.aluguelDeCarro.dados.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.AluguelException;
import br.ufrpe.aluguelDeCarro.excecoes.CarroException;
import br.ufrpe.aluguelDeCarro.excecoes.CpfException;
import br.ufrpe.aluguelDeCarro.excecoes.HabilitacaoException;
import br.ufrpe.aluguelDeCarro.excecoes.IdadeExcetion;
import br.ufrpe.aluguelDeCarro.excecoes.MarcaException;
import br.ufrpe.aluguelDeCarro.excecoes.ModeloException;
import br.ufrpe.aluguelDeCarro.excecoes.NomeException;
import br.ufrpe.aluguelDeCarro.excecoes.PlacaException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Fernando
 */
public class Aluguel extends Entidade implements Cloneable {

    private LocalDateTime retirada;
    private LocalDateTime devolucaoEstimada;
    private LocalDateTime devolucaoReal;
    private BigDecimal valorEstimado;
    private BigDecimal custoAdicional;
    private BigDecimal desconto;
    private Cliente cliente;
    private Carro carro;
    private Usuario usuario;

    public Aluguel() {
    }

    public Aluguel(Cliente cliente, Carro carro, Usuario usuario) {
        this.retirada = LocalDateTime.now();
        this.devolucaoEstimada = this.retirada.plusDays(1);
        this.devolucaoReal = null;
        this.valorEstimado = new BigDecimal(0);
        this.custoAdicional = new BigDecimal(0);
        this.cliente = cliente;
        this.carro = carro;
        this.usuario = usuario;
    }

    public LocalDateTime getRetirada() {
        return retirada;
    }

    public void setRetirada(LocalDateTime retirada) {
        this.retirada = retirada;
    }

    public LocalDateTime getDevolucaoEstimada() {
        return devolucaoEstimada;
    }

    public void setDevolucaoEstimada(LocalDateTime devolucaoEstimada) {
        this.devolucaoEstimada = devolucaoEstimada;
    }

    public LocalDateTime getDevolucaoReal() {
        return devolucaoReal;
    }

    public void setDevolucaoReal(LocalDateTime devolucaoReal) {
        this.devolucaoReal = devolucaoReal;
    }

    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(BigDecimal valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public BigDecimal getCustoAdicional() {
        return custoAdicional;
    }

    public void setCustoAdicional(BigDecimal custoAdicional) {
        this.custoAdicional = custoAdicional;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean validar() throws AluguelException, CpfException, IdadeExcetion, NomeException, HabilitacaoException, PlacaException, MarcaException, ModeloException, CarroException {
        this.cliente.validar();
        this.carro.validar();
        this.usuario.validar();

        if (this.valorEstimado.compareTo(BigDecimal.ZERO) < 0) {
            throw new AluguelException(AluguelException.VALORINVALIDO);
        }
        if (this.custoAdicional.compareTo(BigDecimal.ZERO) < 0) {
            throw new AluguelException(AluguelException.VALORINVALIDO);
        }
        return true;
    }

    @Override
    public Aluguel clone() {
        try {
            return (Aluguel) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone não efetuado");
        }
        return this;
    }

    /**
     * calcula o valor estimado do aluguel, multiplicando o valor do preço do carro (diária) pela quantidade de dias do
     * aluguel, e coloca o valor no atributo valorEstimado
     */
    public void calcularValorEstimado() {
        if (this.retirada != null && this.devolucaoEstimada != null && this.carro != null) {
            long horas = ChronoUnit.HOURS.between(this.retirada, this.devolucaoEstimada);
            double dias = horas / 24.0;
            this.valorEstimado = new BigDecimal((dias * this.carro.getPreco().doubleValue()));
        }
    }

    @Override
    public String toString() {
        return "Aluguel{" +
                "retirada=" + retirada +
                ", devolucaoEstimada=" + devolucaoEstimada +
                ", devolucaoReal=" + devolucaoReal +
                ", valorEstimado=" + valorEstimado +
                ", custoAdicional=" + custoAdicional +
                ", desconto=" + desconto +
                ", cliente=" + cliente +
                ", carro=" + carro +
                ", usuario=" + usuario +
                '}';
    }
}
