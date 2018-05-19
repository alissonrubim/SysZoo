/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models;

/**
 *
 * @author reny.correia
 */
public class AnimalTratador implements IModel {
    private int id;
    private Animal animal;
    private Tratador tratador;
    
    public AnimalTratador(int id, Animal a, Tratador t){
        this.id = id;
        this.animal = a;
        this.tratador = t;
    }
    

    @Override
    public int getId() {
        return this.id;
    }
    
    public Animal getAnimal(){
        return this.animal;
    }
    
    public Tratador getTratador(){
        return this.tratador;
    }
}
