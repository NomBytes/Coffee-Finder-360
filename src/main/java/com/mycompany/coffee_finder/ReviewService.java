/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coffee_finder;

import data.Model;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import objects.User;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author wingoz
 */
@Path("users")
public class ReviewService {

    static final Logger logger = Logger.getLogger(ReviewService.class.getName());
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public ReviewService() {
    }

    /**
     * Retrieves representation of an instance of services.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getUsers() {
        //TODO return proper representation object
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}</style><b>USERS LIST:</b><br><br><table cellpadding=10 border=1><tr><td>Username</td><td>Email</td><td>Password</td><td>ID</td></tr>");
        try
        {
            Model db = Model.singleton();
            Review[] reviews = db.getReviews();
            for (int i=0;i<reviews.length;i++)
                sb.append("<tr><td>" + reviews[i].getMyUserId() + "</td><td>" + reviews[i].getMyShopId() + "</td><td>" + reviews[i].getMyCoffeeScore() + "</td><td>" + reviews[i].getMyBurritoScore() + "</td></tr>");
        }
        catch (Exception e)
        {
            sb.append("</table><br>Error getting reviews: " + e.toString() + "<br>");
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
        Review review = mapper.readValue(jobj.toString(), Review.class);
        StringBuilder text = new StringBuilder();
        try {
            Model db = Model.singleton();
            int userid = review.getMyUserId();
            db.updateReview(review);
            logger.log(Level.INFO, "update review with userid=" + userid);
            text.append("User id updated with user id=" + userid + "\n");
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
        Review review = mapper.readValue(jobj.toString(), Review.class);
        StringBuilder text = new StringBuilder();
        try {
            Model db = Model.singleton();
            int userid = review.getMyUserId();
            db.deleteReview(userid);
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
    public String createUser(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Review review = mapper.readValue(jobj.toString(), Review.class);
        
        
        StringBuilder text = new StringBuilder();
        try {
            Model db = Model.singleton();
            int userid = db.newReview(review);
            logger.log(Level.INFO, "user persisted to db as userid=" + userid);
            text.append("User id persisted with id=" + userid);
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