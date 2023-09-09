package pointer.components;

import pointer.constants.FrameConstants;
import pointer.util.EmpUtil;
import pointer.util.ScreenUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class EmpUpdateDialog extends JDialog {
    private final Integer id;
    private final String name;
    private final String gender;
    private final String password;
    private final String phone;

    public EmpUpdateDialog(String title, boolean modal, Integer id, String name, String gender, String password, String phone) throws IOException {
        // 起始构造
        setTitle(title);
        setModal(modal);
        setResizable(false);
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.password = password;
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
        JLabel uLabel = new JLabel("姓    名：");
        JTextField uField = new JTextField(name, 15);
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(uField);
        // 密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密    码：");
        JTextField pField = new JTextField(password, 15);

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

        // 性别
        Box gBox = Box.createHorizontalBox();
        JLabel gLabel = new JLabel("性    别：");
        JRadioButton maleBtn = new JRadioButton("男");
        JRadioButton femaleBtm = new JRadioButton("女");

        if (maleBtn.getText().equals(gender)) {
            maleBtn.setSelected(true);
        } else femaleBtm.setSelected(true);

        ButtonGroup gGroup = new ButtonGroup();
        gGroup.add(maleBtn);
        gGroup.add(femaleBtm);
        gBox.add(gLabel);
        gBox.add(Box.createHorizontalStrut(20));
        gBox.add(maleBtn);
        gBox.add(femaleBtm);
        gBox.add(Box.createHorizontalStrut(125));

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
        registerBox.add(gBox);
        registerBox.add(Box.createVerticalStrut(25));
        registerBox.add(tBox);
        registerBox.add(Box.createVerticalStrut(25));
        registerBox.add(bBox);

        // 面板添加到界面中
        bgPanel.add(registerBox);
        this.add(bgPanel);

        updateBtn.addActionListener(e -> {
            String name = uField.getText().trim();
            String password = pField.getText().trim();
            String gender = gGroup.isSelected(maleBtn.getModel()) ? "男" : "女";
            String phone = tField.getText().trim();

            // TODO 更新表格
            EmpUtil.updateTable(this.id,name,password,gender,phone);
        });
    }

}
