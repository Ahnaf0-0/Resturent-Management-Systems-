package classes;

import DBconnect.DBconnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UProfile extends JFrame implements ActionListener {

    private final JButton previous0;
    private JLabel details,label1;
    private JPanel panel;

    private final JTextArea display;
    private final String username;
    private DBconnect Db;

    public UProfile(String user) {
        super("UProfile");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        username = user;
        Db = new DBconnect();

        panel = new JPanel();
        panel.setLayout(null);

        display = new JTextArea(5, 5);
        display.setBounds(40, 45, 500, 270);
        display.setFont(new Font("Times New Roman", Font.BOLD, 19));
        display.setEditable(false);
        display.setBackground(new Color(245, 245, 245));
        panel.add(display);

        details = new JLabel("Personal Profile");
        details.setBounds(230, 5, 300, 30);
        details.setFont(new Font("Biome", Font.BOLD, 16));
        details.setForeground(Color.decode("#030918"));
        panel.add(details);

        getUserData();

        previous0 = new JButton("Go Back");
        previous0.setBounds(230, 340, 100, 30);
        previous0.setFocusable(false);
        previous0.setBackground(Color.LIGHT_GRAY);
        previous0.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        previous0.addActionListener(this);
        previous0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(previous0);

        this.add(panel);
        this.getContentPane().setBackground(Color.white);
        this.setVisible(true);
    }

    private void getUserData() {
        String query = "SELECT * FROM RMS_CUSTOMER where customer_name = '" + username + "';";
        String addquery = "SELECT ROAD_NO FROM RMS_ADDRESS where customer_id=(select customer_id from RMS_CUSTOMER where customer_name = '" + username + "');";
        try {
            Db.openConnection();
            Db.result = Db.st.executeQuery(query);

            if (Db.result.next()) {
                String customerId = "Customer ID: " + Db.result.getString("CUSTOMER_ID");
                String customerName = "Customer Name: " + Db.result.getString("CUSTOMER_NAME");
                String email = "Email: " + Db.result.getString("EMAIL");
                String password = "Password: " + Db.result.getString("CUSTOMER_PASSWORD");
                String area = "Area: " + Db.result.getString("AREA");

                Db.result = Db.st.executeQuery(addquery);

                String roadNo = null;
                if (Db.result.next()) {
                    roadNo = "Road No: " + Db.result.getString("ROAD_NO");
                }
                display.setText(customerId + "\n\n" + customerName + "\n\n" + email + "\n\n" + password + "\n\n" + area + "\n\n" + roadNo);
            } else {
                JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
                Restaurant R=new Restaurant(username);
                R.setVisible(true);
                this.setVisible(false);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while fetching user data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
            Db.closeConnection();

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previous0) {
            Restaurant R=new Restaurant(username);
            R.setVisible(true);
            this.setVisible(false);
        }
    }
}
