package preparation.emp;

import preparation.emp.constants.JDBCConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static Connection connection;

    // 使用静态代码块连接数据库
    static {
        try {
            // 连接数据库
            connection = DriverManager.getConnection(
                    JDBCConstants.URL,
                    JDBCConstants.USER,
                    JDBCConstants.PASSWORD
            );

            System.out.println("数据库连接成功");

        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
    }

    public static Connection getConnection () {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("关闭数据库");
            }
        } catch (SQLException e) {
            System.out.println("关闭数据库异常" + e.getMessage());
        }
    }
}
