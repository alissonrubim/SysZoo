/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.DAO;

import com.alissonrubim.Models.Database.DatabaseController;
import com.alissonrubim.Models.Database.DatabaseParameter;
import com.alissonrubim.Models.Especie;
import com.alissonrubim.Models.Funcionario;
import com.alissonrubim.Models.Tratador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alisson.rubim
 */
public class TratadorDAO implements IGenericDAO<Tratador, Integer> {

    @Override
    public Integer insert(Tratador obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getFuncionario().getId()));
        return DatabaseController.executeInsert("INSERT INTO Tratador(FuncionarioId) VALUES(?)", params); 
    }

    @Override
    public void delete(Tratador obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("DELETE FROM Tratador WHERE TratadorId = ?", params);
    }

    @Override
    public void update(Tratador obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getFuncionario().getId()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("UPDATE Tratador SET FuncionarioId = ? WHERE TratadorId = ?", params);
    }

    @Override
    public Tratador getAt(Integer key) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, key));
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Tratador WHERE TratadorId = ?", params);
        Tratador l = null;
        if(r.next())
            l = createTratador(r);
        return l;
    }

    @Override
    public ArrayList<Tratador> getAll() throws SQLException, ClassNotFoundException {
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Tratador", null);
        ArrayList<Tratador> l = new ArrayList<>();
        while(r.next())
            l.add(createTratador(r));
        return l;
    }
    
    private Tratador createTratador(ResultSet r) throws SQLException, ClassNotFoundException{
        Funcionario f = new FuncionarioDAO().getAt(r.getInt("FuncionarioID"));
        return new Tratador(
            r.getInt("TratadorId"), 
            f);
    }
    
}
