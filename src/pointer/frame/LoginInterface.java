package pointer.frame;

import pointer.componments.BackGroundPanel;
import pointer.constants.FrameConstants;
import pointer.util.DBUtil;
import pointer.util.LoginUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginInterface extends JFrame {
    public LoginInterface() throws IOException {
        super(FrameConstants.APP_NAME);
        setIconImage(new ImageIcon("images/icon.png").getImage());
        setSize(FrameConstants.LOGIN_SIZE[0], FrameConstants.LOGIN_SIZE[1]);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addComponents();
    }

    private void addComponents() throws IOException {
        // 设置JFrame的 panel
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/longinBackground.png")));
        bgPanel.setBounds(0, 0, FrameConstants.LOGIN_SIZE[0], FrameConstants.LOGIN_SIZE[1]);

        // 添加用户、密码文本框
        Box loginBox = Box.createVerticalBox();
        // 用户
        Box userBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("帐   号：");
        uLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        JTextField uField = new JTextField(15);
        userBox.add(uLabel); // 添加标签到Box里面
        userBox.add(Box.createHorizontalStrut(20)); // 为两个组件添加间隔
        userBox.add(uField);

        // 密码
        Box passwordBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密   码：");
        pLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        JPasswordField pField = new JPasswordField(15); // JPasswordField：专门为密码设计的textField
        passwordBox.add(pLabel);
        passwordBox.add(Box.createHorizontalStrut(20));
        passwordBox.add(pField);

        // 添加登录按钮
        Box ButtonBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("登录");
        JButton regisBtn = new JButton("注册");
        JButton JDBCBtn = new JButton("测试数据库连接");
        ButtonBox.add(JDBCBtn);
        ButtonBox.add(Box.createHorizontalStrut(10));
        ButtonBox.add(loginBtn);
        ButtonBox.add(Box.createHorizontalStrut(10));
        ButtonBox.add(regisBtn);

        // 整合所有按钮组成登录
        loginBox.add(Box.createVerticalStrut(60));
        loginBox.add(userBox); // user
        loginBox.add(Box.createVerticalStrut(20));
        loginBox.add(passwordBox); // password
        loginBox.add(Box.createVerticalStrut(40));
        loginBox.add(ButtonBox); // buttons
        bgPanel.add(loginBox);
        this.add(bgPanel);

        // 添加监听
        JDBCBtn.addActionListener(e -> { // 测试数据库连接监听
            Connection connection = DBUtil.getConnection();
            try {
                if (!connection.isClosed()) {
                    JOptionPane.showMessageDialog(null, "数据库连接成功", "数据库连接结果", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "数据库失败", "数据库连接结果", JOptionPane.WARNING_MESSAGE);

                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        loginBtn.addActionListener(e -> { // 登录监听
            String text = uField.getText().trim();
            String password = new String(pField.getPassword());
            if (text.length() == 0 || password.length() == 0) {
                JOptionPane.showMessageDialog(null,"帐号、密码不能为空","错误",JOptionPane.ERROR_MESSAGE);
            }
            else {
                Integer id = Integer.parseInt(text);
                if (LoginUtil.loginVerify(id,password)) {
                    this.dispose();

                    // 创建管理员界面
                    try {
                        new ManagerInterface().setVisible(true);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "帐号或密码有误！", "警告", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        regisBtn.addActionListener(e -> { // 注册监听
            this.dispose();

            // 创建登录
            try {
                new RegisterInterface().setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }


}
