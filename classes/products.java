package classes;

import DBconnect.DBconnect;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class products extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label1;
    private JButton previous0;
    private ImageIcon framelogo;
    private JButton add;
    private JTable table;
    private DefaultTableModel model;
    private String[] column = { "Food ID","Name", "Category", "Price" };
    private JScrollPane scroll;
    private User x;
    private DBconnect DB;

    public products(User a) {
        super("Products");
        this.setSize(850, 550);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x = a;

        DB = new DBconnect();

        panel = new JPanel();
        panel.setLayout(null);
        framelogo = new ImageIcon("Image/logo.png");
        setIconImage(framelogo.getImage());

        label1 = new JLabel();
        label1.setText("Product Details");
        label1.setForeground(Color.decode("#C70909"));
        label1.setFont(new Font("Forte", Font.PLAIN, 40));
        label1.setBounds(260, 20, 600, 60);
        panel.add(label1);

        table = new JTable();
        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(column);

        table.setModel(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        table.setSelectionBackground(Color.gray);
        table.setBackground(Color.decode("#FFFFFF"));
        table.setRowHeight(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(280);
        table.getColumnModel().getColumn(3).setPreferredWidth(197);

        scroll = new JScrollPane(table);
        scroll.setBounds(70, 200, 700, 280);
        scroll.setBackground(Color.WHITE);
        panel.add(scroll);

        getUserData();

        add = new JButton("Update Price");
        add.setFont(new Font("Calibri Light (Headings)", Font.BOLD, 15));
        add.setBounds(210, 100, 130, 35);
        add.setFocusable(false);
        add.setBackground(Color.decode("#E0FFFF"));
        add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add.setBorder(BorderFactory.createEtchedBorder());
        add.addActionListener(this);
        panel.add(add);


        previous0 = new JButton("Go Back");
        previous0.setFont(new Font("Calibri Light (Headings)", Font.BOLD, 15));
        previous0.setBounds(490, 100, 130, 35);
        previous0.setFocusable(false);
        previous0.setBackground(Color.decode("#E0FFFF"));
        previous0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        previous0.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        previous0.addActionListener(this);
        panel.add(previous0);

        this.add(panel);
        this.setVisible(true);
    }

    private void getUserData() {

        String query = "SELECT * FROM RMS_FITEM";

        try {
            DB.openConnection();
            DB.result=DB.st.executeQuery(query);
            while (DB.result.next()) {
                int id = DB.result.getInt("FOOD_ID");
                String name = DB.result.getString("FOOD_NAME");
                String category = DB.result.getString("CATEGORY");
                String price = DB.result.getString("PRICE");
                model.addRow(new Object[]{id,name,category,price});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        DB.closeConnection();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            updatePrice a = new updatePrice(x);
            a.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == previous0) {
            AdminDashboard a = new AdminDashboard();
            a.setVisible(true);
            this.setVisible(false);
        }
    }



}