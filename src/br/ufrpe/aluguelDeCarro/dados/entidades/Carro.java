/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.dados.entidades;

import br.ufrpe.aluguelDeCarro.excecoes.CarroException;
import br.ufrpe.aluguelDeCarro.excecoes.MarcaException;
import br.ufrpe.aluguelDeCarro.excecoes.ModeloException;
import br.ufrpe.aluguelDeCarro.excecoes.PlacaException;

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
     *
     * @throws PlacaException  - Se a placa passada estiver fora do padrão de 3
     *                         letras e 4 dígitos.
     * @throws MarcaException  - Se não for passado uma marca.
     * @throws ModeloException - Se não for passado um modelo.
     * @throws CarroException  - Se número de portas, ocupantes, cambio, direção
     *                         categoria ou valor da diária não for passado ou for passado um valor
     *                         diferente dos valores validos.
     */

    public void validar() throws PlacaException, MarcaException, ModeloException, CarroException {

        if (placa == null || placa.isEmpty()) {
            throw new PlacaException(PlacaException.NULL);
        }
        if (!placa.matches("[a-zA-Z]{3}\\d{4}")) {
            throw new PlacaException(PlacaException.INVALIDA);
        }
        if (marca == null || marca.isEmpty()) {
            throw new MarcaException(MarcaException.NULL);
        }
        if (!marca.matches("[a-zA-Z]{2,}")) {
            throw new MarcaException(MarcaException.INVALIDA);
        }
        if (modelo == null || modelo.isEmpty()) {
            throw new ModeloException(ModeloException.NULL);
        }
        if (!modelo.matches("[a-zA-Z]{2,}")) {
            throw new ModeloException(ModeloException.INVALIDA);
        }
        if (portas < 1) {
            throw new CarroException(CarroException.NUMERO_PORTAS);
        }
        if (ocupantes < 1) {
            throw new CarroException(CarroException.NUMERO_OCUPANTES);
        }
        if (cambio == null || (cambio.getValor() < 1 || cambio.getValor() > 3)) {
            throw new CarroException(CarroException.CAMBIO_INVALIDO);
        }
        if (direcao == null || (direcao.getValor() < 1 || direcao.getValor() > 3)) {
            throw new CarroException(CarroException.DIRECAO_INVALIDA);
        }
//        if (categoria == null || (categoria.getValor() < 1 || categoria.getValor() > Categoria.values().length)) {
//            throw new CarroException(CarroException.CATEGORIA_INVALIDA);
//        }
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
        return "Carro{"
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return id == carro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
