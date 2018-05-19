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
public class AnimalDAO implements IGenericDAO<Animal, Integer>{

    @Override
    public Integer insert(Animal obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Animal obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("DELETE FROM Animal WHERE EspecieId = ?", params);
    }

    @Override
    public void update(Animal obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Animal getAt(Integer key) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, key));
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Animal WHERE AnimalId = ?", params);
        Animal l = null;
        if(r.next())
            l = createAnimal(r);
        return l;
    }

    @Override
    public ArrayList<Animal> getAll() throws SQLException, ClassNotFoundException {
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Animal", null);
        ArrayList<Animal> l = new ArrayList<>();
        while(r.next()){
            l.add(createAnimal(r));
        }
        return l;
    }

    private Animal createAnimal(ResultSet r) throws SQLException, ClassNotFoundException{
        Especie e = new EspecieDAO().getAt(r.getInt("EspecieID"));
        return new Animal(
                r.getInt("AnimalId"), 
                r.getString("Nome"),
                r.getString("Regiao"),
                r.getDate("DataNascimento"),
                r.getDouble("Peso"),
                e);
    }
}
