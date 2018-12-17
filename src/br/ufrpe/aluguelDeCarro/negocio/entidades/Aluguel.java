package br.ufrpe.aluguelDeCarro.negocio.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.aluguel.*;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.carro.CarroObrigatorioException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.categoria.CategoriaObrigatorioException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.cliente.ClienteObrigatorioException;
import br.ufrpe.aluguelDeCarro.excecoes.pessoa.PessoaInvalidaException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioInvalidoException;
import br.ufrpe.aluguelDeCarro.excecoes.usuario.UsuarioObrigatorioException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * @author Fernando
 */
public class Aluguel implements Cloneable {

    private int id;
    private boolean ativo;
    private LocalDateTime retirada;
    private LocalDateTime devolucaoEstimada;
    private LocalDateTime devolucaoReal;
    private BigDecimal valorEstimado;
    private BigDecimal custoAdicional;
    //    private BigDecimal desconto;
    private Cliente cliente;
    private Carro carro;
    private Categoria categoria;
    private Usuario usuario;

    public Aluguel() {
        this.retirada = LocalDateTime.now();
        this.devolucaoEstimada = this.retirada.plusDays(1);
        this.devolucaoReal = null;
        this.valorEstimado = BigDecimal.ZERO;
        this.custoAdicional = BigDecimal.ZERO;
        this.cliente = new Cliente();
        this.carro = new Carro();
        this.usuario = new Usuario();
        this.categoria = new Categoria();

    }

    public Aluguel(Cliente cliente, Carro carro, Categoria categoria, Usuario usuario) {
        this();
        this.cliente = cliente;
        this.carro = carro;
        this.categoria = categoria;
        this.usuario = usuario;
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

    //    public BigDecimal getDesconto() {
//        return desconto;
//    }
//
//    public void setDesconto(BigDecimal desconto) {
//        this.desconto = desconto;
//    }
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Valida os dados obrigatórios para o aluguel.
     * <p>
     * @throws ClienteObrigatorioException - Se o cliente nao for informado
     * @throws CarroObrigatorioException - Se o carro nao for informado
     * @throws UsuarioObrigatorioException - Se o usuario nao for informado
     * @throws CategoriaObrigatorioException - Se a categoria nao for informado
     * @throws DataRetiradaObrigatoriaException - Se a data de retirada nao for
     * informado
     * @throws DataDevolucacaoEstimadaObrigatoriaException - Se a data de
     * devolução estimada nao for informado
     * @throws ValorEstimadoNegativoException - Se o custo estimada for
     * negativo.
     * @throws CustoAdicionalNegativoException - Se o custo adicional for
     * negativo.
     * @throws PessoaInvalidaException - Se as validações de Pessoa falharem
     * @throws ClienteInvalidoException - Se as validações de Cliente falharem
     * @throws CategoriaInvalidaException - Se as validações de Cliente falharem
     * 
     */
    public void validar() throws PessoaInvalidaException, CarroInvalidoException, AluguelInvalidoException, ClienteInvalidoException, UsuarioInvalidoException, CategoriaInvalidaException {
        if (this.getCliente() == null) {
            throw new ClienteObrigatorioException();
        }
        if (this.getCarro() == null) {
            throw new CarroObrigatorioException();
        }
        if (this.getUsuario() == null) {
            throw new UsuarioObrigatorioException();
        }
        if (this.getCategoria() == null) {
            throw new CategoriaObrigatorioException();
        }
        if (this.getRetirada() == null) {
            throw new DataRetiradaObrigatoriaException();
        }
        if (this.getDevolucaoEstimada() == null) {
            throw new DataDevolucacaoEstimadaObrigatoriaException();
        }
        this.cliente.validar();
        this.categoria.validar();
        this.carro.validar();
        this.usuario.validar();

        if (this.valorEstimado.compareTo(BigDecimal.ZERO) < 0) {

            throw new ValorEstimadoNegativoException(valorEstimado);
        }
        if (this.custoAdicional.compareTo(BigDecimal.ZERO) < 0) {
            throw new CustoAdicionalNegativoException(valorEstimado);
        }
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

    public static BigDecimal calcularValorEstimado(LocalDateTime retirada, LocalDateTime devolucao, Categoria categoria) {
        long horas = ChronoUnit.HOURS.between(retirada, devolucao);
        double dias = horas / 24.0;
        return new BigDecimal((dias * categoria.getDiaria().doubleValue()));
    }

    /**
     * calcula o valor estimado do aluguel, multiplicando o valor da diaria pela
     * quantidade de dias do aluguel, e coloca o valor no atributo valorEstimado
     */
    public void calcularValorEstimado() {
        if (this.retirada != null && this.devolucaoEstimada != null) {
            long horas = ChronoUnit.HOURS.between(this.retirada, this.devolucaoEstimada);
            double dias = horas / 24.0;
            this.valorEstimado = new BigDecimal((dias * this.categoria.getDiaria().doubleValue()));
        }
    }

    @Override
    public String toString() {
        return "aluguel{"
                + "retirada=" + retirada
                + ", devolucaoEstimada=" + devolucaoEstimada
                + ", devolucaoReal=" + devolucaoReal
                + ", valorEstimado=" + valorEstimado
                + ", custoAdicional=" + custoAdicional
                + //                ", desconto=" + desconto +
                ", cliente=" + cliente
                + ", carro=" + carro
                + ", usuario=" + usuario
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aluguel aluguel = (Aluguel) o;
        return id == aluguel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
