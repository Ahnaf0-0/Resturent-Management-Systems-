
package classes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Staffdashboard extends JFrame implements ActionListener {

    private JTextArea staffDetailsTextArea;
    private JButton addStaffButton;
    private JButton removeStaffButton;
    private String staffDetailsFile = "staff_details.txt";

    public Staffdashboard() {
        setTitle(" Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        staffDetailsTextArea = new JTextArea(10, 30);
        staffDetailsTextArea.setEditable(false); // Make text area read-only
        JScrollPane scrollPane = new JScrollPane(staffDetailsTextArea);

        addStaffButton = new JButton("Add Staff");
        removeStaffButton = new JButton("Remove Staff");

        // Add components to the frame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addStaffButton);
        buttonPanel.add(removeStaffButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set frame size
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Read and display staff details
        displayStaffDetails();

        // Add action listeners for buttons
        addStaffButton.addActionListener(this);
        removeStaffButton.addActionListener(this);
    }

    private void displayStaffDetails() {
        // Read staff details from text file and display in text area
        try (BufferedReader br = new BufferedReader(new FileReader(staffDetailsFile))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            staffDetailsTextArea.setText(sb.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading staff details", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStaff() {
        String name = JOptionPane.showInputDialog(this, "Enter staff name:");
        if (name != null && !name.isEmpty()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(staffDetailsFile, true))) {
                pw.println(name);
                JOptionPane.showMessageDialog(this, "Staff added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error adding staff", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeStaff() {
        String selectedStaff = staffDetailsTextArea.getSelectedText();
        if (selectedStaff != null && !selectedStaff.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this staff?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    File inputFile = new File(staffDetailsFile);
                    File tempFile = new File("staff_details.txt");

                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

                    String currentLine;
                    while ((currentLine = reader.readLine()) != null) {
                        if (!currentLine.equals(selectedStaff)) {
                            writer.println(currentLine);
                        }
                    }
                    writer.close();
                    reader.close();
                    boolean successful = tempFile.renameTo(inputFile);
                    if (successful) {
                        JOptionPane.showMessageDialog(this, "Staff removed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        displayStaffDetails(); // Refresh display
                    } else {
                        JOptionPane.showMessageDialog(this, "Error removing staff", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error removing staff", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a staff to remove", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStaffButton) {
            addStaff();
        } else if (e.getSource() == removeStaffButton) {
            removeStaff();
        }
    }
    public static void main(String[] args) {
         new Staffdashboard();
    }
    }