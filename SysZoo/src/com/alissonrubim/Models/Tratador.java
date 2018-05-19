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
public class Tratador implements IModel{
    private int id;
    private Funcionario funcionario;
    
    public Tratador(int id, Funcionario funcionario){
        this.id = id;
        this.funcionario = funcionario;
    }
    @Override
    public int getId(){
        return id;
    }
    public Funcionario getFuncionario(){
        return funcionario;
    }
    public void setId(int v){
        id = v;
    }
    public void setFuncionario(Funcionario v){
        funcionario = v;
    }
}
