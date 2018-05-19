/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alisson.rubim
 */
public class Receita implements IModel {   
    private int id;
    private Date data;
    private String observacao;
    private ArrayList<ReceitaMedicamento> medicamentos;
    private Veterinario veterinario;
 
    public Receita(int id, Veterinario veterinario, Date data, String observacao, ArrayList<ReceitaMedicamento> medicamentos){
    	this.id = id;
    	this.data = data;
        this.observacao = observacao;
    	this.medicamentos = medicamentos;
        this.veterinario = veterinario;
    }

    @Override
    public int  getId(){
    	return id;
    }
    
     public Veterinario  getVeterinario(){
    	return veterinario;
    }
     
    public Date getData(){
        return data;
    }
    
    public String getObservacao(){
        return observacao;
    }
    
    public ArrayList<ReceitaMedicamento> getMedicamentos(){
        return medicamentos;
    }
   
    public void setId(int v){
        this.id = v;
    }
    
    public void setData(Date v){
        this.data = v;
    }
    
    public void setObservacao(String v){
        this.observacao = v;
    }
    
    public void setMedicamentos(ArrayList<ReceitaMedicamento> v){
        this.medicamentos = v;
    }
    
    public void setVeterinario(Veterinario v){
        this.veterinario = v;
    }
}
