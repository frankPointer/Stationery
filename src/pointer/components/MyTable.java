package pointer.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MyTable extends JTable {
    public MyTable(MyTableModel tableModel) {
        super(tableModel);
        setRowHeight(20); // 设置行高度
        setFont(new Font("宋体", Font.PLAIN, 15));
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 设置只能单选
        setAutoCreateRowSorter(false);
        setBackground(new Color(236, 232, 219)); // 设置背景色

        // 创建格式渲染器来使得文字居中
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        // 设置表格内容居中
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, headerRenderer);

        getTableHeader().setPreferredSize(new Dimension(getTableHeader().getWidth(), 22)); // 修改表头高度
        getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 15));
        getTableHeader().setReorderingAllowed(false); // 禁止列拖拽调整


    }

}
