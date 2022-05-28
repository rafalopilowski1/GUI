import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class PisanieKomend {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new Lista());
            frame.setSize(640, 480);
            frame.setVisible(true);
        });
    }
}

class Lista extends JPanel {
    ArrayList<String> nazwy;

    Lista() {
        nazwy = new ArrayList<>();
        setLayout(new BorderLayout());
        JList<String> list = new JList<>();
        JScrollPane jScrollPane = new JScrollPane(list);
        add(jScrollPane, BorderLayout.CENTER);
        JTextField field = new JTextField();
        Font font = field.getFont();
        field.setFont(font.deriveFont(Font.ITALIC));
        field.setForeground(Color.BLUE);

        field.addActionListener((l) -> {
            String text = field.getText();
            if (text.equals("quit")) {
                System.exit(0);
            }
            switch (text.substring(0, 3)) {
                case "add" -> {
                    String name = text.substring(4);
                    if (nazwy.contains(name)) {

                    } else {
                        nazwy.add(name);
                    }
                }
                case "del" -> {
                    String name = text.substring(4);
                    if (nazwy.contains(name)) {
                        nazwy.remove(name);
                    }
                }
                default -> {
                }
            }
        });

        add(field, BorderLayout.PAGE_END);
    }
}
