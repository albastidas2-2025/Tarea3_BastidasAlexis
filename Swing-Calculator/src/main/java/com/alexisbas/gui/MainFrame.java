package com.alexisbas.gui;

import javax.swing.*;
import java.awt.*;

import com.alexisbas.logic.ICalculable;
import com.alexisbas.logic.GradeManager;

public class MainFrame extends JFrame {

    private JTextField txtName;
    private JTextField txtGrade1;
    private JTextField txtGrade2;
    private JTextField txtGrade3;
    private JLabel lblResult;

    private ICalculable gradeManager;
    private JButton calculateButton;
    private JButton clearButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public MainFrame() {
        initComponents();
        gradeManager = new GradeManager();
        initComponents();


    }

    private void calculate() {
        try {
            String name = txtName.getText().trim();

            // Validar nombre
            if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(this,
                        "Enter something valid",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            double g1 = Double.parseDouble(txtGrade1.getText());
            double g2 = Double.parseDouble(txtGrade2.getText());
            double g3 = Double.parseDouble(txtGrade3.getText());

            // Validar rango de notas (0 - 20)
            if (g1 < 0 || g1 > 20 || g2 < 0 || g2 > 20 || g3 < 0 || g3 > 20) {
                JOptionPane.showMessageDialog(this,
                        "Grades must be between 0 and 20",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            double average = gradeManager.calculateAverage(g1, g2, g3);
            String status = gradeManager.determineStatus(average);

            lblResult.setText(
                    String.format("%s - Promedio: %.2f (%s)", name, average, status)
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric grades",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private void clearFields() {
        txtName.setText("");
        txtGrade1.setText("");
        txtGrade2.setText("");
        txtGrade3.setText("");
        lblResult.setText("---");
    }

    private void initComponents() {

        setTitle("Academic Grade Average Calculator");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        panel.add(new JLabel("Student Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Grade 1:"));
        txtGrade1 = new JTextField();
        panel.add(txtGrade1);

        panel.add(new JLabel("Grade 2:"));
        txtGrade2 = new JTextField();
        panel.add(txtGrade2);

        panel.add(new JLabel("Grade 3:"));
        txtGrade3 = new JTextField();
        panel.add(txtGrade3);

        panel.add(new JLabel("Result:"));
        lblResult = new JLabel("---");
        panel.add(lblResult);

        JButton btnCalculate = new JButton("Calculate");
        JButton btnClear = new JButton("Clear");

        panel.add(btnCalculate);
        panel.add(btnClear);
        btnCalculate.addActionListener(e -> calculate());
        btnClear.addActionListener(e -> clearFields());

        add(panel);
    }
}