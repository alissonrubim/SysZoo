/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.DAO;

import com.alissonrubim.Models.Database.DatabaseController;
import com.alissonrubim.Models.Database.DatabaseParameter;
import com.alissonrubim.Models.Medicamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alisson.rubim
 */
public class MedicamentoDAO implements IGenericDAO<Medicamento, Integer>
{

    @Override
    public Integer insert(Medicamento obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getNome()));
        return DatabaseController.executeInsert("INSERT INTO Medicamento(Nome) VALUES(?)", params);
    }

    @Override
    public void delete(Medicamento obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("DELETE FROM Medicamento WHERE MedicamentoId = ?", params);
    }

    @Override
    public void update(Medicamento obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getNome()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("UPDATE Medicamento SET Nome = ? WHERE MedicamentoId = ?", params);
    }

    @Override
    public Medicamento getAt(Integer key) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, key));
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Medicamento WHERE MedicamentoId = ?", params);
        Medicamento l = null;
        if(r.next())
            l = createMedicamento(r);
        return l;
    }

    @Override
    public ArrayList<Medicamento> getAll() throws SQLException, ClassNotFoundException {
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Medicamento", null);
        ArrayList<Medicamento> l = new ArrayList<>();
        while(r.next())
            l.add(createMedicamento(r));
        return l;
    }
    
    private Medicamento createMedicamento(ResultSet r) throws SQLException, ClassNotFoundException{
        return new Medicamento(
            r.getInt("MedicamentoId"), 
            r.getString("Nome"));
   }
    
}
