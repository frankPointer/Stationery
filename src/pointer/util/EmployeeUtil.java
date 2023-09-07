package pointer.util;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class EmployeeUtil {
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

    public static int insertEmployee(String name, String password, String gender, String phone) {
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
}
