package pointer.components;

import pointer.constants.FrameConstants;
import pointer.util.ScreenUtil;
import pointer.util.SupplierUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;


public class SupplierUpdateDialog extends JDialog {
    private final Integer id;
    private final String name;
    private final String person;
    private final String phone;
    private final String address;

    public SupplierUpdateDialog(String title, boolean modal, Integer id, String name, String person, String phone, String address) throws IOException {
        // 起始构造
        setTitle(title);
        setModal(modal);
        setResizable(false);
        this.id = id;
        this.name = name;
        this.address = address;
        this.person = person;
        this.phone = phone;


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

        // 员工编号
        Box idBox = Box.createHorizontalBox();
        JLabel idLabel = new JLabel("编    号：");
        JTextField idField = new JTextField(id.toString(), 15);
        idField.setEditable(false);
        idBox.add(idLabel);
        idBox.add(Box.createHorizontalStrut(15));
        idBox.add(idField);

        // 员工名字
        Box uBox = Box.createHorizontalBox();
        JLabel nLabel = new JLabel("名    字：");
        JTextField uField = new JTextField(name, 15);
        uBox.add(nLabel);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(uField);
        // 密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("联系人：");
        JTextField pField = new JTextField(person, 15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(pField);

        // 手机号码
        Box tBox = Box.createHorizontalBox();
        JLabel tLabel = new JLabel("手机号：");
        JTextField tField = new JTextField(phone, 15);

        tBox.add(tLabel);
        tBox.add(Box.createHorizontalStrut(15));
        tBox.add(tField);

        // 地址
        Box aBox = Box.createHorizontalBox();
        JLabel aLabel = new JLabel("地    址：");
        JTextField aField = new JTextField(address, 15);
        aBox.add(aLabel);
        aBox.add(Box.createHorizontalStrut(15));
        aBox.add(aField);

        // 组装按钮
        Box bBox = Box.createHorizontalBox();
        JButton updateBtn = new JButton("修改");
        JButton cancelBtn = new JButton("取消");
        bBox.add(updateBtn);
        bBox.add(Box.createHorizontalStrut(120));
        bBox.add(cancelBtn);

        // 组装注册界面
        registerBox.add(Box.createVerticalStrut(40));
        registerBox.add(idBox);
        registerBox.add(Box.createVerticalStrut(25));
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

        updateBtn.addActionListener(e -> {
            Vector<String> myList = new Vector<>();
            myList.add(uField.getText().trim());
            myList.add(pField.getText().trim());
            myList.add(tField.getText().trim());
            myList.add(aField.getText().trim());

            SupplierUtil supplierUtil = new SupplierUtil("supplier");
            supplierUtil.updateTable(id,myList);
            this.dispose();
        });

        cancelBtn.addActionListener(e -> this.dispose());
    }
}
