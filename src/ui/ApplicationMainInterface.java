package ui;

import pointer.constants.FrameConstants;
import pointer.ui.componments.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ApplicationMainInterface extends JFrame {
    public ApplicationMainInterface() throws IOException {
        super(FrameConstants.APP_NAME);
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
        JLabel uLabel = new JLabel("用户名：");
        uLabel.setFont(new Font("微软雅黑",Font.BOLD,16));
        JTextField uField = new JTextField(15);
        uField.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"),"none");
        userBox.add(uLabel); // 添加标签到Box里面
        userBox.add(Box.createHorizontalStrut(20)); // 为两个组件添加间隔
        userBox.add(uField);

        // 密码
        Box passwordBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密   码：");
        pLabel.setFont(new Font("微软雅黑",Font.BOLD,16));
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

        loginBox.add(Box.createVerticalStrut(60));
        loginBox.add(userBox);
        loginBox.add(Box.createVerticalStrut(20));
        loginBox.add(passwordBox);
        loginBox.add(Box.createVerticalStrut(40));
        loginBox.add(ButtonBox);
        bgPanel.add(loginBox);
        this.add(bgPanel);
    }


}
