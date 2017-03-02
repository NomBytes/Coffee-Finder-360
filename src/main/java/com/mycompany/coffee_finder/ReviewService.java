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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
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
    @Produces(MediaType.TEXT_HTML)
    public String getReviews() {
        //TODO return proper representation object
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}"
                + "</style><b>Reviews LIST:</b><br><br><table cellpadding=10 border=1><tr>"
                + "<td>ReviewId</td><td>UserId</td><td>ShopId</td><td>CoffeeScore</td><td>BurritoScore</td><td>DollarScore</td><td>Review</td><td>Helpful</td><td>UnHelpful</td></tr>");
        try
        {
            ReviewModel db = ReviewModel.singleton();
            Review[] reviews = db.getReviews();
            for (int i=0;i<reviews.length;i++)
                sb.append("<tr><td>" + reviews[i].getReviewId() + "</td><td>" + reviews[i].getUserId() + "</td><td>" + reviews[i].getShopId()+ "</td><td>" + reviews[i].getCoffeeScore()+ "</td><td>"+ reviews[i].getBurritoScore() +"</td><td>"+ reviews[i].getDollarScore() +"</td><td>"+ reviews[i].getReview() +"</td><td>"+ reviews[i].getNumHelpful() +"</td><td>"+ reviews[i].getNumUnhelpful() +"</td></tr>");
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
            logger.log(Level.INFO, "user persisted to db as userid=" + reviewid);
            text.append("User id persisted with id=" + reviewid);
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