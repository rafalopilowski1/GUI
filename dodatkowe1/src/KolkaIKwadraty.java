import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


enum FigureChoice {
    Circle,
    Rectangle
}

interface Drawable {
    void paint(Graphics g);
}

public class KolkaIKwadraty {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame jframe = new JFrame();
            CirclesSquaresView circlesSquaresView = new CirclesSquaresView();
            jframe.setContentPane(circlesSquaresView);
            jframe.setSize(640, 480);
            jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jframe.setTitle("Circles & Squares");
            jframe.setVisible(true);
        });
    }
}

class CirclesSquaresView extends JPanel {
    List<Drawable> drawableList;
    FigureChoice figureChoice;
    Color color;

    CirclesSquaresView() {
        drawableList = new ArrayList<>();
        figureChoice = FigureChoice.Circle;
        color = Color.RED;
        handleMousePress();
        handleKeyboardPress();
        setBackground(Color.BLUE);
        setVisible(true);
    }

    private void handleKeyboardPress() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_R -> color = Color.RED;
                    case KeyEvent.VK_G -> color = Color.GREEN;
                    case KeyEvent.VK_O -> color = Color.ORANGE;
                    case KeyEvent.VK_SPACE ->
                            figureChoice = figureChoice == FigureChoice.Rectangle ? FigureChoice.Circle : FigureChoice.Rectangle;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void handleMousePress() {
        addMouseListener(new MouseInputListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int size = (int) (Math.random() * 30 + 20);
                    switch (figureChoice) {
                        case Circle -> {
                            if (e.isShiftDown()) {
                                drawableList.add(new FilledCircle(e.getX(), e.getY(), size, size, color));
                            } else {
                                drawableList.add(new OutlineCircle(e.getX(), e.getY(), size, size, color));
                            }
                        }
                        case Rectangle -> {
                            if (e.isShiftDown()) {
                                drawableList.add(new FilledRectangle(e.getX(), e.getY(), size, size, color));
                            } else {
                                drawableList.add(new OutlineRectangle(e.getX(), e.getY(), size, size, color));
                            }
                        }
                    }
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        requestFocusInWindow();
        for (Drawable drawable :
                drawableList) {
            drawable.paint(g);
        }
    }
}

abstract class Figure implements Drawable {
    int x, y, width, height;
    Color color;

    public Figure(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
    }
}

class OutlineCircle extends Figure {
    public OutlineCircle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(x, y, width, height);
    }
}

class FilledCircle extends Figure {
    public FilledCircle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, width, height);
    }
}

class OutlineRectangle extends Figure {
    public OutlineRectangle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(x, y, width, height);
    }
}

class FilledRectangle extends Figure {
    public FilledRectangle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(x, y, width, height);
    }
}