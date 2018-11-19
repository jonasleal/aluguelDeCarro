package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Fernando
 */
public class Reserva implements Cloneable {

    private int id;
    private boolean ativo;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

    @Override
    public Reserva clone() {
        try {
            return (Reserva) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone não efetuado");
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return id == reserva.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
