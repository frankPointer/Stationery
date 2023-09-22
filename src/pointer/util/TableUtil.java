package pointer.util;

import pointer.components.MyTableModel;

import java.sql.*;
import java.util.StringJoiner;
import java.util.Vector;

/**
 * @author Frank_pointer
 * <p><code>TableUtil</code> 对数据库表格 crud 类的操作</p>
 *
 * <p>{@link #insertTable(Vector)} 对于自增主码的表格传入除了主码之外的其他数据之后就会将数据插入表格中，并返回新生成的主码</p>
 * <p>{@link #getTableData(MyTableModel)} 从数据库表格得到数据并生成可赋值给 Jtable 的 TableModel</p>
 * <p>{@link #updateTable(int, Vector)} 给出 id，和除了 id 之外的数据就可更新</p>
 * <p>{@link #deleteTable(Integer)} 对于给定的 主码id 可以删除对应的数据</p>
 * @version 1.0
 */
public abstract class TableUtil {
    private final String TABLE_NAME; // 数据库表格名字
    private Vector<String> columnNames; // 数据库每一列的列名
    private int columnCount; // 列的数量

    public TableUtil(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from " + TABLE_NAME);

            // 结果集的元数据对象，用来获取列数和列名
            ResultSetMetaData metaData = ps.getMetaData();
            columnCount = metaData.getColumnCount(); // 获取列数
            columnNames = new Vector<>();
            // 创建向量来存储列名
            for (int i = 1; i <= columnCount; i++) {
                String dataColumnName = metaData.getColumnName(i);
                columnNames.add(dataColumnName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertTable(Vector<String> myList) {
        int empID = 0;

        // 使用 StringJoiner 来组成sql 语句
        StringJoiner joinerOne = new StringJoiner(",", "(", ")");
        StringJoiner joinerTwo = new StringJoiner(",", "(", ")");
        for (int i = 1; i < columnCount; i++) {
            joinerOne.add(columnNames.get(i));
            joinerTwo.add("?");
        }
        String query = "insert into " +
                TABLE_NAME + " " +
                joinerOne + " values " +
                joinerTwo;

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < myList.size(); i++) {
                ps.setString(i + 1, myList.get(i));
            }

            ps.executeUpdate();

            // 得到自增主键
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                empID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empID;
    }

    public void getTableData(MyTableModel tableModel) {
        try {
            Connection connection = DBUtil.getConnection();
            // 这里查询的每一个表的视图，表的列名为英文，视图的列名为中文
            PreparedStatement ps = connection.prepareStatement("select * from " + TABLE_NAME + "_view");
            ResultSet rs = ps.executeQuery();

            // 结果集的元数据对象，用来获取列数和列名
            ResultSetMetaData metaData = ps.getMetaData();
            int columnViewCount = metaData.getColumnCount(); // 获取列数

            // 创建向量来存储列名
            Vector<String> columnViewNames = new Vector<>();
            for (int i = 1; i <= columnViewCount; i++) {
                columnViewNames.add(metaData.getColumnName(i));
            }
            // 将列名向量作为参数传递给表格模型对象，设置其列名
            tableModel.setColumnIdentifiers(columnViewNames);

            while (rs.next()) {
                Vector<Object> rowData = new Vector<>(); // 创建一个向量对象，用来存储每一行的数据
                for (int i = 1; i <= columnViewCount; i++) {
                    rowData.add(rs.getObject(i)); // 将每一列的数据添加到向量中
                }
                tableModel.addRow(rowData); // 将该行数据的向量添加到表格模型对象中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTable(int id, Vector<String> myList) {
        // 组成 sql 语句
        StringJoiner joiner = new StringJoiner(" = ?,", " ", " = ?");
        for (int i = 1; i < columnCount; i++) {
            joiner.add(columnNames.get(i));
        }
        String query = "update " + TABLE_NAME + " set" +
                joiner +
                " where " + columnNames.get(0) + "= ?";

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            for (int i = 0; i < myList.size(); i++) {
                ps.setString(i + 1, myList.get(i));
            }
            ps.setInt(columnCount, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(Integer id) {
        String query = "delete from " + TABLE_NAME + " where id = ?";

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



