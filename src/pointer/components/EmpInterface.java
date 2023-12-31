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
        searchMethodBox.addItem("编号");
        searchMethodBox.addItem("名字");

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

                    if (searchWay.equals("编号")) {
                        row = retrieveEmpID(searchTarget);

                        if (row == -1) {
                            JOptionPane.showMessageDialog(null, "查无此人", "提示", JOptionPane.WARNING_MESSAGE);
                        } else {

                            table.setRowSelectionInterval(0, row);  // 选中查找行
                        }

                    } else if (searchWay.equals("名字")) {
                        row = retrieveEmpName(searchTarget);

                        if (row == -1) {
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
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "当前未选中任何数据", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // 提交更新到数据库
                Integer id = (Integer) table.getValueAt(row, 0);
                String name = (String) table.getValueAt(row, 1);
                String gender = (String) table.getValueAt(row, 2);
                String password = (String) table.getValueAt(row, 3);
                String phone = (String) table.getValueAt(row, 4);
                try {
                    new EmpUpdateDialog("更改员工信息", true, id, name, gender, password, phone).setVisible(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // 更新表格
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
                int result = JOptionPane.showConfirmDialog(null, "<html>确定删除用户 <b>" + name + "</b> 吗？</html>", "提示", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    EmpUtil.deleteTable(id);
                    updateTable();
                }
            }
        });
        insertButton.addActionListener(e -> {
            try {
                new EmpInsertDialog("添加新员工", true).setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            updateTable();
        });
        return buttonPanel;
    }

    private static void updateTable() {
        MyTableModel tableModel = new MyTableModel();
        EmpUtil.getTableData(tableModel);
        table.setModel(tableModel);
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

    private static int retrieveEmpID(String searchTarget) {
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            String valueAt =table.getModel().getValueAt(i, 0).toString();
            if (searchTarget.equals(valueAt)) {
                return i;
            }
        }
        return -1;
    }


}

