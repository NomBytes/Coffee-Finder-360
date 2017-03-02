package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import objects.Messages;
import objects.Review;

public class ReviewModel extends Model  {

    private static ReviewModel instance;
    
    ReviewModel()  throws Exception {
        super();
    }
    
    public static ReviewModel singleton() throws Exception {
        if (instance == null) {
            instance = new ReviewModel();
        }
        return (ReviewModel)instance;
    }
        
  public int newReview(Review review) throws SQLException
   {
       String sqlInsert="insert into reviews (userid, review, dateadded) values (" + review.getUserId() + ", '" + review.getReview() + "', now());";
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

   public Review[] getReviews() throws SQLException
   {
       LinkedList<Review> ll = new LinkedList<Review>();
       String sqlQuery ="select * from reviews;";
       Statement st = createStatement();
       ResultSet rows = st.executeQuery(sqlQuery);
       while (rows.next())
       {
           logger.log(Level.INFO, "Reading row...");
           Review rev = new Review();
           rev.setReviewId(rows.getInt("reviewid"));
           rev.setMyUserId(rows.getInt("userid"));
           rev.setMyShopId(rows.getInt("shopid"));
           rev.setMyCoffeeScore(rows.getInt("coffeescore"));
           rev.setMyBurritoScore(rows.getInt("burritoscore"));
           rev.setMyDollarScore(rows.getInt("dollarscore"));
           rev.setMyReview(rows.getString("review"));
           rev.setMyHelpful(rows.getInt("helpfulcount"));
           rev.setMyUnhelpful(rows.getInt("unhelpfulcount"));
           logger.log(Level.INFO, "Adding user to list with id=" + rev.getReviewId());
           ll.add(rev);
       }
       return ll.toArray(new Review[ll.size()]);
   }
}