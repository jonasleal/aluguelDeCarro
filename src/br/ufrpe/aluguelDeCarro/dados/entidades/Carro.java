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

import java.math.BigDecimal;

/**
 * @author JonasJr
 */
public class Carro implements Cloneable {

    private int id;
    private boolean ativo;
    private String placa;
    private String modelo;
    private String marca;
    private BigDecimal preco;
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
    }

    public Carro(String placa, String modelo, String moarca, Categoria categoria, BigDecimal preco) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = moarca;
        this.portas = 0;
        this.ocupantes = 0;
        this.categoria = categoria;
        this.cambio = Cambio.MANUAL;
        this.direcao = Direcao.MECANICA;
        this.arCondicionado = false;
        this.airBag = false;
        this.travaEletrica = false;
        this.freioAbs = false;
        this.vidroEletrico = false;
        this.disponivel = false;
        this.preco = preco;
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    /**
     * Valida os dados obrigatórios para um carro
     *
     * @return Se toda a validação tiver sucesso.
     * @throws PlacaException  - Se a placa passada estiver fora do padrão de 3
     *                         letras e 4 dígitos.
     * @throws MarcaException  - Se não for passado uma marca.
     * @throws ModeloException - Se não for passado um modelo.
     * @throws CarroException  - Se número de portas, ocupantes, cambio, direção
     *                         categoria ou valor da diária não for passado ou for passado um valor
     *                         diferente dos valores validos.
     */

    public boolean validar() throws PlacaException, MarcaException, ModeloException, CarroException {

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
            throw new CarroException(CarroException.NUMPORTAS);
        }
        if (ocupantes < 1) {
            throw new CarroException(CarroException.NUMOCUPANTES);
        }
        if (cambio == null || (cambio.getValor() < 1 || cambio.getValor() > 3)) {
            throw new CarroException(CarroException.CAMBIOINVALIDO);
        }
        if (direcao == null || (direcao.getValor() < 1 || direcao.getValor() > 3)) {
            throw new CarroException(CarroException.DIRECAOINVALIDO);
        }
        if (categoria == null || (categoria.getValor() < 1 || categoria.getValor() > Categoria.values().length)) {
            throw new CarroException(CarroException.CATEGORIAINVALIDO);
        }
        if (preco.compareTo(new BigDecimal(0)) < 1) {
            throw new CarroException(CarroException.PRECOINVALIDO);
        }

        return true;
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
                + ", preco=" + preco
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
}
