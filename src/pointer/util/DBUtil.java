package pointer.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Connection connection;
    static  {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("db.properties");
            // 加载数据库配置文件
            Properties properties = new Properties();
            properties.load(inputStream);

            // 建立数据库连接
            connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.user"),
                    properties.getProperty("jdbc.password")
                    );
        } catch (Exception e)  {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
        //ResultSet关闭
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Statement关闭
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Connection关闭
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
