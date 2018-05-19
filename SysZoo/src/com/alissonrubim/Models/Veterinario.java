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
public class Veterinario implements IModel {   
    private int id;
    private Funcionario funcionario;
    private String registroCRMV;
    private java.util.Date dataCRMV;
 
    public Veterinario(int id, Funcionario funcionario, String registroCRMV, Date dataCRMV){
    	this.id = id;
    	this.funcionario = funcionario;
        this.registroCRMV = registroCRMV;
    	this.dataCRMV = dataCRMV;
    }

    @Override
    public int  getId(){
    	return id;
    }
    public Funcionario getFuncionario(){
    	return funcionario;
    }
    public String getRegistroCRMV(){
    	return registroCRMV;
    }
    public Date getDataCRMV(){
    	return dataCRMV;
    }
 
    public void setId(int v){
    	id = v;
    }
    public void setFuncionario(Funcionario v){
    	funcionario = v;
    }
    public void setRegistroCRMV(String v){
    	registroCRMV = v;
    }
    public void setDataCRMV(Date v){
    	dataCRMV = v;
    }
}
