import pointer.componments.EmployeeInterface;

import javax.swing.*;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
//        new ManagerInterface().setVisible(true);
        JFrame frame = new JFrame("Table Fuzzy Search Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.getContentPane().add(new EmployeeInterface());
    }
}
