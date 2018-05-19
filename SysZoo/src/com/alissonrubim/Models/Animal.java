/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models;

import java.sql.Date;

/**
 *
 * @author alisson.rubim
 */
public class Animal implements IModel {   
    private int id;
    private String nome;
    private String regiao;
    private Date dataNascimento;
    private double peso;
    private Especie especie;

    public Animal(int id, String nome, String regiao, Date dataNascimento, double peso, Especie especie){
    	this.id = id;
    	this.nome = nome;
        this.regiao = regiao;
    	this.dataNascimento = dataNascimento;
    	this.peso = peso;
    	this.especie = especie;
    }

    @Override
    public int  getId(){
    	return id;
    }
    public String getNome(){
    	return nome;
    }
    public String getRegiao(){
    	return regiao;
    }
    public Date getDataNascimento(){
    	return dataNascimento;
    }
    public double getPeso(){
    	return peso;
    }
    public Especie getEspecie(){
    	return especie;
    }

    public void setId(int v){
    	id = v;
    }
    public void setNome(String v){
    	nome = v;
    }
    public void setRegiao(String v){
    	regiao = v;
    }
    public void setDataNascimento(Date v){
    	dataNascimento = v;
    }
    public void setPeso(double v){
    	peso = v;
    }
    public void setEspecie(Especie v){
    	especie = v;
    }
}
