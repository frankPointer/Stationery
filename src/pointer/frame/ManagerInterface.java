package pointer.frame;

import pointer.components.EmpInterface;
import pointer.components.SupplierInterface;
import pointer.constants.FrameConstants;
import pointer.util.ScreenUtil;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;

public class ManagerInterface extends JFrame {
    public ManagerInterface() throws IOException {
        super(FrameConstants.MANAGER_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds((ScreenUtil.getScreenWidth() - FrameConstants.MANAGER_SIZE[0]) / 2, (ScreenUtil.getScreenHeight() - FrameConstants.MANAGER_SIZE[1]) / 2, FrameConstants.MANAGER_SIZE[0], FrameConstants.MANAGER_SIZE[1]);
        setResizable(true);

        // 设置图标
        setIconImage(new ImageIcon("images/icon.png").getImage());
        addComponents();
    }

    private void addComponents()  {


        // 添加状态栏
        addMenubar();
        addSplitPane();

    }

    private void addMenubar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("文件");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem exitItem = new JMenuItem("退出");

        // 组装状态栏
        menu.add(reLoginItem);
        menu.add(exitItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        // 添加监听器
        reLoginItem.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "确定返回登录界面吗？", "提示", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                this.dispose();
                try {
                    new LoginInterface().setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitItem.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "确定退出？", "提示", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void addSplitPane()  {

        // 分割面板
        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 设置左右分割
        splitPane.setContinuousLayout(true); // 支持连续布局
        splitPane.setDividerLocation(200); // 初始显示位置
        splitPane.setDividerSize(7); // 设置分割条宽度

        // 设置左侧工作树
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
        DefaultMutableTreeNode empManage = new DefaultMutableTreeNode("员工管理");
        DefaultMutableTreeNode customerManage = new DefaultMutableTreeNode("客户管理");
        DefaultMutableTreeNode productManage = new DefaultMutableTreeNode("产品");
        DefaultMutableTreeNode categoryManage = new DefaultMutableTreeNode("产品分类");
        DefaultMutableTreeNode orderManage = new DefaultMutableTreeNode("订单");
        DefaultMutableTreeNode supplierManage = new DefaultMutableTreeNode("供货商");

        root.add(productManage);
        root.add(categoryManage);
        root.add(orderManage);
        root.add(supplierManage);
        root.add(customerManage);
        root.add(empManage);

        // 绑定根节点
        JTree tree = new JTree(root);
        splitPane.setLeftComponent(tree);

        // tree 添加监听
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node.equals(empManage)) {
                splitPane.setRightComponent(new EmpInterface());
                splitPane.setDividerLocation(200);
            } else if (node.equals(supplierManage)) {
                splitPane.setRightComponent(new SupplierInterface());
                splitPane.setDividerLocation(200);
            }
        });
        tree.setSelectionRow(1);
        this.add(splitPane);
    }
}
