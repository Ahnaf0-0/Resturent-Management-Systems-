package classes;

import DBconnect.DBconnect;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class DeliveryMan extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label1, nameLabel, passwordLabel, salaryLabel, joiningDateLabel, phoneNumberLabel,confirmPassLabel;
    private JTextField nameField, salaryField, joiningDateField, phoneNumberField;
    private JPasswordField passwordField,confirmPassField;
    private JButton saveButton, backButton;
    private ImageIcon on, off;
    private JToggleButton toggleButton,confirmToggleButton;
    private DBconnect DB;

    public DeliveryMan() {
        super("Delivery Man Registration");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(820, 580);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        DB = new DBconnect();

        panel = new JPanel();
        panel.setBounds(0, 0, 820, 580);
        panel.setLayout(null);

        label1 = new JLabel("Enter Delivery Man Details Below");
        label1.setForeground(Color.decode("#BF1A1A"));
        label1.setFont(new Font("Algerian", Font.PLAIN, 30));
        label1.setBounds(130, 0, 600, 140);
        panel.add(label1);

        label1 = new JLabel("____________________________________________________________________________");
        label1.setBounds(80, 30, 800, 90);
        label1.setFont(new Font("Biome", Font.BOLD, 16));
        label1.setForeground(Color.BLACK);
        panel.add(label1);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(282, 140, 150, 30);
        nameLabel.setFont(new Font("Biome", Font.BOLD, 16));
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(370, 145, 140, 23);
        nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(nameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(260, 260, 150, 30);
        passwordLabel.setFont(new Font("Biome", Font.BOLD, 16));
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(370, 265, 140, 23);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        passwordField.setEchoChar('*');
        panel.add(passwordField);

        confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassLabel.setBounds(210, 300, 150, 30);
        confirmPassLabel.setFont(new Font("Biome", Font.BOLD, 16));
        panel.add(confirmPassLabel);

        confirmPassField = new JPasswordField();
        confirmPassField.setBounds(370, 305, 140, 23);
        confirmPassField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        confirmPassField.setEchoChar('*');
        panel.add(confirmPassField);

        on = new ImageIcon("Image/tg1.png");
        off = new ImageIcon("Image/tg2.png");

        toggleButton = new JToggleButton(off);
        toggleButton.setBounds(515, 265, 30, 22);
        toggleButton.setBackground(Color.decode("#BF1A1A"));
        toggleButton.setOpaque(true);
        toggleButton.setBorder(BorderFactory.createEmptyBorder());
        toggleButton.setFocusable(false);
        toggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(toggleButton);
        toggleButton.addActionListener(this);

        confirmToggleButton = new JToggleButton(off);
        confirmToggleButton.setBounds(515, 305, 30, 22);
        confirmToggleButton.setBackground(Color.decode("#BF1A1A"));
        confirmToggleButton.setOpaque(true);
        confirmToggleButton.setBorder(BorderFactory.createEmptyBorder());
        confirmToggleButton.setFocusable(false);
        confirmToggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(confirmToggleButton);
        confirmToggleButton.addActionListener(this);

        salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(280, 180, 150, 30);
        salaryLabel.setFont(new Font("Biome", Font.BOLD, 16));
        panel.add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(370, 185, 140, 23);
        panel.add(salaryField);

        joiningDateLabel = new JLabel("Joining Date:");
        joiningDateLabel.setBounds(250, 220, 150, 30);
        joiningDateLabel.setFont(new Font("Biome", Font.BOLD, 16));
        panel.add(joiningDateLabel);

        joiningDateField = new JTextField();
        joiningDateField.setBounds(370, 225, 140, 23);
        panel.add(joiningDateField);

        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(230, 340, 150, 30);
        phoneNumberLabel.setFont(new Font("Biome", Font.BOLD, 16));
        panel.add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(370, 345, 140, 23);
        panel.add(phoneNumberField);

        saveButton = new JButton("Save");
        saveButton.setBounds(280, 390, 120, 30);
        saveButton.setBackground(Color.decode("#E4002B"));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Segoe UI", 1, 18));
        saveButton.setBorder(new LineBorder(Color.BLACK, 2));
        saveButton.addActionListener(this);
        panel.add(saveButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(430, 390, 120, 30);
        backButton.setBackground(Color.decode("#E4002B"));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", 1, 18));
        backButton.setBorder(new LineBorder(Color.BLACK, 2));
        backButton.addActionListener(this);
        panel.add(backButton);

        this.add(panel);

        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toggleButton) {
            if (toggleButton.isSelected()) {
                toggleButton.setIcon(on);
                passwordField.setEchoChar((char) 0);
            } else {
                toggleButton.setIcon(off);
                passwordField.setEchoChar('*');
            }
        }
        if (e.getSource() == confirmToggleButton) {
            if (confirmToggleButton.isSelected()) {
                confirmToggleButton.setIcon(on);
                confirmPassField.setEchoChar((char) 0);
            } else {
                confirmToggleButton.setIcon(off);
                confirmPassField.setEchoChar('*');
            }
        }

        if (e.getSource() == backButton) {
            this.setVisible(false);
            new HomePage().setVisible(true);
        }

        if (e.getSource() == saveButton) {
            // Database connection
            String username = nameField.getText();
            String pass = String.valueOf(passwordField.getPassword());
            String confirmPass = String.valueOf(confirmPassField.getPassword());
            String salary = salaryField.getText();
            String joining_date= joiningDateField.getText();
            String phone_No=phoneNumberField.getText();
            if (!username.isEmpty() && !pass.isEmpty() && !phone_No.isEmpty()) {
                if (confirmPass.equals(pass)) {

                    try {
                        DB.openConnection();
                        String dmquery = "INSERT INTO RMS_DMAN (DM_ID, DM_NAME, SALARY, JOINING_DATE, PASSWORD) " +
                                "VALUES (NEXTVAL(DMAN_ID), '" + username + "', '" + salary + "', " +
                                "STR_TO_DATE('" + joining_date + "', '%d-%b-%Y'), '" + pass + "')";
                        DB.st.executeUpdate(dmquery);
                    }
                    catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + "NOT  successful!");
                        }
                    try {
                        String query = "SELECT DM_ID FROM RMS_DMAN WHERE DM_NAME = '" + username + "'";
                        DB.result = DB.st.executeQuery(query);

                        if (DB.result.next()) {
                            int DMANid = DB.result.getInt("DM_ID");

                            if (DMANid != -1) {
                                String phonequery = "INSERT INTO RMS_DNUMBER (DM_ID, DM_NUMBER) VALUES ('" + DMANid + "', '" + phone_No + "')";
                                if (phone_No != null && !phone_No.isEmpty()) {
                                    DB.st.executeUpdate(phonequery);
                                }
                            }
                        } else {
                            System.out.println("No DM_ID found for username: " + username);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    }
                    DB.closeConnection();
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            }
            JOptionPane.showMessageDialog(null, "Sign-up successful!");
            Dmanlogin H = new Dmanlogin();
            H.setVisible(true);
            this.setVisible(false);
        }
    }
    //run only this panel
    public static void main(String[] args) {
        new DeliveryMan();
    }

}
