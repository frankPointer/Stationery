package pointer.components;

import pointer.constants.FrameConstants;
import pointer.util.CategoryUtil;
import pointer.util.ScreenUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class CategoryInsertDialog extends JDialog {
    public CategoryInsertDialog(String title, boolean modal) throws IOException {
        setTitle(title);
        setModal(modal);
        setResizable(false);

        addComponents();
    }

    private void addComponents() throws IOException {
        // 添加组件
        setBounds((ScreenUtil.getScreenWidth() - FrameConstants.REGISTER_SIZE[0]) / 2, (ScreenUtil.getScreenHeight() - FrameConstants.REGISTER_SIZE[1]) / 2, FrameConstants.REGISTER_SIZE[0], FrameConstants.REGISTER_SIZE[1]);
        setIconImage(new ImageIcon("images/icon.png").getImage());
        setBounds((ScreenUtil.getScreenWidth() - FrameConstants.REGISTER_SIZE[0]) / 2, (ScreenUtil.getScreenHeight() - FrameConstants.REGISTER_SIZE[1]) / 2, FrameConstants.REGISTER_SIZE[0], FrameConstants.REGISTER_SIZE[1]);

        // 背景面板
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/registerBG.jpg")));

        Box insertBox = Box.createVerticalBox();


        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("名字");
        JTextField nameField = new JTextField(15);
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(15));
        nameBox.add(nameField);

        Box dBox = Box.createVerticalBox();
        JLabel dLabel = new JLabel("描述");

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setPreferredSize(new Dimension(200, 100)); // 设置首选大小
        // 将文本区域添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(textArea);
        dBox.add(dLabel);
        dBox.add(Box.createVerticalStrut(15));
        dBox.add(scrollPane);

        // 组装按钮
        Box bBox = Box.createHorizontalBox();
        JButton insertBtn = new JButton("添加");
        JButton cancelBtn = new JButton("取消");
        bBox.add(insertBtn);
        bBox.add(Box.createHorizontalStrut(120));
        bBox.add(cancelBtn);

        insertBox.add(Box.createVerticalStrut(40));
        insertBox.add(nameBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(dBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(bBox);

        bgPanel.add(insertBox);
        this.add(bgPanel);


        insertBtn.addActionListener(e -> {
            Vector<String> myList = new Vector<>();
            myList.add(nameField.getText().trim());
            myList.add(textArea.getText());

            CategoryUtil categoryUtil = new CategoryUtil("category");
            categoryUtil.insertTable(myList);
            JOptionPane.showMessageDialog(null, "添加新类别成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        });

        cancelBtn.addActionListener(e -> this.dispose());
    }
}
