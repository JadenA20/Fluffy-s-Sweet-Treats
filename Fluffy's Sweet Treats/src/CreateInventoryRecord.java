//Author: Jada-kay Williams
//Last Modified: November 29th, 2024

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CreateInventoryRecord extends JFrame{

    // Class for the CreateItem GUI

    private JButton cancel;
    private JButton save;
    private JPanel itemPanel;
    private JPanel entryPanel;
    private JTextField enterItemName;
    private JTextArea enterItemDesc;
    private JTextField enterStockCount;
    private JTextField enterAreaofStorage;
    private JTextField entershelflife;
    private JLabel priorityselect;
    private JLabel confirmPerishable;
    private JComboBox<String> priorityDrop;
    private JComboBox<String> confirmDrop;
    private InventoryFile infile = new InventoryFile();
    private String[] prioritystate= {"1", "2", "3"};
    private String prioritystatus = " ";
    private String itemStatus = " ";
    private String[] itemStatusOptions = {"Yes", "No"};
    private ViewInventory thisViewInventory;
    private User userAccount;
    private Admin adminAccount;
    private int id;
  

    //Constructor formats the frame for the addition of an entry to the InventoryItems.txt file
    public CreateInventoryRecord(ViewInventory viewInt, User userAcc, Admin adminAcc){

        this.thisViewInventory = viewInt;
        this.userAccount = userAcc;
        this.adminAccount = adminAcc;

        setTitle("New Inventory Entry");

        itemPanel = new JPanel();
        entryPanel = new JPanel();



        //Input fields for user

        entryPanel.add(new JLabel("Enter item name: ")); 
        enterItemName = new JTextField(40);
        entryPanel.add(enterItemName);


        entryPanel.add(new JLabel("Enter item description:"));
        enterItemDesc = new JTextArea(3,40);
        enterItemDesc.setLineWrap(true);
        enterItemDesc.setWrapStyleWord(true);
        entryPanel.add(enterItemDesc);

        entryPanel.add(new JLabel("Enter stock count:"));
        enterStockCount = new JTextField(10);
        entryPanel.add(enterStockCount);

        entryPanel.add(new JLabel("Enter item storage area:"));
        enterAreaofStorage = new JTextField(30);
        entryPanel.add(enterAreaofStorage);

        entryPanel.add(new JLabel("Enter self life or expriration:"));
        entershelflife = new JTextField(30);
        entryPanel.add(entershelflife);


        //Dropdown selection for priority status
        priorityselect = new JLabel("Select priority status:");
        priorityselect.setBounds(50,50,130,15);
        priorityDrop = new JComboBox<>(prioritystate);
        priorityDrop.setBounds(150,50,130,15);
        entryPanel.add(priorityselect);
        entryPanel.add(priorityDrop);


        //Dropdown selection for perishable status
        confirmPerishable = new JLabel("Is this item perishable?");
        confirmPerishable.setBounds(90,50,130,15);
        confirmDrop = new JComboBox<>(itemStatusOptions);
        confirmDrop.setBounds(150,50,130,15);
        entryPanel.add(confirmPerishable);
        entryPanel.add(confirmDrop);

        priorityDrop.addActionListener(new PriorityDropDownListener());
        confirmDrop.addActionListener(new ConfirmDropDownListener());


        entryPanel.setLayout(new GridLayout(12,2));

        //Save and Cancel buttons
       
        save= new JButton("Save");
        cancel= new JButton("Cancel");

        itemPanel.add(save);
        itemPanel.add(cancel);
        
        //Add functionality for the buttons
        cancel.addActionListener(new CloseButtonListener());
        save.addActionListener(new SaveButtonListener());
        

        add(entryPanel, BorderLayout.CENTER);
        add(itemPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);


    }


    //Listener for the priority status dropdown

    private class PriorityDropDownListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                prioritystatus = (String) priorityDrop.getSelectedItem();
            }

            catch(NullPointerException npe){
                JOptionPane.showMessageDialog(CreateInventoryRecord.this, "Invalid entry detected. Please select a priority status.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ConfirmDropDownListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                itemStatus = (String) confirmDrop.getSelectedItem();
            }

            catch(NullPointerException npe){
                JOptionPane.showMessageDialog(CreateInventoryRecord.this, "Invalid entry detected. Please select yes or no.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //Listener for the save button

    private class SaveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{

                if (itemStatus.equals("No")){

                
                String name = enterItemName.getText().trim();
                String description = enterItemDesc.getText().trim();
                String stockCount = enterStockCount.getText().trim();
                
                Inventory item = new Inventory(0, Integer.parseInt(stockCount), Integer.parseInt(prioritystatus), name, description);
                
                infile.addToInventoryFile(item, null);
                JOptionPane.showMessageDialog(CreateInventoryRecord.this,"Item Sucessfully Added", "Successful Entry",JOptionPane.INFORMATION_MESSAGE);
                
                setVisible(true);
                
            }
            else if (itemStatus.equals("Yes")){

                String name = enterItemName.getText().trim();
                String description = enterItemDesc.getText().trim();
                String stockCount = enterStockCount.getText().trim();
                String storage = enterAreaofStorage.getText().trim();
                String shelfLife= entershelflife.getText().trim();


                PerishableRecord periRecord = new PerishableRecord(0, Integer.parseInt(stockCount), Integer.parseInt(prioritystatus), name, description, storage, shelfLife);
                
                infile.addToInventoryFile(null, periRecord);
                JOptionPane.showMessageDialog(CreateInventoryRecord.this,"Item Sucessfully Added", "Successful Entry",JOptionPane.INFORMATION_MESSAGE);
                
                setVisible(true);

            } 

            

                

            }
                      
            catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(CreateInventoryRecord.this, "Invalid Entry Detected. Please check to ensure all fields are filled correctly.", "Error", JOptionPane.ERROR_MESSAGE);
 
            }

            catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(CreateInventoryRecord.this, "Invalid Entry Detected. Please ensure numbers are entered in numerical fields.", "Error", JOptionPane.ERROR_MESSAGE);
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


