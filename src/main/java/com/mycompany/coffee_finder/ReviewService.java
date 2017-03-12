/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coffee_finder;

import data.Model;
import data.ReviewModel;
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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import objects.Review;
import objects.User;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author wingoz
 */
@Path("reviews")
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
    @Path("{reviewid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReviews(@PathParam("reviewid") String rid) {
        LinkedList<Review> lreviews = new LinkedList<Review>();
        
        StringBuilder sb = new StringBuilder();
//        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}"
//                + "</style><b>Reviews LIST:</b><br><br><table cellpadding=10 border=1><tr>"
//                + "<td>ReviewId</td><td>UserId</td><td>ShopId</td><td>CoffeeScore</td><td>BurritoScore</td><td>DollarScore</td><td>Review</td><td>Helpful</td><td>UnHelpful</td></tr>");
        try
        {
            int reviewid = Integer.parseInt(rid);
            ReviewModel db = ReviewModel.singleton();
            Review[] reviews = db.getReviews(reviewid);
            if (reviewid == 0)
                for (int i=0;i<reviews.length;i++)
                    lreviews.add(reviews[i]);
            else
                lreviews.add(reviews[0]);
            logger.log(Level.INFO, "Received request to fetch shop id=" + reviewid);
            return lreviews;
               
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
 /*   @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateReview(String jobj) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        Review review = mapper.readValue(jobj.toString(), Review.class);
        StringBuilder text = new StringBuilder();
        try {
            ReviewModel db = (ReviewModel)Model.singleton();
            int userid = review.getUserId();
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
*/
/*   
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteReview(String jobj) throws IOException
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
    }*/
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createReview(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Review review = mapper.readValue(jobj.toString(), Review.class);
        
        
        StringBuilder text = new StringBuilder();
        try {
            ReviewModel db = ReviewModel.singleton();
            int reviewid = db.newReview(review);
            logger.log(Level.INFO, "review persisted to db as reviewid=" + reviewid);
            text.append("Review id persisted with id=" + reviewid);
        }
        catch (SQLException sqle)
        {
            String errText = "Error persisting review after db connection made:\n" + sqle.getMessage() + " --- " + sqle.getSQLState() + "\n";
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