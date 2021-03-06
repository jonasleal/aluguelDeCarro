/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.negocio.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.carro.*;

import java.util.Objects;

/**
 * @author JonasJr
 */
public class Carro implements Cloneable {

    private int id;
    private boolean ativo;
    private String placa;
    private String modelo;
    private String marca;
    private int portas;
    private int ocupantes;
    private Categoria categoria;
    private Cambio cambio;
    private Direcao direcao;
    private boolean arCondicionado;
    private boolean airBag;
    private boolean travaEletrica;
    private boolean freioAbs;
    private boolean vidroEletrico;
    private boolean disponivel;

    public Carro() {
        this.placa = "";
        this.modelo = "";
        this.marca = "";
        this.categoria = new Categoria();
        this.cambio = Cambio.MANUAL;
        this.direcao = Direcao.MECANICA;
    }

    public Carro(String placa, String modelo, String moarca, Categoria categoria) {
        this();
        this.placa = placa;
        this.modelo = modelo;
        this.marca = moarca;
        this.categoria = categoria;
    }

    public Carro(String placa, String modelo, String marca, int portas, int ocupantes, Categoria categoria, Cambio cambio, Direcao direcao, boolean arCondicionado, boolean airBag, boolean travaEletrica, boolean freioAbs, boolean vidroEletrico) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.portas = portas;
        this.ocupantes = ocupantes;
        this.categoria = categoria;
        this.cambio = cambio;
        this.direcao = direcao;
        this.arCondicionado = arCondicionado;
        this.airBag = airBag;
        this.travaEletrica = travaEletrica;
        this.freioAbs = freioAbs;
        this.vidroEletrico = vidroEletrico;
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    public int getOcupantes() {
        return ocupantes;
    }

    public void setOcupantes(int ocupantes) {
        this.ocupantes = ocupantes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public Direcao getDirecao() {
        return direcao;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public boolean isAirBag() {
        return airBag;
    }

    public void setAirBag(boolean airBag) {
        this.airBag = airBag;
    }

    public boolean isTravaEletrica() {
        return travaEletrica;
    }

    public void setTravaEletrica(boolean travaEletrica) {
        this.travaEletrica = travaEletrica;
    }

    public boolean isFreioAbs() {
        return freioAbs;
    }

    public void setFreioAbs(boolean freioAbs) {
        this.freioAbs = freioAbs;
    }

    public boolean isVidroEletrico() {
        return vidroEletrico;
    }

    public void setVidroEletrico(boolean vidroEletrico) {
        this.vidroEletrico = vidroEletrico;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Valida os dados obrigatórios para um carro
     * <p>
     * @throws PlacaObrigatorioException - Se não for passado uma placa.
     * @throws MarcaObrigatorioException - Se não for passado uma marca.
     * @throws ModeloObrigatorioException - Se não for passado um modelo.
     * @throws NumeroDePortasException - Se for passado um numero de portas menor que 1.
     * @throws NumeroDeOcupantesException - Se for passado um numero de ocupantes menor que 1.
     * 
     * @throws FormatoPlacaInvalidoException - Se a placa passada estiver fora
     * do padrão de 3 letras e 4 dígitos.
     * @throws FormatoMarcaException - Se a marca não tiver pelo menos dois
     * caracteres alfabeticos
     * @throws FormatoModeloException - Se o modelo não tiver pelo menos dois
     * caracteres alfabeticos
     *@throws CambioInvalidoException - Se for passado um valor diferente de Manual, Automatico ou CVT
     *@throws DirecaoInvalidaException - Se for passado um valor diferente de Mecanica, Hidraulica ou Eletrica
     
     */
    public void validar() throws CarroInvalidoException {

        if (placa == null || placa.isEmpty()) {
            throw new PlacaObrigatorioException();
        }
        if (!placa.matches("[a-zA-Z]{3}\\d{4}")) {
            throw new FormatoPlacaInvalidoException(placa);
        }
        if (marca == null || marca.isEmpty()) {
            throw new MarcaObrigatorioException();
        }
        if (!marca.matches("[a-zA-Z]{2,}")) {
            throw new FormatoMarcaException(marca);
        }
        if (modelo == null || modelo.isEmpty()) {
            throw new ModeloObrigatorioException();
        }
        if (!modelo.matches("[a-zA-Z]{2,}")) {
            throw new FormatoModeloException(modelo);
        }
        if (portas < 1) {
            throw new NumeroDePortasException(portas);
        }
        if (ocupantes < 1) {
            throw new NumeroDeOcupantesException(ocupantes);
        }
        if (cambio == null || (cambio.getValor() < 1 || cambio.getValor() > 3)) {
            throw new CambioInvalidoException();
        }
        if (direcao == null || (direcao.getValor() < 1 || direcao.getValor() > 3)) {
            throw new DirecaoInvalidaException();
        }
    }

    @Override
    public Carro clone() {
        try {
            return (Carro) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone não efetuado");
        }
        return this;
    }

    @Override
    public String toString() {
        return "carro{"
                + "placa='" + placa + '\''
                + ", modelo='" + modelo + '\''
                + ", marca='" + marca + '\''
                + ", portas=" + portas
                + ", ocupantes=" + ocupantes
                + ", categoria=" + categoria
                + ", cambio=" + cambio
                + ", direcao=" + direcao
                + ", arCondicionado=" + arCondicionado
                + ", airBag=" + airBag
                + ", travaEletrica=" + travaEletrica
                + ", freioAbs=" + freioAbs
                + ", vidroEletrico=" + vidroEletrico
                + ", disponivel=" + disponivel
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
        Carro carro = (Carro) o;
        return id == carro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
