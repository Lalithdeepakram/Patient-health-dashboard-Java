import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PatientHealthDashboard extends JFrame implements ActionListener {

    private JTextField nameField, heartField, tempField, oxygenField;
    private JTextArea outputArea;
    private JButton checkBtn, clearBtn;

    public PatientHealthDashboard() {
        setTitle("Patient Health Monitoring Dashboard");
        setSize(550, 450);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(235, 247, 255));

        // Call method to set up all components
        initComponents();

        // Make frame visible at the end
        setVisible(true);
    }

    private void initComponents() {
        JLabel title = new JLabel("Patient Health Monitoring Dashboard", JLabel.CENTER);
        title.setBounds(30, 20, 480, 30);
        title.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(80, 80, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 80, 200, 25);
        add(nameField);

        JLabel heartLabel = new JLabel("Heart Rate (bpm):");
        heartLabel.setBounds(80, 120, 150, 25);
        add(heartLabel);

        heartField = new JTextField();
        heartField.setBounds(200, 120, 200, 25);
        add(heartField);

        JLabel tempLabel = new JLabel("Temperature (°C):");
        tempLabel.setBounds(80, 160, 150, 25);
        add(tempLabel);

        tempField = new JTextField();
        tempField.setBounds(200, 160, 200, 25);
        add(tempField);

        JLabel oxyLabel = new JLabel("Oxygen Level (%):");
        oxyLabel.setBounds(80, 200, 150, 25);
        add(oxyLabel);

        oxygenField = new JTextField();
        oxygenField.setBounds(200, 200, 200, 25);
        add(oxygenField);

        checkBtn = new JButton("Check Health");
        checkBtn.setBounds(100, 250, 150, 30);
        add(checkBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(280, 250, 120, 30);
        add(clearBtn);

        outputArea = new JTextArea();
        outputArea.setBounds(80, 300, 360, 80);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(outputArea);

        // Attach listeners AFTER all components are created
        attachListeners();
    }

    private void attachListeners() {
        checkBtn.addActionListener(this);
        clearBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == checkBtn) {
            handleCheck();
        } else if (src == clearBtn) {
            clearFields();
        }
    }

    private void handleCheck() {
        String name = nameField.getText().trim();

        try {
            int heart = Integer.parseInt(heartField.getText().trim());
            double temp = Double.parseDouble(tempField.getText().trim());
            int oxygen = Integer.parseInt(oxygenField.getText().trim());

            StringBuilder result = new StringBuilder();
            result.append("Patient: ").append(name).append("\n");
            result.append("Heart Rate: ").append(heart).append(" bpm\n");
            result.append("Temperature: ").append(temp).append(" °C\n");
            result.append("Oxygen: ").append(oxygen).append("%\n\n");

            boolean alert = false;

            if (heart < 60 || heart > 100) {
                result.append("⚠ Alert: Abnormal Heart Rate!\n");
                alert = true;
            }
            if (temp > 37.5) {
                result.append("⚠ Alert: High Temperature!\n");
                alert = true;
            }
            if (oxygen < 95) {
                result.append("⚠ Alert: Low Oxygen Level!\n");
                alert = true;
            }

            if (!alert) {
                result.append("✅ Status: Normal Health.\n");
            }

            outputArea.setText(result.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        heartField.setText("");
        tempField.setText("");
        oxygenField.setText("");
        outputArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatientHealthDashboard());
    }
}
