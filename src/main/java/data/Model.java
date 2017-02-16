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
    
    /* BEGIN USER METHODS */    
    public int newUser(User usr) throws SQLException
    {
        String sqlInsert="insert into users (username, email, password) values ('" + usr.getUsername() + "'" + ", '" + usr.getEmail() + "', '" + usr.getPassword() + "');";
        Statement s = createStatement();
        logger.log(Level.INFO, "attempting statement execute");
        s.execute(sqlInsert,Statement.RETURN_GENERATED_KEYS);
        logger.log(Level.INFO, "statement executed.  atempting get generated keys");
        ResultSet rs = s.getGeneratedKeys();
        logger.log(Level.INFO, "retrieved keys from statement");
        int userid = -1;
        while (rs.next())
            userid = rs.getInt(4);   // assuming 4th column is userid
        logger.log(Level.INFO, "The new user id=" + userid);
        return userid;
    }
    
    public void deleteUser(int userid) throws SQLException
    {
        String sqlDelete="delete from users where userid=?";
        PreparedStatement pst = createPreparedStatement(sqlDelete);
        pst.setInt(1, userid);
        pst.execute();
    }
    
    public User[] getUsers() throws SQLException
    {
        LinkedList<User> ll = new LinkedList<User>();
        String sqlQuery ="select * from users;";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);
        while (rows.next())
        {
            logger.log(Level.INFO, "Reading row...");
            User usr = new User();
            usr.setUsername(rows.getString("username"));
            usr.setEmail(rows.getString("email"));
            usr.setPassword(rows.getString("password"));
            usr.setUserId(rows.getInt("userid"));
            logger.log(Level.INFO, "Adding user to list with id=" + usr.getUserId());
            ll.add(usr);
        }
        return ll.toArray(new User[ll.size()]);
    }
    
    public boolean updateUser(User usr) throws SQLException
    {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("update users ");
        sqlQuery.append("set username='" + usr.getUsername() + "', ");
        sqlQuery.append("email='" + usr.getEmail() + "', ");
        sqlQuery.append("password='" + usr.getPassword() + "' ");
        sqlQuery.append("where userid=" + usr.getUserId() + ";");
        Statement st = createStatement();
        logger.log(Level.INFO, "UPDATE SQL=" + sqlQuery.toString());
        return st.execute(sqlQuery.toString());
    }
   
    
    
    public int newShop(CoffeeShop shop) throws SQLException
    {
        String sqlInsert="insert into shops (shopname, shopaddress, shopphone) values ('" + shop.getMyshopname()+ "', '" + shop.getMyaddress()+ "', '" + shop.getMyphone()+ "');";
        Statement s = createStatement();
        logger.log(Level.INFO, "attempting statement execute");
        s.execute(sqlInsert,Statement.RETURN_GENERATED_KEYS);
        logger.log(Level.INFO, "statement executed.  atempting get generated keys");
        ResultSet rs = s.getGeneratedKeys();
        logger.log(Level.INFO, "retrieved keys from statement");
        int shopid = -1;
        while (rs.next())
            shopid = rs.getInt(1);   // assuming 1rd column is userid
        logger.log(Level.INFO, "The new shop id=" + shopid);
        return shopid;
    }
    
    public void deleteShop(int shopid) throws SQLException
    {
        String sqlDelete="delete from shop where shopid=?";
        PreparedStatement pst = createPreparedStatement(sqlDelete);
        pst.setInt(1, shopid);
        pst.execute();
    } 
    
    public CoffeeShop[] getShops() throws SQLException
    {
        LinkedList<CoffeeShop> ll = new LinkedList<CoffeeShop>();
        String sqlQuery ="select * from shops;";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);
        while (rows.next())
        {
            logger.log(Level.INFO, "Reading row...");
            CoffeeShop shop = new CoffeeShop();
            shop.setMyshopname(rows.getString("shopname"));
            shop.setMyaddress(rows.getString("shopaddress"));
            shop.setMyphone(rows.getString("shopphone"));
            shop.setMyshopId(rows.getInt("shopid"));
            logger.log(Level.INFO, "Adding user to list with id=" + shop.getMyshopId());
            ll.add(shop);
        }
        return ll.toArray(new CoffeeShop[ll.size()]);
    }
    
    public boolean updateShop(CoffeeShop shop) throws SQLException
    {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("update shops ");
        sqlQuery.append("set shopname='" + shop.getMyshopname()+ "', ");
        sqlQuery.append("shopaddress='" + shop.getMyaddress()+ "', ");
        sqlQuery.append("shopphone='" + shop.getMyphone()+ "' ");
        sqlQuery.append("where shopid=" + shop.getMyshopId()+ ";");
        Statement st = createStatement();
        logger.log(Level.INFO, "UPDATE SQL=" + sqlQuery.toString());
        return st.execute(sqlQuery.toString());
    }
    
    
   public int newMessage(Messages msg) throws SQLException
    {
        String sqlInsert="insert into messages (userid, message, dateadded) values (" + msg.getUserId() + ", '" + msg.getMessage() + "', now());";
        Statement s = createStatement();
        logger.log(Level.INFO, "attempting statement execute");
        s.execute(sqlInsert,Statement.RETURN_GENERATED_KEYS);
        logger.log(Level.INFO, "statement executed.  atempting get generated keys");
        ResultSet rs = s.getGeneratedKeys();
        logger.log(Level.INFO, "retrieved keys from statement");
        int userid = -1;
        while (rs.next())
            userid = rs.getInt(2);   // assuming 3rd column is userid
        logger.log(Level.INFO, "The new user id=" + userid);
        return userid;
    }
    
    public Messages[] getMessages() throws SQLException
    {
        LinkedList<Messages> ll = new LinkedList<Messages>();
        String sqlQuery ="select * from messages;";
        Statement st = createStatement();
        ResultSet rows = st.executeQuery(sqlQuery);
        while (rows.next())
        {
            logger.log(Level.INFO, "Reading row...");
            Messages msg = new Messages();
            msg.setMessageId(rows.getInt("messageid"));
            msg.setUserId(rows.getInt("userid"));
            msg.setMessage(rows.getString("message"));
            logger.log(Level.INFO, "Adding user to list with id=" + msg.getUserId());
            ll.add(msg);
        }
        return ll.toArray(new Messages[ll.size()]);
    }
}
