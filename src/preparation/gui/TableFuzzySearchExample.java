package preparation.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableFuzzySearchExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Table Fuzzy Search Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        Object[][] data = {
                {"John", "Doe", 25, "USA"},
                {"Alice", "Smith", 30, "Canada"},
                {"Bob", "Johnson", 35, "UK"},
                {"David", "Lee", 40, "USA"}
        };

        String[] columnNames = {"First Name", "Last Name", "Age", "Country"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        // 设置列渲染器，自定义单元格显示效果
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                String searchText = "Jo"; // 模糊查询的文本
                int columnIndex = 0; // 要进行模糊查询的列索引

                Object cellValue = table.getValueAt(row, columnIndex);
                if (cellValue != null) {
                    String cellText = cellValue.toString();

                    // 使用正则表达式进行模糊匹配
                    Pattern pattern = Pattern.compile(searchText, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(cellText);

                    if (matcher.find()) {
                        // 如果匹配成功，设置背景色为黄色
                        component.setBackground(Color.YELLOW);
                    } else {
                        component.setBackground(table.getBackground());
                    }
                } else {
                    component.setBackground(table.getBackground());
                }

                return component;
            }
        };

        table.getColumnModel().getColumn(0).setCellRenderer(renderer); // 对第一列应用渲染器

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}
