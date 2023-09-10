package pointer.components;

import pointer.constants.FrameConstants;
import pointer.util.EmpUtil;
import pointer.util.ScreenUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class EmpInsertDialog extends JDialog {
    public EmpInsertDialog(String title, boolean modal) throws IOException {
        setTitle(title);
        setModal(modal);
        setResizable(false);

        addComponents();
    }

    private void addComponents() throws IOException {
        // 添加组件
        setIconImage(new ImageIcon("images/icon.png").getImage());
        setBounds((ScreenUtil.getScreenWidth() - FrameConstants.REGISTER_SIZE[0]) / 2, (ScreenUtil.getScreenHeight() - FrameConstants.REGISTER_SIZE[1]) / 2, FrameConstants.REGISTER_SIZE[0], FrameConstants.REGISTER_SIZE[1]);
        // 背景面板
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/registerBG.jpg")));


        Box insertBox = Box.createVerticalBox(); // 登录界面总的Box

        // 员工名字
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("姓    名：");
        JTextField uField = new JTextField(15);
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(uField);
        // 密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("密    码：");
        JPasswordField pField = new JPasswordField(15);

        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(pField);

        // 手机号码
        Box tBox = Box.createHorizontalBox();
        JLabel tLabel = new JLabel("手机号：");
        JTextField tField = new JTextField(15);

        tBox.add(tLabel);
        tBox.add(Box.createHorizontalStrut(15));
        tBox.add(tField);

        // 性别
        Box gBox = Box.createHorizontalBox();
        JLabel gLabel = new JLabel("性    别：");
        JRadioButton maleBtn = new JRadioButton("男", true);
        JRadioButton femaleBtm = new JRadioButton("女", false);
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
        JButton insertBtn = new JButton("添加");
        JButton cancelBtn = new JButton("取消");
        bBox.add(insertBtn);
        bBox.add(Box.createHorizontalStrut(120));
        bBox.add(cancelBtn);

        // 组装注册界面
        insertBox.add(Box.createVerticalStrut(40));
        insertBox.add(uBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(pBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(gBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(tBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(bBox);

        // 面板添加到界面中
        bgPanel.add(insertBox);
        this.add(bgPanel);

        // 添加按钮监听器
        cancelBtn.addActionListener(e ->
                this.dispose()
        );

        insertBtn.addActionListener(e -> {
            String name = uField.getText().trim();
            String password = new String(pField.getPassword());
            String gender = gGroup.isSelected(maleBtn.getModel()) ? "男" : "女";
            String phone = tField.getText().trim();

            if (name.length() == 0 || password.length() < 6 || phone.length() != 11) {
                JOptionPane.showMessageDialog(null, "名字不能为空，密码不少于6位，手机号码为11位", "警告", JOptionPane.ERROR_MESSAGE);
            } else {
                int empID = EmpUtil.insertTable(name, password, gender, phone);
                JOptionPane.showMessageDialog(null, "添加新员工成功\n员工帐号为: " + empID + "\n员工密码为: " + password, "提示", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }

        });
    }
}
