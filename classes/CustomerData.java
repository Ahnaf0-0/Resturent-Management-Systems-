package classes;

import DBconnect.DBconnect;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerData extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label1;
    private JButton previous0;
    private ImageIcon framelogo;
    //private JButton add;
    //private JButton delete;
    private JTable table;
    private DefaultTableModel model;
    private String[] column = { "Customer's ID","Customer's Name", "Email", "Phone Number", "AREA", "Road-No" };
    private JScrollPane scroll;
    private User x;
    private DBconnect DB;

    public CustomerData(User a) {
        super("Customer Data");
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
        label1.setText("Customer's Details");
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
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(140);
        table.getColumnModel().getColumn(2).setPreferredWidth(240);
        table.getColumnModel().getColumn(3).setPreferredWidth(140);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);

        scroll = new JScrollPane(table);
        scroll.setBounds(70, 100, 700, 350);
        scroll.setBackground(Color.WHITE);
        panel.add(scroll);

        getUserData();

//        add = new JButton("Add User");
//        add.setFont(new Font("Calibri Light (Headings)", Font.BOLD, 15));
//        add.setBounds(210, 100, 130, 35);
//        add.setFocusable(false);
//        add.setBackground(Color.decode("#E0FFFF"));
//        add.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        add.setBorder(BorderFactory.createEtchedBorder());
//        add.addActionListener(this);
//        panel.add(add);
//
//        delete = new JButton("Delete User");
//        delete.setFont(new Font("Calibri Light (Headings)", Font.BOLD, 15));
//        delete.setBounds(150, 450, 130, 35);
//        delete.setFocusable(false);
//        delete.setBackground(Color.decode("#E0FFFF"));
//        delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        delete.setBorder(BorderFactory.createEtchedBorder());
//        delete.addActionListener(this);
//        panel.add(delete);

        previous0 = new JButton("Go Back");
        previous0.setFont(new Font("Calibri Light (Headings)", Font.BOLD, 15));
        previous0.setBounds(400, 450, 130, 35);
        previous0.setFocusable(false);
        previous0.setBackground(Color.decode("#E4002B"));
        previous0.setForeground(Color.decode("#FFFFFF"));
        previous0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        previous0.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        previous0.addActionListener(this);
        panel.add(previous0);

        this.add(panel);
        this.setVisible(true);
    }

    private void getUserData() {

        String query = "SELECT * FROM CUSTOMERDETAILS;";

        try {
            DB.openConnection();
            DB.result=DB.st.executeQuery(query);
            while (DB.result.next()) {
                int id = DB.result.getInt("CUSTOMER_ID");
                String name = DB.result.getString("CUSTOMER_NAME");
                String email = DB.result.getString("EMAIL");
                String number = DB.result.getString("DM_NUMBER");
                String AREA = DB.result.getString("AREA");
                String road = DB.result.getString("ROAD_NO");
                model.addRow(new Object[]{id,name, email,number, AREA,road});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
            DB.closeConnection();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previous0) {
            AdminDashboard a = new AdminDashboard();
            a.setVisible(true);
            this.setVisible(false);
        }
    }
}
