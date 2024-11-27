//Author: Tara-Lee Donald
//Last Modified: 26-11-2024

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.*;

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
    private Current order;
    private Customer customer;
    private OrderFile ofile = new OrderFile();
    private CustomerFile cfile = new CustomerFile();
    private String[] paymentState= {"Deposited", "Pending", "Completed"};
    private String creationDate;
    private String paymentStatus = "Deposited";
    private int id = 0;

    //Constructor formats the frame for the addition of an entry to the Orders.txt file
    public CreateOrders(){
        setTitle("New Order Entry");

        orderPanel = new JPanel();
        entryPanel = new JPanel();

        LocalDate current = LocalDate.now();

        creationDate = current.toString();


        //Input fields for user

        entryPanel.add(new JLabel("Customer's First Name: ")); 
        enterFName = new JTextField(40);
        entryPanel.add(enterFName);

        entryPanel.add(new JLabel("Customer's Last Name: ")); 
        enterLName = new JTextField(40);
        entryPanel.add(enterLName);

        entryPanel.add(new JLabel("Address:"));
        enterAddr = new JTextField(40);
        entryPanel.add(enterAddr);

        entryPanel.add(new JLabel("Telephone Number:"));
        enterNumber = new JTextField(15);
        entryPanel.add(enterNumber);

        entryPanel.add(new JLabel("Contact Method (Eg: WhatsApp, Email, Instagram):"));
        enterContactMethod = new JTextField(20);
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
                String event = enterType.getText().trim();
                String desc = enterDesc.getText().trim();
                float price = Float.parseFloat(enterPrice.getText().trim());
                String location = enterDest.getText().trim();
                String addr = enterAddr.getText().trim();
                String flavour = enterFlavour.getText().trim();
                String method = enterContactMethod.getText().trim();
                            
                customer = new Customer(id, fName, lName, addr, tele_num, method);

                order = new Current(id, customer, creationDate, event, flavour, desc, price, paymentStatus, location, date);
                
                ofile.addToCurrentFile(order);
                cfile.addToFile(customer);
                

                JOptionPane.showMessageDialog(CreateOrders.this,"Order Sucessfully Saved", "Successful Entry",JOptionPane.INFORMATION_MESSAGE);
                
                setVisible(false);

            }
                      
            catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(CreateOrders.this, "Invalid Entry Detected. Please check to ensure all fields are filled correctly.", "Error", JOptionPane.ERROR_MESSAGE);
 
            }

            catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(CreateOrders.this, "Invalid Entry Detected. Please ensure numbers are entered in numerical fields.", "Error", JOptionPane.ERROR_MESSAGE);
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

