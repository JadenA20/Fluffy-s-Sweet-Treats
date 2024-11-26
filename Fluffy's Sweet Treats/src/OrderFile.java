//Authors: Jaden Anthony, Dana Archer, Tara-Lee Donald
//Last Modified: 25-11-2024

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class OrderFile{

    public OrderFile ordFile;

    public OrderFile(){

        this.ordFile = this; 
    }


    //This function adds the entries to the CurrentOrders.txt file
    public void addToFile(Current currentOrd){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("CurrentOrders.txt", true));

            writer.write(currentOrd.getID() + ";" + currentOrd.getCustomer().getName() + ";" + currentOrd.getCreationDate() + ";" + currentOrd.getEvent() + ";" + currentOrd.getFlavour() + ";" + currentOrd.getDesc() + ";" + currentOrd.getPrice() + ";" + currentOrd.getDeliveryLocation() + ";" + currentOrd.getPaymentStatus() +  ";" + currentOrd.getDueDate() + ";" + currentOrd.getStatus());

            writer.newLine();

            writer.close();
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to current order file.");
        }
    }



}

