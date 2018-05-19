/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models;

/**
 *
 * @author alisson.rubim
 */
public class Especie implements IModel {
    private int id;
    private String nome;

    public Especie(int id, String nome){
    	this.id = id;
    	this.nome = nome;
    }

    @Override
    public int getId(){
    	return id;
    }

    public String getNome(){
    	return nome;
    }

    public void setId(int v){
    	id = v;
    }

    public void setNome(String v){
    	nome = v;
    }
}
