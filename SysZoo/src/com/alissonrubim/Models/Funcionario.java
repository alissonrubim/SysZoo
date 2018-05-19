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
public class Funcionario implements IModel {
    private int id;
    private String nome;
    private String matricula;
    private String endereco;
    private String telefone;
    
    public Funcionario(int id, String nome, String matricula, String endereco, String telefone){
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    
    @Override
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public String getMatricula(){
        return matricula;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public void setId(int v) {
        id = v;
    }

    public void setNome(String v) {
        nome = v;
    }
    
    public void setMatricula(String v){
        matricula = v;
    }
    
    public void setEndereco(String v){
        endereco= v;
    }
    
    public void setTelefone(String v){
        telefone = v;
    }
}
