package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import objects.CoffeeShop;

public class ShopModel extends Model{
  private static ShopModel instance;
    
  ShopModel() throws Exception {
      super();
  }

  public static ShopModel singleton() throws Exception {
        if (instance == null) {
            instance = new ShopModel();
        }
        return (ShopModel)instance;
  }
  public CoffeeShop newShop(CoffeeShop shop) throws SQLException
  {
      String sqlInsert="insert into shops (shopname, shopaddress, city, state, zip, shopphone, latitude, longitude) values ('" + shop.getMyshopname() + "', '" + shop.getMystreet()+ "', '" + shop.getMycity()+ "', '" + shop.getMystate() + "', " + shop.getMyzip() + ", '" + shop.getMyphone() + "', " + shop.getMylatitude() + ", " + shop.getMylongitude() + ");";
      Statement s = createStatement();
      logger.log(Level.INFO, "attempting statement execute");
      s.execute(sqlInsert,Statement.RETURN_GENERATED_KEYS);
      logger.log(Level.INFO, "statement executed.  atempting get generated keys");
      ResultSet rs = s.getGeneratedKeys();
      logger.log(Level.INFO, "retrieved keys from statement");
      int shopid = -1;
      while (rs.next())
          shopid = rs.getInt(1);   // assuming 1rd column is shopid
      logger.log(Level.INFO, "The new shop id=" + shopid);
      shop.setMyshopId(shopid);
      return shop;
  }

  public void deleteShop(int shopid) throws SQLException
  {
      String sqlDelete="delete from shops where shopid=?";
      PreparedStatement pst = createPreparedStatement(sqlDelete);
      pst.setInt(1, shopid);
      pst.execute();
  }

  public CoffeeShop[] getShops(int sid) throws SQLException
  {
      LinkedList<CoffeeShop> ll = new LinkedList<CoffeeShop>();
      String sqlQuery ="select * from shops";
      sqlQuery += (sid > 0) ? " where shopid=" + sid + " order by shopid;" : " order by shopid;";
      Statement st = createStatement();
      
      
      ResultSet rows = st.executeQuery(sqlQuery);
      
      while (rows.next())
      {
          logger.log(Level.INFO, "Reading row...");
          CoffeeShop shop = new CoffeeShop();
          shop.setMyshopname(rows.getString("shopname"));
          shop.setMystreet(rows.getString("shopaddress"));
          shop.setMycity(rows.getString("city"));
          shop.setMystate(rows.getString("state"));
          shop.setMyphone(rows.getString("shopphone"));
          shop.setMyzip(rows.getInt("zip"));
          shop.setMylatitude(rows.getString("latitude"));
          shop.setMylongitude(rows.getString("longitude"));
          shop.setMyshopId(rows.getInt("shopid"));
          shop.setMyopenhours(rows.getInt("openhours"));
          shop.setMyclosehours(rows.getInt("closehours"));
          logger.log(Level.INFO, "Adding shop to list with id=" + shop.getMyshopId());
          ll.add(shop);
      }
      
      return ll.toArray(new CoffeeShop[ll.size()]);
  }

  public boolean updateShop(CoffeeShop shop) throws SQLException
  {
      StringBuilder sqlQuery = new StringBuilder();
      sqlQuery.append("update shops ");
      sqlQuery.append("set shopname='" + shop.getMyshopname()+ "', ");
      sqlQuery.append("shopaddress='" + shop.getMystreet()+ "', ");
      sqlQuery.append("city='"+ shop.getMycity() +"', ");
      sqlQuery.append("state='"+ shop.getMystate() +"', ");
      sqlQuery.append("zip='"+ shop.getMyzip() +"', ");
      sqlQuery.append("shopphone='" + shop.getMyphone()+ "', ");
      sqlQuery.append("latitude=" + shop.getMylatitude() + ", ");
      sqlQuery.append("longitude=" + shop.getMylongitude() + ", ");
      sqlQuery.append("openhours=" + shop.getMyopenhours() + ", ");
      sqlQuery.append("closehours=" + shop.getMyclosehours() + " ");
      sqlQuery.append("where shopid=" + shop.getMyshopId()+ ";");
      Statement st = createStatement();
      logger.log(Level.INFO, "UPDATE SQL=" + sqlQuery.toString());
      return st.execute(sqlQuery.toString());
  }
}
