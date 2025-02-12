package classes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AdminDashboard extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel labelSystemPanel, frame, labelDashboard, labelImg, headline;
    private JButton buttonCustomerReport, buttonStaffReport, buttonProducts, buttonLogout;
    private ImageIcon img, bannerIcon;
    private Font ff, fff;
    private User x;

    public AdminDashboard() {
        super("Admin Dashboard");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //x = a;

        setSize(1050, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        img = new ImageIcon("Image/logo.png");
        setIconImage(img.getImage());

        ff = new Font("Arial", Font.BOLD, 20);
        fff = new Font("Arial", Font.BOLD, 28);

        panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        bannerIcon = new ImageIcon("Image/images (1).jpeg.jpg");
        labelImg = new JLabel(bannerIcon);
        labelImg.setBounds(310, 63, 600, 600);
        labelImg.setBackground(Color.decode("#FAF9F6"));
        panel.add(labelImg);

        labelSystemPanel = new JLabel("ADMIN PANEL");
        labelSystemPanel.setBounds(83, 50, 230, 90);
        labelSystemPanel.setFont(ff);
        labelSystemPanel.setBackground(Color.BLACK);
        labelSystemPanel.setForeground(Color.BLACK);
        panel.add(labelSystemPanel);

        labelDashboard = new JLabel("_________________");
        labelDashboard.setBounds(55, 53, 200, 90);
        labelDashboard.setFont(ff);
        labelDashboard.setForeground(Color.BLACK);
        panel.add(labelDashboard);

        labelDashboard = new JLabel("_________________");
        labelDashboard.setBounds(55, 26, 200, 90);
        labelDashboard.setFont(ff);
        labelDashboard.setForeground(Color.BLACK);
        panel.add(labelDashboard);

        labelDashboard = new JLabel("______________________________________________________");
        labelDashboard.setBounds(305, 0, 800, 90);
        labelDashboard.setFont(ff);
        labelDashboard.setForeground(Color.BLACK);
        panel.add(labelDashboard);

        headline = new JLabel("Restaurant Management System");
        headline.setBounds(400, -55, 650, 160);
        headline.setFont(fff);
        headline.setForeground(Color.black);
        panel.add(headline);

        buttonCustomerReport = new JButton("Customer's");
        buttonCustomerReport.setBounds(70, 170, 150, 30);
        buttonCustomerReport.setBorder(new LineBorder(Color.decode("#000000"), 2));
        buttonCustomerReport.setBackground(Color.decode("#E4002B"));
        buttonCustomerReport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonCustomerReport.setForeground(Color.decode("#FFFFFF"));
        buttonCustomerReport.setFont(ff);
        panel.add(buttonCustomerReport);

        buttonStaffReport = new JButton("Staff's");
        buttonStaffReport.setBounds(70, 230, 150, 30);
        buttonStaffReport.setBorder(new LineBorder(Color.decode("#000000"), 2));
        buttonStaffReport.setBackground(Color.decode("#E4002B"));
        buttonStaffReport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonStaffReport.setForeground(Color.decode("#FFFFFF"));
        buttonStaffReport.setFont(ff);
        panel.add(buttonStaffReport);

        buttonProducts = new JButton("Products");
        buttonProducts.setBounds(70, 290, 150, 30);
        buttonProducts.setBorder(new LineBorder(Color.decode("#000000"), 2));
        buttonProducts.setBackground(Color.decode("#E4002B"));
        buttonProducts.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonProducts.setForeground(Color.decode("#FFFFFF"));
        buttonProducts.setFont(ff);
        panel.add(buttonProducts);


        buttonLogout = new JButton("Logout");
        buttonLogout.setBounds(95, 600, 100, 30);
        buttonLogout.setBorder(new LineBorder(Color.decode("#000000"), 2));
        buttonLogout.setBackground(Color.decode("#E4002B"));
        buttonLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonLogout.setForeground(Color.decode("#FFFFFF"));
        buttonLogout.setFont(ff);
        panel.add(buttonLogout);

        this.add(panel);
        buttonLogout.addActionListener(this);
        buttonCustomerReport.addActionListener(this);
        buttonStaffReport.addActionListener(this);
        buttonProducts.addActionListener(this);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonLogout) {
            HomePage h = new HomePage();
            h.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == buttonCustomerReport) {
            CustomerData d = new CustomerData(x);
            d.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == buttonStaffReport) {
            DMANData s = new DMANData(x);
            s.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == buttonProducts) {
            products s = new products(x);
            s.setVisible(true);
            this.setVisible(false);
        }

    }
}
