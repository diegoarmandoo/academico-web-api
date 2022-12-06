package br.com.academico.endereco;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class Endereco implements Serializable {

    // Atributos de Instância ou do Objeto

    private int id;

    @Range(min=10000000, max=99999999, message = "O atributo CEP deve ser inteiro e ter no mínimo 8 algarismos.")
    private int CEP;

    @Size(min=5, max=50, message = "O atributo rua deve conter no mínimo 5 e no máximo 50 caracteres.")
    @NotEmpty(message = "O atributo rua não pode ser nulo nem vazio.")
    private String rua;

    private String bairro;
    private String cidade;
    private String estado;
    private StatusEndereco status;

    // Gets e Sets

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int cEP) {
        CEP = cEP;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public StatusEndereco getStatus() {
        return status;
    }

    public void setStatus(StatusEndereco status){
        this.status = status;
    }

    // Construtores

    public Endereco() {
        this.status = StatusEndereco.ATIVO;
    }

    public Endereco(int CEP, String rua, String bairro, String cidade, String estado) {
        this.CEP = CEP;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.status = StatusEndereco.ATIVO;
    }

    // Métodos

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "Id: " + this.getId() + " \n";
		detalhes += "CEP: " + this.getCEP() + " \n";
		detalhes += "Rua: " + this.getRua() + " \n";
		detalhes += "Bairro: " + this.getBairro() + " \n";
		detalhes += "Cidade: " + this.getCidade() + " \n";
		detalhes += "Estado: " + this.getEstado() + " \n";
        detalhes += "Status: " + this.getStatus() + " \n";
		return detalhes;
    }

}
