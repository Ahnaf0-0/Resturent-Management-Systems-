package classes;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import DBconnect.DBconnect;

public class Category1 extends JFrame implements ActionListener {

    private JPanel panel;
    private JButton back, order;
    private JLabel burger, prclassic, prtower, prsuper, banner;
    private ImageIcon img1, img2, img3, img4;
    private JCheckBox classiczinger, rtower, rsuper;
    private JComboBox<String> bclassic, btower, bsuper;
    private String[] quantities = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private String user, FOOD1, FOOD2, FOOD3;
    private int priceClassic, priceTower, priceSuper;
    private DBconnect DB;

    public Category1(String user) {
        super("Burger");
        this.user = user;
        DB = new DBconnect();
        ProductDetails();

        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("Image/logo.png").getImage());

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 900, 600);
        panel.setBackground(Color.decode("#800000"));
        add(panel);

        back = createButton("Back", 50, 500, 100, 40);
        back.addActionListener(this);
        panel.add(back);

        order = createButton("Add To Cart", 625, 500, 150, 40);
        order.addActionListener(this);
        panel.add(order);

        banner = new JLabel(new ImageIcon("Image/burgermenu.png"));
        banner.setBounds(0, 0, 900, 150);
        panel.add(banner);

        addBurgerSection("Image/classicburger.png", 70, FOOD1, priceClassic, bclassic = new JComboBox<>(quantities), classiczinger = new JCheckBox());

        addBurgerSection("Image/img.png", 350, FOOD2, priceTower, btower = new JComboBox<>(quantities), rtower = new JCheckBox());

        addBurgerSection("Image/superchargerburger.png", 620, FOOD3, priceSuper, bsuper = new JComboBox<>(quantities), rsuper = new JCheckBox());

        setVisible(true);
    }

    private void addBurgerSection(String imgPath, int x, String foodName, int price, JComboBox<String> quantityBox, JCheckBox checkbox) {
        burger = new JLabel(new ImageIcon(imgPath));
        burger.setBounds(x, 175, 200, 200);
        panel.add(burger);

        checkbox.setText(foodName);
        checkbox.setBounds(x + 20, 385, 160, 30);
        checkbox.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(checkbox);

        JLabel priceLabel = new JLabel("Price: " + price + " Tk");
        priceLabel.setBounds(x + 50, 420, 150, 20);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        priceLabel.setForeground(Color.WHITE);
        panel.add(priceLabel);

        quantityBox.setBounds(x + 80, 445, 40, 20);
        quantityBox.setBackground(Color.GRAY);
        quantityBox.setForeground(Color.WHITE);
        panel.add(quantityBox);
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.darkGray);
        button.setForeground(Color.WHITE);
        return button;
    }

    private void ProductDetails() {
        String query = "SELECT FOOD_NAME, PRICE FROM RMS_FITEM WHERE CATEGORY = 'BURGER'";
        try {
            DB.openConnection();
             DB.result=DB.st.executeQuery(query);
                while (DB.result.next()) {
                    String itemName = DB.result.getString("FOOD_NAME");
                    int price = DB.result.getInt("PRICE");
                    switch (itemName) {
                        case "CLASSIC ZINGER":
                            FOOD1 = itemName;
                            priceClassic = price;
                            break;
                        case "TOWER ZINGER":
                            FOOD2 = itemName;
                            priceTower = price;
                            break;
                        case "SUPER CHARGER BURGER":
                            FOOD3 = itemName;
                            priceSuper = price;
                            break;
                        }
                    }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load product details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new Restaurant(user).setVisible(true);
            dispose();
        } else if (e.getSource() == order) {
            String cartID = fetchCartID(user);
            Cart c= new Cart(cartID);
            c.setVisible(true);
            this.setVisible(false);
            if (cartID == null) {
                insertNewCart(user);
            }
            addSelectedItemsToCart();
        }
    }
    private void addSelectedItemsToCart() {
        if (classiczinger.isSelected()) {
            addToCart(FOOD1, Integer.parseInt((String) bclassic.getSelectedItem()));
        }
        if (rtower.isSelected()) {
            addToCart(FOOD2, Integer.parseInt((String) btower.getSelectedItem()));
        }
        if (rsuper.isSelected()) {
            addToCart(FOOD3, Integer.parseInt((String) bsuper.getSelectedItem()));
        }
        JOptionPane.showMessageDialog(this, "Items added to cart successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private String fetchCartID(String user) {
        String query = "SELECT cart_id FROM RMS_CCART WHERE CUSTOMER_ID = (SELECT CUSTOMER_ID FROM RMS_CUSTOMER WHERE CUSTOMER_NAME = '" + user + "')";
        try {
                DB.openConnection();
                DB.result=DB.st.executeQuery(query);
                if (DB.result.next()) {
                    return  String.valueOf(DB.result.getInt("cart_id"));

                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to fetch cart ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private void insertNewCart(String user) {
        String query = "INSERT INTO RMS_CCART (CUSTOMER_ID, CART_ID) VALUES ((SELECT CUSTOMER_ID FROM RMS_CUSTOMER WHERE CUSTOMER_NAME = '"+user+"'), NEXTVALUE(CART))";
        try {DB.openConnection();
            DB.st.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to create new cart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addToCart(String itemname, int quantity) {
        String query = "INSERT INTO RMS_CART (CART_ID,QUANTITY) VALUES (SELECT CART_ID from RMS_FCA where FOOD_ID = (SELECT FOOD_ID FROM RMS_FITEM WHERE FOOD_NAME= '"+itemname+"'),'" + quantity + "')";
        try {DB.openConnection();
            DB.st.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to add item to cart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        new Category1("User");
    }
}
