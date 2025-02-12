package classes;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DBconnect.DBconnect;

public class Category2 extends JFrame implements ActionListener {

    private JPanel panel;
    private JButton back, order;
    private JLabel rice, prcripsy, prpopcorn, prbox, banner;
    private ImageIcon img1, img2, img3, img4;
    private JCheckBox rcripsy, rpopcorn, rbox;
    private JComboBox<String> bcripsy, bpopcorn, bbox;
    private String[] quantities = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private String user,FOOD1,FOOD2,FOOD3;
    private int priceCrispy, pricePopcorn, priceBox;
    private DBconnect DB;

    public Category2(String user) {
        super("Rice Bowl");
        this.user = user;
        DB = new DBconnect();
        ProductDetails();

        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon framelogo = new ImageIcon("Image/logo.png");
        setIconImage(framelogo.getImage());

        img1 = new ImageIcon("Image/ricebowlmenu.png");
        img2 = new ImageIcon("Image/cripsyricebowl.png");
        img4 = new ImageIcon("Image/menuricebowl.png");
        img3 = new ImageIcon("Image/boxricebowl.png");

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 900, 600);
        panel.setBackground(Color.decode("#800000"));
        add(panel);

        back = new JButton("Back");
        back.setBounds(185, 500, 100, 40);
        back.setFont(new Font("Arial", Font.BOLD, 20));
        back.setBackground(Color.darkGray);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        order = new JButton("Add To Cart");
        order.setBounds(625, 500, 150, 40);
        order.setFont(new Font("Arial", Font.BOLD, 20));
        order.setBackground(Color.darkGray);
        order.setForeground(Color.WHITE);
        order.addActionListener(this);
        panel.add(order);

        banner = new JLabel(img1);
        banner.setBounds(0, 0, 900, 150);
        panel.add(banner);

        // Hot & Crispy Rice Bowl
        rice = new JLabel(img2);
        rice.setBounds(93, 175, 197, 200);
        panel.add(rice);

        rcripsy = new JCheckBox(FOOD1);
        rcripsy.setBounds(95, 385, 197, 30);
        rcripsy.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(rcripsy);

        prcripsy = new JLabel("Price: " +priceCrispy+" Tk");
        prcripsy.setBounds(153, 417, 150, 20);
        prcripsy.setFont(new Font("Arial", Font.BOLD, 15));
        prcripsy.setForeground(Color.WHITE);
        panel.add(prcripsy);

        bcripsy = new JComboBox<>(quantities);
        bcripsy.setBounds(170, 445, 50, 20);
        bcripsy.setBackground(Color.GRAY);
        bcripsy.setForeground(Color.WHITE);
        panel.add(bcripsy);

        // Popcorn Rice Bowl
        rice = new JLabel(img3);
        rice.setBounds(350, 175, 200, 200);
        panel.add(rice);

        rpopcorn = new JCheckBox(FOOD2);
        rpopcorn.setBounds(367, 385, 167, 30);
        rpopcorn.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(rpopcorn);

        prpopcorn = new JLabel("Price: " + pricePopcorn + " Tk");
        prpopcorn.setBounds(405, 417, 150, 20);
        prpopcorn.setFont(new Font("Arial", Font.BOLD, 15));
        prpopcorn.setForeground(Color.WHITE);
        panel.add(prpopcorn);

        bpopcorn = new JComboBox<>(quantities);
        bpopcorn.setBounds(424, 445, 50, 20);
        bpopcorn.setBackground(Color.GRAY);
        bpopcorn.setForeground(Color.WHITE);
        panel.add(bpopcorn);

        // Rice Box
        rice = new JLabel(img4);
        rice.setBounds(610, 175, 200, 200);
        panel.add(rice);

        rbox = new JCheckBox(FOOD3);
        rbox.setBounds(625, 385, 167, 30);
        rbox.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(rbox);

        prbox = new JLabel("Price: " +priceBox+" Tk");
        prbox.setBounds(660, 417, 150, 20);
        prbox.setFont(new Font("Arial", Font.BOLD, 15));
        prbox.setForeground(Color.WHITE);
        panel.add(prbox);

        bbox = new JComboBox<>(quantities);
        bbox.setBounds(677, 445, 50, 20);
        bbox.setBackground(Color.GRAY);
        bbox.setForeground(Color.WHITE);
        panel.add(bbox);

        this.setVisible(true);
    }

    private void ProductDetails() {
        String query = "SELECT FOOD_NAME, PRICE FROM RMS_FITEM WHERE CATEGORY ='RICE BOWLS';";
        try {
            DB.openConnection();
            System.out.println("Connection established.");

            DB.result = DB.st.executeQuery(query);
            System.out.println("Query executed: " + query);

            while (DB.result.next()) {
                String itemName = DB.result.getString("FOOD_NAME");
                int price = DB.result.getInt("PRICE");

                switch (itemName) {
                    case "Hot & Crispy Rice Bowl":
                        FOOD1=itemName;
                        priceCrispy = price;
                        break;
                    case "Chicken Teriyaki":
                        FOOD2=itemName;
                        pricePopcorn = price;
                        break;
                    case "Popcorn Rice Bowl":
                        FOOD3=itemName;
                        priceBox = price;
                        break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to load product details from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
            DB.closeConnection();
        System.out.println("Database connection closed.");
    }
//    private void saveFoodDetailsInCart() {
//        String query="INSERT INTO RMS_CART"
//
//    }



        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            Restaurant r = new Restaurant(user);
            r.setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == order){
            //saveFoodDetailsInCart();
//
//            c.setVisible(true);
//            this.setVisible(false);
            }
             else {
               // JOptionPane.showMessageDialog(null, "Please select something before Adding Food Items in cart!", "Message", JOptionPane.WARNING_MESSAGE);
            }
        }
        //run only this panel
    public static void main(String[] args) {
        new Category2("User");
    }
    }


