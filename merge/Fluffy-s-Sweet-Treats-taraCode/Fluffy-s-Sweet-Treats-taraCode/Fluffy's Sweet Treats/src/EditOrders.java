//Author: Dana Archer
//Last Modified: 26/11/2024

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;

public class EditOrders extends JFrame {
    private JPanel mainPanel, fieldPanel, buttonPanel;
    private JLabel id, customerName, customerTele, address, contactMethod,deadline,flavour,descriptiion, location, price, payment_status;
    private JTextField idField, customerNamField, customerTeleField, addressField, contactMethodField, deadlinField, flavourField, descriptionField, locationField, priceField;
    private JButton save, cancel;
    private JComboBox<String> payment_statusBox;
    private ArrayList<Current> orderList;
    private ArrayList<Customer> customerList;
    private String[] paymentState = {"Deposited", "Pending", "Completed"};
    private ViewCurrent viewCurrent;
    private OrderFile file;
    private CustomerFile cusFile;


    public EditOrders(ViewCurrent viewCurrent, OrderFile file){
        setTitle("Edit Order Entry");

        this.viewCurrent = viewCurrent;
        this.file = file;
        this.cusFile = new CustomerFile();

        // Set up panels
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(8, 2));
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());


        //Insantiate Elements
        id = new JLabel("Enter the ID of the order you wish to edit");
        idField = new JTextField();

        customerTele = new JLabel("Customer Telephone: ");
        customerTeleField = new JTextField();

        deadline = new JLabel("Order Deadline");
        deadlinField = new JTextField();

        flavour = new JLabel("Cake Flavour: ");
        flavourField = new JTextField();

        descriptiion = new JLabel("Cake Desciption: ");
        descriptionField = new JTextField();

        location = new JLabel("Delivery Location: ");
        locationField = new JTextField();

        price = new JLabel("Price: ");
        priceField = new JTextField();

        payment_status = new JLabel("Payment Status: ");
        payment_statusBox = new JComboBox<>(paymentState);

        save = new JButton("Save");
        save.addActionListener(new ButtonListener());

        cancel = new JButton("Cancel");
        cancel.addActionListener(new ButtonListener());



        // Add tp 
        fieldPanel.add(id);
        fieldPanel.add(idField);

        fieldPanel.add(customerTele);
        fieldPanel.add(customerTeleField);

        fieldPanel.add(flavour);
        fieldPanel.add(flavourField);

        fieldPanel.add(descriptiion);
        fieldPanel.add(descriptionField);

        fieldPanel.add(deadline);
        fieldPanel.add(deadlinField);

        fieldPanel.add(location);
        fieldPanel.add(locationField);

        fieldPanel.add(price);
        fieldPanel.add(priceField);

        fieldPanel.add(payment_status);
        fieldPanel.add(payment_statusBox);

        buttonPanel.add(save);
        buttonPanel.add(cancel);



        // set up main panel
        mainPanel.add(fieldPanel);
        mainPanel.add(buttonPanel);

        this.orderList = file.load2();
        this.customerList = cusFile.load();


        add(mainPanel);
        pack();
        setVisible(true);
        setSize(new Dimension(600, 400));


    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == cancel){
                setVisible(false);
                viewCurrent.setVisible(true);



            }

            if(e.getSource() == save){
                System.out.println(idField.getText());
                System.out.println(orderList);
                for(Current c: orderList) {
                    System.out.println(c.getID());

                    if (!(idField.getText().equals(String.valueOf(c.getID())))){
                        JOptionPane.showMessageDialog(null, "Not a valid ID number");
                        //This is looping 
                        //Help 
                    }

                    if (idField.getText().equals(String.valueOf(c.getID()))){


                        Current target = c;
                        System.out.println("HELLO");
                        Customer cusTarget = c.getCustomer();


                        //Get customer info
                       
                        String newTelephone = customerTeleField.getText();
                        

                        //Get Order info
                        String newDeadline = deadlinField.getText();
                        String newFlavour = flavourField.getText();
                        String newDesc = descriptionField.getText();
                        String location = locationField.getText();
                        String newPrice = priceField.getText();
                        String newStatus = String.valueOf(payment_statusBox.getSelectedItem());


                        if((newTelephone != "") ){
                            System.out.println("YEEEE");
                            cusFile.updateCustomer(cusTarget, newTelephone);
                            file.updateOrder(target,newDeadline, newFlavour, newDesc, location, newPrice, newStatus, "Ongoing");
        
                        }
                        

                        else{     
                            file.updateOrder(target,newDeadline, newFlavour, newDesc, location, newPrice, newStatus, "Ongoing");
                        }
                                             
                      

                    }
                    
                }

                setVisible(false);
                viewCurrent.setVisible(true);

            }
        }

    }

}