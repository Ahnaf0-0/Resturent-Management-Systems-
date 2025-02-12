package classes;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DBconnect.DBconnect;

public class Cart extends JFrame implements ActionListener {

    private JPanel panel;
    private JTable cartTable;
    private JButton back, placeOrder;
    private JLabel cartIDLabel, totalPriceLabel;
    private String cartID;
    private String[] columnNames = {"Food Item", "Quantity", "Price (Tk)"};
    private DefaultTableModel tableModel;
    private DBconnect DB;
    private String user;

    public Cart(String user) {
        super("Cart");
        this.user = user;
        setSize(900, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DB = new DBconnect();

        cartID = fetchCartID(user);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 900, 600);
        panel.setBackground(Color.decode("#800000"));
        add(panel);

        cartIDLabel = new JLabel("Cart ID: " + cartID);
        cartIDLabel.setBounds(20, 20, 300, 30);
        cartIDLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartIDLabel.setForeground(Color.WHITE);
        panel.add(cartIDLabel);

        tableModel = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };

        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBounds(50, 80, 800, 300);
        panel.add(scrollPane);

        back = new JButton("Back");
        back.setBounds(185, 500, 100, 40);
        back.setFont(new Font("Arial", Font.BOLD, 20));
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        placeOrder = new JButton("Place Order");
        placeOrder.setBounds(625, 500, 150, 40);
        placeOrder.setFont(new Font("Arial", Font.BOLD, 20));
        placeOrder.setBackground(Color.DARK_GRAY);
        placeOrder.setForeground(Color.WHITE);
        placeOrder.addActionListener(this);
        panel.add(placeOrder);

        totalPriceLabel = new JLabel("Total Price: 0 Tk");
        totalPriceLabel.setBounds(700, 400, 200, 30);
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPriceLabel.setForeground(Color.WHITE);
        panel.add(totalPriceLabel);

        loadCartItems();
        this.add(panel);
        this.setVisible(true);
    }

    private String fetchCartID(String user) {
        String cartID = null;
        String query = "SELECT cart_id FROM RMS_CCART WHERE CUSTOMER_ID = (SELECT CUSTOMER_ID FROM RMS_CUSTOMER WHERE CUSTOMER_NAME='" + user + "')";
        try {
            DB.openConnection();
            DB.result = DB.st.executeQuery(query);
            if (DB.result.next()) {
                cartID = DB.result.getString("cart_id");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to load cart ID from database.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            DB.closeConnection();
        }
        return cartID;
    }

    private void loadCartItems() {
        tableModel.setRowCount(0);
        String query = "SELECT * FROM cartdetails where cart_id='" + cartID + "'";
        double totalPrice = 0;
        boolean hasItems = false;
        try {
            DB.openConnection();
            DB.result = DB.st.executeQuery(query);
            while (DB.result.next()) {
                hasItems = true;
                String itemName = DB.result.getString("item_name");
                double price = DB.result.getDouble("price");
                int quantity = DB.result.getInt("quantity");
                double totalItemPrice = price * quantity;
                totalPrice += totalItemPrice;
                tableModel.addRow(new Object[]{itemName, quantity, price});
            }

            if (!hasItems) {
                totalPriceLabel.setText("Total Price: 0 Tk");
            } else {
                totalPriceLabel.setText("Total Price: " + totalPrice + " Tk");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to load cart items from database.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            DB.closeConnection();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.setVisible(false);
            new Restaurant(user);
        } else if (e.getSource() == placeOrder) {
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Your cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public static void main(String[] args) {
        new Cart("User");
    }
}