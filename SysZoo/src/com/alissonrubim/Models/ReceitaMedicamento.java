/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models;

import java.util.Date;

/**
 *
 * @author alisson.rubim
 */
public class ReceitaMedicamento implements IModel {   
    private int id;
    private Receita receita;
    private Medicamento medicamento;
    private double dose;
    private int frequencia;
 
    public ReceitaMedicamento(int id, Receita receita, Medicamento medicamento, double dose, int frequencia){
    	this.id = id;
    	this.receita = receita;
        this.medicamento = medicamento;
    	this.dose = dose;
        this.frequencia = frequencia;
    }

    @Override
    public int  getId(){
    	return id;
    }
    
    public Receita getReceita(){
        return receita;
    }
    
    public Medicamento getMedicamento(){
        return medicamento;
    }
    
    public double getDose(){
        return dose;
    }
    
    public int getFrequencia(){
        return frequencia;
    }
    
    public void setInt(int v){
        id = v;
    }
    
    public void setReceita(Receita v){
        this.receita = v;
    }
    
    public void setMedicamento(Medicamento v){
        medicamento =v;
    }
    
    public void setDose(double v){
        dose = v;
    }
    
    public void setFrequencia(int v){
        frequencia = v;
    }

   
}
