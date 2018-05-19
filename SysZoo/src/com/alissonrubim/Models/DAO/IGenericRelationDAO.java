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
 * @author alisson.rubim
 */
public interface IGenericRelationDAO<C, D, K> {
    public void update(D parent, ArrayList<C> obj) throws SQLException, ClassNotFoundException;
    public ArrayList<C> getAll(D parent) throws SQLException, ClassNotFoundException;
}
