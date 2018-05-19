/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.DAO;

import com.alissonrubim.Models.Database.DatabaseController;
import com.alissonrubim.Models.Database.DatabaseParameter;
import com.alissonrubim.Models.Funcionario;
import com.alissonrubim.Models.Veterinario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alisson.rubim
 */
public class VeterinarioDAO implements IGenericDAO<Veterinario, Integer>{

    @Override
    public Integer insert(Veterinario obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getFuncionario().getId()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getRegistroCRMV()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.DATE, obj.getDataCRMV()));
        return DatabaseController.executeInsert("INSERT INTO Veterinario(FuncionarioId, RegistroCRMV, DataCRMV) VALUES(?,?,?)", params);
    }

    @Override
    public void delete(Veterinario obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("DELETE FROM Veterinario WHERE VeterinarioId = ?", params);
    }

    @Override
    public void update(Veterinario obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getFuncionario().getId()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getRegistroCRMV()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.DATE, obj.getDataCRMV()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("UPDATE Veterinario SET FuncionarioId = ?, RegistroCRMV = ?, DataCRMV = ? WHERE VeterinarioId = ?", params);
    }

    @Override
    public Veterinario getAt(Integer key) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, key));
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Veterinario WHERE VeterinarioId = ?", params);
        Veterinario l = null;
        if(r.next())
            l = createVecterinario(r);
        return l;
    }

    @Override
    public ArrayList<Veterinario> getAll() throws SQLException, ClassNotFoundException {
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Veterinario", null);
        ArrayList<Veterinario> l = new ArrayList<>();
        while(r.next())
            l.add(createVecterinario(r));
        return l;
    }
    
    private Veterinario createVecterinario(ResultSet r) throws SQLException, ClassNotFoundException{
        Funcionario f = new FuncionarioDAO().getAt(r.getInt("FuncionarioId"));
        return new Veterinario(
            r.getInt("VeterinarioId"), 
            f, 
            r.getString("RegistroCRMV"), 
            r.getDate("DataCRMV"));
   }
    
}
