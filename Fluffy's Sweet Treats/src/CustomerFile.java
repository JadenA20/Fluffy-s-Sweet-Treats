//Authors: Jaden Anthony, Dana Archer, Tara-Lee Donald
//Last Modified: 25-11-2024

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class CustomerFile{

    public CustomerFile custFile;

    public CustomerFile(){

        this.custFile = this; 
    }


    //This function adds the entries to the CurrentOrders.txt file
    public void addToFile(Customer cust){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt", true));

            writer.write(cust.getFirstName() + ";" + cust.getLastName() + ";" + cust.getTelephone() + ";" + cust.getAddress() + ";" + cust.getContactMethod()); 

            writer.newLine();

            writer.close();
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to customer file.");
        }
    }



}