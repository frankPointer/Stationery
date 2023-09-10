package pointer.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.StringJoiner;

public class TableUtil {
    private final String TABLE_NAME;
    private ArrayList<String> columnNames;
    private int columnCount;
    public TableUtil(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from " + TABLE_NAME);

            // 结果集的元数据对象，用来获取列数和列名
            ResultSetMetaData metaData = ps.getMetaData();
            columnCount = metaData.getColumnCount(); // 获取列数
            columnNames = new ArrayList<>();
            // 创建向量来存储列名
            for (int i = 1; i <= columnCount; i++) {
                String dataColumnName = metaData.getColumnName(i);
                columnNames.add(dataColumnName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertTable(ArrayList<String> myList) {
        int empID ;

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
                ps.setString(i+1, myList.get(i));
            }

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                empID = rs.getInt(1);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return empID;
    }

    public static void main(String[] args) {
        TableUtil tableUtil = new TableUtil("orders");
        ArrayList<String> myList = new ArrayList<>();
        myList.add("Mary");
        myList.add("111111");
        myList.add("Mary");
        myList.add("Mary");

        tableUtil.insertTable();
    }
}



