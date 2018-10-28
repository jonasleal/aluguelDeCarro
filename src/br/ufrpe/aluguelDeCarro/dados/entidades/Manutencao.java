package br.ufrpe.aluguelDeCarro.dados.entidades;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fernando
 */
public class Manutencao extends Entidade {
    private Gerente gerente;
    private Carro carro;
    private List<String> afazeres;
    private LocalDate data;
    private double orcamento;

    public Manutencao() {
    }

    public Manutencao(Gerente gerente, Carro carro, List<String> afazeres, LocalDate data, double orcamento) {
        this.gerente = gerente;
        this.carro = carro;
        this.afazeres = afazeres;
        this.data = data;
        this.orcamento = orcamento;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
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

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }
}
