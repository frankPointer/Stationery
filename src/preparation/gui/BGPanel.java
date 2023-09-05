package preparation.gui;

import javax.swing.*;
import java.awt.*;

// 定义一个 BackGroundPanel 的子类
class MyPanel extends BackGroundPanel {
    // 构造方法，传入一个 Image 对象
    public MyPanel(Image img) {
        super(img);
    }

    // 重写 paintComponent 方法
    public void paintComponent(Graphics g) {
        // 调用父类的方法
        super.paintComponent(g);
        // 在面板上绘制一些文字
        g.setColor(Color.WHITE);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString("这是一个带有背景图片的面板", 50, 50);
    }
}

// 定义一个 BackGroundPanel 类，继承自 JPanel 类
class BackGroundPanel extends JPanel {
    // 定义一个 Image 类型的成员变量，用于存储背景图片
    private Image img;

    // 构造方法，传入一个 Image 对象
    public BackGroundPanel(Image img) {
        this.img = img;
        // 设置面板的大小为图片的大小
        int width = img.getWidth(this);
        int height = img.getHeight(this);
        this.setSize(width, height);
    }

    // 重写 paintComponent 方法
    public void paintComponent(Graphics g) {
        // 调用父类的方法
        super.paintComponent(g);
        // 绘制背景图片
        g.drawImage(img, 0, 0, this);
    }
}

// 定义一个测试类
public class BGPanel {
    public static void main(String[] args) {
        // 创建一个 JFrame 对象
        JFrame frame = new JFrame("测试背景面板");
        // 设置窗口的关闭方式为退出程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口的大小为 400*300 像素
        frame.setSize(400, 300);
        // 设置窗口的位置为屏幕中央
        frame.setLocationRelativeTo(null);
        // 从文件中加载一张图片作为背景图片
        Image img = Toolkit.getDefaultToolkit().getImage("background.jpg");
        // 创建一个 MyPanel 对象，传入背景图片
        MyPanel panel = new MyPanel(img);
        // 将面板添加到窗口中
        frame.add(panel);
        // 设置窗口可见
        frame.setVisible(true);
    }
}


