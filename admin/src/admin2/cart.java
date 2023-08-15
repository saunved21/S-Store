package admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class cart extends JFrame implements ActionListener {

    // GUI components
    private JLabel nameLabel, phoneLabel, itemLabel, priceLabel, quantityLabel;
    private JTextField nameTextField, phoneTextField;
    private JComboBox<String> itemComboBox;
    private JLabel[] priceLabels;
    private JSpinner[] quantitySpinners;
    private JButton addButton, clearButton, checkoutButton;
    private JTextArea receiptTextArea;

    // Database connection
    private Connection conn;
    private Statement stmt;

    public cart() {

        // Initialize GUI components
        nameLabel = new JLabel("Customer Name:");
        nameTextField = new JTextField(20);
        phoneLabel = new JLabel("Phone Number:");
        phoneTextField = new JTextField(20);

        // Retrieve items from menu table and populate combo box
        itemLabel = new JLabel("Item:");
        itemComboBox = new JComboBox<String>();
        priceLabel = new JLabel("Price:");
        quantityLabel = new JLabel("Quantity:");
        

        

        
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tree", "root", "admin1234");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT item_name, price FROM menu");
            while (rs.next()) {
                itemComboBox.addItem(rs.getString("item_name"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Initialize price and quantity labels
        int numItems = itemComboBox.getItemCount();
        priceLabels = new JLabel[numItems];
        quantitySpinners = new JSpinner[numItems];
        JPanel itemPanel = new JPanel(new GridLayout(numItems, 3));
        for (int i = 0; i < numItems; i++) {
            String itemName = (String) itemComboBox.getItemAt(i);
            JLabel itemLabel = new JLabel(itemName);
            itemPanel.add(itemLabel);

            // Retrieve price from menu table and display
            double itemPrice = 0.0;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tree", "root", "admin1234");
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT price FROM menu WHERE item_name='" + itemName + "'");
                rs.next();
                itemPrice = rs.getDouble("price");
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JLabel priceLabel = new JLabel(String.format("%.2f", itemPrice));
            priceLabels[i] = priceLabel;
            itemPanel.add(priceLabel);

            // Display quantity spinner with default value of 1
            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
            quantitySpinners[i] = quantitySpinner;
            itemPanel.add(quantitySpinner);
        }

        // Initialize buttons
        addButton = new JButton("Add to Cart");
        addButton.addActionListener(this);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(this);

        // Initialize receipt text area
        receiptTextArea = new JTextArea(20, 40);
        receiptTextArea.setEditable(false);
        JScrollPane receiptScrollPane = new JScrollPane(receiptTextArea);

        // Add components to content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneTextField);
        inputPanel.add(itemLabel);
        
        // Add item panel to scroll pane
        JScrollPane itemScrollPane = new JScrollPane(itemPanel);

        // Add components to content pane
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(itemScrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(checkoutButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(receiptScrollPane, BorderLayout.EAST);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new store().frame.setVisible(true);
            }
        });
        buttonPanel.add(btnBack);
        
        
        

        // Set frame properties
        setTitle("Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Add item to receipt text area
            String name = nameTextField.getText();
            String phone = phoneTextField.getText();
            String item = (String) itemComboBox.getSelectedItem();
            double price = Double.parseDouble(priceLabels[itemComboBox.getSelectedIndex()].getText());
            int quantity = (int) quantitySpinners[itemComboBox.getSelectedIndex()].getValue();
            double total = price * quantity;
            String lineItem = String.format("%-20s%-10s%-10d%-10.2f%.2f\n", item, name, quantity, price, total);
            receiptTextArea.append(lineItem);
        } else if (e.getSource() == clearButton) {
            // Clear all fields
            nameTextField.setText("");
            phoneTextField.setText("");
            itemComboBox.setSelectedIndex(0);
            for (int i = 0; i < quantitySpinners.length; i++) {
                quantitySpinners[i].setValue(1);
            }
            receiptTextArea.setText("");
        } else if (e.getSource() == checkoutButton) {
            // Calculate total price and display message dialog
            double total = 0.0;
            Scanner scanner = new Scanner(receiptTextArea.getText());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\\s+");
                double price = Double.parseDouble(fields[3]);
                int quantity = Integer.parseInt(fields[2]);
                total += price * quantity;
            }
            scanner.close();
            JOptionPane.showMessageDialog(this, String.format("Total price: %.2f", total));
        }
    }

    public static void main(String[] args) {
        new cart();
    }
}
