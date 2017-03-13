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
    @Path("{shopid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CoffeeShop> getShops(@PathParam("shopid") String shopid) {
        LinkedList<CoffeeShop> lshops = new LinkedList<CoffeeShop>();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        try
        {
            int sid = Integer.parseInt(shopid);
            ShopModel db = ShopModel.singleton();
            CoffeeShop[] shops = db.getShops(sid);
            for (int i=0;i<shops.length;i++)
                lshops.add(shops[i]);
        }
        catch (Exception e)
        {
            sb.append("Error getting users: " + e.toString());
        }
        return lshops;
    }
}