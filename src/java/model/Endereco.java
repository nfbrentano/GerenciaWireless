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
public class Endereco {
    private int idendereco;
    private String rua;
    private String bairro_idbairro;
    private boolean disponibilidade;

    public int getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(int idendereco) {
        this.idendereco = idendereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro_idbairro() {
        return bairro_idbairro;
    }

    public void setBairro_idbairro(String bairo_idbairro) {
        this.bairro_idbairro = bairo_idbairro;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

  
    
}
