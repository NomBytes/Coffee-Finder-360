package data;

import java.sql.PreparedStatement;
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
        
  public Review newReview(Review review) throws SQLException
   {
       String sqlInsert="insert into reviews (userid, review, dateadded) values (" + review.getUserId() + ", '" + review.getReview() + "', now());";
       Statement s = createStatement();
       logger.log(Level.INFO, "attempting statement execute");
       s.execute(sqlInsert,Statement.RETURN_GENERATED_KEYS);
       logger.log(Level.INFO, "statement executed.  atempting get generated keys");
       ResultSet rs = s.getGeneratedKeys();
       logger.log(Level.INFO, "retrieved keys from statement");
       int reviewid = -1;
       while (rs.next())
           reviewid = rs.getInt(2);   // assuming 3rd column is userid
       logger.log(Level.INFO, "The new user id=" + reviewid);
       review.setReviewId(reviewid);
       return review;
   }

   public Review[] getReviews(int rid) throws SQLException
   {
       LinkedList<Review> ll = new LinkedList<Review>();
       
       String sqlQuery ="select * from reviews";
       sqlQuery += (rid > 0) ? " where reviewid=" + rid + " order by reviewid;" : " order by reviewid;";
       Statement st = createStatement();
       ResultSet rows = st.executeQuery(sqlQuery);
       while (rows.next())
       {
           logger.log(Level.INFO, "Reading row...");
           Review rev = new Review();
           System.out.println("REV: "+ rev.toString());
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
   
   
   public boolean updateReview(Review review) throws SQLException
  {
      StringBuilder sqlQuery = new StringBuilder();
      sqlQuery.append("update reviews ");
      sqlQuery.append("set userid=" + review.getUserId()+ ", ");
      sqlQuery.append("shopid="+ review.getShopId() +", ");
      sqlQuery.append("coffeescore="+ review.getCoffeeScore() +", ");
      sqlQuery.append("burritoscore="+ review.getBurritoScore() +", ");
      sqlQuery.append("dollarscore=" + review.getDollarScore() + ", ");
      sqlQuery.append("review='" + review.getReview() + "', ");
      sqlQuery.append("helpfulcount=" + review.getNumHelpful() + ", ");
      sqlQuery.append("unhelpfulcount=" + review.getNumUnhelpful() + " ");
      sqlQuery.append("where reviewid=" + review.getReviewId() + ";");
      Statement st = createStatement();
      logger.log(Level.INFO, "UPDATE SQL=" + sqlQuery.toString());
      return st.execute(sqlQuery.toString());
  }
   
   
    public void deleteReview(int reviewid) throws SQLException
    {
      String sqlDelete="delete from reviews where reviewid=?";
      PreparedStatement pst = createPreparedStatement(sqlDelete);
      pst.setInt(1, reviewid);
      pst.execute();
    }
}