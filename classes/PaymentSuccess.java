package classes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PaymentSuccess implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel tcs;
    private JLabel ap;
    private JButton home;
    private JButton recipt;
    private String f;

    public PaymentSuccess(double amount, String user) {

        frame = new JFrame("PaymentSuccess");

        ImageIcon framelogo = new ImageIcon("Image/logo.png");
        frame.setIconImage(framelogo.getImage());

        f = user;

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 500);
        frame.add(panel);

        tcs = new JLabel("Transaction Completed Successfully");
        tcs.setVerticalTextPosition(JLabel.BOTTOM);
        tcs.setHorizontalTextPosition(JLabel.CENTER);
        tcs.setForeground(Color.BLACK);
        tcs.setFont(new Font("Segoe UI Black", Font.PLAIN, 28));
        tcs.setBounds(135, 00, 520, 200);
        panel.add(tcs);

        ap = new JLabel("Amount Paid :    " + amount + " tk");
        ap.setForeground(Color.BLACK);
        ap.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
        ap.setBounds(300, 200, 400, 100);
        panel.add(ap);

        home = new JButton("Home");
        home.setBounds(250, 300, 100, 30);
        home.setFocusable(false);
        home.setBackground(Color.decode("#BF1A1A"));
        home.setForeground(Color.WHITE);
        home.setBorder(BorderFactory.createEmptyBorder());
        home.setCursor(new Cursor(Cursor.HAND_CURSOR));
        home.addActionListener(this);
        panel.add(home);

        recipt = new JButton("Print Receipt");
        recipt.setBounds(450, 300, 130, 30);
        recipt.setFocusable(false);
        recipt.setBackground(Color.decode("#BF1A1A"));
        recipt.setForeground(Color.WHITE);
        recipt.setBorder(BorderFactory.createEmptyBorder());
        recipt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recipt.addActionListener(this);
        panel.add(recipt);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.getContentPane().setBackground(Color.decode("#F2F2F2"));
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == home) {

            Restaurant r=new Restaurant(f);
            r.setVisible(true);
            frame.setVisible(false);
        } else if (e.getSource() == recipt) {
            generateReceipt();
        }
    }

    private void generateReceipt() {
        try {
            File newfile = new File("data\\Credit.txt");
            Scanner sc = new Scanner(newfile);

            String line1 = null;
            String line2 = null;
            String line3 = null;
            String line4 = null;
            String line5 = null;
            String line6 = null;
            String line7 = null;
            String line8 = null;
            String line9 = null;

            while (sc.hasNext()) {
                line1 = sc.nextLine();
                line2 = sc.nextLine();
                line3 = sc.nextLine();
                line4 = sc.nextLine();
                line5 = sc.nextLine();
            }
            sc.close();

            File newfile2 = new File("data\\Credit.txt");
            Scanner sc2 = new Scanner(newfile2);
            while (sc2.hasNext()) {
                line6 = sc2.nextLine();
                line7 = sc2.nextLine();
                line8 = sc2.nextLine();
                line9 = sc2.nextLine();
            }
            sc2.close();

            File receipt = new File("data\\Receipt.txt");
            receipt.createNewFile();
            FileWriter writeFile = new FileWriter("data\\Receipt.txt");

            writeFile.write(line1 + "\n" + line2 + "\n NAME: " + line3 + "\n" + line4 + "\n" + line5
                    + "\n        \n ----------------------\n        \n" + line6 + "\n" + line7 + "\n" + line8
                    + "\n" + line9 + "\n////////////////////////////\n" + "\nThank You for using our service :) ");

            writeFile.close();

            newfile.delete();
            newfile2.delete();

            JOptionPane.showMessageDialog(null, "Receipt printed here--" + receipt.getAbsolutePath());

        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "An error occurred and failed to create the file");
            return;
        }
    }
}
