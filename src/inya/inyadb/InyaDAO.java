/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inya.inyadb;

import inya.Inya;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THOR
 */
public final class InyaDAO {
    
    private Connection con = null;
    private final String driverName = "com.mysql.cj.jdbc.Driver";
    private final String serverName = "localhost";
    private final String mydatabase = "inyadb";
    private final String username = "root";
    private final String password = "@Walkabcd189560";
    
    public InyaDAO() {
        
    }
    
    public void startDBConnection() {
        try {                               
            try {
                Class.forName(driverName);
            } catch (ClassNotFoundException ex) {
                System.out.println("Driver não encontrado!"+ex);
            }
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useTimezone=true&serverTimezone=UTC";
            con = DriverManager.getConnection(url, username, password);
            System.out.println(mydatabase + " conectado!");
        } catch (SQLException ex) {
            Logger.getLogger(Inya.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection(){
        try {
            con.close();
            System.out.println(mydatabase + " desconectado!");
        } catch (SQLException ex) {
            Logger.getLogger(InyaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean getUser(String username, String password) {
        startDBConnection();
        boolean existAccount = false;
        try {            
            String query = "SELECT * FROM users WHERE user_name = '" + username + "' AND user_password = '" + password + "';";
            PreparedStatement pStm = con.prepareStatement(query);
            ResultSet queryResult = pStm.executeQuery();
            if (queryResult.next()) {
                existAccount = true;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar usuário no banco de dados!");
        } finally {
            closeConnection();
        }
        return existAccount;
    }
    
    public int getUserPermission(String username) {
        startDBConnection();
        int permission = -1;
        try {            
            String query = "SELECT * FROM users WHERE user_name = '" + username + "';";
            PreparedStatement pStm = con.prepareStatement(query);
            ResultSet queryResult = pStm.executeQuery();
            queryResult.next();
            permission = (int) queryResult.getObject("user_permission");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar usuário no banco de dados!");
        } finally {
            closeConnection();
        }
        return permission;
    }
}
