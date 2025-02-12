package classes;
import DBconnect.DBconnect;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class updatePrice extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label1, updatedPrice, foodId;
    private ImageIcon framelogo;
    private JTextField tfid, tfnp;
    private JButton updatePrice , back;
    private DBconnect DB;

    private User x;

    public updatePrice(User a) {
        super("updatePrice");
        this.setSize(480, 600);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        x = a;
        DB = new DBconnect();

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 480, 600);
        add(panel);

        framelogo = new ImageIcon("Image/logo.png");
        setIconImage(framelogo.getImage());

        label1 = new JLabel();
        label1.setForeground(Color.decode("#C70909"));
        label1.setFont(new Font("Forte", Font.BOLD, 40));
        label1.setBounds(150, 50, 250, 60);
        panel.add(label1);

        foodId = new JLabel("Food ID: ");
        foodId.setBounds(130, 180, 100, 30);
        foodId.setFont(new Font("Calibri", Font.BOLD, 16));
        foodId.setForeground(Color.black);
        panel.add(foodId);

        updatedPrice = new JLabel("New price: ");
        updatedPrice.setBounds(130, 220, 100, 30);
        updatedPrice.setFont(new Font("Calibri", Font.BOLD, 16));
        updatedPrice.setForeground(Color.black);
        panel.add(updatedPrice);

        tfid = new JTextField();
        tfid.setBounds(210, 180, 140, 23);
        tfid.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 1));
        tfid.setToolTipText("Enter food id here");
        panel.add(tfid);

        tfnp = new JTextField();
        tfnp.setBounds(210, 220, 140, 23);
        tfnp.setBorder(BorderFactory.createLineBorder(Color.decode("#000000"), 1));
        tfnp.setToolTipText("Enter new price here");
        panel.add(tfnp);



        updatePrice  = new JButton("Update Price");
        updatePrice .setBounds(180, 340, 100, 30);
        updatePrice .setFocusable(false);
        updatePrice .setBackground(Color.decode("#C70909"));
        updatePrice .setForeground(Color.decode("#FFFFFF"));
        updatePrice .setBorder(BorderFactory.createEtchedBorder());
        updatePrice .setCursor(new Cursor(Cursor.HAND_CURSOR));
        updatePrice .addActionListener(this);
        panel.add(updatePrice );

        back = new JButton("Back");
        back.setBounds(200, 390, 100, 30);
        back.addActionListener(this);
        panel.add(back);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == updatePrice) {
            String idText = tfid.getText();
            String npText = tfnp.getText();

            if (idText.isEmpty() || npText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all of the fields.", "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            } else {

                try {
                    int id = Integer.parseInt(idText);
                    int np = Integer.parseInt(npText);

                    DB.openConnection();
                    System.out.println("Connection established.");

                    String query = "UPDATE RMS_FITEM SET PRICE="+np+" WHERE FOOD_ID="+id+";";

                    int rowsAffected = DB.st.executeUpdate(query);;
                    System.out.println("Query executed: " + query);

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Price updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Price updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No record found with the given Food ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("No record found with the given Food ID.");
                    }


                    DB.closeConnection();
                    System.out.println("Database connection closed.");



                } catch (Exception ex) {
                    System.out.print(ex);
                }

            }


        } else if (e.getSource() == back) {
            products u=new products(x);
            u.setVisible(true);
            this.setVisible(false);
        }

    }
}