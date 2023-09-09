package preparation.gui;

import pointer.components.MyTable;
import pointer.components.MyTableModel;
import pointer.util.EmpUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

/**
 * 这个类是用来将查询后的数据用表格显示出来的，到时候图形化界面展示信息的时候主要靠得就是这个了
 */
public class DBTableDemo extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/stationery_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";
    private static final String DB_TABLE_NAME = "product";

    // 定义表格组件和滚动面板组件
    private JTable table;
    public DBTableDemo() {
        // 调用父类构造方法，设置窗口标题
        super("DBTableDemo");

        // 创建表格模型对象，并调用方法从数据库中获取数据
        MyTableModel tableModel = new MyTableModel();

        EmpUtil.getTableData(tableModel);

        // 创建表格对象，并将表格模型对象作为参数传递给它
        table = new JTable(tableModel);


        // 创建格式渲染器来使得文字居中
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        // 设置表格内容居中
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, headerRenderer);

        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 30));	//修改表头的高度
        table.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 15)); // 设置表头字体
        table.getTableHeader().setReorderingAllowed(false); // 禁用列拖拽调整

        // 设置表格的一些属性，如行高、选择模式、背景色等
        table.setEnabled(false); // 设置不可修改
        table.setRowHeight(35); // 设置行高为 30 像素
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 设置只能单选
        table.setBackground(new Color(224, 242, 255)); // 设置背景色为浅蓝色
        table.setFont(new Font("新宋体", Font.PLAIN, 18)); // 设置字体


        // 创建滚动面板对象，并将表格对象添加到它里面
//        JScrollPane scrollPane = new JScrollPane(table);
        MyTable myTable = new MyTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(myTable);

        // 将滚动面板对象添加到窗口中，并设置窗口的大小、位置、可见性等属性
        this.add(scrollPane);
        this.setSize(800, 600); // 设置窗口大小为 800 x 600 像素
        this.setLocationRelativeTo(null); // 设置窗口居中显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口关闭时退出程序
        this.setVisible(true); // 设置窗口可见
    }
    public void refreshTableData(JTable table) {
        DefaultTableModel model =(DefaultTableModel) table.getModel();
        getDataFromDB(model);
        model.fireTableDataChanged();
    }

    public void getDataFromDB(DefaultTableModel tableModel) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            PreparedStatement ps = connection.prepareStatement("select * from " + DB_TABLE_NAME);
            ResultSet rs = ps.executeQuery();

            // 结果集的元数据对象，用来获取列数和列名
            ResultSetMetaData rsmd = ps.getMetaData();
            int columnCount = rsmd.getColumnCount(); // 获取列数

            // 创建向量来存储列名
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }

            // // 将列名向量作为参数传递给表格模型对象，设置其列名
            tableModel.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Vector<Object> rowData = new Vector<Object>(); // 创建一个向量对象，用来存储每一行的数据
                for (int i = 1; i <= columnCount; i++) {
                    rowData.add(rs.getObject(i)); // 将每一列的数据添加到向量中
                }
                tableModel.addRow(rowData); // 将该行数据的向量添加到表格模型对象中
            }

            // 关闭结果集对象、预编译语句对象和连接对象，释放资源
            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 定义一个主方法，用来创建并运行窗口对象
    public static void main(String[] args) {
        new DBTableDemo(); // 创建窗口对象
    }
}
