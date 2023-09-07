package pointer.frame;

import pointer.componments.BackGroundPanel;
import pointer.constants.FrameConstants;
import pointer.util.ScreenUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ManagerInterface extends JFrame {
    public  ManagerInterface() throws IOException {
        super(FrameConstants.MANAGER_NAME);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds((ScreenUtil.getScreenWidth() - FrameConstants.MANAGER_SIZE[0]) / 2, (ScreenUtil.getScreenHeight() - FrameConstants.MANAGER_SIZE[1]) / 2, FrameConstants.MANAGER_SIZE[0], FrameConstants.MANAGER_SIZE[1]);
        setResizable(false);

        // 设置图标
        setIconImage(new ImageIcon("images/icon.png").getImage());
        addComponents();
    }

    private void addComponents() throws IOException {
        // 添加背景 panel
        BackGroundPanel backGroundPanel = new BackGroundPanel(ImageIO.read(new File("images/managerBG.png")));
        backGroundPanel.setBounds(0, 0, FrameConstants.MANAGER_SIZE[0], FrameConstants.MANAGER_SIZE[1]);
        this.add(backGroundPanel);

        // 添加
    }
}
