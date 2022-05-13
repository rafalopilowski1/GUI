package src;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

public class ResponsivePanel {

    public ResponsivePanel() {
    }

    public JPanel layout1() {
        JPanel layout1 = new JPanel();
        layout1.setLayout(new GridLayout(2, 1));

        JPanel text_fields_panel = new JPanel();
        text_fields_panel.setLayout(new GridLayout(3, 1));
        for (int i = 0; i < 3; i++) {
            JTextField text = new JTextField();
            text.setText(String.format("JTextField %s", i + 1));
            text_fields_panel.add(text);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JButton butt = new JButton();
            butt.setText(String.format("JButton %s", i + 1));
            buttonPanel.add(butt);
        }

        JPanel text_button_panel = new JPanel();
        text_button_panel.setLayout(new GridLayout(1, 2));
        JTextArea text_area = new JTextArea();
        text_area.setText("JTextArea 1");
        text_button_panel.add(text_area);
        text_button_panel.add(buttonPanel);
        layout1.add(text_fields_panel);
        layout1.add(text_button_panel);
        return layout1;
    }

    public JPanel layout2() {
        JPanel layout1 = new JPanel();
        layout1.setLayout(new GridLayout(2, 1));

        JPanel text_areas_buttons = new JPanel();
        text_areas_buttons.setLayout(new BorderLayout());

        JTextArea textArea1 = new JTextArea();
        textArea1.setText("JTextArea 1");
        JTextArea textArea2 = new JTextArea();
        textArea2.setText("JTextArea 2");

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 4));
        for (int i = 0; i < 12; i++) {
            JButton butt = new JButton();
            butt.setText(String.valueOf(i + 1));
            buttons.add(butt);
        }

        JTextField text_field = new JTextField();
        text_field.setText("JTextField 4");

        text_areas_buttons.add(textArea1, BorderLayout.EAST);
        text_areas_buttons.add(buttons, BorderLayout.CENTER);
        text_areas_buttons.add(textArea2, BorderLayout.WEST);
        layout1.add(text_areas_buttons);
        layout1.add(text_field);
        return layout1;
    }

    public JPanel layout3() {
        JPanel layout1 = new JPanel();
        layout1.setLayout(new GridLayout(2, 1));
        JPanel text_fields_panel = new JPanel();
        text_fields_panel.setLayout(new GridLayout(3, 1, 1, 1));
        for (int i = 0; i < 3; i++) {
            JTextField text = new JTextField();
            text.setText(String.format("JTextField %s", i + 1));
            text_fields_panel.add(text);
        }

        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < 4; i++) {
            JButton butt = new JButton();
            butt.setText(String.format("JButton %s", i + 1));
            buttonPanel1.add(butt);
        }

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new GridLayout(2, 2));
        for (int i = 4; i < 8; i++) {
            JButton butt = new JButton();
            butt.setText(String.format("JButton %s", i + 1));
            buttonPanel2.add(butt);
        }

        JPanel text_button_panel = new JPanel();
        text_button_panel.setLayout(new GridLayout(1, 3));

        text_button_panel.add(buttonPanel1);
        text_button_panel.add(text_fields_panel);
        text_button_panel.add(buttonPanel2);

        JTextArea text_area = new JTextArea();
        text_area.setText("JTextArea 1");

        layout1.add(text_area);
        layout1.add(text_button_panel);

        return layout1;
    }

    public JPanel layout4() {
        JPanel layout1 = new JPanel();
        layout1.setLayout(new GridLayout(3, 1));

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2, 5));
        for (int i = 0; i < 10; i++) {
            JButton butt = new JButton();
            butt.setText(String.valueOf(i + 1));
            buttons.add(butt);
        }

        JPanel fields_text = new JPanel();
        fields_text.setLayout(new GridLayout(3, 1));
        for (int i = 0; i < 3; i++) {
            JTextField text = new JTextField();
            text.setText(String.format("TextArea %s", i + 1));
            fields_text.add(text);
        }

        JPanel flow = new JPanel();
        flow.setLayout(new FlowLayout());
        for (int i = 10; i < 13; i++) {
            JButton butt = new JButton();
            butt.setText(String.valueOf(i + 1));
            flow.add(butt);
        }

        layout1.add(buttons);
        layout1.add(fields_text);
        layout1.add(flow);
        return layout1;
    }

    public JPanel layout5() {
        JPanel layout1 = new JPanel();
        layout1.setLayout(new GridLayout(3, 1));

        JPanel text_fields = new JPanel();
        text_fields.setLayout(new GridLayout(3, 1));
        for (int i = 0; i < 3; i++) {
            JTextField textField = new JTextField();
            textField.setText(String.format("TextField %s", i + 1));
            text_fields.add(textField);
        }

        JPanel text_areas = new JPanel();
        text_areas.setLayout(new GridLayout(1, 2));
        for (int i = 0; i < 2; i++) {
            JTextArea textArea = new JTextArea();
            textArea.setText(String.format("JTextArea %s", i + 1));
            text_areas.add(textArea);
        }

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton();
            button.setText(String.valueOf(i + 1));
            buttons.add(button);
        }

        layout1.add(text_fields);
        layout1.add(text_areas);
        layout1.add(buttons);

        return layout1;
    }

    public JPanel layout6() {
        JPanel layout1 = new JPanel();

        layout1.setLayout(new GridLayout(3, 1));

        JPanel buttons1 = new JPanel();
        buttons1.setLayout(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            JButton butt = new JButton();
            butt.setText(String.valueOf(i + 1));
            buttons1.add(butt);
        }

        JPanel secondRow = new JPanel();
        secondRow.setLayout(new GridLayout(1, 2));

        JTextArea area = new JTextArea();
        area.setText("JTextArea 1");
        secondRow.add(area);

        JPanel textFields = new JPanel();
        textFields.setLayout(new GridLayout(3, 1));
        for (int i = 0; i < 3; i++) {
            JTextField field = new JTextField();
            field.setText(String.format("JTextField %s", i + 1));
            textFields.add(field);
        }

        secondRow.add(textFields);

        JPanel buttons2 = new JPanel();
        buttons2.setLayout(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            JButton butt = new JButton();
            butt.setText(String.valueOf(i + 1));
            buttons2.add(butt);
        }

        layout1.add(buttons1);
        layout1.add(secondRow);
        layout1.add(buttons2);
        return layout1;
    }

    public static void main(String[] args) {

    }
}
