package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DBconnect.DBconnect;

public class AdminSignIn extends JFrame implements ActionListener {

    private JLabel uname, upass, title, pic;
    private JTextField tname;
    private JPasswordField tpass;
    private JButton signin, back;
    private ImageIcon framelogo,icon;
    private JPanel panel;
    private DBconnect DB;

    public AdminSignIn() {
        super("AdminSignIn");
        framelogo = new ImageIcon("Image/logo.png");
        setIconImage(framelogo.getImage());
        DB = new DBconnect();

        icon = new ImageIcon("Image/admin.png");
        pic = new JLabel(icon);
        pic.setBounds(85, 65, 400, 400);
        this.add(pic);

        setTitle("AdminSignIn");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 900, 600);
        add(panel);

        title = new JLabel("Admin");
        title.setFont(new Font("Forte", Font.PLAIN, 40));
        title.setBounds(645, 70, 300, 55);
        title.setForeground(Color.decode("#E4002B"));
        title.setOpaque(true);
        panel.add(title);


        uname = new JLabel("Admin Username:");
        uname.setFont(new Font("Arial", Font.PLAIN, 20));
        uname.setSize(200, 20);
        uname.setLocation(600, 140);
        panel.add(uname);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 20));
        tname.setSize(200, 40);
        tname.setLocation(600, 170);
        panel.add(tname);

        upass = new JLabel("Admin Password:");
        upass.setFont(new Font("Arial", Font.PLAIN, 20));
        upass.setSize(200, 20);
        upass.setLocation(600, 230);
        panel.add(upass);

        tpass = new JPasswordField();
        tpass.setFont(new Font("Arial", Font.PLAIN, 20));
        tpass.setSize(200, 40);
        tpass.setLocation(600, 260);
        panel.add(tpass);

        signin = new JButton("Admin In");
        signin.setBackground(Color.decode("#E4002B"));
        signin.setForeground(Color.decode("#FFFFFF"));
        signin.setOpaque(true);
        signin.setFont(new Font("Arial", Font.PLAIN, 20));
        signin.setSize(150, 30);
        signin.setLocation(625, 310);
        signin.addActionListener(this);
        panel.add(signin);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(80, 30);
        back.setLocation(660, 345);
        back.setBackground(Color.decode("#E4002B"));
        back.setForeground(Color.decode("#FFFFFF"));
        back.setOpaque(true);
        back.addActionListener(this);
        panel.add(back);
        panel.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signin) {
            String ADMIN = tname.getText();
            String pass = String.valueOf(tpass.getPassword());


            if (!ADMIN.isEmpty() && !pass.isEmpty()) {
                try {
                    DB.openConnection();
                    String query = "SELECT ADMIN_NAME,ADMIN_PASSWORD FROM RMS_ADMIN WHERE ADMIN_NAME = '" + ADMIN + "' AND ADMIN_PASSWORD = '" + pass + "'";
                    DB.result = DB.st.executeQuery(query);

                    if (DB.result.next()) {
                        AdminDashboard dashboard = new AdminDashboard();
                        dashboard.setVisible(true);
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password!");
                    }
                    DB.closeConnection();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter both username and password.");
            }
        }

        if (e.getSource() == back) {
            HomePage h=new HomePage();
            h.setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AdminSignIn();
    }
}
