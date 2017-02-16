/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coffee_finder;

import data.Model;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import objects.CoffeeShop;
import objects.User;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author wlloyd
 */
@Path("shops")
public class CoffeeShopService {

    static final Logger logger = Logger.getLogger(CoffeeShopService.class.getName());
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public CoffeeShopService() {
    }

    /**
     * Retrieves representation of an instance of services.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getShops() {
        //TODO return proper representation object
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}</style><b>USERS LIST:</b><br><br><table cellpadding=10 border=1><tr><td>ID</td><td>Name</td><td>Address</td><td>Phone</td></tr>");
        try
        {
            Model db = Model.singleton();
            CoffeeShop[] shops = db.getShops();
            for (int i=0;i<shops.length;i++)
                sb.append("<tr><td>" + shops[i].getMyshopId()+ "</td><td>" + shops[i].getMyshopname()+ "</td><td>" + shops[i].getMyaddress()+ "</td><td>" + shops[i].getMyphone()+ "</td></tr>");
        }
        catch (Exception e)
        {
            sb.append("</table><br>Error getting users: " + e.toString() + "<br>");
        }
        sb.append("</table></body></html>");
        return sb.toString();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateUser(String jobj) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        CoffeeShop shop = mapper.readValue(jobj.toString(), CoffeeShop.class);
        StringBuilder text = new StringBuilder();
        try {
            Model db = Model.singleton();
            int shopid = shop.getMyshopId();
            db.updateShop(shop);
            logger.log(Level.INFO, "update shop with shopid=" + shopid);
            text.append("Coffee Shop id updated with shop id=" + shopid + "\n");
        }
        catch (SQLException sqle)
        {
            String errText = "Error updating user after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
            logger.log(Level.SEVERE, errText);
            text.append(errText);
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error connecting to db.");
            text.append("Error connecting to db.");
        }
        return text.toString();
    }
    
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteUser(String jobj) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jobj.toString(), User.class);
        StringBuilder text = new StringBuilder();
        try {
            Model db = Model.singleton();
            int userid = user.getUserId();
            db.deleteUser(userid);
            logger.log(Level.INFO, "user deleted from db=" + userid);
            text.append("User id deleted with id=" + userid);
        }
        catch (SQLException sqle)
        {
            String errText = "Error deleteing user after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
            logger.log(Level.SEVERE, errText);
            text.append(errText);
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error connecting to db.");
            text.append("Error connecting to db.");
        }
        return text.toString();
    }
   
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createShop(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CoffeeShop shop = mapper.readValue(jobj.toString(), CoffeeShop.class);
        
        
        StringBuilder text = new StringBuilder();
       /* text.append("The JSON obj:" + jobj.toString() + "\n");
        text.append("Hello " + user.getName() + "\n");
        text.append("You're only " + user.getAge() + " years old.\n");
        text.append("Messages:\n"); 
        
        for (Object msg : user.getMessages())
            text.append(msg.toString() + "\n");
        */
        try {
            Model db = Model.singleton();
            int shopid = db.newShop(shop);
            logger.log(Level.INFO, "shop persisted to db as shopid=" + shopid);
            text.append("Shop id persisted with id=" + shopid);
        }
        catch (SQLException sqle)
        {
            String errText = "Error persisting user after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
            logger.log(Level.SEVERE, errText);
            text.append(errText);
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error connecting to db.");
        }
        
        
        return text.toString();
    }
}
