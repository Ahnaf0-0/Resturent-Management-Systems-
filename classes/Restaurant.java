package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restaurant extends JFrame implements ActionListener {

    private JPanel p, c;
    private JLabel title, c1, c2, c3, lprofile, m;
    private String s;
    private JButton lgout, profile, cat1, cat2, cat3;

    public Restaurant(String user) {
        super("Restaurant");
        ImageIcon logo = new ImageIcon("Image/logo.png");
        setIconImage(logo.getImage());

        s = user;

        Icon icon = new ImageIcon("Image/Towerburger.png");
        Icon icon3 = new ImageIcon("Image/popcornricebowl.png");
        Icon icon4 = new ImageIcon("Image/menudrinks .png");
        Icon iconl = new ImageIcon("Image/logout.png");
        Icon iconp = new ImageIcon("Image/profile.png");
        Icon micon = new ImageIcon("Image/mainbanner.png");

        setTitle("Restaurant");
        setBounds(0, 0, 900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c = new JPanel();
        c.setBounds(0, 0, 900, 600);
        c.setLayout(null);
        add(c);

        title = new JLabel("Khanas");
        title.setBounds(350, 10, 500, 60);
        title.setFont(new Font("forte", Font.PLAIN, 60));
        title.setForeground(Color.white);
        c.add(title);

        m = new JLabel(micon);
        m.setBounds(0, 70, 900, 210);
        c.add(m);

        lgout = new JButton(iconl);
        lgout.setBounds(750, 5, 60, 60);
        lgout.addActionListener(this);
        c.add(lgout);

        profile = new JButton(iconp);
        profile.setBounds(820, 5, 60, 60);
        profile.addActionListener(this);
        c.add(profile);

        lprofile = new JLabel("User:");
        lprofile.setBounds(10, 20, 70, 30);
        lprofile.setForeground(Color.decode("#000000"));
        lprofile.setBackground(Color.decode("#FFFFFF"));
        lprofile.setHorizontalAlignment(JLabel.CENTER);
        lprofile.setOpaque(true);
        c.add(lprofile);

        lprofile = new JLabel(user);
        lprofile.setBounds(70, 20, 100, 30);
        lprofile.setForeground(Color.decode("#000000"));
        lprofile.setBackground(Color.decode("#FFFFFF"));
        lprofile.setHorizontalAlignment(JLabel.CENTER);
        lprofile.setOpaque(true);
        c.add(lprofile);

        cat1 = new JButton(icon);
        cat1.setBounds(50, 300, 200, 200);
        cat1.addActionListener(this);
        c.add(cat1);

        c1 = new JLabel("Burger");
        c1.setBounds(115, 510, 120, 30);
        c1.setFont(new Font("Arial", Font.PLAIN, 20));
        c1.setForeground(Color.decode("#F8F8F8"));
        c1.setBackground(Color.decode("#FFFFFF"));
        c.add(c1);

        cat2 = new JButton(icon3);
        cat2.setBounds(350, 300, 200, 200);
        cat2.addActionListener(this);
        c.add(cat2);

        c2 = new JLabel("Rice Bowls");
        c2.setBounds(400, 510, 150, 30);
        c2.setFont(new Font("Arial", Font.PLAIN, 20));
        c2.setForeground(Color.decode("#F8F8F8"));
        c2.setBackground(Color.decode("#FFFFFF"));
        c.add(c2);

        cat3 = new JButton(icon4);
        cat3.setBounds(650, 300, 200, 200);
        cat3.addActionListener(this);
        c.add(cat3);

        c3 = new JLabel("Drinks");
        c3.setBounds(725, 510, 150, 30);
        c3.setFont(new Font("Arial", Font.PLAIN, 20));
        c3.setForeground(Color.decode("#F8F8F8"));
        c3.setBackground(Color.decode("#FFFFFF"));
        c.add(c3);

        p = new JPanel();
        p.setBounds(0, 0, 900, 700);
        p.setBackground(Color.decode("#800000"));
        c.add(p);
        this.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cat1) {
            Category1 c1=new Category1(s);
            c1.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == cat2) {
            Category2 c2=new Category2(s);
            c2.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == cat3) {
            Category3 c3=new Category3(s);
            c3.setVisible(true);
            this.setVisible(false);

        } else if (e.getSource() == lgout) {
            HomePage h=new HomePage();
            h.setVisible(true);
            this.setVisible(false);
        }
        else if (e.getSource() == profile) {
            UProfile u=new UProfile(s);
            u.setVisible(true);
            this.setVisible(false);

        }
    }
}
