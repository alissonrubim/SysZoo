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
public interface IGenericDAO<C, K> {
    public K insert(C obj) throws SQLException, ClassNotFoundException;
    public void delete(C obj) throws SQLException, ClassNotFoundException;
    public void update(C obj) throws SQLException, ClassNotFoundException;
    public C getAt(K key) throws SQLException, ClassNotFoundException;
    public ArrayList<C> getAll() throws SQLException, ClassNotFoundException;
}
