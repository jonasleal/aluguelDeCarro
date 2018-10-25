/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.aluguelDeCarro.model;

import br.ufrpe.aluguelDeCarro.model.genericas.Entidade;
import br.ufrpe.aluguelDeCarro.constantes.Cambio;
import br.ufrpe.aluguelDeCarro.constantes.Direcao;
import br.ufrpe.aluguelDeCarro.constantes.TipoCarro;

/**
 *
 * @author JonasJr
 */
public class Carro extends Entidade {

    private String placa;
    private int portas;
    private int ocupantes;
    private TipoCarro tipo;
    private Cambio cambio;
    private Direcao direcao;

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
    private boolean arCondicionado;
    private boolean airBag;
    private boolean travaEletrica;
    private boolean freioAbs;
    private boolean vidroEletrico;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public TipoCarro getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarro tipo) {
        this.tipo = tipo;
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

}
