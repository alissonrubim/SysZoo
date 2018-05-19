/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.DAO;

import com.alissonrubim.Models.Database.DatabaseController;
import com.alissonrubim.Models.Database.DatabaseParameter;
import com.alissonrubim.Models.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alisson.rubim
 */
public class FuncionarioDAO implements IGenericDAO<Funcionario, Integer>{

    @Override
    public Integer insert(Funcionario obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getNome()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getMatricula()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getEndereco()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getTelefone()));
        return DatabaseController.executeInsert("INSERT INTO Funcionario(Nome, Matricula, Endereco, Telefone) VALUES(?,?,?,?)", params);
    }

    @Override
    public void delete(Funcionario obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("DELETE FROM Funcionario WHERE FuncionarioId = ?", params);
    }

    @Override
    public void update(Funcionario obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getNome()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getMatricula()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getEndereco()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getTelefone()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("UPDATE Funcionario SET Nome = ?, Matricula = ?, Endereco = ?, Telefone = ? WHERE FuncionarioId = ?", params);
    }

    @Override
    public Funcionario getAt(Integer key) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, key));
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Funcionario WHERE FuncionarioId = ?", params);
        Funcionario l = null;
        if(r.next())
            l = createFuncionario(r);
        return l;
    }

    @Override
    public ArrayList<Funcionario> getAll() throws SQLException, ClassNotFoundException {
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Funcionario", null);
        ArrayList<Funcionario> l = new ArrayList<>();
        while(r.next())
            l.add(createFuncionario(r));
        return l;
    }
    
    private Funcionario createFuncionario(ResultSet r) throws SQLException{
        return new Funcionario(
            r.getInt("FuncionarioId"), 
            r.getString("Nome"), 
            r.getString("Matricula"), 
            r.getString("Endereco"), 
            r.getString("Telefone"));
}
    
}
