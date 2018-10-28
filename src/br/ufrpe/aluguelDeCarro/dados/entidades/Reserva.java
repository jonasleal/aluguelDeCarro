package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class Reserva extends Entidade {
    private Atendente atendente;
    private Cliente cliente;
    private Carro carro;
    private LocalDate retiradaPrevista;

    public Reserva() {
    }

    public Reserva(Atendente atendente, Cliente cliente, Carro carro, LocalDate retiradaPrevista) {
        this.atendente = atendente;
        this.cliente = cliente;
        this.carro = carro;
        this.retiradaPrevista = retiradaPrevista;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
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

    public LocalDate getRetiradaPrevista() {
        return retiradaPrevista;
    }

    public void setRetiradaPrevista(LocalDate retiradaPrevista) {
        this.retiradaPrevista = retiradaPrevista;
    }
}
