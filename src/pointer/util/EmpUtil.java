package pointer.util;

import pointer.components.MyTableModel;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class EmpUtil {
    private static final String TABLE_NAME = "employee";

    public static Map<Integer, String> getNamePassword() {
        // 使用hashmap 来存储名字和密码
        Map<Integer, String> map = new HashMap<>();

        String query = "select employee.id, employee.password from employee;";
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static int insertTable(String name, String password, String gender, String phone) {
        int empID = 0;
        String query = "insert into employee (name, password, gender, phone) values (?, ?, ?, ?);\n";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); // 得到自增主键
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, gender);
            ps.setString(4, phone);
            //获取自动生成的主键
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                empID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empID;
    }

    public static void getTableData(MyTableModel tableModel) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from " + TABLE_NAME + "_view");
            ResultSet rs = ps.executeQuery();

            // 结果集的元数据对象，用来获取列数和列名
            ResultSetMetaData metaData = ps.getMetaData();
            int columnCount = metaData.getColumnCount(); // 获取列数

            // 创建向量来存储列名
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            // // 将列名向量作为参数传递给表格模型对象，设置其列名
            tableModel.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Vector<Object> rowData = new Vector<>(); // 创建一个向量对象，用来存储每一行的数据
                for (int i = 1; i <= columnCount; i++) {
                    rowData.add(rs.getObject(i)); // 将每一列的数据添加到向量中
                }
                tableModel.addRow(rowData); // 将该行数据的向量添加到表格模型对象中
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTable(Integer id, String name, String password, String gender, String phone) {
        String query = "update " + TABLE_NAME + " set " +
                "name  = ?," +
                "password = ?," +
                "gender = ?," +
                "phone = ?" +
                "where id  = ?";

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, gender);
            ps.setString(4, phone);
            ps.setInt(5, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTable(Integer id) {
        String query = "delete from" + " TABLE_NAME " + "where id = ?";

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
