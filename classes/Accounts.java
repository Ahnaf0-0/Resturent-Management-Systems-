package classes;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class Accounts extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel foodCostLabel, salaryLabel, rentLabel, otherExpenseLabel, incomeLabel;
    private JTextField foodCostTF, salaryTF, rentTF, otherExpenseTF, incomeTF;
    private JButton enterBtn, gobackbtn;


    public Accounts() {

        super("Accounts");
        this.setSize(400, 400);



        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);

        incomeLabel = new JLabel("Income:");
        incomeLabel.setBounds(52, 50, 80, 25);
        panel.add(incomeLabel);

        foodCostLabel = new JLabel("Product Cost:");
        foodCostLabel.setBounds(35, 100, 80, 25);
        panel.add(foodCostLabel);

        salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(52, 150, 80, 25);
        panel.add(salaryLabel);

        rentLabel = new JLabel("Rent:");
        rentLabel.setBounds(55, 200, 80, 25);
        panel.add(rentLabel);

        otherExpenseLabel = new JLabel("Other Expense:");
        otherExpenseLabel.setBounds(25, 250, 100, 25);
        panel.add(otherExpenseLabel);

        incomeTF = new JTextField();
        incomeTF.setBounds(130, 50, 200, 25);
        panel.add(incomeTF);

        foodCostTF = new JTextField();
        foodCostTF.setBounds(130, 100, 200, 25);
        panel.add(foodCostTF);

        salaryTF = new JTextField();
        salaryTF.setBounds(130, 150, 200, 25);
        panel.add(salaryTF);

        rentTF = new JTextField();
        rentTF.setBounds(130, 200, 200, 25);
        panel.add(rentTF);

        otherExpenseTF = new JTextField();
        otherExpenseTF.setBounds(130, 250, 200, 25);
        panel.add(otherExpenseTF);

        enterBtn = new JButton("Enter");
        enterBtn.setBounds(100, 300, 80, 25);
        enterBtn.setBorder(new LineBorder(Color.decode("#000000"), 2));
        enterBtn.setBackground(Color.decode("#E4002B"));
        enterBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        enterBtn.setForeground(Color.decode("#FFFFFF"));
        enterBtn.addActionListener(this);
        panel.add(enterBtn);

        gobackbtn = new JButton("Go Back");
        gobackbtn.setBounds(180, 300, 110, 25);
        gobackbtn.setBorder(new LineBorder(Color.decode("#000000"), 2));
        gobackbtn.setBackground(Color.decode("#E4002B"));
        gobackbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gobackbtn.setForeground(Color.decode("#FFFFFF"));
        gobackbtn.addActionListener(this);
        panel.add(gobackbtn);
        panel.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gobackbtn) {
            Dmanlogin c=new Dmanlogin();
            c.setVisible(true);
            this.setVisible(false);

        } else if (e.getSource() == enterBtn) {
            try {
                double incomeAmount = Double.parseDouble(incomeTF.getText());
                double foodCostAmount = Double.parseDouble(foodCostTF.getText());
                double salaryAmount = Double.parseDouble(salaryTF.getText());
                double rentAmount = Double.parseDouble(rentTF.getText());
                double otherExpenseAmount = Double.parseDouble(otherExpenseTF.getText());

                double totalExpense = foodCostAmount + salaryAmount + rentAmount + otherExpenseAmount;
                double netProfitAmount = incomeAmount - totalExpense;

                JOptionPane.showMessageDialog(null, "Net Profit = " + netProfitAmount, "Net Profit",
                        JOptionPane.INFORMATION_MESSAGE);

                File file = new File("data/.Accounts.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.println("Income : " + incomeAmount);
                pw.println("Product Cost : " + foodCostAmount);
                pw.println("Salary : " + salaryAmount);
                pw.println("Rent : " + rentAmount);
                pw.println("Other Expense : " + otherExpenseAmount);
                pw.println("Total Expense : " + totalExpense);
                pw.println("Net Profit : " + netProfitAmount);
                pw.println("===============================================");
                pw.close();

                JOptionPane.showMessageDialog(null, "Data saved successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                incomeTF.setText("");
                foodCostTF.setText("");
                salaryTF.setText("");
                rentTF.setText("");
                otherExpenseTF.setText("");
            } catch (NumberFormatException | IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid input or something went wrong. Please check your input and try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
