package pointer.components;

import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {
    // 声明图片
    private Image backGroundIcon;

    public BackGroundPanel(Image backIcon) {
        this.backGroundIcon = backIcon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 将得到的图片绘制在panel上
        g.drawImage(backGroundIcon,0,0,this.getWidth(),this.getHeight(),null);
    }
}
