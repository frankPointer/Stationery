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

public class CategoryUpdateDialog extends JDialog {
    private final Integer id;
    private final String name;
    private final String description;

    public CategoryUpdateDialog(String title, boolean modal, Integer id, String name, String description) throws IOException {
        setTitle(title);
        setModal(modal);
        setResizable(false);

        this.id = id;
        this.name = name;
        this.description = description;

        addComponents();
    }

    private void addComponents() throws IOException {
        setBounds((ScreenUtil.getScreenWidth() - FrameConstants.REGISTER_SIZE[0]) / 2, (ScreenUtil.getScreenHeight() - FrameConstants.REGISTER_SIZE[1]) / 2, FrameConstants.REGISTER_SIZE[0], FrameConstants.REGISTER_SIZE[1]);

        // 背景面板
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/registerBG.jpg")));

        Box updateBox = Box.createVerticalBox();

        Box idBox = Box.createHorizontalBox();
        JLabel idLabel = new JLabel("编号");
        JTextField idField = new JTextField(id.toString(), 15);
        idField.setEditable(false);
        idBox.add(idLabel);
        idBox.add(Box.createHorizontalStrut(15));
        idBox.add(idField);

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("名字");
        JTextField nameField = new JTextField(name, 15);
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(15));
        nameBox.add(nameField);

        Box dBox = Box.createVerticalBox();
        JLabel dLabel = new JLabel("描述");

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setText(description); // 设置初始文本
        textArea.setPreferredSize(new Dimension(200, 100)); // 设置首选大小
        // 将文本区域添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(textArea);
        dBox.add(dLabel);
        dBox.add(Box.createVerticalStrut(15));
        dBox.add(scrollPane);

        // 组装按钮
        Box bBox = Box.createHorizontalBox();
        JButton updateBtn = new JButton("修改");
        JButton cancelBtn = new JButton("取消");
        bBox.add(updateBtn);
        bBox.add(Box.createHorizontalStrut(120));
        bBox.add(cancelBtn);

        updateBox.add(Box.createVerticalStrut(40));
        updateBox.add(idBox);
        updateBox.add(Box.createVerticalStrut(25));
        updateBox.add(nameBox);
        updateBox.add(Box.createVerticalStrut(25));
        updateBox.add(dBox);
        updateBox.add(Box.createVerticalStrut(25));
        updateBox.add(bBox);

        bgPanel.add(updateBox);
        this.add(bgPanel);

        cancelBtn.addActionListener(e -> this.dispose());

        updateBtn.addActionListener(e -> {
            Vector<String> myList = new Vector<>();
            myList.add(nameField.getText().trim());
            myList.add(textArea.getText());

            CategoryUtil categoryUtil = new CategoryUtil("category");
            categoryUtil.updateTable(id, myList);
            this.dispose();
        });
    }
}
