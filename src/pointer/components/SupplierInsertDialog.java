package pointer.components;

import pointer.constants.FrameConstants;
import pointer.util.ScreenUtil;
import pointer.util.SupplierUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class SupplierInsertDialog extends JDialog {
    public SupplierInsertDialog(String title, boolean modal) throws IOException {
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

        Box registerBox = Box.createVerticalBox(); // 登录界面总的Box


        // 员工名字
        Box uBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("名    字：");
        JTextField uField = new JTextField( 15);
        uBox.add(nLabel);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(uField);
        // 密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("联系人：");
        JTextField pField = new JTextField( 15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(pField);

        // 手机号码
        Box tBox = Box.createHorizontalBox();
        JLabel tLabel = new JLabel("手机号：");
        JTextField tField = new JTextField( 15);

        tBox.add(tLabel);
        tBox.add(Box.createHorizontalStrut(15));
        tBox.add(tField);

        // 地址
        Box aBox = Box.createHorizontalBox();
        JLabel aLabel = new JLabel("地    址：");
        JTextField aField = new JTextField(15);
        aBox.add(aLabel);
        aBox.add(Box.createHorizontalStrut(15));
        aBox.add(aField);

        // 组装按钮
        Box bBox = Box.createHorizontalBox();
        JButton insertButton = new JButton("修改");
        JButton cancelBtn = new JButton("取消");
        bBox.add(insertButton);
        bBox.add(Box.createHorizontalStrut(120));
        bBox.add(cancelBtn);

        // 组装注册界面
        registerBox.add(Box.createVerticalStrut(40));
        registerBox.add(uBox);
        registerBox.add(Box.createVerticalStrut(25));
        registerBox.add(pBox);
        registerBox.add(Box.createVerticalStrut(25));
        registerBox.add(tBox);
        registerBox.add(Box.createVerticalStrut(25));
        registerBox.add(aBox);
        registerBox.add(Box.createVerticalStrut(25));
        registerBox.add(bBox);


        // 面板添加到界面中
        bgPanel.add(registerBox);
        this.add(bgPanel);

        cancelBtn.addActionListener(e -> dispose());
        insertButton.addActionListener(e -> {
            String name = uField.getText().trim();
            String person = pField.getText().trim();
            String phone = tField.getText().trim();
            String address = aField.getText().trim();

            if (name.length() != 0 && person.length() != 0 && phone.length() == 11 && address.length() != 0) {
                Vector<String> myList = new Vector<>();
                myList.add(name);
                myList.add(person);
                myList.add(phone);
                myList.add(address);

                SupplierUtil supplierUtil = new SupplierUtil("supplier");
                supplierUtil.insertTable(myList);

                JOptionPane.showMessageDialog(null, "添加新供货商成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        });
    }
}
