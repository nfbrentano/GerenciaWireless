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
public class CadastroPessoa {

    private int idcadastroPessoa;
    private String nome;
    private String sobrenome;
    private String documento;
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String endereco;
    private String numeroendereco;
    private String nomeusuario;

    public String getNomeusuario() {
        return nomeusuario;
    }

    public void setNomeusuario(String nomeusuario) {
        this.nomeusuario = nomeusuario;
    }

    public String getSenhaacesso() {
        return senhaacesso;
    }

    public void setSenhaacesso(String senhaacesso) {
        this.senhaacesso = senhaacesso;
    }
    private String senhaacesso;

    public int getIdcadastroPessoa() {
        return idcadastroPessoa;
    }

    public void setIdcadastroPessoa(int idcadastroPessoa) {
        this.idcadastroPessoa = idcadastroPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumeroendereco() {
        return numeroendereco;
    }

    public void setNumeroendereco(String numeroendereco) {
        this.numeroendereco = numeroendereco;
    }
    

   
    
}
