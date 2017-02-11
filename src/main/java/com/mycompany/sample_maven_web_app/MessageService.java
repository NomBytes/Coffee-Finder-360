package com.mycompany.sample_maven_web_app;

import static com.mycompany.sample_maven_web_app.UserService.logger;
import data.Model;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import objects.Messages;
import objects.User;
import org.codehaus.jackson.map.ObjectMapper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ubuntu
 */
public class MessageService {
    
    public MessageService() {
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getMessages() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><style>table, th, td {font-family:Arial,Verdana,sans-serif;font-size:16px;padding: 0px;border-spacing: 0px;}</style><b>USERS LIST:</b><br><br><table cellpadding=10 border=1><tr><td>Name</td><td>Age</td><td>userid</td></tr>");
        try
        {
            Model db = Model.singleton();
            Messages[] messages = db.getMessages();
            for (int i = 0; i < messages.length; i++)
                sb.append("<tr><td>" + messages[i].getMessageId() + "</td><td>" + messages[i].getUserId() + "</td><td>" + messages[i].getMessage() + "</td></tr>");
        }
        catch (Exception e)
        {
            sb.append("</table><br>Error getting messages: " + e.toString() + "<br>");
        }
        sb.append("</table></body></html>");
        return sb.toString();
    }
    
    
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateMessage() {
        return "";
    }
    
    
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteMessage() {
        return "";
    }
    
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createMessage(String jobj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Messages message = mapper.readValue(jobj.toString(), Messages.class);
        
        StringBuilder text = new StringBuilder();
        text.append("The JSON obj:" + jobj.toString() + "\n");
        
        try {
            Model db = Model.singleton();
            int messageid = db.newMessage(message);
            logger.log(Level.INFO, "message persisted to db as messageid=" + messageid);
            text.append("Message id persisted with id=" +  messageid);
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
