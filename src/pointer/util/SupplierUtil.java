package pointer.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierUtil extends TableUtil {
    public SupplierUtil(String TABLE_NAME) {
        super(TABLE_NAME);
    }

    @Override
    public void deleteTable(Integer id) {
        String sql1 = "delete from product where SupplierID = ?";
        String sql2 = "delete from supplier where id = ?";
        Connection conn = null;
        PreparedStatement ps1 = null; //预编译语句对象
        PreparedStatement ps2 = null; //预编译语句对象
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            //获取预编译语句对象，并填充占位符
            ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, id); //假设要删除supplierID为1的数据
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, id); //假设要删除id为1的数据
            //执行sql语句，并获取影响的行数
            ps1.executeUpdate();
            ps2.executeUpdate();

            //提交事务
            conn.commit();
        } catch (Exception e) {
            //发生异常时，回滚事务
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
