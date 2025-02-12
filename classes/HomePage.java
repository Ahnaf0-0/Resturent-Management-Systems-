package classes;

import DBconnect.DBconnect;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePage extends JFrame implements ActionListener, MouseListener {

    private JTextField tfUName;
    private JToggleButton toggleButton;
    private JPasswordField pfUPass;
    private JPanel panel;
    private ImageIcon on, off,background,logoIcon;
    private JLabel logo, su, userName, userPass,confirmUserPass;
    private JButton aboutusbtn, staffbtn, adminbtn, loginButton, signUpButton1;
    private final Color btnColor = new Color(0xF9E8D8);
    private DBconnect DB;

    public HomePage() {
        super("HomePage");

        DB = new DBconnect();

        setSize(820, 580);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 820, 580);
        add(panel);

        aboutusbtn = new JButton("About Us");
        aboutusbtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        aboutusbtn.setBounds(655, 485, 130, 38);
        aboutusbtn.setBackground(btnColor);
        aboutusbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutusbtn.setForeground(Color.BLACK);
        aboutusbtn.addMouseListener(this);
        aboutusbtn.addActionListener(this);
        panel.add(aboutusbtn);

        staffbtn = new JButton("D_man Login");
        staffbtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        staffbtn.setBounds(500, 485, 130, 38);
        staffbtn.setBackground(btnColor);
        staffbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        staffbtn.setForeground(Color.BLACK);
        staffbtn.addMouseListener(this);
        staffbtn.addActionListener(this);
        panel.add(staffbtn);

        adminbtn = new JButton("Admin Login");
        adminbtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        adminbtn.setBounds(350, 485, 130, 38);
        adminbtn.setBackground(btnColor);
        adminbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adminbtn.setForeground(Color.BLACK);
        adminbtn.addMouseListener(this);
        adminbtn.addActionListener(this);
        panel.add(adminbtn);


        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", 1, 19));
        loginButton.setBounds(549, 385, 80, 30);
        loginButton.setBorder(new LineBorder(Color.decode("#000000"), 2));
        loginButton.setBackground(Color.decode("#E4002B"));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setForeground(Color.decode("#FFFFFF"));
        loginButton.addActionListener(this);
        panel.add(loginButton);

        signUpButton1 = new JButton("Sign Up");
        signUpButton1.setFont(new Font("Segoe UI", 3, 18));
        signUpButton1.setBounds(725, 147, 80, 30);
        signUpButton1.setBorder(new LineBorder(Color.white, 0));
        signUpButton1.setBackground(Color.white);
        signUpButton1.setForeground(Color.red);
        signUpButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton1.addActionListener(this);
        signUpButton1.setContentAreaFilled(false);
        signUpButton1.setBorderPainted(false);
        panel.add(signUpButton1);


        userName = new JLabel("Username: ");
        userName.setBounds(425, 215, 200, 30);
        userName.setFont(new Font("Segoe UI", 1, 20));
        userName.setVisible(true);
        panel.add(userName);

        tfUName = new JTextField();
        tfUName.setBounds(437, 260, 325, 25);
        tfUName.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
        tfUName.setToolTipText("Enter your name here");
        tfUName.setVisible(true);
        tfUName.setFocusable(true);
        panel.add(tfUName);

        userPass = new JLabel("Password: ");
        userPass.setBounds(425, 300, 200, 30);
        userPass.setFont(new Font("Segoe UI", 1, 20));
        userPass.setVisible(true);
        panel.add(userPass);

        confirmUserPass = new JLabel("Confirm Password: ");
        confirmUserPass.setBounds(475, 275, 180, 30);
        confirmUserPass.setFont(new Font("Calibri", Font.BOLD, 16));
        confirmUserPass.setForeground(Color.decode("#FFFF00"));
        confirmUserPass.setVisible(false);
        panel.add(confirmUserPass);


        pfUPass = new JPasswordField();
        pfUPass.setBounds(437, 345, 325, 25);
        pfUPass.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
        pfUPass.setEchoChar('*');
        pfUPass.setToolTipText("Enter your password here");
        pfUPass.setVisible(true);
        pfUPass.setFocusable(true);
        panel.add(pfUPass);


        su = new JLabel("Don't have an Account?");
        su.setFont(new Font("Segoe UI", 1, 17));
        su.setBounds(500, 147, 200, 30);
        panel.add(su);


        on = new ImageIcon("Image/tg1.png");
        off = new ImageIcon("Image/tg2.png");
        toggleButton = new JToggleButton(off);
        toggleButton.setBounds(770, 348, 25, 20);
        toggleButton.setBackground(Color.decode("#E4002B"));
        toggleButton.setForeground(new Color(173, 255, 47));
        toggleButton.setOpaque(true);
        toggleButton.setBorder(BorderFactory.createEmptyBorder());
        toggleButton.setToolTipText("Show Password");
        toggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleButton.setVisible(true);
        toggleButton.addActionListener(this);
        panel.add(toggleButton);

         background= new ImageIcon("Image/background.png");
         logoIcon = new ImageIcon("Image/logo.png");
        setIconImage(logoIcon.getImage());

        logo = new JLabel(background);
        logo.setBounds(0, 0, 800, 550);
        panel.add(logo);
        this.setVisible(true);
    }
    public void mouseClicked(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {

        if (me.getSource() == aboutusbtn) {
            aboutusbtn.setBackground(Color.white);
            aboutusbtn.setForeground(Color.black);
        }else if (me.getSource() == staffbtn) {
            staffbtn.setBackground(Color.white);
            staffbtn.setForeground(Color.black);
        }else if (me.getSource() == adminbtn) {
            adminbtn.setBackground(Color.white);
            adminbtn.setForeground(Color.black);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == aboutusbtn) {
            aboutusbtn.setBackground(btnColor);
            aboutusbtn.setForeground(Color.BLACK);
        } else if (me.getSource() == staffbtn) {
            staffbtn.setBackground(btnColor);
            staffbtn.setForeground(Color.BLACK);
        } else if (me.getSource() == adminbtn) {
            adminbtn.setBackground(btnColor);
            adminbtn.setForeground(Color.BLACK);
        }
    }

    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aboutusbtn) {
            AboutUs a=new AboutUs();
            a.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == staffbtn) {
            Dmanlogin st=new Dmanlogin();
            st.setVisible(true);
            this.setVisible(false);

            this.setVisible(false);
        } else if (e.getSource() == adminbtn) {
            AdminSignIn s=new AdminSignIn();
            s.setVisible(true);
            this.setVisible(false);
        }

        if (e.getSource() == toggleButton) {
            if (toggleButton.isSelected()) {
                toggleButton.setIcon(on);
                pfUPass.setEchoChar((char) 0);
            } else {
                toggleButton.setIcon(off);
                pfUPass.setEchoChar('*');
            }
        }

        if (e.getSource() == signUpButton1) {
            CustomerSignUp s=new CustomerSignUp();
            s.setVisible(true);
            this.setVisible(false);

        } else if (e.getSource() == loginButton) {
            handleLogin();
        }
    }

    private void handleLogin() {
        String user = tfUName.getText().trim();
        String pass = new String(pfUPass.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the blanks.", "Warning!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            DB.openConnection();
            System.out.println("Connection established.");

            String query = "SELECT CUSTOMER_NAME,CUSTOMER_PASSWORD  FROM RMS_CUSTOMER WHERE CUSTOMER_NAME = '" + user + "' AND CUSTOMER_PASSWORD = '" + pass + "'";

            DB.result=DB.st.executeQuery(query);
            System.out.println("Query executed: " + query);

            if (DB.result.next()) {
                Restaurant r=new Restaurant(user);
                r.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!", "Warning!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
            DB.closeConnection();
        System.out.println("Database connection closed.");
    }
}
