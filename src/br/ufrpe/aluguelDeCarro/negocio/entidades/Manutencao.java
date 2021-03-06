package br.ufrpe.aluguelDeCarro.negocio.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Fernando
 */
public class Manutencao implements Cloneable {

    private int id;
    private boolean ativo;
    private Usuario usuario;
    private Carro carro;
    private List<String> afazeres;
    private LocalDate data;
    private BigDecimal orcamento;

    public Manutencao() {
        this.usuario = new Usuario();
        this.afazeres = new ArrayList<>();
        this.data = LocalDate.now();
        this.orcamento = BigDecimal.ZERO;
    }

    public Manutencao(Usuario usuario, Carro carro, List<String> afazeres, LocalDate data, BigDecimal orcamento) {
        this.usuario = usuario;
        this.carro = carro;
        this.afazeres = afazeres;
        this.data = data;
        this.orcamento = orcamento;
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

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public List<String> getAfazeres() {
        return afazeres;
    }

    public void setAfazeres(List<String> afazeres) {
        this.afazeres = afazeres;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(BigDecimal orcamento) {
        this.orcamento = orcamento;
    }

    @Override
    public Manutencao clone() {
        try {
            return (Manutencao) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone não efetuado");
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manutencao that = (Manutencao) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
