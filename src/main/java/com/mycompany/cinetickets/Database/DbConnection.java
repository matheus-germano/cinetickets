/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinetickets.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus
 */
public class DbConnection {
    private Connection con;
    private String server;
    private String database;
    private String user;
    private String password;
    private String driver;
    private int port;
    
    public DbConnection() {
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.port = 3306;
        this.server = "localhost";
        this.database = "cinetickets";
        this.user = "root";
        this.password = "admin";
    }
    
    public Connection getConnection() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(getURL(), user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
    
    public String getURL() {
        return "jdbc:mysql://" + this.server + ":" + this.port + "/" + this.database
                + "?useTimezone=true&serverTimezone=UTC";
    }
}
