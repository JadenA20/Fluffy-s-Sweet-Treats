//Author: Tara-Lee Donald
//Last Modified: 06-11-2024

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.time.LocalDate;

public class CreateOrders extends JFrame{

    // Class for the CreateOrders GUI

    private JButton cancel;
    private JButton save;
    private JPanel orderPanel;
    private JPanel entryPanel;
    private JTextField enterFName;
    private JTextField enterLName;
    private JTextField enterNumber;
    private JTextField enterContactMethod;
    private JTextField enterDest;
    private JTextField enterAddr;
    private JTextField enterDate;
    private JTextField enterType;
    private JTextField enterPrice;
    private JTextField enterFlavour;
    private JTextArea enterDesc;
    private JLabel paymentSelect;
    private JComboBox<String> paymentDrop;
    private Order order;
    private Customer customer;
    private String[] paymentState= {"Deposited", "Pending", "Completed"};
    private String currentDate, paymentStatus;
    private static int id;

    //Constructor formats the frame for the addition of an entry to the Orders.txt file
    public CreateOrders(){
        setTitle("New Order Entry");

        orderPanel = new JPanel();
        entryPanel = new JPanel();

        ++id;

        LocalDate current = LocalDate.now();

        currentDate = current.toString();

        //Input fields for user

        entryPanel.add(new JLabel("Customer's First Name: ")); 
        enterFName = new JTextField(40);
        entryPanel.add(enterFName);

        entryPanel.add(new JLabel("Customer's Last Name: ")); 
        enterLName = new JTextField(40);
        entryPanel.add(enterLName);

        entryPanel.add(new JLabel("Customer's Address: ")); 
        enterAddr = new JTextField(40);
        entryPanel.add(enterAddr);

        entryPanel.add(new JLabel("Telephone Number:"));
        enterNumber = new JTextField(10);
        entryPanel.add(enterNumber);

        entryPanel.add(new JLabel("Customer Contact Method (Eg. Whatsapp, Facebook etc.):"));
        enterContactMethod = new JTextField(10);
        entryPanel.add(enterContactMethod);

        entryPanel.add(new JLabel("Deadline (format: yyyy-mm-dd):"));
        enterDate = new JTextField(15);
        entryPanel.add(enterDate);

        entryPanel.add(new JLabel("Order Type (Anniversary, Birthday etc.):"));
        enterType = new JTextField(20);
        entryPanel.add(enterType);

        entryPanel.add(new JLabel("Flavour:"));
        enterFlavour = new JTextField(20);
        entryPanel.add(enterFlavour);

        entryPanel.add(new JLabel("Description:"));
        enterDesc = new JTextArea(3,40);
        enterDesc.setLineWrap(true);
        enterDesc.setWrapStyleWord(true);
        entryPanel.add(enterDesc);

        entryPanel.add(new JLabel("Delivery Destination:"));
        enterDest = new JTextField(30);
        entryPanel.add(enterDest);

        entryPanel.add(new JLabel("Price:"));
        enterPrice = new JTextField(10);
        entryPanel.add(enterPrice);

        //Dropdown selection for payment status

        paymentSelect = new JLabel("Select payment status:");
        paymentSelect.setBounds(50,50,130,15);
        paymentDrop = new JComboBox<>(paymentState);
        paymentDrop.setBounds(150,50,130,15);
        entryPanel.add(paymentSelect);
        entryPanel.add(paymentDrop);

        //Adds functionality for payment dropdown
        paymentDrop.addActionListener(new PaymentDropDownListener());

        entryPanel.setLayout(new GridLayout(12,2));

        //Save aand Cancel buttons
       
        save= new JButton("Save");
        cancel= new JButton("Cancel");

        orderPanel.add(save);
        orderPanel.add(cancel);
        
        //Add functionality for the buttons
        cancel.addActionListener(new CloseButtonListener());
        save.addActionListener(new SaveButtonListener());
        

        add(entryPanel, BorderLayout.CENTER);
        add(orderPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);


    }

    //This function adds the entries to the Orders.txt file

    public void addToOrderFile(Order order){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("Current Orders.txt", true));

            writer.write(order.getID() + ";" + order.getCustomer().getFirstName() + " "+ order.getCustomer().getLastName() + ";" + ";" + order.getCurrentDate() + ";" + order.getDueDate() + ";" + order.getType() + ";" + order.getFlavour() + ";" + order.getDesc() + ";" + order.getPrice() + ";" + order.getPaymentStatus() + ";" + order.getLocation() + ";" + order.getStatus());

            writer.newLine();

            writer.close();
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(CreateOrders.this,"An error was detected in accessing the file.", "File Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    //This function adds entries to the Customers.txt file
    public void addToCustomerFile(Customer customer){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt", true));

            writer.write(order.getID() + ";" + customer.getFirstName() + " "+ customer.getLastName() +";" + customer.getAddress() + ";" + customer.getTelephone() + ";" + customer.getContactMethod());

            writer.newLine();

            writer.close();
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(CreateOrders.this,"An error was detected in accessing the file.", "File Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    //Listener for the payment status dropdown

    private class PaymentDropDownListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                paymentStatus = (String) paymentDrop.getSelectedItem();
            }

            catch(NullPointerException npe){
                JOptionPane.showMessageDialog(CreateOrders.this, "Invalid entry detected. Please select a payment status.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //Listener for the save button

    private class SaveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{

                String fName = enterFName.getText().trim();
                String lName = enterLName.getText().trim();
                String tele_num = enterNumber.getText().trim();
                String date = enterDate.getText().trim();
                String type = enterType.getText().trim();
                String desc = enterDesc.getText().trim();
                float price = Float.parseFloat(enterPrice.getText().trim());
                String location = enterDest.getText().trim();
                String addr = enterAddr.getText().trim();
                String flavour = enterFlavour.getText().trim();
                String method = enterContactMethod.getText().trim();
                            
                customer = new Customer(fName, lName, addr, tele_num, method);

                order = new Order(id, customer, currentDate, date, type, flavour, desc, price, paymentStatus, location, "Ongoing");
                
                addToCustomerFile(customer);
                addToOrderFile(order);

                JOptionPane.showMessageDialog(CreateOrders.this,"Order Sucessfully Saved", "Successful Entry",JOptionPane.INFORMATION_MESSAGE);
                
                setVisible(false);

            }
                      
            catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(CreateOrders.this, "Invalid Entry Detected. Please check to ensure all fields are filled correctly.", "Error", JOptionPane.ERROR_MESSAGE);
 
            }

            catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(CreateOrders.this, "Invalid Entry Detected. Please ensure numbers are entered in numericaal fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }

    
        }
    }

    //Listener for the cancel button

    private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
        }
    }

}
