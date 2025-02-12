package classes;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import DBconnect.DBconnect;

public class Dmanlogin extends JFrame implements ActionListener {

    private JPanel frame;
    private JLabel title, logo,banner, userName, userPass, confirmUserPass, su;
    private JButton loginButton, signUpButton1, exitButton, previous0, frgtpass;
    private JTextField tfUName;
    private JPasswordField pfUPass;
    private ImageIcon on, off,framelogo,ban,icon;
    private JToggleButton toggleButton;
    private DBconnect DB;

    public Dmanlogin() {
        super("DelivaryMan Login");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DB= new DBconnect();

        frame = new JPanel();
        frame.setLayout(null);
        frame.setSize(820, 580);

        ban = new ImageIcon("Image/ban.png");
        icon = new ImageIcon("Image/logo1.png");
        framelogo = new ImageIcon("Image/logo1.png");
        setIconImage(framelogo.getImage());


        title = new JLabel("DelivaryMan Login");
        title.setForeground(Color.decode("#E4002B"));
        title.setFont(new Font("Forte", Font.PLAIN, 50));
        title.setBounds(180, 50, 500, 75);
        frame.add(title);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", 1, 19));
        loginButton.setBounds(535, 340, 80, 30);
        loginButton.setBorder(new LineBorder(Color.decode("#000000"), 2));
        loginButton.setBackground(Color.decode("#E4002B"));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setForeground(Color.decode("#FFFFFF"));
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);
        frame.add(loginButton);

        signUpButton1 = new JButton("Sign Up");
        signUpButton1.setFont(new Font("Segoe UI", 1, 18));
        signUpButton1.setBounds(610, 400, 80, 30);
        signUpButton1.setBorder(new LineBorder(Color.decode("#000000"), 2));
        signUpButton1.setBackground(Color.decode("#E4002B"));
        signUpButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton1.addActionListener(this);
        signUpButton1.setFocusable(false);
        signUpButton1.setContentAreaFilled(false);
        signUpButton1.setBorderPainted(false);
        signUpButton1.setForeground(Color.decode("#E4002B"));
        frame.add(signUpButton1);

        su = new JLabel("Don't have an Account?");
        su.setFont(new Font("Segoe UI", 1, 17));
        su.setBounds(415, 400, 200, 30);
        frame.add(su);

        exitButton = new JButton("Go Back");
        exitButton.setFont(new Font("Segoe UI", 1, 18));
        exitButton.setBounds(700, 500, 80, 30);
        exitButton.setBackground(Color.decode("#37f9f8"));
        exitButton.setBorder(new LineBorder(Color.decode("#000000"), 2));
        exitButton.setBackground(Color.decode("#E4002B"));
        exitButton.setForeground(Color.decode("#FFFFFF"));
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);
        frame.add(exitButton);

        userName = new JLabel("Username: ");
        userName.setBounds(400, 160, 200, 30);
        userName.setFont(new Font("Segoe UI", 1, 22));
        userName.setVisible(true);
        frame.add(userName);

        userPass = new JLabel("Password: ");
        userPass.setBounds(400, 255, 200, 30);
        userPass.setFont(new Font("Segoe UI", 1, 22));
        userPass.setVisible(true);
        frame.add(userPass);

        confirmUserPass = new JLabel("Confirm Password: ");
        confirmUserPass.setBounds(180, 280, 180, 30);
        confirmUserPass.setFont(new Font("Calibri", Font.BOLD, 16));
        confirmUserPass.setForeground(Color.decode("#FFFF00"));
        confirmUserPass.setVisible(false);
        frame.add(confirmUserPass);

        tfUName = new JTextField();
        tfUName.setBounds(412, 210, 300, 27);
        tfUName.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
        tfUName.setToolTipText("Enter your name here");
        tfUName.setVisible(true);
        frame.add(tfUName);

        pfUPass = new JPasswordField();
        pfUPass.setBounds(412, 300, 300, 27);
        pfUPass.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
        pfUPass.setEchoChar('*');
        pfUPass.setToolTipText("Enter your password here");
        pfUPass.setVisible(true);
        frame.add(pfUPass);

        on = new ImageIcon("Image/tg1.png");
        off = new ImageIcon("Image/tg2.png");
        toggleButton = new JToggleButton(off);
        toggleButton.setBounds(715, 302, 30, 20);
        toggleButton.setBackground(Color.decode("#E4002B"));
        toggleButton.setForeground(new Color(173, 255, 47));
        toggleButton.setOpaque(true);
        toggleButton.setBorder(BorderFactory.createEmptyBorder());
        toggleButton.setFocusable(false);
        toggleButton.setToolTipText("Show Password");
        toggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleButton.setVisible(true);
        frame.add(toggleButton);
        toggleButton.addActionListener(this);

        banner= new JLabel(ban);
        banner.setBounds(390,150,360,240);
        frame.add(banner);

        logo = new JLabel(icon);
        logo.setBounds(-90, -30, 600, 600);
        frame.add(logo);

        this.add(frame);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
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
            DeliveryMan s=new DeliveryMan();
            s.setVisible(true);
            this.setVisible(false);

        }

        else if (e.getSource() == exitButton) {
            HomePage h=new HomePage();
            h.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == previous0) {
            loginButton.setVisible(true);
            userName.setVisible(false);
            userPass.setVisible(false);
            tfUName.setVisible(false);
            pfUPass.setVisible(false);
            previous0.setVisible(false);
            signUpButton1.setVisible(true);
            toggleButton.setVisible(false);
        }

        if (e.getSource() == loginButton) {
            String user = tfUName.getText().trim();
            String pass = new String(pfUPass.getPassword()).trim();
            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all of the fields.", "Warning!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                DB.openConnection();
                System.out.println("Connection established.");

                String query = "SELECT DM_NAME, PASSWORD FROM RMS_DMAN WHERE  DM_NAME = '" + user + "' AND PASSWORD = '" + pass + "'";
                DB.result=DB.st.executeQuery(query);
                System.out.println("Query executed: " + query);
                if (DB.result.next()) {
                    DmanDashboard a = new DmanDashboard();
                    a.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid User Name or Password!", "Warning!", JOptionPane.WARNING_MESSAGE);
                }
                DB.closeConnection();
                System.out.println("Connection closed.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error connecting to database: " + ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
