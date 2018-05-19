/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.DAO;

import com.alissonrubim.Models.Database.DatabaseController;
import com.alissonrubim.Models.Database.DatabaseParameter;
import com.alissonrubim.Models.Funcionario;
import com.alissonrubim.Models.Receita;
import com.alissonrubim.Models.ReceitaMedicamento;
import com.alissonrubim.Models.Veterinario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alisson.rubim
 */
public class ReceitaDAO implements IGenericDAO<Receita, Integer>{

    @Override
    public Integer insert(Receita obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.DATE, obj.getData()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getObservacao()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getVeterinario().getId()));
        int indentity = DatabaseController.executeInsert("INSERT INTO Receita(`Data`, Observacao, VeterinarioId) VALUES(?,?,?)", params);   
        obj.setId(indentity);
        new ReceitaMedicamentoDAO().update(obj, obj.getMedicamentos());
        return indentity;
    }

    @Override
    public void delete(Receita obj) throws SQLException, ClassNotFoundException {
        new ReceitaMedicamentoDAO().update(obj, null);
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("DELETE FROM Receita WHERE ReceitaId = ?", params);
    }

    @Override
    public void update(Receita obj) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getVeterinario().getId()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.DATE, obj.getData()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.STRING, obj.getObservacao()));
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, obj.getId()));
        DatabaseController.execute("UPDATE Receita SET VeterinarioId = ?, `Data` = ?, Observacao = ? WHERE ReceitaId = ?", params);
        new ReceitaMedicamentoDAO().update(obj, obj.getMedicamentos());
    }

    @Override
    public Receita getAt(Integer key) throws SQLException, ClassNotFoundException {
        ArrayList<DatabaseParameter> params = new ArrayList<>();
        params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, key));
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Receita WHERE ReceitaId = ?", params);
        Receita l = null;
        if(r.next())
            l = createReceita(r);
        return l;
    }

    @Override
    public ArrayList<Receita> getAll() throws SQLException, ClassNotFoundException {
        ResultSet r = DatabaseController.executeQuery("SELECT * FROM Receita", null);
        ArrayList<Receita> l = new ArrayList<>();
        while(r.next())
            l.add(createReceita(r));
        return l;
    }
    
    private Receita createReceita(ResultSet r) throws SQLException, ClassNotFoundException{
        Veterinario v = new VeterinarioDAO().getAt(r.getInt("VeterinarioId"));
        
        Receita receita = new Receita(
            r.getInt("ReceitaId"), 
            v,
            r.getDate("Data"),
            r.getString("Observacao"), 
            null
        );
        
        receita.setMedicamentos(new ReceitaMedicamentoDAO().getAll(receita));
        
        return receita;
   }
}
