package pointer.components;

import pointer.util.CategoryUtil;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.IOException;

public class CategoryInterface extends JPanel {
    private static final CategoryUtil CATEGORY_UTIL = new CategoryUtil("category");
    private static MyTable table;

    public CategoryInterface() {
        setLayout(new BorderLayout());

        this.add(getButtonPanel(), BorderLayout.NORTH);
        this.add(getTableScrollPane(), BorderLayout.CENTER);
    }

    public static JPanel getButtonPanel() {
        JTextField searchField = new JTextField("输入类别名称来查询", 15);
        JButton searchButton = new JButton("查找");
        JButton updateButton = new JButton("更改");
        JButton insertButton = new JButton("添加");
        JButton deleteButton = new JButton("删除");
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(insertButton);

        searchButton.addActionListener(e -> {
            int row;
            String searchTarget = searchField.getText().trim();
            if (searchTarget.equals("输入类别名称来查询") || searchTarget.length() == 0) {
                JOptionPane.showMessageDialog(null, "请输入类别名称来查找", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                row = retrieveEmpName(searchTarget);
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "没有此类别", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    table.setRowSelectionInterval(0, row);
                }
            }
        });

        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "当前未选中任何数据", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Integer id = (Integer) table.getValueAt((row), 0);
                String name = (String) table.getValueAt(row, 1);
                String description = (String) table.getValueAt(row, 2);

                try {
                    new CategoryUpdateDialog("修改商品类别信息", true, id, name, description).setVisible(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                updateTable();
            }
        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "当前未选中任何数据", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Integer id = (Integer) table.getValueAt(row, 0);
                String name = (String) table.getValueAt(row, 1);
                int result = JOptionPane.showConfirmDialog(null, "确定删除类别" + name + "吗？", "提示", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    CATEGORY_UTIL.deleteTable(id);
                    updateTable();
                }
            }
        });

        insertButton.addActionListener(e -> {
            try {
                new CategoryInsertDialog("添加新分类", true).setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            updateTable();
        });
        return buttonPanel;
    }

    public static JScrollPane getTableScrollPane() {
        JScrollPane scrollPane = new JScrollPane();

        MyTableModel tableModel = new MyTableModel();
        CATEGORY_UTIL.getTableData(tableModel);

        table = new MyTable(tableModel);

        // 设置列宽
        TableColumn column0 = table.getColumnModel().getColumn(2);
        column0.setPreferredWidth(25);
        TableColumn column1 = table.getColumnModel().getColumn(2);
        column1.setPreferredWidth(25);
        TableColumn column2 = table.getColumnModel().getColumn(2);
        column2.setPreferredWidth(500);


        scrollPane.setViewportView(table);
        return scrollPane;
    }

    private static int retrieveEmpName(String searchTarget) {
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            String valueAt = (String) table.getModel().getValueAt(i, 1);
            if (searchTarget.equals(valueAt)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateTable() {
        MyTableModel tableModel = new MyTableModel();
        CATEGORY_UTIL.getTableData(tableModel);
        table.setModel(tableModel);
        // 设置列宽
        TableColumn column0 = table.getColumnModel().getColumn(2);
        column0.setPreferredWidth(25);
        TableColumn column1 = table.getColumnModel().getColumn(2);
        column1.setPreferredWidth(25);
        TableColumn column2 = table.getColumnModel().getColumn(2);
        column2.setPreferredWidth(500);
    }
}
