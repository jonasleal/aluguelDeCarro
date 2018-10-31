package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDate;

/**
 * @author Fernando
 */
public class Reserva extends Entidade {
    private Usuario usuario;
    private Cliente cliente;
    private Carro carro;
    private LocalDate retiradaPrevista;

    public Reserva() {
    }

    public Reserva(Atendente atendente, Cliente cliente, Carro carro, LocalDate retiradaPrevista) {
        this.usuario = atendente;
        this.cliente = cliente;
        this.carro = carro;
        this.retiradaPrevista = retiradaPrevista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Atendente usuario) {
        this.usuario = usuario;
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
