/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.testmasivian.bd;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionBD {
    public Connection con;
    public boolean abrirConexion(){
        try{
            javax.naming.Context c = new InitialContext();
            DataSource ds = (DataSource) c.lookup("jdbc/testMasivian");
            con = ds.getConnection();
            return true;
        }catch(NamingException | SQLException e){
            System.out.println("Error ConexionBD.abrirConexion(): " + e);
            return false;
        }
    }
    
    public boolean cerrarConexion(){
        try{
            con.close();
            return true;
        }catch(Exception e){
            con = null;
            return false;
        }
    }
    
}
