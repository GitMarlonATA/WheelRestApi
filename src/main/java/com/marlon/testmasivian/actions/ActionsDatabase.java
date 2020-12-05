package com.marlon.testmasivian.actions;

import com.marlon.testmasivian.bd.ConnectionBD;
import com.marlon.testmasivian.model.Bet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActionsDatabase {
    
    public ActionsDatabase(){}
    
    public int createWheel(){
        try{
            ConnectionBD connection = new ConnectionBD();
            connection.abrirConexion();
            Connection con = connection.con;
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("insert into wheel(state) values(false) returning id ");
            int resultado = -1;
            while (r.next()) {
                resultado = r.getInt("id");
            }
            connection.cerrarConexion();
            return resultado;
        } catch(Exception e){
            System.out.println("excepcion " + e.getMessage());
            return -1;
        }
        
    }
    
    public String activateWheel(int idWheel){
        try{
            ConnectionBD connection = new ConnectionBD();
            connection.abrirConexion();
            Connection con = connection.con;
            Statement s = con.createStatement();
            s.executeUpdate("update wheel set state=true where id=" + idWheel);
            connection.cerrarConexion();
            return "the wheel " + idWheel + " have been activated";
        } catch(Exception e){
            System.out.println("excepcion " + e.getMessage());
            return "";
        }
    }
    
    public String deactivateWheel(int idWheel){
        try{
            ConnectionBD connection = new ConnectionBD();
            connection.abrirConexion();
            Connection con = connection.con;
            Statement s = con.createStatement();
            s.executeUpdate("update wheel set state=false where id=" + idWheel);
            connection.cerrarConexion();
            return "the wheel " + idWheel + " have been deactivated";
        } catch(Exception e){
            System.out.println("excepcion " + e.getMessage());
            return "";
        }
    }
    
    public String createBet(Bet bet){
        try{
            ConnectionBD connection = new ConnectionBD();
            connection.abrirConexion();
            Connection con = connection.con;
            Statement s = con.createStatement();
            String query = "insert into bet(idplayer,money,bet,kindbet,idwheel) "
                    + "values(" + bet.getIdPlayer() + "," + bet.getMoney() + ",'" + bet.getBet() + "','" + bet.getKindBet() + "'," + bet.getIdWheel() + ")";
            s.execute(query);
            connection.cerrarConexion();
            
            return "the bet have been created for the player " + bet.getIdPlayer();
        } catch(Exception e){
            System.out.println("excepcion " + e.getMessage());
            return "";
        }
    }
    
    public List<Bet> findBets(int idWheel){
        try{
            List<Bet> bets = new ArrayList<>();
            ConnectionBD connection = new ConnectionBD();
            connection.abrirConexion();
            Connection con = connection.con;
            Statement s = con.createStatement();
            String query = "select * from bet where idwheel=" + idWheel;
            ResultSet r = s.executeQuery(query);
            while(r.next()){
                bets.add(new Bet(r.getInt("idplayer"), r.getInt("money"), r.getString("bet"), r.getString("kindbet"), idWheel));
            }
            connection.cerrarConexion();
            
            return bets;
        } catch(Exception e){
            System.out.println("excepcion " + e.getMessage());
            return null;
        }
    }
    
    public String listWheels(){
        try{
            String wheels = " { wheels: ";
            ConnectionBD connection = new ConnectionBD();
            connection.abrirConexion();
            Connection con = connection.con;
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("select * from wheel");
            while (r.next()) {
                wheels += "[\"id\":" + r.getInt("id") + "," + "\"state\":" + r.getBoolean("state") + "],";
            }
            wheels = wheels.substring(0, wheels.length()-1);
            wheels += "}";
            connection.cerrarConexion();
            return wheels;
        } catch(Exception e){
            System.out.println("excepcion " + e.getMessage());
            return "";
        }
    }
}
