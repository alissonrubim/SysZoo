/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.DAO;

import com.alissonrubim.Models.Database.DatabaseController;
import com.alissonrubim.Models.Database.DatabaseParameter;
import com.alissonrubim.Models.Funcionario;
import com.alissonrubim.Models.Medicamento;
import com.alissonrubim.Models.Receita;
import com.alissonrubim.Models.ReceitaMedicamento;
import com.alissonrubim.Models.Util.Util;
import com.alissonrubim.Models.Veterinario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alisson.rubim
 */
public class ReceitaMedicamentoDAO implements IGenericRelationDAO<ReceitaMedicamento, Receita, Integer>{
    @Override
    public void update(Receita parent, ArrayList<ReceitaMedicamento> obj) throws SQLException, ClassNotFoundException {
        ArrayList<ReceitaMedicamento> currentDBList = getAll(parent);
        if(obj == null || obj.size() == 0){ //REMOVE TUDO
            ArrayList<DatabaseParameter> params = new ArrayList<>();
            params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, parent.getId()));
            DatabaseController.execute("DELETE FROM ReceitaMedicamento WHERE ReceitaId = ?", params);
        }else{
            for(ReceitaMedicamento rm : obj){
                if(rm.getId() < 1){ //INSERE
                    ArrayList<DatabaseParameter> params = new ArrayList<>();
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, parent.getId()));
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, rm.getMedicamento().getId()));
                    params.add(new DatabaseParameter(DatabaseParameter.Type.DOUBLE, rm.getDose()));
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, rm.getFrequencia()));
                    DatabaseController.execute("INSERT INTO ReceitaMedicamento(ReceitaId, MedicamentoId, Dose, Frequencia) VALUES(?,?,?,?)", params);
                }else{
                    ArrayList<DatabaseParameter> params = new ArrayList<>();
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, rm.getId()));
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, parent.getId()));
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, rm.getMedicamento().getId()));
                    params.add(new DatabaseParameter(DatabaseParameter.Type.DOUBLE, rm.getDose()));
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, rm.getFrequencia()));
                    DatabaseController.execute("UPDATE ReceitaMedicamento SET ReceitaId = ?, MedicamentoId = ?, Dose = ?, Frequencia = ? WHERE ReceitaMedicamentoId = ?", params);
                }
            }
            
            for(ReceitaMedicamento currentRm : currentDBList){
                if(Util.findObjectIndexInArray(obj, currentRm.getId()) < 0){
                    ArrayList<DatabaseParameter> params = new ArrayList<>();
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, currentRm.getId()));
                    DatabaseController.execute("DELETE FROM ReceitaMedicamento WHERE ReceitaMedicamentoId = ?", params);
                }
            }
        }
    }

    @Override
    public ArrayList<ReceitaMedicamento> getAll(Receita parent) throws SQLException, ClassNotFoundException {
        ArrayList<ReceitaMedicamento> l = new ArrayList<>();
        if(parent != null){
            ArrayList<DatabaseParameter> params = new ArrayList<>();
            params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, parent.getId()));
            ResultSet r = DatabaseController.executeQuery("SELECT * FROM ReceitaMedicamento WHERE ReceitaId = ?", params);
            while(r.next())
                l.add(createReceitaMedicamentoUsingReceita(r, parent));
        }
        return l;
    }
    
    private ReceitaMedicamento createReceitaMedicamentoUsingReceita(ResultSet r, Receita receita) throws SQLException, ClassNotFoundException{
        Medicamento m = new MedicamentoDAO().getAt(r.getInt("MedicamentoId"));
        return new ReceitaMedicamento(
            r.getInt("ReceitaMedicamentoId"),
            receita,
            m,    
            r.getDouble("Dose"),
            r.getInt("Frequencia")
        );
   }
}
