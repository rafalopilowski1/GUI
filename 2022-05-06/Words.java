import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Words {
    static String buffer = "";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame jframe = new JFrame();
            jframe.setLayout(new BorderLayout());

            JTextArea jTextArea = new JTextArea();
            jTextArea.setLineWrap(true);
            jTextArea.setBackground(Color.WHITE);

            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setWheelScrollingEnabled(true);
            jScrollPane.add(jTextArea);
            jScrollPane.setViewportView(jTextArea);

            jframe.add(jScrollPane, BorderLayout.CENTER);

            JPanel jpanel = new JPanel();
            jpanel.setLayout(new FlowLayout());

            JButton back_button = new JButton();
            JButton show_words_button = new JButton();

            back_button.setText("Back");
            show_words_button.setText("Show words");

            show_words_button.addActionListener(e -> {
                buffer = jTextArea.getText();
                Pattern regex = Pattern.compile("[^\\d\\W]+");
                String words = regex
                        .matcher(buffer)
                        .results()
                        .map(matchResult -> matchResult.group().toLowerCase() + '\n')
                        .distinct()
                        .reduce("", String::concat);
                jTextArea.setText(words);
            });

            back_button.addActionListener(e -> {
                if (!buffer.equals(""))
                    jTextArea.setText(buffer);
            });

            jpanel.add(back_button);
            jpanel.add(show_words_button);
            jframe.add(jpanel, BorderLayout.PAGE_END);

            jframe.setTitle("WORDS");
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setSize(640, 480);
            jframe.setVisible(true);
        });
    }
}
