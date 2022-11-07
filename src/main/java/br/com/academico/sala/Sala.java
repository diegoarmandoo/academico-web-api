package br.com.academico.sala;

import java.io.Serializable;

public class Sala implements Serializable {

    // Atributos de Instância ou do Objeto

    private int id;
    private int numero;
    private int capacidade;
    private boolean arCondicionado;
    private boolean quadroBranco;
    private boolean laboratorio;

    //Gets e Sets

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public boolean isQuadroBranco() {
        return quadroBranco;
    }

    public void setQuadroBranco(boolean quadroBranco) {
        this.quadroBranco = quadroBranco;
    }

    public boolean isLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(boolean laboratorio) {
        this.laboratorio = laboratorio;
    }

    // Construtor

    public Sala() {
    }

    public Sala(int numero, int capacidade, boolean arCondicionado, boolean quadroBranco, boolean laboratorio) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.arCondicionado = arCondicionado;
        this.quadroBranco = quadroBranco;
        this.laboratorio = laboratorio;
    }

    //Métodos

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "Id: " + this.getId() + " \n";
        detalhes += "Número: " + this.getNumero() + " \n";
        detalhes += "Capacidade de Alunos: " + this.getCapacidade() + " \n";
        detalhes += "Tem Ar Condicionado? " + this.isArCondicionado() + " \n";
        detalhes += "Tem Quadro Branco? " + this.isQuadroBranco() + " \n";
        detalhes += "É um laboratório? " + this.isLaboratorio() + " \n";
        return detalhes;
    } 

    

}
