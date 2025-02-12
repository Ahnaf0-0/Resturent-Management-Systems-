package classes;

import DBconnect.DBconnect;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class orders extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label1;
    private JButton previous0;
    private ImageIcon framelogo;
    private JButton updateStatus;
    private JTable table;
    private DefaultTableModel model;
    private String[] column = { "Customer's Name", "Phone Number", "AREA","ORDER_ID", "Order Status" };
    private JScrollPane scroll;
    private User x;
    private DBconnect DB;

    public orders(User a) {
        super("Orders");
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
        label1.setText("Assigned Orders");
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

        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setPreferredWidth(130);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);

        scroll = new JScrollPane(table);
        scroll.setBounds(70, 200, 700, 280);
        scroll.setBackground(Color.WHITE);
        panel.add(scroll);

        getOrderData();

        updateStatus = new JButton("Update Status");
        updateStatus.setFont(new Font("Calibri Light (Headings)", Font.BOLD, 15));
        updateStatus.setBounds(210, 100, 130, 35);
        updateStatus.setFocusable(false);
        updateStatus.setBackground(Color.decode("#E0FFFF"));
        updateStatus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateStatus.setBorder(BorderFactory.createEtchedBorder());
        updateStatus.addActionListener(this);
        panel.add(updateStatus);


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

    private void getOrderData() {


        String query = "SELECT * FROM `dmanview` where ORDER_STATUS <>'DELIVERED'";
        try {
            DB.openConnection();
            System.out.println("Connection stablished");
            DB.result=DB.st.executeQuery(query);
            System.out.println("executed");
            while (DB.result.next()) {

                String name = DB.result.getString("CUSTOMER_NAME");
                int number = DB.result.getInt("P_NUMBER");
                String area = DB.result.getString("AREA");
                int ORDER_ID = DB.result.getInt("ORDER_ID");
                String status = DB.result.getString("ORDER_STATUS");


                model.addRow(new Object[]{name,number,area,ORDER_ID,status});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        DB.closeConnection();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateStatus) {
            updateStatus a = new updateStatus(x);
            a.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == previous0) {
            DmanDashboard a = new DmanDashboard();
            this.setVisible(false);
        }
    }



}