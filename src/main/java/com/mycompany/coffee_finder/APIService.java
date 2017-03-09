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
public class APIService {

    static final Logger logger = Logger.getLogger(CoffeeShopService.class.getName());
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public APIService() {
    }

    /**
     * Retrieves representation of an instance of services.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getShops() {
        //TODO return proper representation object
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}</style><b>Shops:</b><br><br><table cellpadding=10 border=1><tr><td>ID</td><td>Name</td><td>Address</td><td>City</td><td>State</td><td>Zip</td><td>Phone</td><td>Lattitude</td><td>Longitude</td></tr>");
        try
        {
            ShopModel db = ShopModel.singleton();
            CoffeeShop[] shops = db.getShops();
            for (int i=0;i<shops.length;i++)
                sb.append("shop".shops[i].getMyShopName + "shop".shops[i].getMystreet()+ "shop".shops[i].getMycity() +"shop".shops[i].getMystate() +"shop".shops[i].getMyzip() +"shop".shops[i].getMyphone()+ "shop".shops[i].getMylatitude() + "shop".shops[i].getMylongitude());
        }
        catch (Exception e)
        {
            sb.append("Error getting users: " + e.toString());
        }
        return sb.toString();
    }