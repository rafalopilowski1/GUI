package src;

import javax.swing.*;

import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Responsive {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            ResponsivePanel jpanel = new ResponsivePanel();
            frame.setContentPane(jpanel.layout6());
            frame.setSize(frame.getPreferredSize());
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
