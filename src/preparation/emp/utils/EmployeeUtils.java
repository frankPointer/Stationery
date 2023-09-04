package preparation.emp.utils;

import preparation.emp.Employee;
import preparation.emp.JDBCUtils;

import java.sql.*;

public class EmployeeUtils {
    static Connection connection = JDBCUtils.getConnection();


    public static void createEmp(Employee employee) {
        String query = "INSERT INTO employee (age, name, salary) VALUES (?,?,?)";
        try {
            // 使用 PreparedStatement 防止sql注入
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, employee.getAge());
            ps.setString(2, employee.getName());
            ps.setDouble(3, employee.getSalary());

            int cnt = ps.executeUpdate(); // 判断是否插入成功
            if (cnt != 0) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void showAllEmp() {
        String query = "select * from employee";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.print(rs.getInt("id") + " ");
                System.out.print(rs.getInt("age") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getDouble("salary") + " ");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateEmp(int id, Employee updatedEmP) {
        String query = "update  employee set " +
                "id  = ?," +
                "age = ?," +
                "name = ?," +
                "salary = ?"+
                "where id  = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void deleteEmp(int id) {

    }
}
