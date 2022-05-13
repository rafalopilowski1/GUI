import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;

public class Cities {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame jframe = new JFrame();

            jframe.setLayout(new FlowLayout());

            JButton city_button = new JButton();
            JButton change_title_button = new JButton();
            JTextField city_text_field = new JTextField();

            city_button.setText("Waiting for a city...");
            change_title_button.setText("Change title...");
            city_text_field.setPreferredSize(new Dimension(300, 32));

            city_button.addActionListener(_l -> city_text_field.setText(city_button.getText()));
            change_title_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jframe.setTitle(city_text_field.getText());
                }
            });
            city_text_field.addActionListener(_e -> city_button.setText(city_text_field.getText()));

            jframe.add(city_button);
            jframe.add(change_title_button);
            jframe.add(city_text_field);

            jframe.setSize(640, 480);
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setVisible(true);
        });
    }
}
