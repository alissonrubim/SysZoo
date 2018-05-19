/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.DAO;

import com.alissonrubim.Models.Animal;
import com.alissonrubim.Models.AnimalTratador;
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
public class AnimalTratadorDAO implements IGenericRelationDAO<AnimalTratador, Animal, Integer>{
    @Override
    public void update(Animal parent, ArrayList<AnimalTratador> obj) throws SQLException, ClassNotFoundException {
        ArrayList<AnimalTratador> currentDBList = getAll(parent);
        if(obj == null || obj.size() == 0){ //REMOVE TUDO
            ArrayList<DatabaseParameter> params = new ArrayList<>();
            params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, parent.getId()));
            DatabaseController.execute("DELETE FROM AnimalTratador WHERE AnimalId = ?", params);
        }else{
            for(AnimalTratador rm : obj){
                if(rm.getId() < 1){ //INSERE
                    ArrayList<DatabaseParameter> params = new ArrayList<>();
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, parent.getId()));
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, rm.getTratador().getId()));
                    DatabaseController.execute("INSERT INTO AnimalTratador(AnimalId, TratadorId) VALUES(?,?)", params);
                }
            }
            
            for(AnimalTratador currentRm : currentDBList){
                if(Util.findObjectIndexInArray(obj, currentRm.getId()) < 0){
                    ArrayList<DatabaseParameter> params = new ArrayList<>();
                    params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, currentRm.getId()));
                    DatabaseController.execute("DELETE FROM AnimalTratador WHERE AnimalTratadorId = ?", params);
                }
            }
        }
    }

    @Override
    public ArrayList<AnimalTratador> getAll(Animal parent) throws SQLException, ClassNotFoundException {
        ArrayList<AnimalTratador> l = new ArrayList<>();
        if(parent != null){
            ArrayList<DatabaseParameter> params = new ArrayList<>();
            params.add(new DatabaseParameter(DatabaseParameter.Type.INTEGER, parent.getId()));
            ResultSet r = DatabaseController.executeQuery("SELECT * FROM AnimalTratador WHERE AnimalId = ?", params);
            while(r.next())
                l.add(createReceitaMedicamentoUsingReceita(r, parent));
        }
        return l;
    }
    
    private AnimalTratador createReceitaMedicamentoUsingReceita(ResultSet r, Animal receita) throws SQLException, ClassNotFoundException{
        /*Medicamento m = new MedicamentoDAO().getAt(r.getInt("MedicamentoId"));
        return new ReceitaMedicamento(
            r.getInt("ReceitaMedicamentoId"),
            receita,
            m,    
            r.getDouble("Dose"),
            r.getInt("Frequencia")
        );*/
        return null;
   }
}
