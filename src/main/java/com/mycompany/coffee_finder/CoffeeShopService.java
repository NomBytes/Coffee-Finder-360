/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coffee_finder;

import data.Model;
import data.ShopModel;
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
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}</style><b>Shops:</b><br><br><table cellpadding=10 border=1><tr><td>ID</td><td>Name</td><td>Address</td><td>City</td><td>State</td><td>Zip</td><td>Phone</td><td>Lattitude</td><td>Longitude</td></tr>");
        try
        {
            ShopModel db = ShopModel.singleton();
            CoffeeShop[] shops = db.getShops();
            for (int i=0;i<shops.length;i++)
                sb.append("<tr><td>" + shops[i].getMyshopId()+ "</td><td>" + shops[i].getMyshopname()+ "</td><td>" + shops[i].getMystreet()+ "</td><td>"+ shops[i].getMycity() +"</td><td>"+ shops[i].getMystate() +"</td><td>"+ shops[i].getMyzip() +"</td><td>" + shops[i].getMyphone()+ "</td><td>" + shops[i].getMylatitude() + "</td><td>"+ shops[i].getMylongitude() +"</td></tr>");
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
            ShopModel db = ShopModel.singleton();
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
    public String deleteShop(String jobj) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        CoffeeShop shop = mapper.readValue(jobj.toString(), CoffeeShop.class);
        StringBuilder text = new StringBuilder();
        try {
            ShopModel db = ShopModel.singleton();
            int shopid = shop.getMyshopId();
            db.deleteShop(shopid);
            logger.log(Level.INFO, "shop deleted from db=" + shopid);
            text.append("Shop id deleted with id=" + shopid);
        }
        catch (SQLException sqle)
        {
            String errText = "Error deleteing shop after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
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
            ShopModel db = ShopModel.singleton();
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
