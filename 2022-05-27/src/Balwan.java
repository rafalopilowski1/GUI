import javax.swing.*;
import java.awt.*;

public class Balwan {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            SnowmanView view = new SnowmanView();
            frame.add(view, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(640, 480);
            frame.setVisible(true);
        });
    }
}

class SnowmanView extends JPanel {

    SnowmanView() {
        setBackground(Color.BLUE);
    }

    public void draw(int x, int y, int r, Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(x, y, r, r);
        g.setColor(Color.WHITE);
        g.fillOval(x, y, r, r);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int w = getWidth();
        int h = getHeight();

        int srednica1 = h / 6;
        int srednica2 = h / 3;
        int srednica3 = h / 2;

        draw(w / 2 - srednica1 / 2, 0, srednica1, g);
        draw(w / 2 - srednica2 / 2, srednica1, srednica2, g);
        draw(w / 2 - srednica3 / 2, srednica1 + srednica2, srednica3, g);
    }

}