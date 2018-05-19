/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Models.Database;

import com.alissonrubim.Models.Util.Format;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alisson.rubim
 */
public class DatabaseController {
    public static void execute(String query, ArrayList<DatabaseParameter> params) throws SQLException, ClassNotFoundException{
        PreparedStatement p = prepareStatement(query, params, null);
        p.executeUpdate();
    }
    
    public static int executeInsert(String query, ArrayList<DatabaseParameter> params) throws SQLException, ClassNotFoundException{
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement p = prepareStatement(query, params, c);
        p.executeUpdate();
        PreparedStatement p2 = prepareStatement("SELECT LAST_INSERT_ID() AS ID", null, c);
        ResultSet r = p2.executeQuery();
        r.next();
        return r.getInt("ID");
    }
    
    public static ResultSet executeQuery(String query, ArrayList<DatabaseParameter> params) throws SQLException, ClassNotFoundException{
        PreparedStatement p = prepareStatement(query, params, null);
        return p.executeQuery();
    }
    
    private static PreparedStatement prepareStatement(String query, ArrayList<DatabaseParameter> params, Connection c) throws SQLException, ClassNotFoundException{
        if(c == null)
            c = ConnectionFactory.getConnection();
        PreparedStatement p = c.prepareStatement(query);
        if(params != null){        
            for(int i = 0; i<params.size(); i++){
                DatabaseParameter param = params.get(i);
                if(null != param.getType())
                    switch (param.getType()) {
                    case STRING:
                        p.setString(i+1,(String)param.getValue());
                        break;
                    case INTEGER:
                        p.setInt(i+1,(Integer)param.getValue());
                        break;
                    case DOUBLE:
                        p.setDouble(i+1,(Double)param.getValue());
                        break;
                    case DATE:
                        java.util.Date date = (java.util.Date)param.getValue();
                        p.setDate(i+1,new java.sql.Date(date.getTime()));
                        //p.setString(i+1, Format.DateToString(date, "yyyy-MM-dd"));
                        break;
                    case BOOLEAN:
                        p.setBoolean(i+1,(Boolean)param.getValue());
                        break;
                    default:
                        break;
                }
            }
        }
        return p;
    }
}
