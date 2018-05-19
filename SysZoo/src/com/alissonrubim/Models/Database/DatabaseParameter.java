/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.Database;

/**
 *
 * @author alisson.rubim
 */
public class DatabaseParameter {
    private Type type;
    private Object value;
 
    public DatabaseParameter(Type type, Object value){
        this.type = type;
        this.value = value;
    }
    
    public Type getType(){
        return type;
    }
    
    public Object getValue(){
        return value;
    }
    
    public static enum Type {
        STRING,
        INTEGER, 
        BOOLEAN,
        DATE,
        DOUBLE    
    }
}
