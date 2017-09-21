package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.NoSuchTypeItemException;
import items.FeedingType;
import items.TransportType;
import vouchers.EducationVoucher;
import vouchers.RestVoucher;
import vouchers.ShoppingVoucher;
import vouchers.SportVoucher;
import vouchers.TherapyVoucher;
import vouchers.TripVoucher;
import vouchers.Voucher;

/**
 * Created by Palina_Piarlukhina on 9/20/2017.
 */
public class DataBase implements Resource {
  String url;
  String user;
  String password;
  String table;

  public DataBase() {
    url = "jdbc:mysql://localhost:3306/vouchers";
    user = "root";
    password = "password";
    table = "allvouchers";
  }

  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  @Override
  public List<Voucher> getVouchers() throws SQLException, NoSuchTypeItemException {
    List<Voucher> list = new ArrayList<>();
    String command = "select * from " + table;
    Map<Integer, Object[]> map = executeSelectCommand(command);
    for (Integer i : map.keySet()) {
      Object[] o = map.get(i);
      String type = (String) o[0];
      int duration = (Integer) o[1];
      String transportType = (String) o[2];
      String feedingType = (String) o[3];
      switch (type) {
        case "rest":
          Voucher v = new RestVoucher(duration, TransportType.getTypeByString(transportType), FeedingType.getTypeByString(feedingType));
          list.add(v);
          break;
        case "sport":
          Voucher s = new SportVoucher(duration, TransportType.getTypeByString(transportType), FeedingType.getTypeByString(feedingType));
          list.add(s);
          break;
        case "therapy":
          Voucher t = new TherapyVoucher(duration, TransportType.getTypeByString(transportType), FeedingType.getTypeByString(feedingType));
          list.add(t);
          break;
        case "shopping":
          Voucher sh = new ShoppingVoucher(duration, TransportType.getTypeByString(transportType), FeedingType.getTypeByString(feedingType));
          list.add(sh);
          break;
        case "trip":
          Voucher tr = new TripVoucher(duration, TransportType.getTypeByString(transportType), FeedingType.getTypeByString(feedingType));
          list.add(tr);
          break;
        case "education":
          Voucher e = new EducationVoucher(duration, TransportType.getTypeByString(transportType), FeedingType.getTypeByString(feedingType));
          list.add(e);
          break;
        default:
      }

    }
    return list;
  }

  public void createNewTable() throws SQLException {
    String tablename = getTable();
    String table = "CREATE TABLE " + tablename + "(\n" +
        "voucher_id INT(5) NOT NULL AUTO_INCREMENT,\n" +
        "voucher_type VARCHAR (20) NOT NULL,\n" +
        "duration INT(5) NOT NULL,\n" +
        "transport_type VARCHAR (20) NOT NULL,\n" +
        "feeding_type VARCHAR (20),\n" +
        "see BOOLEAN,\n" +
        "mountains BOOLEAN,\n" +
        "housing_type VARCHAR(20),\n" +
        "museums VARCHAR (150),\n" +
        "museumItemTax DOUBLE PRECISION(2,2),\n" +
        "guide BOOLEAN,\n" +
        "guideCost DOUBLE PRECISION(5,2),\n" +
        "excursions VARCHAR (150),\n" +
        "purchaseWeight INT(5),\n" +
        "purchaseWeightTax DOUBLE PRECISION (2,2),\n" +
        "sports VARCHAR (150),\n" +
        "sportItemTax DOUBLE PRECISION(2,2),\n" +
        "photoService BOOLEAN,\n" +
        "photoServicePrice DOUBLE PRECISION(2,2),\n" +
        "PRIMARY KEY (voucher_id)\n" +
        ")";
    executeUpdateCommand(table);
  }

  public void addVoucher(Voucher v) throws SQLException {
    String table = getTable();
    String columns = "INSERT INTO " + table + "(";
    String values = ") values (";
    String end = ")";
    if (v.getType() != null) {
      columns += "voucher_type, ";
      values += "'" + v.getType().toString().toLowerCase() + "', ";
    }
    if (v.getTransport() != null) {
      columns += "transport_type, ";
      values += "'" + v.getTransport().toString().toLowerCase() + "', ";
    }
    if (v.getDuration() != 0) {
      columns += "duration, ";
      values += v.getDuration() + ", ";
    }
    if (v.getFeeding() != null) {
      columns += "feeding_type, ";
      values += "'" + v.getFeeding().toString().toLowerCase() + "', ";
    }

    int lastColumnsComma = columns.lastIndexOf(",");
    String result = columns.substring(0, lastColumnsComma);
    int lastValuesComma = values.lastIndexOf(",");
    result += values.substring(0, lastValuesComma) + end;

    executeUpdateCommand(result);
  }

  private void executeUpdateCommand(String command) throws SQLException {
    Connection dbConnection = null;
    Statement statement = null;
    try {
      dbConnection = DriverManager.getConnection(url, user, password);
      statement = dbConnection.createStatement();
      statement.execute(command);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      if (statement != null) {
        statement.close();
      }
      if (dbConnection != null) {
        dbConnection.close();
      }
    }
  }

  private Map<Integer, Object[]> executeSelectCommand(String command) throws SQLException {
    Map<Integer, Object[]> res = new HashMap<>();
    Connection dbConnection = null;
    Statement statement = null;
    ResultSet rs = null;
    try {
      dbConnection = DriverManager.getConnection(url, user, password);
      statement = dbConnection.createStatement();
      rs = statement.executeQuery(command);
      while (rs.next()) {
        res.put(rs.getInt(1), new Object[]{rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)});
      }
      return res;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return res;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (statement != null) {
        statement.close();
      }
      if (dbConnection != null) {
        dbConnection.close();
      }
      return res;
    }
  }
}
