package preparation.emp;

import preparation.emp.constants.JDBCConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Mr_chen
 * 这个类是一个JDBC连接的工具类，使用的时候使用这个类名加上方法getConnection就可以直接得到connection了
 * 另外一个close方法是当你窗口关闭的时候调用用来关闭连接释放资源
 */
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
