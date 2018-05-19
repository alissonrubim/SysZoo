/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Reny Correia
 */
public class RotinaTratamentoDAO implements IGenericRelationDAO<Object, Object, Object>{

    public RotinaTratamentoDAO() {
    }

    @Override
    public void update(Object parent, ArrayList<Object> obj) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> getAll(Object parent) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
