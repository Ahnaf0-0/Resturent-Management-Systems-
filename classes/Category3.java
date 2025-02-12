package classes;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DBconnect.DBconnect;

        public class Category3 extends JFrame implements ActionListener {

            private JPanel panel;
            private JButton back, order;
            private JLabel drink, prpepsi, prdwo, prwater, banner;
            private ImageIcon img1, img2, img3, img4;
            private JCheckBox rpepsi, rdwo, rwater;
            private JComboBox<String> bpepsi, bdwo, bwater;
            private String[] quantities = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            private String user, FOOD1, FOOD2, FOOD3;
            private int pricePepsi, priceDuo, priceWater;
            private DBconnect DB;

            public Category3(String user) {
                super("Drinks");
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

                img1 = new ImageIcon("Image/drinksmenu.png");
                img3 = new ImageIcon("Image/pepsi.png");
                img2 = new ImageIcon("Image/Duo.png");
                img4 = new ImageIcon("Image/Water.png");

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

                // mango
                drink = new JLabel(img2);
                drink.setBounds(93, 175, 197, 200);
                panel.add(drink);

                rpepsi = new JCheckBox(FOOD1);
                rpepsi.setBounds(130, 385, 115, 30);
                rpepsi.setFont(new Font("Arial", Font.BOLD, 15));
                panel.add(rpepsi);

                prpepsi = new JLabel("Price: " + pricePepsi + " Tk");
                prpepsi.setBounds(140, 420, 150, 20);
                prpepsi.setFont(new Font("Arial", Font.BOLD, 15));
                prpepsi.setForeground(Color.WHITE);
                panel.add(prpepsi);

                bpepsi = new JComboBox<>(quantities);
                bpepsi.setBounds(160, 445, 50, 20);
                bpepsi.setBackground(Color.GRAY);
                bpepsi.setForeground(Color.WHITE);
                panel.add(bpepsi);

                // pepsi
                drink = new JLabel(img3);
                drink.setBounds(350, 175, 200, 200);
                panel.add(drink);

                rdwo = new JCheckBox(FOOD2);
                rdwo.setBounds(410, 385, 75, 30);
                rdwo.setFont(new Font("Arial", Font.BOLD, 15));
                panel.add(rdwo);

                prdwo = new JLabel("Price: " + priceDuo + " Tk");
                prdwo.setBounds(405, 420, 150, 20);
                prdwo.setFont(new Font("Arial", Font.BOLD, 15));
                prdwo.setForeground(Color.WHITE);
                panel.add(prdwo);

                bdwo = new JComboBox<>(quantities);
                bdwo.setBounds(420, 445, 50, 20);
                bdwo.setBackground(Color.GRAY);
                bdwo.setForeground(Color.WHITE);
                panel.add(bdwo);

                // Strawberry
                drink = new JLabel(img4);
                drink.setBounds(600, 175, 200, 200);
                panel.add(drink);

                rwater = new JCheckBox(FOOD3);
                rwater.setBounds(627, 385, 150, 30);
                rwater.setFont(new Font("Arial", Font.BOLD, 15));
                panel.add(rwater);

                prwater = new JLabel("Price: " + priceWater + " Tk");
                prwater.setBounds(660, 420, 150, 20);
                prwater.setFont(new Font("Arial", Font.BOLD, 15));
                prwater.setForeground(Color.WHITE);
                panel.add(prwater);

                bwater = new JComboBox<>(quantities);
                bwater.setBounds(678, 445, 50, 20);
                bwater.setBackground(Color.GRAY);
                bwater.setForeground(Color.WHITE);
                panel.add(bwater);

                this.setVisible(true);
            }
            private void ProductDetails() {
                String query = "SELECT FOOD_NAME, PRICE FROM RMS_FITEM WHERE CATEGORY ='DRINKS';";
                try {
                    DB.openConnection();
                    System.out.println("Connection established.");

                    DB.result = DB.st.executeQuery(query);
                    System.out.println("Query executed: " + query);


                    while (DB.result.next()) {
                        String itemName = DB.result.getString("FOOD_NAME");
                        int price = DB.result.getInt("PRICE");

                        switch (itemName) {
                            case "OG Mango":
                                FOOD1 = itemName;
                                pricePepsi = price;
                                break;
                            case "Pepsi":
                                FOOD2 = itemName;
                                priceDuo = price;
                                break;
                            case "Cold Strawberry":
                                FOOD3 = itemName;
                                priceWater = price;
                                break;
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Failed to load product details from database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                DB.closeConnection();
                System.out.println("Database connection closed.");
            }
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    new Restaurant(user).setVisible(true);
                    this.setVisible(false);
                }
            }
            //run only this panel
            public static void main(String[] args) {
                new Category3("User");
            }
        }


