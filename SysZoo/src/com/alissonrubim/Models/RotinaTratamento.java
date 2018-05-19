package com.alissonrubim.Models;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Reny Correia
 */
public class RotinaTratamento implements IModel {

    private Animal animal;
    private Date data;
    
    public Animal getAnimal() {
        return animal;
    }

    public Date getData() {
        return data;
    }

    @Override
    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
