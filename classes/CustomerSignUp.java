package classes;

import DBconnect.DBconnect;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class CustomerSignUp extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label1, userName, userPass, email, confirmUserPass,area,phoneNumberLabel;
    private JTextField tfUName, tfemail,tfarea,tfroad,phoneNumberField;
    private JPasswordField pfUPass, cpfUPass;
    private JButton signUpButton1, previous0;
    private ImageIcon on, off, framelogo;
    private final JToggleButton toggleButton1, toggleButton2;
    private DBconnect DB;

    public CustomerSignUp() {
        super("CustomerSignUp");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(820, 580);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        DB = new DBconnect();

        panel = new JPanel();
        panel.setBounds(0, 0, 820, 580);
        panel.setLayout(null);

        framelogo = new ImageIcon("Image/logo.png");
        setIconImage(framelogo.getImage());

        label1 = new JLabel("Enter Your Necessary Details Below");
        label1.setForeground(Color.decode("#BF1A1A"));
        label1.setFont(new Font("Algerian", Font.PLAIN, 30));
        label1.setBounds(115, 0, 600, 140);
        panel.add(label1);

        label1 = new JLabel("____________________________________________________________________________");
        label1.setBounds(80, 30, 800, 90);
        label1.setFont(new Font("Biome", Font.BOLD, 16));
        label1.setForeground(Color.BLACK);
        panel.add(label1);

//name
        userName = new JLabel("Username: ");
        userName.setBounds(250, 140, 150, 30);
        userName.setFont(new Font("Biome", Font.BOLD, 16));
        userName.setForeground(Color.decode("#030914"));
        panel.add(userName);

        tfUName = new JTextField();
        tfUName.setBounds(350, 145, 140, 23);
        tfUName.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 1));
        tfUName.setToolTipText("Enter your name here");
        panel.add(tfUName);
//password

        userPass = new JLabel("Password: ");
        userPass.setBounds(250, 180, 150, 30);
        userPass.setFont(new Font("Biome", Font.BOLD, 16));
        userPass.setForeground(Color.decode("#030914"));
        panel.add(userPass);

        pfUPass = new JPasswordField();
        pfUPass.setBounds(350, 185, 140, 23);
        pfUPass.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 1));
        pfUPass.setEchoChar('*');
        pfUPass.setToolTipText("Enter your password here");
        panel.add(pfUPass);

//conpass
        confirmUserPass = new JLabel("Confirm Password: ");
        confirmUserPass.setBounds(180, 220, 180, 30);
        confirmUserPass.setFont(new Font("Biome", Font.BOLD, 16));
        confirmUserPass.setForeground(Color.decode("#030914"));
        panel.add(confirmUserPass);

        cpfUPass = new JPasswordField();
        cpfUPass.setBounds(350, 225, 140, 23);
        cpfUPass.setBorder(BorderFactory.createLineBorder(Color.decode("#3A4A62"), 1));
        cpfUPass.setEchoChar('*');
        cpfUPass.setToolTipText("Re-write your password here");
        panel.add(cpfUPass);
//email
        email = new JLabel("Email: ");
        email.setBounds(288, 260, 100, 30);
        email.setFont(new Font("Biome", Font.BOLD, 16));
        email.setForeground(Color.decode("#030914"));
        panel.add(email);


        tfemail = new JTextField();
        tfemail.setBounds(350, 265, 140, 23);
        tfemail.setBorder(BorderFactory.createLineBorder(Color.decode("#3A4A62"), 1));
        tfemail.setToolTipText("Your Email Adress?");
        panel.add(tfemail);

        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(220, 300, 150, 30);
        phoneNumberLabel.setFont(new Font("Biome", Font.BOLD, 16));
        panel.add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(350, 305, 140, 23);
        panel.add(phoneNumberField);
//AREA
        area = new JLabel("Address");
        area.setBounds(380, 325, 300, 30);
        area.setFont(new Font("Biome", Font.BOLD, 16));
        area.setForeground(Color.decode("#030918"));
        panel.add(area);
        label1 = new JLabel("______________");
        label1.setBounds(350, 298, 200, 90);
        label1.setFont(new Font("Biome", Font.BOLD, 16));
        label1.setForeground(Color.BLACK);
        panel.add(label1);

        area = new JLabel("Area:");
        area.setBounds(310, 355, 300, 30);
        area.setFont(new Font("Biome", Font.BOLD, 16));
        area.setForeground(Color.decode("#030914"));
        panel.add(area);

        area = new JLabel("Road No:");
        area.setBounds(464, 355, 300, 30);
        area.setFont(new Font("Biome", Font.BOLD, 16));
        area.setForeground(Color.decode("#030914"));
        panel.add(area);

        tfarea = new JTextField();
        tfarea.setBounds(260, 395, 140, 25);
        tfarea.setBorder(BorderFactory.createLineBorder(Color.decode("#3A4A62"), 1));
        tfarea.setToolTipText("ENter Your Area?");
        panel.add(tfarea);

        tfroad = new JTextField();
        tfroad.setBounds(430, 395, 140, 25);
        tfroad.setBorder(BorderFactory.createLineBorder(Color.decode("#3A4A62"), 1));
        tfroad.setToolTipText("Enter your Aroad no!");
        panel.add(tfroad);


        on = new ImageIcon("Image/tg1.png");
        off = new ImageIcon("Image/tg2.png");
        toggleButton1 = new JToggleButton(off);
        toggleButton1.setBounds(495, 185, 30, 22);
        toggleButton1.setBackground(Color.decode("#BF1A1A"));
        toggleButton1.setForeground(Color.decode("#BF1A1A"));
        toggleButton1.setOpaque(true);
        toggleButton1.setBorder(BorderFactory.createEmptyBorder());
        toggleButton1.setFocusable(false);
        toggleButton1.setToolTipText("Show Password");
        toggleButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleButton1.setVisible(true);
        panel.add(toggleButton1);
        toggleButton1.addActionListener(this);

        on = new ImageIcon("Image/tg1.png");
        off = new ImageIcon("Image/tg2.png");
        toggleButton2 = new JToggleButton(off);
        toggleButton2.setBounds(495, 225, 30, 22);
        toggleButton2.setBackground(Color.decode("#BF1A1A"));
        toggleButton2.setForeground(Color.decode("#BF1A1A"));
        toggleButton2.setOpaque(true);
        toggleButton2.setBorder(BorderFactory.createEmptyBorder());
        toggleButton2.setToolTipText("Show Password");
        toggleButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleButton2.setVisible(true);
        panel.add(toggleButton2);
        toggleButton2.addActionListener(this);
