/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.CoffeeShop;
import objects.Messages;
import objects.User;
import objects.Review;

/**
 *
 * @author wingoz
 */
public class Model {
    static final Logger logger = Logger.getLogger(Model.class.getName());
    private static Model instance;
    private Connection conn;
    
    public static Model singleton() throws Exception {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
    
    Model() throws Exception
    {
        Class.forName("org.postgresql.Driver");
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if ((dbUrl == null) || (dbUrl.length() < 1))
            dbUrl = System.getProperties().getProperty("DBCONN");
        logger.log(Level.INFO, "dbUrl=" + dbUrl);  
        logger.log(Level.INFO, "attempting db connection");
        conn = DriverManager.getConnection(dbUrl);
        logger.log(Level.INFO, "db connection ok.");
    }
    
    private Connection getConnection()
    {
        return conn;
    }
    
    private Statement createStatement() throws SQLException
    {
        Connection conn = getConnection();
        if ((conn != null) && (!conn.isClosed()))
        {
            logger.log(Level.INFO, "attempting statement create");
            Statement st = conn.createStatement();
            logger.log(Level.INFO, "statement created");
            return st;
        }
        else
        {
            // Handle connection failure
        }
        return null;
    }
    
    private PreparedStatement createPreparedStatement(String sql) throws SQLException
    {
        Connection conn = getConnection();
        if ((conn != null) && (!conn.isClosed()))
        {
            logger.log(Level.INFO, "attempting statement create");
            PreparedStatement pst = conn.prepareStatement(sql);
            logger.log(Level.INFO, "prepared statement created");
            return pst;
        }
        else
        {
            // Handle connection failure
        }
        return null;
    }
    
  
}
