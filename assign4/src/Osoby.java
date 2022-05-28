import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

class Person {
    String name;
    int weight, height;
    Size size;

    public Person(String name, int weight, int height, Size size) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("%s (h=%s,w=%s,size=%s)", name, height, weight, size.toString());
    }
}

enum Size {
    XS, S, M, L, XL
}

// Renderer
class PersonRenderer extends JPanel implements ListCellRenderer<Person> {
    private final JLabel jLabel;

    PersonRenderer() {
        jLabel = new JLabel();
        add(jLabel);
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Person> list, Person person, int index, boolean isSelected, boolean cellHasFocus) {
        jLabel.setText(person.toString());
        switch (person.size) {
            case XS -> setBackground(Color.green);
            case XL -> setBackground(Color.red);
            default -> setBackground(Color.white);
        }
        return this;
    }
}

// View
class PersonsView extends JPanel {
    // Modal
    private final DefaultListModel<Person> personModel = new DefaultListModel<>();

    PersonsView() {
        setLayout(new BorderLayout());

        JList<Person> personJList = new JList<>();
        personJList.setModel(personModel);
        personJList.setCellRenderer(new PersonRenderer());

        JScrollPane personScrollPane = new JScrollPane(personJList);
        add(personScrollPane, BorderLayout.CENTER);

        JPanel adjustPanel = new JPanel();
        adjustPanel.setLayout(new GridLayout(3, 1, 5, 5));

        JSlider heightSlider = getjSlider("height", "Height [cm]", 100, 200);

        adjustPanel.add(heightSlider);

        JSlider weightSlider = getjSlider("weight", "Weight [kg]", 40, 120);

        adjustPanel.add(weightSlider);

        JPanel lowerThirdsPanel = new JPanel();
        lowerThirdsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name:");
        JTextField nameField = new JTextField(20);
        nameField.setName("name");

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel sizePanel = new JPanel();
        JLabel sizeLabel = new JLabel();
        sizeLabel.setText("Size:");
        JComboBox<Size> sizeJComboBox = new JComboBox<>(Size.values());
        sizeJComboBox.setName("size");

        sizePanel.add(sizeLabel);
        sizePanel.add(sizeJComboBox);

        JButton addPersonButton = new JButton();
        addPersonButton.addActionListener((l) -> {
            if (!nameField.getText().isEmpty())
                personModel.addElement(new Person(nameField.getText(), weightSlider.getValue(), heightSlider.getHeight(), (Size) sizeJComboBox.getSelectedItem()));
            else
                JOptionPane.showMessageDialog(this, "Name field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            nameField.setText("");
        });
        addPersonButton.setText("Add person");

        JButton exitButton = new JButton();
        exitButton.addActionListener((l) -> System.exit(0));
        exitButton.setText("Exit");

        lowerThirdsPanel.add(namePanel);
        lowerThirdsPanel.add(sizePanel);
        lowerThirdsPanel.add(addPersonButton);
        lowerThirdsPanel.add(exitButton);

        adjustPanel.add(lowerThirdsPanel);
        add(adjustPanel, BorderLayout.AFTER_LAST_LINE);
    }

    private JSlider getjSlider(String name, String title, int minimum, int maximum) {
        JSlider jSlider = new JSlider();

        jSlider.setName(name);
        jSlider.setBorder(BorderFactory.createTitledBorder(title));
        jSlider.setMinimum(minimum);
        jSlider.setMaximum(maximum);
        jSlider.setValue(minimum);
        jSlider.setMajorTickSpacing(10);
        jSlider.setMinorTickSpacing(1);
        jSlider.setPaintTicks(true);
        jSlider.setSnapToTicks(true);
        jSlider.setPaintLabels(true);
        return jSlider;
    }
}

public class Osoby {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame jframe = new JFrame();
            PersonsView personsView = new PersonsView();
            jframe.setContentPane(personsView);
            jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jframe.setTitle("PERSONS");
            jframe.setSize(640, 480);
            jframe.setVisible(true);
        });
    }
}