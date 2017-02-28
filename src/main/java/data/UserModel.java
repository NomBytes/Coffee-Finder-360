/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

public class UserModel extends Model{

  public int newUser(User usr) throws SQLException
  {
      String sqlInsert="insert into users (username, email, password) values ('" + usr.getUsername() + "'" + ", '" + usr.getEmail() + "', '" + usr.getPassword() + "');";
      Statement s = createStatement();
      logger.log(Level.INFO, "attempting statement execute");
      s.execute(sqlInsert,Statement.RETURN_GENERATED_KEYS);
      logger.log(Level.INFO, "statement executed.  atempting get generated keys");
      ResultSet rs = s.getGeneratedKeys();
      logger.log(Level.INFO, "retrieved keys from statement");
      int userid = -1;
      while (rs.next())
          userid = rs.getInt(4);   // assuming 4th column is userid
      logger.log(Level.INFO, "The new user id=" + userid);
      return userid;
  }

  public void deleteUser(int userid) throws SQLException
  {
      String sqlDelete="delete from users where userid=?";
      PreparedStatement pst = createPreparedStatement(sqlDelete);
      pst.setInt(1, userid);
      pst.execute();
  }

  public User[] getUsers() throws SQLException
  {
      LinkedList<User> ll = new LinkedList<User>();
      String sqlQuery ="select * from users;";
      Statement st = createStatement();
      ResultSet rows = st.executeQuery(sqlQuery);
      while (rows.next())
      {
          logger.log(Level.INFO, "Reading row...");
          User usr = new User();
          usr.setUsername(rows.getString("username"));
          usr.setEmail(rows.getString("email"));
          usr.setPassword(rows.getString("password"));
          usr.setUserId(rows.getInt("userid"));
          logger.log(Level.INFO, "Adding user to list with id=" + usr.getUserId());
          ll.add(usr);
      }
      return ll.toArray(new User[ll.size()]);
  }

  public boolean updateUser(User usr) throws SQLException
  {
      StringBuilder sqlQuery = new StringBuilder();
      sqlQuery.append("update users ");
      sqlQuery.append("set username='" + usr.getUsername() + "', ");
      sqlQuery.append("email='" + usr.getEmail() + "', ");
      sqlQuery.append("password='" + usr.getPassword() + "' ");
      sqlQuery.append("where userid=" + usr.getUserId() + ";");
      Statement st = createStatement();
      logger.log(Level.INFO, "UPDATE SQL=" + sqlQuery.toString());
      return st.execute(sqlQuery.toString());
  }

}
