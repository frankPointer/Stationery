package pointer.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductUtil extends TableUtil {
    public ProductUtil(String TABLE_NAME) {
        super(TABLE_NAME);
    }


    public void insertTable(String name, Integer categoryID, Integer supplierID, BigDecimal price, Integer quantity, String description) {
        String query = "insert into product (ProductName, CategoryID, SupplierID, Price, StockQuantity, Description) values (?,?,?,?,?,?);";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, categoryID);
            ps.setInt(3, supplierID);
            ps.setBigDecimal(4, price);
            ps.setInt(5, quantity);
            ps.setString(6, description);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Map<Integer, String> getCategory() {
        Map<Integer, String> categoryNames = new HashMap<>();

        String query = "select id,CategoryName from category;";
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoryNames.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryNames;
    }

    public Map<Integer, String> getSupplier() {
        Map<Integer, String> categoryNames = new HashMap<>();

        String query = "select id,SupplierName from supplier;";
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoryNames.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryNames;
    }
}
