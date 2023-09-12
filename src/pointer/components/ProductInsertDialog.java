package pointer.components;

import pointer.constants.FrameConstants;
import pointer.util.ProductUtil;
import pointer.util.ScreenUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Vector;

public class ProductInsertDialog extends JDialog {
    private static final ProductUtil PRODUCT_UTIL = new ProductUtil("product");

    public ProductInsertDialog(String title, boolean modal) throws IOException {
        setTitle(title);
        setModal(modal);
        setResizable(false);

        addComponents();
    }

    private void addComponents() throws IOException {
// 添加组件
        setIconImage(new ImageIcon("images/icon.png").getImage());
        setBounds((ScreenUtil.getScreenWidth() - FrameConstants.INSERT_PRODUCT_SIZE[0]) / 2, (ScreenUtil.getScreenHeight() - FrameConstants.INSERT_PRODUCT_SIZE[1]) / 2, FrameConstants.INSERT_PRODUCT_SIZE[0], FrameConstants.INSERT_PRODUCT_SIZE[1]);

        // 背景面板
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/productBG.jpg")));

        Box insertBox = Box.createVerticalBox();

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("名       称：");
        JTextField nameField = new JTextField(18);
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(15));
        nameBox.add(nameField);


        // 选择类别
        JLabel cLabel = new JLabel("商品分类：");
        Map<Integer, String> category = PRODUCT_UTIL.getCategory();
        Vector<Integer> vector = new Vector<>(category.keySet());
        JComboBox<Integer> catID = new JComboBox<>(vector);

        JTextField catName = new JTextField("笔", 6);
        catName.setEditable(false);
        catID.addActionListener(e -> catName.setText(category.get((Integer) catID.getSelectedItem())));
        Box cBox = Box.createHorizontalBox();
        cBox.add(cLabel);
        cBox.add(Box.createHorizontalStrut(10));
        cBox.add(catID);
        cBox.add(Box.createHorizontalStrut(15));
        cBox.add(catName);


        // 选择供货商人
        JLabel sLabel = new JLabel("商品来源：");
        Map<Integer, String> supplier = PRODUCT_UTIL.getSupplier();
        Vector<Integer> sVector = new Vector<>(supplier.keySet());
        JComboBox<Integer> supplierID = new JComboBox<>(sVector);

        JTextField supplierName = new JTextField("供货商M", 6);
        supplierName.setEditable(false);
        supplierID.addActionListener(e -> supplierName.setText(supplier.get((Integer) supplierID.getSelectedItem())));
        Box sBox = Box.createHorizontalBox();
        sBox.add(sLabel);
        sBox.add(Box.createHorizontalStrut(10));
        sBox.add(supplierID);
        sBox.add(Box.createHorizontalStrut(15));
        sBox.add(supplierName);

        // 价格
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("价    格：");
        JTextField pField = new JTextField(15);
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(pField);

        // 数量
        Box countBox = Box.createHorizontalBox();
        JLabel countLabel = new JLabel("数    量：");
        JTextField countField = new JTextField(15);
        countBox.add(countLabel);
        countBox.add(Box.createHorizontalStrut(15));
        countBox.add(countField);

        // 描述
        Box dBox = Box.createVerticalBox();
        JLabel dLabel = new JLabel("描述");

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setPreferredSize(new Dimension(200, 80)); // 设置首选大小
        // 将文本区域添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(textArea);
        dBox.add(dLabel);
        dBox.add(Box.createVerticalStrut(15));
        dBox.add(scrollPane);

        // 按钮
        Box bBox = Box.createHorizontalBox();
        JButton insertButton = new JButton("添加");
        JButton cancelBtn = new JButton("取消");
        bBox.add(insertButton);
        bBox.add(Box.createHorizontalStrut(120));
        bBox.add(cancelBtn);

        insertBox.add(Box.createVerticalStrut(30));
        insertBox.add(nameBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(cBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(sBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(pBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(countBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(dBox);
        insertBox.add(Box.createVerticalStrut(25));
        insertBox.add(bBox);


        cancelBtn.addActionListener(e -> this.dispose());

        insertButton.addActionListener(e -> {
            BigDecimal price = new BigDecimal(pField.getText().trim());
            PRODUCT_UTIL.insertTable(nameField.getText().trim(), (Integer) catID.getSelectedItem(), (Integer) supplierID.getSelectedItem(), price, Integer.parseInt(countField.getText().trim()), textArea.getText());

            this.dispose();
        });
        bgPanel.add(insertBox);
        this.add(bgPanel);
    }
}
