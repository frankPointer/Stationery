package pointer.components;

import pointer.util.EmpUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EmpInterface extends JPanel {
    private static MyTable table;

    public EmpInterface() {
        setLayout(new BorderLayout());

        this.add(getButtonPanel(), BorderLayout.NORTH);
        this.add(getTableScrollPane(), BorderLayout.CENTER);

    }

    private static JScrollPane getTableScrollPane() {
        JScrollPane scrollPane = new JScrollPane();
        // 创建表格模型对象，并调用方法从数据库中获取数据
        MyTableModel tableModel = new MyTableModel();
        EmpUtil.getTableData(tableModel);

        // 创建表格对象，并将表格模型对象作为参数传递给它
        table = new MyTable(tableModel);

        scrollPane.setViewportView(table);
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
            String searchTarget = searchField.getText().trim();
            int row;
            assert searchWay != null;
            if (searchWay.equals("请选择查找方式")) {
                JOptionPane.showMessageDialog(null, "请选择查找方式", "提示", JOptionPane.INFORMATION_MESSAGE);

            } else {
                if (searchTarget.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请输入查找内容", "提示", JOptionPane.INFORMATION_MESSAGE);

                } else {

                    if (searchWay.equals("员工ID")) {
                        row = retrieveEmpID(searchTarget);

                        if (row == 0) {
                            JOptionPane.showMessageDialog(null, "查无此人", "提示", JOptionPane.WARNING_MESSAGE);
                        } else {

                            table.setRowSelectionInterval(0, row);  // 选中查找行
                        }

                    } else if (searchWay.equals("员工名字")) {
                        row = retrieveEmpName(searchTarget);

                        if (row == 0) {
                            JOptionPane.showMessageDialog(null, "查无此人", "提示", JOptionPane.WARNING_MESSAGE);
                        } else {
                            table.setRowSelectionInterval(1, row);  // 选中查找行
                        }

                    }
                }
            }
        });
        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            // TODO 所选行的数据，修改Dialog的构造方法
            Integer id = (Integer) table.getValueAt(row, 0);
            String name = (String) table.getValueAt(row, 1);
            String gender = (String) table.getValueAt(row, 2);
            String password = (String) table.getValueAt(row, 3);
            String phone = (String) table.getValueAt(row, 4);
            try {
                new EmpUpdateDialog("更新员工信息", true, id, name, gender, password, phone).setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return buttonPanel;
    }

    private static int retrieveEmpName(String searchTarget) {
        int flag = 0;
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            if (searchTarget.equals(table.getModel().getValueAt(i, 1))) {
                flag = i;
                return flag;
            }
        }
        return flag;
    }

    private static int retrieveEmpID(String searchTarget) {
        int flag = 0;
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            if (searchTarget.equals(table.getModel().getValueAt(i, 0))) {
                flag = i;
                return flag;
            }
        }
        return flag;
    }


}

