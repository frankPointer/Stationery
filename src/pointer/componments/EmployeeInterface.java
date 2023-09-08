package pointer.componments;

import pointer.util.EmployeeUtil;

import javax.swing.*;
import java.awt.*;

public class EmployeeInterface extends JPanel {
    public EmployeeInterface() {
        setLayout(new BorderLayout());

        this.add(getButtonPanel(), BorderLayout.NORTH);
        this.add(getTableScrollPane(),BorderLayout.CENTER);

    }

    private static JScrollPane getTableScrollPane() {
        JScrollPane scrollPane = new JScrollPane();
        // 创建表格模型对象，并调用方法从数据库中获取数据
        MyTableModel tableModel = new MyTableModel();
        EmployeeUtil.getTableData(tableModel);

        // 创建表格对象，并将表格模型对象作为参数传递给它
        MyTable table = new MyTable(tableModel);

        scrollPane.add(table);
        return scrollPane;
    }

    private static JPanel getButtonPanel() {
        // 创建工具按钮
        JComboBox<String> searchMethodBox = new JComboBox<>();
        searchMethodBox.addItem("请选择查找方式");
        searchMethodBox.addItem("员工ID");
        searchMethodBox.addItem("员工名字");

        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("查找");
        JButton updateButton = new JButton("更改");
        JButton insertButton = new JButton("添加");
        JButton deleteButton = new JButton("删除");
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(searchMethodBox);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(insertButton);

        searchButton.addActionListener(e -> {
            String searchWay = (String) searchMethodBox.getSelectedItem();

        });
        return buttonPanel;
    }


}

