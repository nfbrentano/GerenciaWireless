/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author natan
 */
public class Roteador {

    private int idpontoacesso;
    private String ssid;
    private String modelo;
    private String largurabanda;
    private String frequencia;
    private String iproteador;
    private String usuario;
    private String pass;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    private boolean disponibilidade;

    public int getIdpontoacesso() {
        return idpontoacesso;
    }

    public void setIdpontoacesso(int idpontoacesso) {
        this.idpontoacesso = idpontoacesso;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLargurabanda() {
        return largurabanda;
    }

    public void setLargurabanda(String largurabanda) {
        this.largurabanda = largurabanda;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getIproteador() {
        return iproteador;
    }

    public void setIproteador(String iproteador) {
        this.iproteador = iproteador;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
