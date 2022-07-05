import javax.swing.*;
import java.awt.*;

class Person {
    private final String name;
    private final int weight, height;
    private final Size size;

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

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
        switch (person.getSize()) {
            case XS -> setBackground(Color.green);
            case XL -> setBackground(Color.red);
            default -> setBackground(Color.white);
        }
        return this;
    }
}

// View
class PersonsView extends JFrame {
    // Modal
    private final JList<Person> personJList;
    private final JScrollPane personScrollPane;
    private final JPanel adjustPanel;
    private final JPanel lowerThirdsPanel;
    private final JPanel namePanel;
    private final JLabel nameLabel;
    private final JTextField nameField;
    private final JPanel sizePanel;
    private final JLabel sizeLabel;
    private final JComboBox<Size> sizeJComboBox;
    private final JButton addPersonButton;
    private final JButton exitButton;
    private final JSlider heightSlider;
    private final JSlider weightSlider;

    PersonsView() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PERSONS");
        setSize(640, 480);
        setVisible(true);

        personJList = new JList<>();
        adjustPanel = new JPanel();
        lowerThirdsPanel = new JPanel();
        namePanel = new JPanel();
        nameLabel = new JLabel();
        nameField = new JTextField(20);
        sizePanel = new JPanel();
        sizeLabel = new JLabel();
        sizeJComboBox = new JComboBox<>(Size.values());
        addPersonButton = new JButton();
        exitButton = new JButton();
        personScrollPane = new JScrollPane(personJList);
        heightSlider = getjSlider("height", "Height [cm]", 100, 200);
        weightSlider = getjSlider("weight", "Weight [kg]", 40, 120);

        nameLabel.setText("Name:");
        nameField.setName("name");
        sizeLabel.setText("Size:");
        sizeJComboBox.setName("size");
        addPersonButton.setText("Add person");
        exitButton.setText("Exit");

        adjustPanel.setLayout(new GridLayout(3, 1, 5, 5));
        lowerThirdsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        adjustPanel.add(heightSlider);
        adjustPanel.add(weightSlider);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        sizePanel.add(sizeLabel);
        sizePanel.add(sizeJComboBox);
        lowerThirdsPanel.add(namePanel);
        lowerThirdsPanel.add(sizePanel);
        lowerThirdsPanel.add(addPersonButton);
        lowerThirdsPanel.add(exitButton);
        adjustPanel.add(lowerThirdsPanel);

        add(personScrollPane, BorderLayout.CENTER);
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

    public JList<Person> getPersonJList() {
        return personJList;
    }


    public JTextField getNameField() {
        return nameField;
    }


    public JComboBox<Size> getSizeJComboBox() {
        return sizeJComboBox;
    }


    public JButton getAddPersonButton() {
        return addPersonButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JSlider getHeightSlider() {
        return heightSlider;
    }


    public JSlider getWeightSlider() {
        return weightSlider;
    }


}

class PersonControler {
    private final DefaultListModel<Person> personModel;
    private final PersonsView view;

    public PersonControler(DefaultListModel<Person> personModel, PersonsView view) {
        this.personModel = personModel;
        this.view = view;
        initView();
    }

    public void initView() {
        view.getPersonJList().setModel(personModel);
        view.getPersonJList().setCellRenderer(new PersonRenderer());
    }

    public void initController() {
        view.getAddPersonButton().addActionListener((l) -> addPerson());
        view.getExitButton().addActionListener((l) -> closeApp());
    }

    private void closeApp() {
        System.exit(0);
    }

    private void addPerson() {
        JTextField nameField = view.getNameField();
        if (!nameField.getText().isEmpty())
            personModel.addElement(new Person(view.getNameField().getText(), view.getWeightSlider().getValue(), view.getHeightSlider().getHeight(), (Size) view.getSizeJComboBox().getSelectedItem()));
        else
            JOptionPane.showMessageDialog(view, "Name field is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        nameField.setText("");
    }
}

public class Osoby {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DefaultListModel<Person> personModel = new DefaultListModel<>();
            PersonsView personsView = new PersonsView();
            PersonControler personControler = new PersonControler(personModel, personsView);
            personControler.initController();
        });
    }
}