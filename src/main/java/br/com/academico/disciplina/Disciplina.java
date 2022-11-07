package br.com.academico.disciplina;

import java.io.Serializable;

public class Disciplina implements Serializable {

    // Atributos de Instância ou do Objeto

    private int id;
    private String nome;
    private int cargaHoraria;

    //Gets e Sets

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    // Construtores 

    public Disciplina() {
    }

    public Disciplina(String nome, int cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    //Métodos

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "Id: " + this.getId() + " \n";
        detalhes += "Nome: " + this.getNome() + " \n";
        detalhes += "Carga Horária " + this.getCargaHoraria() + " \n";
        return detalhes;
    } 
    

}
