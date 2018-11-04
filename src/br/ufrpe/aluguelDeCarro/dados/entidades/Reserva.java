package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDateTime;

/**
 * @author Fernando
 */
public class Reserva extends Entidade implements Cloneable{
    private Usuario usuario;
    private Cliente cliente;
    private Carro carro;
    private LocalDateTime retiradaPrevista;

    public Reserva() {
    }

    public Reserva(Usuario usuario, Cliente cliente, Carro carro, LocalDateTime retiradaPrevista) {
        this.usuario = usuario;
        this.cliente = cliente;
        this.carro = carro;
        this.retiradaPrevista = retiradaPrevista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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

    public LocalDateTime getRetiradaPrevista() {
        return retiradaPrevista;
    }

    public void setRetiradaPrevista(LocalDateTime retiradaPrevista) {
        this.retiradaPrevista = retiradaPrevista;
    }

    public Reserva clone() {
        try {
            return (Reserva) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone não efetuado");
        }
        return this;
    }
}
