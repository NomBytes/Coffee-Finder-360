/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coffee_finder;

import data.Model;
import data.ShopModel;
import data.UserModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import objects.CoffeeShop;
import objects.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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
    @Path("{shopid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CoffeeShop> getShops(@PathParam("shopid") String sid) {
        
    
        LinkedList<CoffeeShop> lshops = new LinkedList<CoffeeShop>();
        
       
        try
        {
            int shopid = Integer.parseInt(sid);
                    
            ShopModel db = ShopModel.singleton();
            
            CoffeeShop[] shops = db.getShops(shopid);
            
            if (shopid == 0)
                for (int i=0;i<shops.length;i++)
                    lshops.add(shops[i]);
            else
                lshops.add(shops[0]);
            logger.log(Level.INFO, "Received request to fetch shop id=" + shopid);
            return lshops;
        }
        catch (Exception e)
        {
            return null;
        }
        
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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<CoffeeShop> createShop(String jobj) throws IOException {
        
        
        LinkedList<CoffeeShop> lshops = new LinkedList<CoffeeShop>();
        
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
            CoffeeShop shp = db.newShop(shop);
            lshops.add(shp);
            logger.log(Level.INFO, "shop persisted to db as shopid=" + shp.getMyshopId());
            text.append("Shop id persisted with id=" + shp.getMyshopId());
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
        
        
        return lshops;
    }
      
}