//back
        previous0 = new JButton("Go Back");
        previous0.setBounds(420, 450, 120, 30);
        previous0.setBackground(Color.decode("#E4002B"));
        previous0.setForeground(Color.decode("#FFFFFF"));
        previous0.setFont(new Font("Segoe UI", 1, 18));
        previous0.setBorder(new LineBorder(Color.decode("#000000"), 2));
        previous0.addActionListener(this);
        previous0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(previous0);
//login
        signUpButton1 = new JButton("Signup");
        signUpButton1.setBounds(290, 450, 120, 30);
        signUpButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton1.setBackground(Color.decode("#E4002B"));
        signUpButton1.setForeground(Color.decode("#FFFFFF"));
        signUpButton1.setFont(new Font("Segoe UI", 1, 18));
        signUpButton1.setBorder(new LineBorder(Color.decode("#000000"), 2));
        signUpButton1.addActionListener(this);
        panel.add(signUpButton1);

        this.add(panel);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == toggleButton1) {
            if (toggleButton1.isSelected()) {
                toggleButton1.setIcon(on);
                pfUPass.setEchoChar((char) 0);
            } else {
                toggleButton1.setIcon(off);
                pfUPass.setEchoChar('*');
            }
        }
        if (e.getSource() == toggleButton2) {
            if (toggleButton2.isSelected()) {
                toggleButton2.setIcon(on);
                cpfUPass.setEchoChar((char) 0);
            } else {
                toggleButton2.setIcon(off);
                cpfUPass.setEchoChar('*');
            }
        }
        if (e.getSource() == previous0) {
            HomePage h = new HomePage();
            h.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == signUpButton1) {
            String username = tfUName.getText();
            String passw = String.valueOf(pfUPass.getPassword());
            String confirmPass = String.valueOf(cpfUPass.getPassword());
            String sEmail = tfemail.getText();
            String area= tfarea.getText();
            String roadno=tfroad.getText();
            int phone = Integer.parseInt(phoneNumberField.getText());

            if (!username.isEmpty() && !passw.isEmpty() && !sEmail.isEmpty()) {
                if (confirmPass.equals(passw)) {
                    try {
                        DB.openConnection();
                        String cusquery = "INSERT INTO rms_customer (CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_PASSWORD, EMAIL,AREA) " +
                                "VALUES (NEXTVAL(cusid), '"+ username + "','" + passw + "','"+ sEmail + "','"+ area +"')";
                        System.out.println("Connected to database");
                        DB.st.executeUpdate(cusquery);
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + "NOT  successful!");
                    }
                    DB.closeConnection();
                    System.out.println("Closed the  database connection");

                    try {
                        DB.openConnection();
                        System.out.println("Connected to database");
                        String query = "SELECT CUSTOMER_ID FROM rms_customer WHERE CUSTOMER_NAME = '" + username + "'";
                        System.out.println("Executing query: " + query);
                        DB.result = DB.st.executeQuery(query);

                        if (DB.result.next()) {
                            int CUSTOMERID = DB.result.getInt("CUSTOMER_ID");
                            if (roadno != null && !roadno.isEmpty() && area != null && !area.isEmpty() && phone != 0) {
                                String addquery = "INSERT INTO rms_address (CUSTOMER_ID, AREA, ROAD_NO) VALUES ('" + CUSTOMERID + "', '" + area + "', '" + roadno + "')";
                                String phoneNO = "INSERT INTO rms_cphone (CUSTOMER_ID, P_NUMBER) VALUES ('" + CUSTOMERID + "', '" + area + "', '" + roadno + "')";
                                DB.st.executeUpdate(addquery);
                                DB.st.executeUpdate(phoneNO);
                          JOptionPane.showMessageDialog(null, "Sign-up successful!");
                                    HomePage H = new HomePage();
                                    H.setVisible(true);
                                    this.setVisible(false);
                            } else {
                                if (roadno == null || roadno.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Road number cannot be empty!");
                                } else if (area == null || area.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Area cannot be empty!");
                                }
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error querying customer ID: " + ex.getMessage());
                    }
                        DB.closeConnection();
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            }
        }
    }
    //run only this panel
    public static void main(String[] args) {
        new CustomerSignUp();
    }
}