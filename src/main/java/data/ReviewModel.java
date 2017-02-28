package data;

public class ReviewModel extends model{

  public int newReview(Messages msg) throws SQLException
   {
       String sqlInsert="insert into messages (userid, message, dateadded) values (" + msg.getUserId() + ", '" + msg.getMessage() + "', now());";
       Statement s = createStatement();
       logger.log(Level.INFO, "attempting statement execute");
       s.execute(sqlInsert,Statement.RETURN_GENERATED_KEYS);
       logger.log(Level.INFO, "statement executed.  atempting get generated keys");
       ResultSet rs = s.getGeneratedKeys();
       logger.log(Level.INFO, "retrieved keys from statement");
       int userid = -1;
       while (rs.next())
           userid = rs.getInt(2);   // assuming 3rd column is userid
       logger.log(Level.INFO, "The new user id=" + userid);
       return userid;
   }

   public Messages[] getReview() throws SQLException
   {
       LinkedList<Messages> ll = new LinkedList<Messages>();
       String sqlQuery ="select * from messages;";
       Statement st = createStatement();
       ResultSet rows = st.executeQuery(sqlQuery);
       while (rows.next())
       {
           logger.log(Level.INFO, "Reading row...");
           Messages msg = new Messages();
           msg.setMessageId(rows.getInt("messageid"));
           msg.setUserId(rows.getInt("userid"));
           msg.setMessage(rows.getString("message"));
           logger.log(Level.INFO, "Adding user to list with id=" + msg.getUserId());
           ll.add(msg);
       }
       return ll.toArray(new Messages[ll.size()]);
   }
}
}
