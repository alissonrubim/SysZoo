/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.DAO;

import com.alissonrubim.Models.Animal;
import com.alissonrubim.Models.Database.DatabaseController;
import com.alissonrubim.Models.Database.DatabaseParameter;
import com.alissonrubim.Models.Especie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alisson.rubim
 */
public class EspecieDAO implements IGenericDAO<Especie, Integer> {

    @Override
    public Integer insert(Especie obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getNome()));
        return DatabaseController.executeInsert("INSERT INTO Especie(Nome) VALUES(?)", params);
    }

    @Override
    public void delete(Especie obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("DELETE FROM Especie WHERE EspecieId = ?", params);
    }

    @Override
    public void update(Especie obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getNome()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("UPDATE Especie SET Nome = ? WHERE EspecieId = ?", params);
    }

    @Override
    public Especie getAt(Integer key) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, key));
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Especie WHERE EspecieId = ?", params);
        Especie l = null;
        if(r.next())
            l = createEspecie(r);
        return l;
    }

    @Override
    public ArrayList<Especie> getAll() throws SQLException, ClassNotFoundException {
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Especie", null);
        ArrayList<Especie> l = new ArrayList<>();
        while(r.next())
            l.add(createEspecie(r));
        return l;
    }
    
    private Especie createEspecie(ResultSet r) throws SQLException{
        return new Especie(
                r.getInt("EspecieId"), 
                r.getString("Nome"));
    }
    
}
