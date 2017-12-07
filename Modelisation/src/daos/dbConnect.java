/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Joel
 * La classe gérant la connexion vers la base de données
 */
public class dbConnect {
    private Connection conn = null;
    private static dbConnect dbConnection = null;
    
    private dbConnect(){
        try {
            String url = "jdbc:sqlite:D:\\Université\\Trimestre 3\\INF1163\\projet_oop\\UI\\Modelisation\\agence.db";
            this.conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            
        }
    }
    
    private Statement createStatement(){
        try {
            return this.conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static Statement getStatement(){
        if (dbConnection == null){
            dbConnection = new dbConnect();
        }
        Statement outputConnection = dbConnection.createStatement();
        return outputConnection;
    }
    
    public static void init(){
        dbConnection = new dbConnect();
    }
}
