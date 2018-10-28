package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class Aluguel extends Entidade {
    private LocalDate retirada;
    private LocalDate devolucaoEstimada;
    private LocalDate devolucaoReal;
    private double valorEstimado;
    private double valorFinal;
    private Cliente cliente;
    private Carro carro;

    public Aluguel() {
    }

    public Aluguel(LocalDate retirada, LocalDate devolucaoEstimada, LocalDate devolucaoReal, double valorEstimado,
                   double valorFinal, Cliente cliente, Carro carro) {
        this.retirada = retirada;
        this.devolucaoEstimada = devolucaoEstimada;
        this.devolucaoReal = devolucaoReal;
        this.valorEstimado = valorEstimado;
        this.valorFinal = valorFinal;
        this.cliente = cliente;
        this.carro = carro;
    }

    public LocalDate getRetirada() {
        return retirada;
    }

    public void setRetirada(LocalDate retirada) {
        this.retirada = retirada;
    }

    public LocalDate getDevolucaoEstimada() {
        return devolucaoEstimada;
    }

    public void setDevolucaoEstimada(LocalDate devolucaoEstimada) {
        this.devolucaoEstimada = devolucaoEstimada;
    }

    public LocalDate getDevolucaoReal() {
        return devolucaoReal;
    }

    public void setDevolucaoReal(LocalDate devolucaoReal) {
        this.devolucaoReal = devolucaoReal;
    }

    public double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
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
}
