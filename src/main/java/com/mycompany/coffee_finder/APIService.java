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
@Path("api/shops")
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
    public List<CoffeeShops> getShops(@PathParam("shopid") String shopid) {
        LinkedList<CoffeeShops> lshops = new LinkedList<CoffeeShops>();
        StringBuilder sb = new StringBuilder();
        
        try
        {
            int sid = Integer.parseInt(shopid);
            ShopModel db = ShopModel.singleton();
            CoffeeShop[] shops = db.getShops(sid);
            for (int i=0;i<shops.length;i++) {
                CoffeeShops c = new CoffeeShops(shops[i]);
                lshops.add(c);
            }
        }
        catch (Exception e)
        {
            sb.append("Error getting users: " + e.toString());
        }
        return lshops;
    }
    
    
    private class CoffeeShops {

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public long getZip() {
            return zip;
        }

        public void setZip(long zip) {
            this.zip = zip;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getOpen() {
            return open;
        }

        public void setOpen(int open) {
            this.open = open;
        }

        public int getClose() {
            return close;
        }

        public void setClose(int close) {
            this.close = close;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        
        private String name;
        private String address;
        private String city;
        private String state;
        private long zip;
        private String phone;
        private int open;
        private int close;
        private String description;
        
        CoffeeShops(CoffeeShop c) {
            name = c.getMyshopname();
            address = c.getMystreet();
            city = c.getMycity();
            state = c.getMystate();
            zip = c.getMyzip();
            phone = c.getMyphone();
            open = c.getMyopenhours();
            close = c.getMyclosehours();
            description = c.getMylatitude() +","+ c.getMylongitude();
        }
    }
}