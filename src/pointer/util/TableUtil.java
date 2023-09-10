package pointer.util;

import pointer.components.MyTableModel;

import java.sql.*;
import java.util.StringJoiner;
import java.util.Vector;

public class TableUtil {
    private final String TABLE_NAME;
    private Vector<String> columnNames;
    private int columnCount;

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

    public static void main(String[] args) {
        TableUtil tableUtil = new TableUtil("employee");
        Vector<String> myList = new Vector<>();
        myList.add("Mary");
        myList.add("111111");
        myList.add("男");
        myList.add("12345678451");

        tableUtil.insertTable(myList);
    }
}



