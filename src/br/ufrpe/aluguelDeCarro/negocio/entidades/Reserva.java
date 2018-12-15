package br.ufrpe.aluguelDeCarro.negocio.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.reserva.*;

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
    private Categoria categoria;
    private LocalDateTime retiradaPrevista;
    private LocalDateTime devolucaoPrevista;

    public Reserva() {
        this.usuario = new Usuario();
        this.cliente = new Cliente();
        this.categoria = new Categoria();
        this.retiradaPrevista = LocalDateTime.now();
        this.devolucaoPrevista = LocalDateTime.now();
    }

    public Reserva(Usuario usuario, Cliente cliente, Categoria categoria, LocalDateTime retiradaPrevista, LocalDateTime devolucaoPrevista) {
        this.usuario = usuario;
        this.cliente = cliente;
        this.categoria = categoria;
        this.retiradaPrevista = retiradaPrevista;
        this.devolucaoPrevista = devolucaoPrevista;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getRetiradaPrevista() {
        return retiradaPrevista;
    }

    public void setRetiradaPrevista(LocalDateTime retiradaPrevista) {
        this.retiradaPrevista = retiradaPrevista;
    }

    public LocalDateTime getDevolucaoPrevista() {
        return devolucaoPrevista;
    }

    public void setDevolucaoPrevista(LocalDateTime devolucaoPrevista) {
        this.devolucaoPrevista = devolucaoPrevista;
    }

    public void validar() throws ReservaInvalidaException {
        if (this.retiradaPrevista == null) throw new DataRetiradaObrigatoriaException();
        if (this.devolucaoPrevista == null) throw new DataDevolucaoObrigatoriaException();
        if (this.cliente == null) throw new ClienteObrigatorioException();
        if (this.categoria == null) throw new CategoriaObrigatoriaException();
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
