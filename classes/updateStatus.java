package classes;

import DBconnect.DBconnect;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class updateStatus extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label1, orderIdLabel, statusLabel;
    private ImageIcon framelogo;
    private JTextField tfOrderId;
    private JComboBox<String> statusDropdown;
    private JButton updateStatus, back;
    private DBconnect DB;
    private User x;

    public updateStatus(User a) {
        super("UpdateStatus");
        this.setSize(480, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        x = a;
        DB = new DBconnect();

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 480, 600);
        add(panel);

        framelogo = new ImageIcon("Image/logo.png");
        setIconImage(framelogo.getImage());

        label1 = new JLabel("Update Status");
        label1.setForeground(Color.decode("#C70909"));
        label1.setFont(new Font("Forte", Font.BOLD, 40));
        label1.setBounds(120, 50, 300, 60);
        panel.add(label1);

        orderIdLabel = new JLabel("Order ID:");
        orderIdLabel.setBounds(130, 180, 100, 30);
        orderIdLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        orderIdLabel.setForeground(Color.black);
        panel.add(orderIdLabel);

        statusLabel = new JLabel("Status:");
        statusLabel.setBounds(130, 220, 100, 30);
        statusLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        statusLabel.setForeground(Color.black);
        panel.add(statusLabel);

        tfOrderId = new JTextField();
        tfOrderId.setBounds(210, 180, 140, 23);
        tfOrderId.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 1));
        tfOrderId.setToolTipText("Enter Order ID here");
        panel.add(tfOrderId);

        String[] statuses = {"DELIVERED", "ASSIGNED"};
        statusDropdown = new JComboBox<>(statuses);
        statusDropdown.setBounds(210, 220, 140, 23);
        panel.add(statusDropdown);

        updateStatus = new JButton("Update Status");
        updateStatus.setBounds(180, 340, 130, 30);
        updateStatus.setFocusable(false);
        updateStatus.setBackground(Color.decode("#C70909"));
        updateStatus.setForeground(Color.decode("#FFFFFF"));
        updateStatus.setBorder(BorderFactory.createEtchedBorder());
        updateStatus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateStatus.addActionListener(this);
        panel.add(updateStatus);

        back = new JButton("Back");
        back.setBounds(200, 390, 100, 30);
        back.addActionListener(this);
        panel.add(back);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateStatus) {
            // Declare variables
            int orderId;
            String statusText;

            // Get values from input fields
            String idText = tfOrderId.getText();
            statusText = statusDropdown.getSelectedItem().toString();

            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Order ID.", "Warning!", JOptionPane.WARNING_MESSAGE);
            } else {
                try {

                    orderId = Integer.parseInt(idText);

                    DB.openConnection();
                    System.out.println("Connection established.");


                    String query = "UPDATE dmanview SET ORDER_STATUS='" + statusText + "' WHERE ORDER_ID=" + orderId + ";";
                    int rowsAffected = DB.st.executeUpdate(query);
                    System.out.println("Query executed: " + query);

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Order status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Order status updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No record found with the given Order ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("No record found with the given Order ID.");
                    }

                    DB.closeConnection();
                    System.out.println("Database connection closed.");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Order ID. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Invalid Order ID format.");
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            }
        } else if (e.getSource() == back) {
            orders u = new orders(x);
            u.setVisible(true);
            this.setVisible(false);
        }
    }
}
