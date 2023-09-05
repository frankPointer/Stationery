package preparation.gui;

import javax.swing.*;

public class BoxTest {
    public static void main(String[] args) {
        // 创建一个 JFrame 对象
        JFrame frame = new JFrame("测试 Box 类");
        // 设置窗口的关闭方式为退出程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口的大小为 300*200 像素
        frame.setSize(300, 200);
        // 设置窗口的位置为屏幕中央
        frame.setLocationRelativeTo(null);
        // 创建一个垂直方向的 Box 容器
        Box vBox = Box.createVerticalBox();
        // 创建一个水平方向的 Box 容器
        Box hBox = Box.createHorizontalBox();
        // 创建四个按钮
        JButton button1 = new JButton("按钮1");
        JButton button2 = new JButton("按钮2");
        JButton button3 = new JButton("按钮3");
        JButton button4 = new JButton("按钮4");
        // 将四个按钮添加到水平方向的 Box 容器中，并添加一些间隔
        hBox.add(button1);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(button2);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(button3);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(button4);
        // 创建两个文本框
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        // 将两个文本框添加到垂直方向的 Box 容器中，并添加一些间隔
        vBox.add(textField1);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(textField2);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(hBox);
        // 将垂直方向的 Box 容器添加到窗口中
        frame.add(vBox);
        // 设置窗口可见
        frame.setVisible(true);
    }
}

